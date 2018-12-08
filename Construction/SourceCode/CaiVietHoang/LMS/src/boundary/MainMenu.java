/*
. Full Name: Cai Việt Hoàng
. Date: 10 thg 4, 2018
. Project name: LMS
. Teacher's name: Sc.D Nguyễn Thị Thu Trang
. Class: VUWIT15
. Someone help me: 
. Description: 
*/
package boundary;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import static boundary.LoginScreen.USERNAME;



public class MainMenu extends JFrame
{
	
	private JPanel contentPane;
	//private JFrame frame;
	private JFrame frame;

	/**
	 * Create the frame.
	 */
	public MainMenu()
	{
		
		this.setTitle("CONTROL PANEL - Library System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
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
		
		JLabel lblWelcomeToLibrary = new JLabel("Welcome to Library System. Below is some funtions");
		lblWelcomeToLibrary.setFont(new Font("Arial", Font.PLAIN, 16));
		lblWelcomeToLibrary.setBounds(68, 95, 621, 28);
		contentPane.add(lblWelcomeToLibrary);
		
		JButton btnBorrowBook = new JButton("Borrow Book");
		btnBorrowBook.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				RegborrowScreen reg = new RegborrowScreen();
				reg.setVisible(true);
				setVisible(false);
			}
		});
		btnBorrowBook.setFont(new Font("Arial", Font.PLAIN, 15));
		btnBorrowBook.setBounds(68, 135, 140, 33);
		contentPane.add(btnBorrowBook);
		
		JButton btnListOfBorrowed = new JButton("List of borrowed");
		btnListOfBorrowed.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				setVisible(false);
				ViewborrowedScreen vb = new ViewborrowedScreen();
				vb.setVisible(true);
			}
		});
		btnListOfBorrowed.setFont(new Font("Arial", Font.PLAIN, 15));
		btnListOfBorrowed.setBounds(243, 135, 140, 33);
		contentPane.add(btnListOfBorrowed);
		
		
		
		JButton btnLogOut = new JButton("Log out");
		btnLogOut.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				setVisible(false);
				LoginScreen ls = new LoginScreen();
				ls.setVisible(true);
			}
		});
		btnLogOut.setBounds(861, 44, 89, 23);
		contentPane.add(btnLogOut);
		
		JLabel lblHello = new JLabel("Hello");
		lblHello.setFont(new Font("Arial", Font.PLAIN, 16));
		lblHello.setBounds(823, 0, 37, 29);
		contentPane.add(lblHello);
		
		LoginScreen ls = new LoginScreen();
		JLabel lblName = new JLabel("");
		lblName.setBounds(1194, 32, 76, 15);
		contentPane.add(lblName);
		
		lblName.setText(USERNAME);
		
		
		
		
	}
	public JFrame getFrame()
	{
		return getFrame();
	}

	public void setFrame(JFrame frame)
	{
		this.frame = frame;
	}
}
