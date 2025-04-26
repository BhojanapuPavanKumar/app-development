#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define MAX_BUSES 10
#define MAX_SEATS 40

// Structure to hold seat information
struct Seat
{
    int number;
    int is_booked;
};

// Structure to hold bus information
struct Bus
{
    int bus_id;
    char origin[20];
    char destination[20];
    int fare;
    int num_seats;
    struct Seat seats[MAX_SEATS];
};

// Global array to hold buses
struct Bus buses[MAX_BUSES];  // Fixed: Use 'struct Bus' here
int num_buses = 0;

// Function to display available buses for a route
void displayBuses(char *origin, char *destination)
{
    printf("Available buses for %s to %s route:\n", origin,destination);
    int count = 0;
    for (int i = 0; i < num_buses; i++)
    {
        if (strcmp(buses[i].origin, origin) == 0 && strcmp(buses[i].destination, destination) == 0)
        {
            printf("%d. Bus ID: %d, Fare: %d\n", count + 1,
            buses[i].bus_id, buses[i].fare);
            count++;
        }
    }
    if (count == 0)
    {
        printf("No buses found for %s to %s route.\n", origin,destination);
    }
}

// Function to add a new bus
void addBus()
{
    int i;
    if (num_buses == MAX_BUSES)
    {
        printf("Maximum number of buses reached.\n");
        return;
    }
    struct Bus new_bus;  // Fixed: Use 'struct Bus' here
    printf("Enter bus ID: ");
    scanf("%d", &new_bus.bus_id);
    printf("Enter origin: ");
    scanf("%s", new_bus.origin);
    printf("Enter destination: ");
    scanf("%s", new_bus.destination);
    printf("Enter fare: ");
    scanf("%d", &new_bus.fare);
    printf("Enter number of seats: ");
    scanf("%d", &new_bus.num_seats);
    for (i = 0; i < new_bus.num_seats; i++)
    {
        new_bus.seats[i].number = i + 1;
        new_bus.seats[i].is_booked = 0;
    }
    buses[num_buses] = new_bus;
    num_buses++;
    printf("Bus added successfully.\n");
}

// Function to book a seat
void bookSeat(int bus_id, int seat_number)
{
    for (int i = 0; i < num_buses; i++)
    {
        if (buses[i].bus_id == bus_id)
        {
            if (seat_number < 1 || seat_number > buses[i].num_seats)
            {
                printf("Invalid seat number.\n");
                return;
            }
            if (buses[i].seats[seat_number - 1].is_booked)
            {
                printf("Seat already booked.\n");
                return;
            }
            buses[i].seats[seat_number - 1].is_booked = 1;
            printf("Seat %d booked successfully.\n", seat_number);
            return;
        }
    }
    printf("Bus not found.\n");
}

// Function to cancel a seat
void cancelSeat(int bus_id, int seat_number)
{
    for (int i = 0; i < num_buses; i++)
    {
        if (buses[i].bus_id == bus_id)
        {
            if (seat_number < 1 || seat_number > buses[i].num_seats)
            {
                printf("Invalid seat number.\n");
                return;
            }
            if (!buses[i].seats[seat_number - 1].is_booked)
            {
                printf("Seat not booked.\n");
                return;
            }
            buses[i].seats[seat_number - 1].is_booked = 0;
            printf("Seat %d cancelled successfully.\n", seat_number);
            return;
        }
    }
    printf("Bus not found.\n");
}

// Function to display available seats for a bus
void displaySeats(int bus_id)
{
    for (int i = 0; i < num_buses; i++)
    {
        if (buses[i].bus_id == bus_id)
        {
            printf("Available seats for Bus ID %d:\n", bus_id);
            for (int j = 0; j < buses[i].num_seats; j++)
            {
                if (!buses[i].seats[j].is_booked)
                {
                    printf("Seat %d\n", buses[i].seats[j].number);
                }
            }
            return;
        }
    }
    printf("Bus not found.\n");
}

// Function to display all booked seats for a bus
void displayBookedSeats(int bus_id)
{
    for (int i = 0; i < num_buses; i++)
    {
        if (buses[i].bus_id == bus_id)
        {
            printf("Booked seats for Bus ID %d:\n", bus_id);
            for (int j = 0; j < buses[i].num_seats; j++)
            {
                if (buses[i].seats[j].is_booked)
                {
                    printf("Seat %d\n", buses[i].seats[j].number);
                }
            }
            return;
        }
    }
    printf("Bus not found.\n");
}

char origin[20], destination[20];
int bus_id, seat_number;

// Main function
int main()
{
    int choice;
    do
    {
        printf("Enter your choice:\n");
        printf("1. Display available buses\n");
        printf("2. Add a new bus\n");
        printf("3. Book a seat\n");
        printf("4. Cancel a seat\n");
        printf("5. Display available seats for a bus\n");
        printf("6. Display all booked seats for a bus\n");
        printf("7. Exit\n");
        scanf("%d", &choice);

        switch (choice)
        {
            case 1:
                printf("Enter origin: ");
                scanf("%s", origin);
                printf("Enter destination: ");
                scanf("%s", destination);
                displayBuses(origin, destination);
                break;

            case 2:
                addBus();
                break;

            case 3:
                printf("Enter bus ID: ");
                scanf("%d", &bus_id);
                printf("Enter seat number: ");
                scanf("%d", &seat_number);
                bookSeat(bus_id, seat_number);
                break;

            case 4:
                printf("Enter bus ID: ");
                scanf("%d", &bus_id);
                printf("Enter seat number: ");
                scanf("%d", &seat_number);
                cancelSeat(bus_id, seat_number);
                break;

            case 5:
                printf("Enter bus ID: ");
                scanf("%d", &bus_id);
                displaySeats(bus_id);
                break;

            case 6:
                printf("Enter bus ID: ");
                scanf("%d", &bus_id);
                displayBookedSeats(bus_id);
                break;

            case 7:
                exit(0);
                break;

            default:
                printf("Invalid choice.\n");
                break;
        }

    } while (choice != 7);
    return 0;
}
