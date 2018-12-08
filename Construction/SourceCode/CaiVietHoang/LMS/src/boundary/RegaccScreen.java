/*
. Full Name: Cai Việt Hoàng
. Date: 22 thg 4, 2018
. Project name: LMS
. Teacher's name: Sc.D Nguyễn Thị Thu Trang
. Class: VUWIT15
. Someone help me: 
. Description: 
*/
package boundary;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.RegaccController;
import dbInfo.dbInclude;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.sql.*;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.lang.reflect.GenericDeclaration;
import java.awt.event.ActionEvent;

import entity.Book;

public class RegaccScreen extends JFrame
{
	private JPanel contentPane;
	private JTextField unField;
	private JPasswordField pField;
	private JTextField fullnameField;
	private JTextField emailField;
	private JTextField contactField;
	private JTextField studentidField;
	private JComboBox genderBox;
	private JComboBox courseBox;
	private JComboBox instituteBox;
	Connection connection = null;
	/**
	 * Create the frame.
	 */
	public RegaccScreen()
	{
		connection = dbInclude.dbConnector();
		
		this.setTitle("REGISTER - Library System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
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

		JLabel lblIsCompulsory = new JLabel("(*) is compulsory blank that you have to fill");
		lblIsCompulsory.setFont(new Font("Arial", Font.PLAIN, 15));
		lblIsCompulsory.setBounds(68, 89, 287, 15);
		contentPane.add(lblIsCompulsory);

		JLabel lblAccountInformation = new JLabel("Account Information");
		lblAccountInformation.setFont(new Font("Arial", Font.PLAIN, 18));
		lblAccountInformation.setBounds(112, 122, 157, 22);
		contentPane.add(lblAccountInformation);

		JLabel lblUsername = new JLabel("Username (*)");
		lblUsername.setFont(new Font("Arial", Font.PLAIN, 15));
		lblUsername.setBounds(112, 156, 91, 15);
		contentPane.add(lblUsername);

		JLabel lblPassword = new JLabel("Password (*)");
		lblPassword.setFont(new Font("Arial", Font.PLAIN, 15));
		lblPassword.setBounds(112, 192, 91, 15);
		contentPane.add(lblPassword);

		unField = new JTextField();
		unField.setFont(new Font("Arial", Font.PLAIN, 15));
		unField.setBounds(215, 156, 184, 21);
		contentPane.add(unField);
		unField.setColumns(10);

		pField = new JPasswordField();
		pField.setFont(new Font("Arial", Font.PLAIN, 15));
		pField.setBounds(215, 189, 184, 21);
		contentPane.add(pField);

		JLabel lblPersonalInformation = new JLabel("Personal Information");
		lblPersonalInformation.setFont(new Font("Arial", Font.PLAIN, 18));
		lblPersonalInformation.setBounds(112, 227, 287, 22);
		contentPane.add(lblPersonalInformation);

		JLabel lblFullName = new JLabel("Full name (*)");
		lblFullName.setFont(new Font("Arial", Font.PLAIN, 15));
		lblFullName.setBounds(112, 261, 91, 15);
		contentPane.add(lblFullName);

		JLabel lblEmail = new JLabel("Email (*)");
		lblEmail.setFont(new Font("Arial", Font.PLAIN, 15));
		lblEmail.setBounds(112, 288, 91, 15);
		contentPane.add(lblEmail);

		JLabel lblContact = new JLabel("Contact (*)");
		lblContact.setFont(new Font("Arial", Font.PLAIN, 15));
		lblContact.setBounds(112, 315, 91, 15);
		contentPane.add(lblContact);

		fullnameField = new JTextField();
		fullnameField.setFont(new Font("Arial", Font.PLAIN, 15));
		fullnameField.setBounds(215, 258, 184, 21);
		contentPane.add(fullnameField);
		fullnameField.setColumns(10);

		emailField = new JTextField();
		emailField.setFont(new Font("Arial", Font.PLAIN, 15));
		emailField.setBounds(215, 285, 184, 21);
		contentPane.add(emailField);
		emailField.setColumns(10);

		contactField = new JTextField();
		contactField.setFont(new Font("Arial", Font.PLAIN, 15));
		contactField.setBounds(215, 312, 184, 21);
		contentPane.add(contactField);
		contactField.setColumns(10);

		JLabel lblGender = new JLabel("Gender (*)");
		lblGender.setFont(new Font("Arial", Font.PLAIN, 15));
		lblGender.setBounds(435, 261, 68, 15);
		contentPane.add(lblGender);
		
		genderBox = new JComboBox();
		genderBox.setFont(new Font("Arial", Font.PLAIN, 15));
		genderBox.setModel(new DefaultComboBoxModel(new String[]
		{ "Male", "Female", "Unknow" }));
		genderBox.setBounds(524, 258, 83, 21);
		contentPane.add(genderBox);

		JLabel lblStudentInformation = new JLabel("Student Information");
		lblStudentInformation.setFont(new Font("Arial", Font.PLAIN, 18));
		lblStudentInformation.setBounds(112, 352, 287, 22);
		contentPane.add(lblStudentInformation);

		JCheckBox hustCheck = new JCheckBox("You are HUST Student");
		hustCheck.setFont(new Font("Arial", Font.PLAIN, 15));
		hustCheck.setBounds(112, 379, 287, 25);
		contentPane.add(hustCheck);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(112, 219, 495, 2);
		contentPane.add(separator);
		
		JLabel lblStudentId = new JLabel("Student ID (*)");
		lblStudentId.setFont(new Font("Arial", Font.PLAIN, 15));
		lblStudentId.setBounds(112, 412, 91, 15);
		contentPane.add(lblStudentId);

		JLabel lblInstitude = new JLabel("Institute (*)");
		lblInstitude.setFont(new Font("Arial", Font.PLAIN, 15));
		lblInstitude.setBounds(112, 439, 91, 15);
		contentPane.add(lblInstitude);

		JLabel lblCourse = new JLabel("Course (*)");
		lblCourse.setFont(new Font("Arial", Font.PLAIN, 15));
		lblCourse.setBounds(112, 466, 91, 15);
		contentPane.add(lblCourse);
		
		studentidField = new JTextField();
		studentidField.setFont(new Font("Arial", Font.PLAIN, 15));
		studentidField.setBounds(215, 409, 184, 21);
		contentPane.add(studentidField);
		studentidField.setColumns(10);

		courseBox = new JComboBox();
		courseBox.setFont(new Font("Arial", Font.PLAIN, 15));
		courseBox.setModel(new DefaultComboBoxModel(new String[]
		{ "K57", "K58", "K59", "K60", "K61", "K62" }));
		courseBox.setBounds(215, 463, 54, 21);
		contentPane.add(courseBox);

		instituteBox = new JComboBox();
		instituteBox.setFont(new Font("Arial", Font.PLAIN, 15));
		instituteBox.setModel(new DefaultComboBoxModel(new String[] {"Viện Cơ khí", "Viện Cơ khí Động lực", "Viện Công nghệ Sinh học và Công nghệ Thực phẩm", "Viện Công nghệ Thông tin và Truyền thông", "Viện Dệt may - Da giầy và Thời trang", "Viện Đào tạo Quốc tế", "Viện Khoa học và Công nghệ Môi trường", "Viện Khoa học và Công nghệ nhiệt lạnh", "Viện Khoa học và Kỹ thuật Vật liệu", "Viện Kinh tế và Quản lý", "Viện Kỹ thuật hạt nhân và Vật lý môi trường", "Viện Kỹ thuật Hóa học", "Viện Kỹ thuật Điều khiển và Tự động hóa", "Viện Ngoại ngữ", "Viện Sư phạm Kỹ thuật", "Viện Toán ứng dụng và Tin học", "Viện Vật lý Kỹ thuật", "Viện Điện", "Viện Điện tử - Viễn thông"}));
		instituteBox.setBounds(215, 436, 288, 21);
		contentPane.add(instituteBox);
		/*
		try
		{
			RegaccController rac = new RegaccController();
		
			//String query = "Update loginInfo set StudentID='" + studentidField.getText() + "', Username= '"+unField.getText()+"', Password='"+pField.getText()+"', FullName='"+fullnameField.getText()+"',Email='"+emailField.getText()+"', Contact='"+contactField.getText()+"'      ";
			PreparedStatement pst = connection.prepareStatement(rac.insertInfo());
			pst.setString(2, unField.getText());
			pst.setString(3, pField.getText());
			pst.setString(4, fullnameField.getText());
			pst.setString(5, emailField.getText());
			pst.setString(6, contactField.getText());
			pst.setString(1, studentidField.getText());
		} catch (Exception g)
		{
			g.printStackTrace();
		}
		*/
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					RegaccController rac = new RegaccController();
					
					PreparedStatement pst = connection.prepareStatement(rac.requestinsertInfo());
					pst.setString(2, unField.getText());
					pst.setString(3, pField.getText());
					pst.setString(4, fullnameField.getText());
					pst.setString(6, emailField.getText());
					pst.setString(7, contactField.getText());
					String gender = genderBox.getSelectedItem().toString();
					pst.setString(5, gender);
										
					if (unField.getText().isEmpty())
					{
						JOptionPane.showMessageDialog(null, "Insert Username");
						unField.requestFocus();
						return;
					}
					if (rac.checkusernameDuplicate(unField.getText())) 
					{
						JOptionPane.showMessageDialog(null, "Duplicate Username");
						unField.requestFocus();
						return;
					} 
					if (pField.getText().isEmpty())
					{
						JOptionPane.showMessageDialog(null, "Insert Password");
						pField.requestFocus();
						return;
					}
					
					if (fullnameField.getText().isEmpty())
					{
						JOptionPane.showMessageDialog(null, "Insert your full name");
						fullnameField.requestFocus();
						return;
					}
					
					if (emailField.getText().isEmpty())
					{
						JOptionPane.showMessageDialog(null, "Insert email");
						emailField.requestFocus();
						return;
					}
					
					if (contactField.getText().isEmpty())
					{
						JOptionPane.showMessageDialog(null, "Insert contact");
						contactField.requestFocus();
						return;
					}
					
					
					if (hustCheck.isSelected())
					{
						pst.setString(1, studentidField.getText());
						String institute = instituteBox.getSelectedItem().toString();
						pst.setString(8, institute);
						String course = courseBox.getSelectedItem().toString();
						pst.setString(9, course);
						
						if (studentidField.getText().isEmpty())
						{
							JOptionPane.showMessageDialog(null, "Insert StudentID");
							studentidField.requestFocus();
							return;
						}
						
						if (rac.checkstudentidDuplicate(studentidField.getText()))
						{
							JOptionPane.showMessageDialog(null, "Duplicate StudentID");
							studentidField.requestFocus();
							return;
						}
					}
					pst.setInt(10, 5);
					
					pst.execute();
					JOptionPane.showMessageDialog(null, "Register Complete.");
					
					pst.close();
					
				} catch (Exception f)
				{
					f.printStackTrace();
					JOptionPane.showMessageDialog(null, "Failed. Unknow reason.");
				}finally
				{
					if (connection != null)
					{
						try
						{
							connection.close();
						} catch (Exception f)
						{
							f.printStackTrace();
						}
					}
				}
			}
		});
		
		btnRegister.setFont(new Font("Arial", Font.PLAIN, 15));
		btnRegister.setBounds(824, 486, 97, 23);
		contentPane.add(btnRegister);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				setVisible(false);
				LoginScreen ls = new LoginScreen();
				ls.setVisible(true);
			}
		});
		btnBack.setFont(new Font("Arial", Font.PLAIN, 15));
		btnBack.setBounds(715, 486, 97, 23);
		contentPane.add(btnBack);

	}
	
	
}