/*
. Full Name: Cai Việt Hoàng
. Date: 16 thg 4, 2018
. Project name: LMS
. Teacher's name: Sc.D Nguyễn Thị Thu Trang
. Class: VUWIT15
. Someone help me: 
. Description: 
*/
package boundary;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Image;
import java.awt.Font;
import java.awt.Color;

import javax.swing.border.EmptyBorder;
import javax.swing.*;

import java.sql.*;
import javax.swing.border.TitledBorder;

import controller.LoginController;
import dbInfo.dbInclude;

public class LoginScreen
{
	Connection connection = null;
	private JFrame frame;
	private JPasswordField PField;
	public static String USERNAME;

	/**
	 * Create the application.
	 */
	public LoginScreen()
	{
		initialize();
		connection = dbInclude .dbConnector();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		
		setFrame(new JFrame());

		getFrame().setTitle("Library System");
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrame().setBounds(100, 100, 803, 338);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		getFrame().setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblImg = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/logoBk.png")).getImage();
		lblImg.setIcon(new ImageIcon(img));
		lblImg.setBounds(12, 12, 44, 65);
		contentPane.add(lblImg);

		JLabel lblHUST = new JLabel("Hanoi University of Science and Technology");
		lblHUST.setFont(new Font("Arial", Font.BOLD, 28));
		lblHUST.setBounds(68, 12, 601, 35);
		contentPane.add(lblHUST);

		JLabel lblLibrarySystem = new JLabel("Library System");
		lblLibrarySystem.setFont(new Font("Arial", Font.PLAIN, 26));
		lblLibrarySystem.setBounds(68, 47, 180, 30);
		contentPane.add(lblLibrarySystem);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Login", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(242, 126, 272, 88);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(6, 20, 85, 15);
		panel.add(lblUsername);
		lblUsername.setFont(new Font("Arial", Font.PLAIN, 15));

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(6, 63, 85, 15);
		panel.add(lblPassword);
		lblPassword.setFont(new Font("Arial", Font.PLAIN, 15));

		JTextField UNField = new JTextField();
		UNField.setBounds(103, 17, 163, 21);
		panel.add(UNField);
		UNField.setColumns(10);

		PField = new JPasswordField();
		PField.setBounds(103, 60, 163, 21);
		panel.add(PField);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				try
				{	
					LoginController lc = new LoginController();
					PreparedStatement pst = connection.prepareStatement(lc.requestgetvalidloginInfo());
					pst.setString(1, UNField.getText());
					pst.setString(2, PField.getText());
					if (UNField.getText().isEmpty())
					{
						JOptionPane.showMessageDialog(null, "You have not entered an username");
						return;
					}
					if (PField.getText().isEmpty())
					{
						JOptionPane.showMessageDialog(null, "You have not entered a password");
						return;
					}
					ResultSet rs = pst.executeQuery();
					int count = 0;
					while (rs.next())
					{
						count++;
					}
					if (count == 1)
					{
						JOptionPane.showMessageDialog(null, "Username and Password is correct");
						USERNAME = UNField.getText();
						frame.dispose();
						MainMenu ms = new MainMenu();
						ms.setVisible(true);
					} else if (count > 1)
					{
						JOptionPane.showMessageDialog(null, "Duplicate Username and Password");
					} else
					{
						JOptionPane.showMessageDialog(null, "Username and Password isn't correct. Plase try again");
					}
					rs.close();
					pst.close();
				} catch (Exception e)
				{
					JOptionPane.showMessageDialog(null, e);
				} finally
				{
					if (connection != null)
					{
						try
						{
							connection.close();
						} catch (Exception e)
						{
							e.printStackTrace();
						}
					}
					try
					{

					} catch (Exception e)
					{
						JOptionPane.showMessageDialog(null, e);
					}
				}
			}
		});

		btnLogin.setFont(new Font("Arial", Font.PLAIN, 16));
		btnLogin.setBounds(389, 226, 97, 23);
		contentPane.add(btnLogin);
		
		JLabel lblNotice = new JLabel("To begin, please Log in. If you don't have account, click on Register");
		lblNotice.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNotice.setForeground(Color.RED);
		lblNotice.setBounds(68, 100, 537, 21);
		contentPane.add(lblNotice);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				frame.dispose();
				RegaccScreen ras = new RegaccScreen();
				ras.setVisible(true);
			}
		});
		btnRegister.setFont(new Font("Arial", Font.PLAIN, 15));
		btnRegister.setBounds(265, 226, 97, 23);
		contentPane.add(btnRegister);
	}

	public JFrame getFrame()
	{
		return frame;
	}

	public void setFrame(JFrame frame)
	{
		this.frame = frame;
	}

	public void setVisible(boolean b)
	{
		LoginScreen window = new LoginScreen();
		window.getFrame().setVisible(true);
	}
}
