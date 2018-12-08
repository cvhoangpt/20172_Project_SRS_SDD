/*
. Full Name: Cai Việt Hoàng
. Date: 17 thg 4, 2018
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.Statement;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import controller.RegborrowController;
import controller.ViewborrowController;
import dbInfo.dbInclude;
import net.proteanit.sql.DbUtils;

import javax.swing.UIManager;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import static boundary.LoginScreen.USERNAME;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegborrowScreen extends JFrame
{

	private JPanel contentPane;
	Connection connection = null;
	private JTextField FieldBookID;
	private JTextField FieldTitle;
	private JTextField FieldType;
	private JTextField FieldAuthor;
	private JTextField FieldISBN;
	private JTextField FieldPublisher;
	public static String BOOKID;

	RegborrowController rbc = new RegborrowController();
	private JTable catalogTable;

	/**
	 * Create the frame.
	 */
	public RegborrowScreen()
	{
		connection = dbInclude.dbConnector();
		this.setTitle("BORROWING - Library System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 30, 1300, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblImg = new JLabel("");
		lblImg.setBounds(19, 17, 44, 65);
		Image img = new ImageIcon(this.getClass().getResource("/logoBk.png")).getImage();
		lblImg.setIcon(new ImageIcon(img));

		JLabel lblHUST = new JLabel("Hanoi University of Science and Technology");
		lblHUST.setBounds(75, 17, 601, 33);
		lblHUST.setFont(new Font("Arial", Font.BOLD, 28));

		JLabel lblLibrarySystem = new JLabel("Library System");
		lblLibrarySystem.setBounds(75, 50, 180, 30);
		lblLibrarySystem.setFont(new Font("Arial", Font.PLAIN, 26));

		JButton btnLogOut = new JButton("Log out");
		btnLogOut.setBounds(1188, 49, 89, 23);
		btnLogOut.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				setVisible(false);
				LoginScreen ls = new LoginScreen();
				ls.setVisible(true);
			}
		});

		JButton btnBack = new JButton("Back");
		btnBack.setBounds(967, 513, 129, 23);
		btnBack.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				setVisible(false);
				MainMenu ms = new MainMenu();
				ms.setVisible(true);
			}
		});
		btnBack.setFont(new Font("Arial", Font.PLAIN, 15));

		JPanel panel = new JPanel();
		panel.setBounds(66, 94, 439, 236);
		panel.setForeground(new Color(25, 25, 112));
		// Font boder = new Font("Arial", Font.BOLD, 16);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Book Detail",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 255)));
		panel.setLayout(null);

		JLabel lblBookID = new JLabel("BookID");
		lblBookID.setFont(new Font("Arial", Font.PLAIN, 15));
		lblBookID.setBounds(6, 33, 70, 15);
		panel.add(lblBookID);

		JLabel lblTitle = new JLabel("Title");
		lblTitle.setFont(new Font("Arial", Font.PLAIN, 15));
		lblTitle.setBounds(6, 60, 56, 15);
		panel.add(lblTitle);

		JLabel lblType = new JLabel("Type");
		lblType.setFont(new Font("Arial", Font.PLAIN, 15));
		lblType.setBounds(6, 94, 56, 15);
		panel.add(lblType);

		JLabel lblAuthor = new JLabel("Author");
		lblAuthor.setFont(new Font("Arial", Font.PLAIN, 15));
		lblAuthor.setBounds(6, 136, 56, 15);
		panel.add(lblAuthor);

		JLabel lblPublisher = new JLabel("Publisher");
		lblPublisher.setFont(new Font("Arial", Font.PLAIN, 15));
		lblPublisher.setBounds(6, 168, 70, 15);
		panel.add(lblPublisher);

		JLabel lblIsbn = new JLabel("ISBN");
		lblIsbn.setFont(new Font("Arial", Font.PLAIN, 15));
		lblIsbn.setBounds(6, 208, 56, 15);
		panel.add(lblIsbn);

		FieldBookID = new JTextField();
		FieldBookID.setFont(new Font("Arial", Font.PLAIN, 14));
		FieldBookID.setBounds(105, 30, 190, 21);
		panel.add(FieldBookID);
		FieldBookID.setColumns(10);

		FieldTitle = new JTextField();
		FieldTitle.setFont(new Font("Arial", Font.PLAIN, 14));
		FieldTitle.setBounds(105, 57, 190, 21);
		panel.add(FieldTitle);
		FieldTitle.setColumns(10);

		FieldType = new JTextField();
		FieldType.setFont(new Font("Arial", Font.PLAIN, 14));
		FieldType.setBounds(105, 90, 190, 21);
		panel.add(FieldType);
		FieldType.setColumns(10);

		FieldAuthor = new JTextField();
		FieldAuthor.setFont(new Font("Arial", Font.PLAIN, 14));
		FieldAuthor.setBounds(105, 132, 190, 21);
		panel.add(FieldAuthor);
		FieldAuthor.setColumns(10);

		FieldPublisher = new JTextField();
		FieldPublisher.setFont(new Font("Arial", Font.PLAIN, 14));
		FieldPublisher.setColumns(10);
		FieldPublisher.setBounds(105, 164, 190, 21);
		panel.add(FieldPublisher);

		FieldISBN = new JTextField();
		FieldISBN.setFont(new Font("Arial", Font.PLAIN, 14));
		FieldISBN.setBounds(105, 204, 190, 21);
		panel.add(FieldISBN);
		FieldISBN.setColumns(10);

		JButton btnBorrow = new JButton("Borrow");
		btnBorrow.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				try
				{
					PreparedStatement pst1 = connection.prepareStatement(rbc.requestbookBorrowed());
					pst1.setString(1, USERNAME);
					pst1.setString(2, FieldBookID.getText());
					pst1.setString(3, FieldTitle.getText());
					pst1.setString(4, FieldType.getText());
					pst1.setString(5, FieldAuthor.getText());
					pst1.setString(6, FieldPublisher.getText());
					pst1.setString(7, FieldISBN.getText());

					if (FieldBookID.getText().isEmpty())
					{
						JOptionPane.showMessageDialog(null, "Missing BookID");
						return;
					}
					if (rbc.checkbookselectDuplicate(FieldBookID.getText()))
					{
						JOptionPane.showMessageDialog(null, "You have chosen this book");
						FieldBookID.requestFocus();
						return;
					}
					if (FieldTitle.getText().isEmpty())
					{
						JOptionPane.showMessageDialog(null, "Missing Title");
						return;
					}
					if (FieldType.getText().isEmpty())
					{
						JOptionPane.showMessageDialog(null, "Missing Type");
						return;
					}
					if (FieldAuthor.getText().isEmpty())
					{
						JOptionPane.showMessageDialog(null, "Missing Author");
						return;
					}
					if (FieldPublisher.getText().isEmpty())
					{
						JOptionPane.showMessageDialog(null, "Missing Publisher");
						return;
					}
					if (FieldISBN.getText().isEmpty())
					{
						JOptionPane.showMessageDialog(null, "Missing ISBN");
						return;
					}
					
					PreparedStatement pst2 = connection.prepareStatement(rbc.requestfindborrowMax());
					ResultSet rs2 = pst2.executeQuery();
					int count = 1;
					while (rs2.next())
					{
						count++;
					}
					System.out.println(count);
					if (count > 5) 
					{
						JOptionPane.showMessageDialog(null, "You have borrowed more than 5 books");
						return;
					} else if (count <= 5)
					{
						JOptionPane.showMessageDialog(null, "Borrow Successful");
					}
					pst1.execute();
					pst1.close();
					pst2.close();
					rs2.close();
				} catch (Exception e)
				{
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error");
				}				
			}
		});
		btnBorrow.setFont(new Font("Arial", Font.PLAIN, 15));
		btnBorrow.setBounds(318, 27, 109, 27);
		panel.add(btnBorrow);

		JLabel lblNote = new JLabel("(*) Enter BookID that view in Book Catalog to search Book");
		lblNote.setBounds(66, 342, 439, 15);
		lblNote.setForeground(Color.BLUE);
		lblNote.setFont(new Font("Arial", Font.PLAIN, 14));

		JLabel lblHello = new JLabel("Hello");
		lblHello.setBounds(1140, 5, 37, 29);
		lblHello.setFont(new Font("Arial", Font.PLAIN, 16));

		JLabel lblName = new JLabel("");
		lblName.setBounds(1188, 29, 76, 23);

		lblName.setText(USERNAME);

		JButton btnViewBorrowed = new JButton("View Borrowed");
		btnViewBorrowed.setBounds(1114, 513, 151, 23);
		btnViewBorrowed.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				setVisible(false);
				ViewborrowedScreen vb = new ViewborrowedScreen();
				vb.setVisible(true);
			}

		});
		contentPane.setLayout(null);
		contentPane.add(lblImg);
		contentPane.add(lblHUST);
		contentPane.add(lblLibrarySystem);
		contentPane.add(lblHello);
		contentPane.add(lblName);
		contentPane.add(btnLogOut);
		contentPane.add(panel);
		contentPane.add(lblNote);
		contentPane.add(btnBack);
		contentPane.add(btnViewBorrowed);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(539, 94, 731, 265);
		contentPane.add(scrollPane);
		
		catalogTable = new JTable();
		catalogTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				try
				{
					int row = catalogTable.getSelectedRow();
					System.out.println(row);
					String bookID = (catalogTable.getModel().getValueAt(row, 0)).toString();
					BOOKID = bookID;
					System.out.println(BOOKID);
					RegborrowController rbc = new RegborrowController();
					PreparedStatement pst = connection.prepareStatement(rbc.selectBook());
					ResultSet rs = pst.executeQuery();
					while (rs.next())
					{
						FieldBookID.setText(rs.getString("BookID"));
						FieldTitle.setText(rs.getString("Title"));
						FieldType.setText(rs.getString("Type"));
						FieldAuthor.setText(rs.getString("Author"));
						FieldPublisher.setText(rs.getString("Publisher"));
						FieldISBN.setText(rs.getString("ISBN"));
					}
					pst.close();
					rs.close();
				} catch (Exception e)
				{
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		scrollPane.setViewportView(catalogTable);
		
		bookCatalog();
		/*
		try
		{
			ViewborrowController vbc = new ViewborrowController();
			PreparedStatement pst = connection.prepareStatement(vbc.getbookQuantity());
			ResultSet rs = pst.executeQuery();
			String count = Integer.toString(rs.getInt(1));
			while (rs.next())
			{
				System.out.println("COUNT(*) = "+rs.getInt(1));
				System.out.println(count);
				QuantityField.setText(count);
			}
			pst.close();
			rs.close();
			
		} catch(Exception e)
		{
			e.getStackTrace();
		}
		*/
	}
	
	public void bookCatalog()
	{
		try
		{
			RegborrowController rbc = new RegborrowController();
			PreparedStatement pst = connection.prepareStatement(rbc.requestgetbookCatalog());
			ResultSet rs = pst.executeQuery();
			catalogTable.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
			e.printStackTrace();
		}
	}
}