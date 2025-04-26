package Banking System;

import java.awt.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

import dao.ConnectionProvider;

import java.awt.event.*;
public class BankingSystem extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private CardLayout cardLayout;
	private JTextField textField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JPasswordField passwordField_2;
	private JPasswordField passwordField_3;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_15;
	private JPasswordField passwordField_4;
	private JPasswordField passwordField_5;
	private JTextField textField_16;
	private PreparedStatement pst;
	private Connection con;
	private int accountNo = 0;
	private ResultSet rs;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BankingSystem frame = new BankingSystem();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void about() {
		String aboutText = ".........";
        JTextArea textArea = new JTextArea(aboutText, 15, 50);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        JOptionPane.showMessageDialog(BankingSystem.this, scrollPane, "About", JOptionPane.INFORMATION_MESSAGE);
	}
	public BankingSystem() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 820, 673);
		setTitle("Banking system");
        setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		JPanel panel = new JPanel();
		contentPane.add(panel);
		cardLayout = new CardLayout();
		panel.setLayout(cardLayout);
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.CYAN);
		panel.add(panel_1, "card1");
		panel_1.setLayout(null);
		JLabel lblNewLabel = new JLabel("Bank Name");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 60));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 776, 95);
		panel_1.add(lblNewLabel);
		JButton btnNewButton = new JButton("Employee Account");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(panel, "card2");
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 35));
		btnNewButton.setBounds(22, 277, 342, 99);
		panel_1.add(btnNewButton);
		JButton btnNewButton_1 = new JButton("Customer Account");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(panel, "card3");
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 35));
		btnNewButton_1.setBounds(406, 277, 367, 99);
		panel_1.add(btnNewButton_1);
		JSeparator separator_5 = new JSeparator();
		separator_5.setBackground(new Color(0, 0, 0));
		separator_5.setBounds(10, 104, 776, 11);
		panel_1.add(separator_5);
		JButton btnNewButton_12 = new JButton("Exit");
		btnNewButton_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int option = JOptionPane.showConfirmDialog(BankingSystem.this,
                        "Are you sure you want to exit?", "Exit Confirmation", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    dispose();  // Close the frame
                }
			}
		});
		btnNewButton_12.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_12.setBounds(684, 31, 89, 23);
		panel_1.add(btnNewButton_12);
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.CYAN);
		panel.add(panel_2, "card2");
		panel_2.setLayout(null);
		JLabel lblNewLabel_1 = new JLabel("Employee Login Page");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 60));
		lblNewLabel_1.setBounds(10, 11, 776, 94);
		panel_2.add(lblNewLabel_1);
		JLabel lblNewLabel_2 = new JLabel("Employee No");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_2.setBounds(154, 289, 225, 33);
		panel_2.add(lblNewLabel_2);
		JLabel lblNewLabel_3 = new JLabel("Password");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_3.setBounds(165, 378, 187, 33);
		panel_2.add(lblNewLabel_3);
		textField = new JTextField();
		textField.setBounds(443, 281, 237, 41);
		textField.setColumns(10);
		passwordField = new JPasswordField();
		passwordField.setBounds(443, 378, 237, 33);
		passwordField.setEchoChar('*');
		panel_2.add(passwordField);
		JCheckBox chckbxNewCheckBox = new JCheckBox("Show Password");
		chckbxNewCheckBox.setFont(new Font("Tahoma", Font.BOLD, 11));
		chckbxNewCheckBox.setBounds(329, 448, 151, 23);
		chckbxNewCheckBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    passwordField.setEchoChar((char) 0);
                } else {
                    passwordField.setEchoChar('*');;
                }
            }
		});
		JButton btnNewButton_2 = new JButton("Signin");
		btnNewButton_2.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String e_no = textField.getText();
		        char[] ec_pass = passwordField.getPassword();
		        String e_pass = new String(ec_pass);
		        try {
		            con = ConnectionProvider.getConnection();
		            String query = "SELECT E_NO, E_PASS FROM EBACCOUNT WHERE E_NO = ? AND E_PASS = ?";
		            pst = con.prepareStatement(query);
		            pst.setString(1, e_no);
		            pst.setString(2, e_pass);
		            rs = pst.executeQuery();
		            textField.setText("");
		            passwordField.setText("");
		            if (rs.next()) {
		                JOptionPane.showMessageDialog(BankingSystem.this, "Login successful!");
		                accountNo = Integer.parseInt(e_no);
		                cardLayout.show(panel, "card4");
		            } else {
		                JOptionPane.showMessageDialog(BankingSystem.this, "Invalid account number or password.");
		            }
		        } catch (Exception e1) {
		            e1.printStackTrace();
		        }
		    }
		});

		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnNewButton_2.setBounds(287, 511, 145, 41);
		panel_2.add(textField);
		panel_2.add(btnNewButton_2);
		panel_2.add(chckbxNewCheckBox);
		JButton btnNewButton_3 = new JButton("->back");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
                passwordField.setText("");
				cardLayout.show(panel, "card1");
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 9));
		btnNewButton_3.setBounds(648, 571, 72, 23);
		panel_2.add(btnNewButton_3);
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(0, 255, 255));
		panel.add(panel_3, "card3");
		panel_3.setLayout(null);
		JLabel lblNewLabel_4 = new JLabel("Customer Login Page");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 70));
		lblNewLabel_4.setBounds(10, 11, 776, 87);
		panel_3.add(lblNewLabel_4);
		JLabel lblNewLabel_5 = new JLabel("Account No");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_5.setBounds(172, 270, 188, 49);
		panel_3.add(lblNewLabel_5);
		JLabel lblNewLabel_6 = new JLabel("Password");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_6.setBounds(172, 363, 172, 37);
		panel_3.add(lblNewLabel_6);
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(407, 367, 198, 33);
		passwordField_1.setEchoChar('*');
		panel_3.add(passwordField_1);
		textField_1 = new JTextField();
		textField_1.setBounds(407, 286, 205, 33);
		panel_3.add(textField_1);
		textField_1.setColumns(10);
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("show password");
		chckbxNewCheckBox_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		chckbxNewCheckBox_1.setBounds(384, 438, 129, 23);
		chckbxNewCheckBox_1.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    passwordField_1.setEchoChar((char) 0);
                } else {
                    passwordField_1.setEchoChar('*');
                }
            }
		});
		panel_3.add(chckbxNewCheckBox_1);	
		JButton btnNewButton_4 = new JButton("Login");
		btnNewButton_4.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String e_no = textField_1.getText();
		        char[] ec_pass = passwordField_1.getPassword();
		        String e_pass = new String(ec_pass);
		        try {		      
		            String query = "SELECT C_NO, C_PASS FROM CBACCOUNT WHERE C_NO = ? AND C_PASS = ?";
		            pst = con.prepareStatement(query);
		            pst.setString(1, e_no);
		            pst.setString(2, e_pass);
		            rs = pst.executeQuery();
		            textField_1.setText("");
		            passwordField_1.setText("");
		            if (rs.next()) {
		                JOptionPane.showMessageDialog(BankingSystem.this, "Login successful!");
		                accountNo = Integer.parseInt(e_no);
		                cardLayout.show(panel, "card5");
		            } else {
		                JOptionPane.showMessageDialog(BankingSystem.this, "Invalid account number or password.");
		            }
		        } catch (Exception e1) {
		            e1.printStackTrace();
		        }
		    }
		});

		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton_4.setBounds(312, 506, 122, 33);
		panel_3.add(btnNewButton_4);
		JButton btnNewButton_5 = new JButton("->back");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_1.setText("");
                passwordField_1.setText("");
				cardLayout.show(panel, "card1");
			}
		});
		btnNewButton_5.setFont(new Font("Tahoma", Font.BOLD, 9));
		btnNewButton_5.setBounds(646, 547, 74, 20);
		panel_3.add(btnNewButton_5);
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(0, 255, 255));
		panel.add(panel_4, "card4");
		panel_4.setLayout(null);		
		JLabel lblNewLabel_7 = new JLabel("Picture");
		lblNewLabel_7.setBackground(new Color(0, 255, 0));
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setBounds(42, 278, 132, 132);
		ImageIcon imageIcon = new ImageIcon("C:\\Users\\bhoja\\OneDrive\\Pictures\\profile.jpg");
		Image image = imageIcon.getImage();
        Image newImg = image.getScaledInstance(200, 200,  java.awt.Image.SCALE_SMOOTH); 
        imageIcon = new ImageIcon(newImg);
		lblNewLabel_7.setIcon(imageIcon);
		panel_4.add(lblNewLabel_7);		
		JLabel lblNewLabel_8 = new JLabel("Name");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_8.setForeground(new Color(0, 0, 0));
		lblNewLabel_8.setBounds(234, 252, 507, 14);
		panel_4.add(lblNewLabel_8);		
		JLabel lblNewLabel_9 = new JLabel("Email");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_9.setBounds(234, 295, 507, 14);
		panel_4.add(lblNewLabel_9);		
		JLabel lblNewLabel_10 = new JLabel("Gender");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_10.setBounds(234, 416, 507, 21);
		panel_4.add(lblNewLabel_10);		
		JLabel lblNewLabel_11 = new JLabel("Address");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_11.setBounds(234, 336, 507, 14);
		panel_4.add(lblNewLabel_11);		
		JLabel lblNewLabel_12 = new JLabel("Mobile No");
		lblNewLabel_12.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_12.setBounds(234, 378, 507, 14);
		panel_4.add(lblNewLabel_12);		
		JLabel lblNewLabel_14 = new JLabel("Bank Name");
		lblNewLabel_14.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_14.setFont(new Font("Times New Roman", Font.BOLD, 60));
		lblNewLabel_14.setBounds(10, 21, 759, 85);
		panel_4.add(lblNewLabel_14);		
		JLabel lblNewLabel_15 = new JLabel("Welcome!");
		lblNewLabel_15.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewLabel_15.setBounds(24, 29, 150, 47);
		panel_4.add(lblNewLabel_15);		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(0, 0, 0));
		separator.setBounds(10, 99, 776, 7);
		panel_4.add(separator);		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(10, 106, 762, 37);
		panel_4.add(menuBar);		
		JMenu mnNewMenu = new JMenu("Home");
		mnNewMenu.setFont(new Font("Tahoma", Font.BOLD, 20));
		menuBar.add(mnNewMenu);		
		JMenuItem mntmNewMenuItem = new JMenuItem("Add Account");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(panel, "add");
			}
		});
		mntmNewMenuItem.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mnNewMenu.add(mntmNewMenuItem);		
		JMenuItem mntmNewMenuItem_16 = new JMenuItem("Payment");
		mntmNewMenuItem_16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(panel, "pay");
			}
		});
		mntmNewMenuItem_16.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mnNewMenu.add(mntmNewMenuItem_16);
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Delete Account");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(panel, "card7");
			}
		});
		mntmNewMenuItem_1.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mnNewMenu.add(mntmNewMenuItem_1);
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Add Emplyee Account");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(panel, "card11");
			}
		});
		mntmNewMenuItem_2.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mnNewMenu.add(mntmNewMenuItem_2);		
		JSeparator separator_1 = new JSeparator();
		mnNewMenu.add(separator_1);		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Exit");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int option = JOptionPane.showConfirmDialog(BankingSystem.this,"Are you sure you want to exit?", "Exit Confirmation", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    dispose(); 
                }
			}
		});
		mntmNewMenuItem_3.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mnNewMenu.add(mntmNewMenuItem_3);		
		JMenu mnNewMenu_1 = new JMenu("Edit");
		mnNewMenu_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		menuBar.add(mnNewMenu_1);
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Cut");
		mntmNewMenuItem_4.setHorizontalAlignment(SwingConstants.CENTER);
		mntmNewMenuItem_4.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mnNewMenu_1.add(mntmNewMenuItem_4);
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Copy");
		mntmNewMenuItem_5.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mnNewMenu_1.add(mntmNewMenuItem_5);
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Paste");
		mntmNewMenuItem_6.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mnNewMenu_1.add(mntmNewMenuItem_6);
		JMenu mnNewMenu_2 = new JMenu("Help");
		mnNewMenu_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		menuBar.add(mnNewMenu_2);
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("about");
		mntmNewMenuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  about();
			}
		});
		JMenuItem mntmNewMenuItem_20 = new JMenuItem("Contact");
		mntmNewMenuItem_20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 about();
			}
		});		
		mntmNewMenuItem_20.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mnNewMenu_2.add(mntmNewMenuItem_20);		
		JMenuItem mntmNewMenuItem_19 = new JMenuItem("Support");
		mntmNewMenuItem_19.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 about();
			}
		});		
		mntmNewMenuItem_19.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mnNewMenu_2.add(mntmNewMenuItem_19);
		mntmNewMenuItem_7.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mnNewMenu_2.add(mntmNewMenuItem_7);		
		JButton btnNewButton_11 = new JButton("SignOut");
		btnNewButton_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 JOptionPane.showMessageDialog(BankingSystem.this, "You have been Signout Successfully");
				 lblNewLabel_8.setText("");
	            	lblNewLabel_12.setText("");
	            	lblNewLabel_10.setText("");
	            	lblNewLabel_9.setText("");
	            	lblNewLabel_11.setText("");	
	            	accountNo=0;
				cardLayout.show(panel,"card2");
			}
		});
		btnNewButton_11.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_11.setBounds(675, 38, 108, 37);
		panel_4.add(btnNewButton_11);		
		JButton btnNewButton_10= new JButton("Click here");
		btnNewButton_10.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            String query = "SELECT * FROM EBACCOUNT WHERE E_NO = ?";
		            pst = con.prepareStatement(query);
		            pst.setLong(1, accountNo);
		            rs = pst.executeQuery();
		            if (rs.next()) {
		                lblNewLabel_8.setText("Name:                  " + rs.getString("E_NAME"));
		                lblNewLabel_12.setText("Mobile No:             " + rs.getString("E_MOBILENO"));
		                lblNewLabel_10.setText("Designation:          " + rs.getString("E_DESIGNATION"));
		                lblNewLabel_9.setText("Email:                 " + rs.getString("E_MAIL"));
		                lblNewLabel_11.setText("Address:              " + rs.getString("E_ADDRESS"));
		            }
		        } catch (Exception em) {
		            em.printStackTrace();
		        }
		    }
		});

		btnNewButton_10.setFont(new Font("Tahoma",Font.BOLD,20));
		btnNewButton_10.setBounds(42,449,150,37);
		panel_4.add(btnNewButton_10);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(0, 255, 255));
		panel.add(panel_5, "card5");
		panel_5.setLayout(null);		
		JLabel lblNewLabel_16 = new JLabel("Bank Account");
		lblNewLabel_16.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_16.setFont(new Font("Times New Roman", Font.BOLD, 60));
		lblNewLabel_16.setBounds(10, 11, 776, 76);
		panel_5.add(lblNewLabel_16);		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBackground(new Color(0, 0, 0));
		separator_2.setBounds(10, 91, 776, 7);
		panel_5.add(separator_2);		
		JMenuBar menuBar_1 = new JMenuBar();
		menuBar_1.setBounds(10, 98, 766, 37);
		panel_5.add(menuBar_1);		
		JMenu mnNewMenu_3 = new JMenu("Home");
		mnNewMenu_3.setFont(new Font("Tahoma", Font.BOLD, 20));
		menuBar_1.add(mnNewMenu_3);		
		JMenuItem mntmNewMenuItem_8 = new JMenuItem("Send Money");
		mntmNewMenuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(panel, "send");
			}
		});
		mntmNewMenuItem_8.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mnNewMenu_3.add(mntmNewMenuItem_8);
		JMenuItem mntmNewMenuItem_9 = new JMenuItem("Check Balance");
		mntmNewMenuItem_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(panel, "check");
			}
		});
		mntmNewMenuItem_9.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mnNewMenu_3.add(mntmNewMenuItem_9);
		JMenuItem mntmNewMenuItem_10 = new JMenuItem("Cards");
		mntmNewMenuItem_10.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mnNewMenu_3.add(mntmNewMenuItem_10);
		JMenuItem mntmNewMenuItem_11 = new JMenuItem("Connect Account");
		mntmNewMenuItem_11.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mnNewMenu_3.add(mntmNewMenuItem_11);
		JSeparator separator_6 = new JSeparator();
		mnNewMenu_3.add(separator_6);		
		JMenuItem mntmNewMenuItem_12 = new JMenuItem("Exit");
		mntmNewMenuItem_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int option = JOptionPane.showConfirmDialog(BankingSystem.this,"Are you sure you want to exit?", "Exit Confirmation", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    dispose();  // Close the frame
                }
			}
		});
		mntmNewMenuItem_12.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mnNewMenu_3.add(mntmNewMenuItem_12);
		JMenu mnNewMenu_4 = new JMenu("Edit");
		mnNewMenu_4.setFont(new Font("Tahoma", Font.BOLD, 20));
		menuBar_1.add(mnNewMenu_4);		
		JMenuItem mntmNewMenuItem_13 = new JMenuItem("Copy");
		mntmNewMenuItem_13.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mnNewMenu_4.add(mntmNewMenuItem_13);
		JMenuItem mntmNewMenuItem_14 = new JMenuItem("Paste");
		mntmNewMenuItem_14.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mnNewMenu_4.add(mntmNewMenuItem_14);		
		JMenu mnNewMenu_5 = new JMenu("Help");
		mnNewMenu_5.setFont(new Font("Tahoma", Font.BOLD, 20));
		menuBar_1.add(mnNewMenu_5);		
		JMenuItem mntmNewMenuItem_15 = new JMenuItem("about");
		mntmNewMenuItem_15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 about();
			}
		});		
		JMenuItem mntmNewMenuItem_18 = new JMenuItem("Contact");
		mntmNewMenuItem_18.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 about();
			}
		});		
		mntmNewMenuItem_18.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mnNewMenu_5.add(mntmNewMenuItem_18);		
		JMenuItem mntmNewMenuItem_17 = new JMenuItem("Support");
		mntmNewMenuItem_17.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 about();
			}
		});		
		mntmNewMenuItem_17.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mnNewMenu_5.add(mntmNewMenuItem_17);
		mntmNewMenuItem_15.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mnNewMenu_5.add(mntmNewMenuItem_15);
		
		
		JLabel lblNewLabel_32 = new JLabel("Picture");
		lblNewLabel_32.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_32.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_32.setBounds(593, 225, 161, 148);
		lblNewLabel_32.setIcon(imageIcon);
		panel_5.add(lblNewLabel_32);		
		JLabel lblNewLabel_33 = new JLabel("Account No");
		lblNewLabel_33.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_33.setBounds(111, 263, 363, 37);
		panel_5.add(lblNewLabel_33);		
		JLabel lblNewLabel_34 = new JLabel("Name");
		lblNewLabel_34.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_34.setBounds(111, 325, 408, 37);
		panel_5.add(lblNewLabel_34);		
		JLabel lblNewLabel_35 = new JLabel("Email");
		lblNewLabel_35.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_35.setBounds(111, 397, 438, 37);
		panel_5.add(lblNewLabel_35);		
		JLabel lblNewLabel_36 = new JLabel("Mobile No");
		lblNewLabel_36.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_36.setBounds(111, 453, 408, 27);
		panel_5.add(lblNewLabel_36);		
		JLabel lblNewLabel_46 = new JLabel("Welcome!");
		lblNewLabel_46.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewLabel_46.setBounds(25, 30, 147, 50);
		panel_5.add(lblNewLabel_46);	
		JButton btnNewButton_13 = new JButton("SignOut");
		btnNewButton_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 JOptionPane.showMessageDialog(BankingSystem.this, "You have been Signout Successfully");
				 lblNewLabel_34.setText("");
		        	lblNewLabel_36.setText("");
		        	lblNewLabel_35.setText("");
		        	lblNewLabel_33.setText("");
		        	accountNo=0;
				cardLayout.show(panel, "card3");
			}
		});
		btnNewButton_13.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_13.setBounds(665, 30, 111, 27);
		panel_5.add(btnNewButton_13);	
		JButton btnNewButton_22 = new JButton("Click Here");
		btnNewButton_22.setFont(new Font("Tahoma",Font.BOLD,12));
		 btnNewButton_22.setBounds(629,453,111,37);
		 btnNewButton_22.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			        try {
			            String query = "SELECT * FROM CBACCOUNT WHERE C_NO = ?";
			            pst = con.prepareStatement(query);
			            pst.setLong(1, accountNo);
			           rs = pst.executeQuery();
			            if (rs.next()) {
			                lblNewLabel_34.setText("Name:      " + rs.getString("C_NAME"));
			                lblNewLabel_36.setText("Mobile No:     " + rs.getString("C_MOBILENO"));
			                lblNewLabel_35.setText("Email:      " + rs.getString("C_MAIL"));
			                lblNewLabel_33.setText("Account No:      " + rs.getString("C_NO")); 
			            }
			        } catch (Exception ez) {
			            ez.printStackTrace();
			        }
			    }
			});

		panel_5.add(btnNewButton_22);
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(0, 255, 255));
		panel.add(panel_6, "add");
		panel_6.setLayout(null);
		JLabel lblNewLabel_17 = new JLabel("Account Registration");
		lblNewLabel_17.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_17.setFont(new Font("Times New Roman", Font.BOLD, 60));
		lblNewLabel_17.setBounds(10, 5, 776, 87);
		panel_6.add(lblNewLabel_17);
		JSeparator separator_3 = new JSeparator();
		separator_3.setBackground(new Color(0, 0, 0));
		separator_3.setBounds(10, 103, 776, 8);
		panel_6.add(separator_3);
		JLabel lblNewLabel_18 = new JLabel("Name");
		lblNewLabel_18.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_18.setBounds(48, 198, 103, 37);
		panel_6.add(lblNewLabel_18);
		JLabel lblNewLabel_19 = new JLabel("Email");
		lblNewLabel_19.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_19.setBounds(48, 262, 96, 37);
		panel_6.add(lblNewLabel_19);
		JLabel lblNewLabel_20 = new JLabel("Address");
		lblNewLabel_20.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_20.setBounds(48, 463, 103, 28);
		panel_6.add(lblNewLabel_20);
		JLabel lblNewLabel_21 = new JLabel("Mobile No");
		lblNewLabel_21.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_21.setBounds(48, 330, 118, 37);
		panel_6.add(lblNewLabel_21);
		JLabel lblNewLabel_22 = new JLabel("Gender");
		lblNewLabel_22.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_22.setBounds(48, 400, 103, 36);
		panel_6.add(lblNewLabel_22);
		textField_2 = new JTextField();
		textField_2.setBounds(176, 206, 216, 28);
		panel_6.add(textField_2);
		textField_2.setColumns(10);
		textField_3 = new JTextField();
		textField_3.setBounds(176, 270, 216, 28);
		panel_6.add(textField_3);
		textField_3.setColumns(10);
		textField_4 = new JTextField();
		textField_4.setBounds(176, 467, 209, 28);
		panel_6.add(textField_4);
		textField_4.setColumns(10);
		textField_5 = new JTextField();
		textField_5.setBounds(176, 336, 216, 28);
		panel_6.add(textField_5);
		textField_5.setColumns(10);
		textField_10 = new JTextField();
		textField_10.setBounds(623,360,140,28);
		panel_6.add(textField_10);
		textField_10.setColumns(10);
		JLabel lblNewLabel_24 = new JLabel("Picture");
		lblNewLabel_24.setBackground(new Color(0, 0, 255));
		lblNewLabel_24.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_24.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_24.setBounds(605, 156, 140, 95);
		lblNewLabel_24.setIcon(imageIcon);
		panel_6.add(lblNewLabel_24);
		String[] options= {"Male","Female"};
		JComboBox<String> comboBox = new JComboBox<>(options);
		comboBox.setBounds(176, 411, 216, 25);
		panel_6.add(comboBox);
		JButton btnNewButton_8 = new JButton("SignUp");
		btnNewButton_8.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton_8.setBounds(342, 543, 140, 37);
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String c_name = textField_2.getText();
				String c_email = textField_3.getText();
				Long c_mno= Long.parseLong(textField_5.getText());
				char c_sex = comboBox.equals("Male") ? 'M' : 'F';
				String c_address= textField_4.getText();
				int c_no = Integer.parseInt(textField_10.getText());
				String c_pass="";
				char[] g_pass=passwordField_3.getPassword();
				String check= new String(g_pass);
				char[] g1_pass=passwordField_2.getPassword();
				String check1=new String(g1_pass);
				if(check.equals(check1)) {
					c_pass=check;
					try {
					    String query = "INSERT INTO CBACCOUNT (C_NO, C_NAME, C_MOBILENO, C_SEX, C_ADDRESS, C_PASS, C_MAIL) VALUES (?, ?, ?, ?, ?, ?, ?)";
					    pst = con.prepareStatement(query);
					    pst.setLong(1, c_no);
					    pst.setString(2, c_name);
					    pst.setLong(3, c_mno);
					    pst.setString(4, String.valueOf(c_sex));
					    pst.setString(5, c_address);                                                                  
					    pst.setString(6, c_pass);
					    pst.setString(7, c_email);
					    pst.executeUpdate();
					    JOptionPane.showMessageDialog(BankingSystem.this, "Record saved successfully.");
					    textField_2.setText("");
					    textField_10.setText("");
					    textField_5.setText("");
					    textField_3.setText("");
					    textField_4.setText("");
					    passwordField_3.setText("");
					    passwordField_2.setText("");
					} catch (Exception ex) {
					    ex.printStackTrace();
					}

				}else {
					 JOptionPane.showMessageDialog(BankingSystem.this, "Both Password are not equal");
				}
			}
		});
		panel_6.add(btnNewButton_8);		
		passwordField_2 = new JPasswordField();
		passwordField_2.setBounds(623, 463, 140, 28);
		panel_6.add(passwordField_2);		
		JLabel lblNewLabel_23 = new JLabel("New Password");
		lblNewLabel_23.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_23.setBounds(461, 406, 140, 28);
		panel_6.add(lblNewLabel_23);		
		passwordField_3 = new JPasswordField();
		passwordField_3.setBounds(623, 409, 140, 26);
		panel_6.add(passwordField_3);		
		JLabel lblNewLabel_25 = new JLabel("Confirm Password");
		lblNewLabel_25.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_25.setBounds(461, 463, 146, 25);
		panel_6.add(lblNewLabel_25);		
		JButton btnNewButton_9 = new JButton("->back");
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_2.setText("");
                textField_3.setText("");
                textField_4.setText("");
                passwordField_3.setText("");
                passwordField_2.setText("");
				cardLayout.show(panel, "card4");
			}
		});
		btnNewButton_9.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_9.setBounds(660, 554, 80, 23);
		panel_6.add(btnNewButton_9);		
		JLabel lblNewLabel_50=new JLabel("Account No");
		lblNewLabel_50.setFont(new Font("Tahoma",Font.BOLD,15));
		lblNewLabel_50.setBounds(461, 359, 118,23);
		panel_6.add(lblNewLabel_50);		
		JPanel panel_8 = new JPanel();
		panel_8.setBackground(new Color(0, 255, 255));
		panel.add(panel_8, "send");
		panel_8.setLayout(null);		
		JLabel lblNewLabel_37 = new JLabel("Payment");
		lblNewLabel_37.setFont(new Font("Times New Roman", Font.BOLD, 60));
		lblNewLabel_37.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_37.setBounds(10, 11, 776, 73);
		panel_8.add(lblNewLabel_37);		
		JSeparator separator_7 = new JSeparator();
		separator_7.setBackground(new Color(0, 0, 0));
		separator_7.setBounds(10, 95, 776, 22);
		panel_8.add(separator_7);		
		JLabel lblNewLabel_38 = new JLabel("Send to Account no");
		lblNewLabel_38.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_38.setBounds(24, 242, 348, 45);
		panel_8.add(lblNewLabel_38);		
		textField_6 = new JTextField();
		textField_6.setBounds(391, 229, 323, 52);
		panel_8.add(textField_6);
		textField_6.setColumns(10);		
		JLabel lblNewLabel_39 = new JLabel("Rs");
		lblNewLabel_39.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_39.setBounds(256, 361, 105, 45);
		panel_8.add(lblNewLabel_39);		
		textField_7 = new JTextField();
		textField_7.setBounds(303, 354, 297, 52);
		panel_8.add(textField_7);
		textField_7.setColumns(10);		
		JLabel lblNewLabel_40 = new JLabel("Amount");
		lblNewLabel_40.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_40.setBounds(119, 361, 214, 45);
		panel_8.add(lblNewLabel_40);
		JLabel lblNewLabel_41 = new JLabel("...");
		JButton btnNewButton_14 = new JButton("Send");
		btnNewButton_14.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton_14.setBounds(648, 354, 116, 49);
		btnNewButton_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String a_no=textField_6.getText();
				double mon = Double.parseDouble(textField_7.getText());
				try {
				    con.setAutoCommit(false);

				    String checkBalanceQuery = "SELECT C_BALANCE FROM CBACCOUNT WHERE C_NO = ?";
				    pst = con.prepareStatement(checkBalanceQuery);
				    pst.setInt(1, accountNo);
				    rs = pst.executeQuery();

				    if (rs.next()) {
				        double balance = rs.getDouble("C_BALANCE");
				        if (balance >= mon) {
				            String updateSourceQuery = "UPDATE CBACCOUNT SET C_BALANCE = C_BALANCE - ? WHERE C_NO = ?";
				            PreparedStatement updateSourceStmt = con.prepareStatement(updateSourceQuery);
				            updateSourceStmt.setDouble(1, mon);
				            updateSourceStmt.setInt(2, accountNo);
				            updateSourceStmt.executeUpdate();

				            String updateDestQuery = "UPDATE CBACCOUNT SET C_BALANCE = C_BALANCE + ? WHERE C_NO = ?";
				            PreparedStatement updateDestStmt = con.prepareStatement(updateDestQuery);
				            updateDestStmt.setDouble(1, mon);
				            updateDestStmt.setString(2, a_no);
				            updateDestStmt.executeUpdate();

				            con.commit();
				            lblNewLabel_41.setText("Money transferred successfully!");
				            textField_6.setText("");
				            textField_7.setText("");
				            JOptionPane.showMessageDialog(BankingSystem.this, "Money successfully transferred");
				        } else {
				            lblNewLabel_41.setText("Insufficient balance.");
				            JOptionPane.showMessageDialog(BankingSystem.this, "Insufficient balance.");
				        }
				    } else {
				        lblNewLabel_41.setText("Account not found.");
				        JOptionPane.showMessageDialog(BankingSystem.this, "Account not found.");
				    }
				} catch (Exception ex) {
				    ex.printStackTrace();
				}
			}

		});
		panel_8.add(btnNewButton_14);	
		lblNewLabel_41.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_41.setBounds(187, 475, 402, 44);
		panel_8.add(lblNewLabel_41);		
		JButton btnNewButton_16 = new JButton("->back");
		btnNewButton_16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_7.setText("");
				textField_6.setText("");
				cardLayout.show(panel, "card5");
			}
		});
		btnNewButton_16.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_16.setBounds(632, 568, 105, 23);
		panel_8.add(btnNewButton_16);		
		JPanel panel_9 = new JPanel();
		panel_9.setBackground(new Color(0, 255, 255));
		panel.add(panel_9, "check");
		panel_9.setLayout(null);		
		JLabel lblNewLabel_42 = new JLabel("Check Balance");
		lblNewLabel_42.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_42.setFont(new Font("Times New Roman", Font.BOLD, 60));
		lblNewLabel_42.setBounds(10, 11, 776, 72);
		panel_9.add(lblNewLabel_42);		
		JSeparator separator_8 = new JSeparator();
		separator_8.setBackground(new Color(0, 0, 0));
		separator_8.setBounds(20, 87, 766, 9);
		panel_9.add(separator_8);
		JLabel lblNewLabel_43 = new JLabel("...");
		JButton btnNewButton_15 = new JButton("Check Balance");
		btnNewButton_15.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            
		            String checkBalanceQuery = "SELECT C_BALANCE FROM CBACCOUNT WHERE C_NO = ?";
		          pst= con.prepareStatement(checkBalanceQuery);
		            pst.setInt(1, accountNo);
		            ResultSet rs = pst.executeQuery();

		            if (rs.next()) {
		                double balance = rs.getDouble("C_BALANCE");
		                lblNewLabel_43.setText("Your current balance is " + balance);
		                JOptionPane.showMessageDialog(BankingSystem.this, "Your balance is " + balance);
		            } else {
		                JOptionPane.showMessageDialog(BankingSystem.this, "Account not found.");
		            }

		        } catch (SQLException e11) {
		            e11.printStackTrace();
		        }
		    }
		});

		btnNewButton_15.setFont(new Font("Tahoma", Font.BOLD, 30));
		btnNewButton_15.setBounds(88, 317, 277, 90);
		panel_9.add(btnNewButton_15);		
		lblNewLabel_43.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_43.setBounds(409, 317, 352, 84);
		panel_9.add(lblNewLabel_43);		
		JButton btnNewButton_17 = new JButton("->back");
		btnNewButton_17.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNewLabel_43.setText("...");
				cardLayout.show(panel, "card5");
			}
		});
		btnNewButton_17.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_17.setBounds(625, 544, 89, 23);
		panel_9.add(btnNewButton_17);		
		JPanel panel_10 = new JPanel();
		panel_10.setBackground(new Color(0, 255, 255));
		panel.add(panel_10, "pay");
		panel_10.setLayout(null);		
		JLabel lblNewLabel_44 = new JLabel("Payment");
		lblNewLabel_44.setFont(new Font("Times New Roman", Font.BOLD, 60));
		lblNewLabel_44.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_44.setBounds(10, 11, 776, 75);
		panel_10.add(lblNewLabel_44);		
		JSeparator separator_9 = new JSeparator();
		separator_9.setBackground(new Color(0, 0, 0));
		separator_9.setBounds(10, 97, 776, 15);
		panel_10.add(separator_9);	
		JLabel lblNewLabel_45 = new JLabel("");
		lblNewLabel_45.setBounds(91, 185, 49, 14);
		panel_10.add(lblNewLabel_45);		
		JLabel lblNewLabel_47 = new JLabel("Deposit  Rs");
		lblNewLabel_47.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_47.setBounds(198, 310, 202, 50);
		panel_10.add(lblNewLabel_47);	
		JLabel lblNewLabel_48 = new JLabel("Account no");
		lblNewLabel_48.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_48.setBounds(197, 210, 191, 43);
		panel_10.add(lblNewLabel_48);
		JLabel lblNewLabel_49 = new JLabel("...");
		lblNewLabel_49.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_49.setBounds(402, 487, 380, 62);
		panel_10.add(lblNewLabel_49);
		textField_8 = new JTextField();
		textField_8.setBounds(402, 210, 250, 43);
		panel_10.add(textField_8);
		textField_8.setColumns(10);
		textField_9 = new JTextField();
		textField_9.setBounds(410, 310, 242, 43);
		panel_10.add(textField_9);
		textField_9.setColumns(10);
		JButton btnNewButton_18 = new JButton("Deposit");
		btnNewButton_18.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String a_no = textField_8.getText();
		        double depositAmount = Double.parseDouble(textField_9.getText());
		        try {
		       
		            String updateBalanceQuery = "UPDATE CBACCOUNT SET C_BALANCE = C_BALANCE + ? WHERE C_NO = ?";
		            pst = con.prepareStatement(updateBalanceQuery);
		            pst.setDouble(1, depositAmount);
		            pst.setString(2, a_no);

		            int rowsUpdated = pst.executeUpdate();
		            if (rowsUpdated > 0) {
		                JOptionPane.showMessageDialog(BankingSystem.this, "Deposited successfully");
		                textField_9.setText("");
		                textField_8.setText("");
		            } else {
		                JOptionPane.showMessageDialog(BankingSystem.this, "Account not found. Deposit failed.");
		            }
		        } catch (SQLException e10) {
		            e10.printStackTrace();
		        }
		    }
		});

		btnNewButton_18.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_18.setBounds(358, 389, 115, 27);
		panel_10.add(btnNewButton_18);	
		JButton btnNewButton_19 = new JButton("Check Balance");
		btnNewButton_19.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String a_no = textField_8.getText();
		        try {
		          
		            String checkBalanceQuery = "SELECT C_BALANCE FROM CBACCOUNT WHERE C_NO = ?";
		            pst = con.prepareStatement(checkBalanceQuery);
		            pst.setString(1, a_no);
		            rs = pst.executeQuery();

		            if (rs.next()) {
		                double balance = rs.getDouble("C_BALANCE");
		                textField_8.setText("");
		                lblNewLabel_49.setText("Your current balance is " + balance);
		            } else {
		                JOptionPane.showMessageDialog(BankingSystem.this, "Account not found.");
		            }
		        } catch (SQLException e11) {
		            e11.printStackTrace();
		        }
		    }
		});

		btnNewButton_19.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_19.setBounds(80, 491, 291, 50);
		panel_10.add(btnNewButton_19);	
		JButton btnNewButton_20 = new JButton("->back");
		btnNewButton_20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNewLabel_49.setText("...");
				cardLayout.show(panel, "card4");
			}
		});
		btnNewButton_20.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_20.setBounds(658, 578, 89, 23);
		panel_10.add(btnNewButton_20);
		JPanel panel_11 = new JPanel();
		panel_11.setBackground(new Color(0, 255, 255));
		panel.add(panel_11, "card11");
		panel_11.setLayout(null);		
		JLabel lblNewLabel_51 = new JLabel("Registration Account");
		lblNewLabel_51.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_51.setFont(new Font("Times New Roman", Font.BOLD, 60));
		lblNewLabel_51.setBounds(10, 5, 776, 98);
		panel_11.add(lblNewLabel_51);
		JLabel lblNewLabel_131 = new JLabel("Name");
		lblNewLabel_131.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_131.setBounds(42, 206, 150, 31);
		panel_11.add(lblNewLabel_131);
		JLabel lblNewLabel_52 = new JLabel("Email");
		lblNewLabel_52.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_52.setBounds(42, 293, 143, 31);
		panel_11.add(lblNewLabel_52);		
		JLabel lblNewLabel_53 = new JLabel("Address");
		lblNewLabel_53.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_53.setBounds(42, 368, 143, 31);
		panel_11.add(lblNewLabel_53);		
		JLabel lblNewLabel_54 = new JLabel("Mobile no");
		lblNewLabel_54.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_54.setBounds(42, 446, 165, 37);
		panel_11.add(lblNewLabel_54);		
		JLabel lblNewLabel_55 = new JLabel("Designation");
		lblNewLabel_55.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel_55.setBounds(28, 515, 187, 32);
		panel_11.add(lblNewLabel_55);		
		textField_11 = new JTextField();
		textField_11.setBounds(190, 209, 194, 36);
		panel_11.add(textField_11);
		textField_11.setColumns(10);		
		JLabel lblNewLabel_56 = new JLabel("Picture");
		lblNewLabel_56.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_56.setBounds(598, 177, 143, 109);
		lblNewLabel_56.setIcon(imageIcon);
		panel_11.add(lblNewLabel_56);		
		textField_12 = new JTextField();
		textField_12.setBounds(190, 296, 194, 36);
		panel_11.add(textField_12);
		textField_12.setColumns(10);		
		textField_13 = new JTextField();
		textField_13.setBounds(190, 371, 194, 31);
		panel_11.add(textField_13);
		textField_13.setColumns(10);		
		textField_14 = new JTextField();
		textField_14.setBounds(190, 455, 194, 31);
		panel_11.add(textField_14);
		textField_14.setColumns(10);
		textField_15 = new JTextField();
		textField_15.setBounds(190, 519, 198, 31);
		panel_11.add(textField_15);
		textField_15.setColumns(10);
		JLabel lblNewLabel_57 = new JLabel("Account no");
		lblNewLabel_57.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_57.setBounds(484, 354, 165, 31);
		panel_11.add(lblNewLabel_57);
		JLabel lblNewLabel_58 = new JLabel("New Password");
		lblNewLabel_58.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_58.setBounds(457, 448, 154, 31);
		panel_11.add(lblNewLabel_58);
		JLabel lblNewLabel_59 = new JLabel("Confirm Password");
		lblNewLabel_59.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_59.setBounds(437, 520, 150, 31);
		panel_11.add(lblNewLabel_59);
		passwordField_4 = new JPasswordField();
		passwordField_4.setBounds(598, 454, 178, 29);
		panel_11.add(passwordField_4);
		passwordField_5 = new JPasswordField();
		passwordField_5.setBounds(598, 522, 177, 31);
		panel_11.add(passwordField_5);
		textField_16 = new JTextField();
		textField_16.setBounds(598, 356, 178, 31);
		panel_11.add(textField_16);
		textField_16.setColumns(10);
		JSeparator separator_10 = new JSeparator();
		separator_10.setBackground(new Color(0, 0, 0));
		separator_10.setBounds(10, 99, 776, 14);
		panel_11.add(separator_10);
		JButton btnNewButton_6 = new JButton("SignUp");
		btnNewButton_6.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_6.setBounds(347, 581, 127, 34);
		btnNewButton_6.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String c_name = textField_11.getText();
		        String c_email = textField_12.getText();
		        long c_mno = Long.parseLong(textField_14.getText());
		        String c_des = textField_15.getText(); 
		        String c_address = textField_13.getText();
		        int c_no = Integer.parseInt(textField_16.getText());
		        String c_pass = "";
		        char[] g_pass = passwordField_4.getPassword();
		        String check = new String(g_pass);
		        char[] g1_pass = passwordField_5.getPassword();
		        String check1 = new String(g1_pass);

		        if (check.equals(check1)) {
		            c_pass = check;
		            try {
		               
		                String query = "INSERT INTO EBACCOUNT (E_NO, E_NAME, E_MOBILENO, E_DESIGNATION, E_ADDRESS, E_PASS, E_MAIL) VALUES (?, ?, ?, ?, ?, ?, ?)";
		                pst= con.prepareStatement(query);
		                pst.setInt(1, c_no);  
		                pst.setString(2, c_name);
		                pst.setLong(3, c_mno);
		                pst.setString(4, c_des);
		                pst.setString(5, c_address);
		                pst.setString(6, c_pass);
		                pst.setString(7, c_email);
		                pst.executeUpdate();

		                JOptionPane.showMessageDialog(BankingSystem.this, "Record saved successfully.");
		                textField_11.setText("");
		                textField_12.setText("");
		                textField_15.setText("");
		                textField_13.setText("");
		                textField_16.setText("");
		                textField_14.setText("");
		                passwordField_4.setText("");
		                passwordField_5.setText("");
		                
		            } catch (Exception ex) {
		                ex.printStackTrace();
		            }
		        } else {
		            JOptionPane.showMessageDialog(BankingSystem.this, "Both passwords are not equal.");
		        }
		    }
		});

		panel_11.add(btnNewButton_6);
		JButton btnNewButton_21 = new JButton("->back");
		btnNewButton_21.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_11.setText("");
				 textField_12.setText("");
				 textField_15.setText("");
				 textField_13.setText("");
				 passwordField_4.setText("");
				 passwordField_5.setText("");
				cardLayout.show(panel, "card4");;
			}
		});
		btnNewButton_21.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_21.setBounds(664, 589, 89, 23);
		panel_11.add(btnNewButton_21);
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(new Color(0, 255, 255));
		panel.add(panel_7, "card7");
		panel_7.setLayout(null);
		JLabel lblNewLabel_26 = new JLabel("Delete account");
		lblNewLabel_26.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_26.setFont(new Font("Times New Roman", Font.BOLD, 60));
		lblNewLabel_26.setBounds(10, 11, 776, 123);
		panel_7.add(lblNewLabel_26);
		JLabel lblNewLabel_27 = new JLabel("Account No");
		lblNewLabel_27.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_27.setBounds(168, 279, 200, 52);
		panel_7.add(lblNewLabel_27);
		JSeparator separator_4 = new JSeparator();
		separator_4.setBackground(new Color(0, 0, 0));
		separator_4.setBounds(10, 110, 776, 11);
		panel_7.add(separator_4);
		JTextField textField_80 = new JTextField();
		textField_80.setBounds(403, 284, 264, 47);
		panel_7.add(textField_80);
		textField_80.setColumns(10);
		JButton btnNewButton_23 = new JButton("Delete");
		btnNewButton_23.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		             String deleteQuery = "DELETE FROM CBACCOUNT WHERE C_NO = ?";
		            pst = con.prepareStatement(deleteQuery);
		            pst.setString(1, textField_80.getText());
		            int rowsDeleted = pst.executeUpdate();

		            if (rowsDeleted > 0) {
		                JOptionPane.showMessageDialog(BankingSystem.this, "Account has been deleted");
		                textField_80.setText("");
		            } else {
		                JOptionPane.showMessageDialog(BankingSystem.this, "Account not found");
		            }
		        } catch (SQLException e12) {
		            e12.printStackTrace();
		        }
		    }
		});

		btnNewButton_23.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton_23.setBounds(313, 412, 149, 47);
		panel_7.add(btnNewButton_23);
		JButton btnNewButton_24 = new JButton("->back");
		btnNewButton_24.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_80.setText("");
				cardLayout.show(panel, "card4");
			}
		});
		btnNewButton_24.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton_24.setBounds(655, 574, 89, 23);
		panel_7.add(btnNewButton_24);
	}
}