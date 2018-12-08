/*
. Full Name: Cai Việt Hoàng
. Date: 26 thg 4, 2018
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.CancelborrowController;
import controller.RegborrowController;
import controller.ViewborrowController;
import dbInfo.dbInclude;
import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;

public class ViewborrowedScreen extends JFrame
{
	Connection connection = null;
	
	private JPanel contentPane;
	private JButton btnBack;
	private JButton btnBackToMain;
	private JLabel lblLibrarySystem;
	private JTable BorrowedTable;
	PreparedStatement pst = null;
	ResultSet rs = null;
	private JTextField BookIDField;
	public static String BOOKID;
	private JTextField QuantityField;
	
	public void refreshTable()
	{
		
		try
		{
			ViewborrowController vbc = new ViewborrowController();
			PreparedStatement pst = connection.prepareStatement(vbc.requestgetbookBorrowed());
			ResultSet rs = pst.executeQuery();
			BorrowedTable.setModel(DbUtils.resultSetToTableModel(rs));
			pst.close();
			rs.close();

		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * Create the frame.
	 */
	public ViewborrowedScreen()
	{
		connection = dbInclude.dbConnector();
		this.setTitle("BORROWED LIST - Library System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 30, 1300, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		btnBack = new JButton("Back to RBS");
		btnBack.setBounds(17, 105, 155, 27);
		btnBack.setFont(new Font("Arial", Font.PLAIN, 15));
		btnBack.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				setVisible(false);
				RegborrowScreen rbs = new RegborrowScreen();
				rbs.setVisible(true);
			}
		});

		btnBackToMain = new JButton("Back to Main Menu");
		btnBackToMain.setBounds(17, 150, 155, 27);
		btnBackToMain.setFont(new Font("Arial", Font.PLAIN, 15));
		btnBackToMain.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				setVisible(false);
				MainMenu mm = new MainMenu();
				mm.setVisible(true);
			}
		});
		
		JLabel lblHanoiUniversityOf = new JLabel("Hanoi University of Science and Technology");
		lblHanoiUniversityOf.setBounds(115, 17, 596, 33);
		lblHanoiUniversityOf.setFont(new Font("Arial", Font.BOLD, 28));
		
		lblLibrarySystem = new JLabel("Library System");
		lblLibrarySystem.setBounds(115, 49, 171, 30);
		lblLibrarySystem.setFont(new Font("Arial", Font.PLAIN, 26));
		contentPane.setLayout(null);
		contentPane.add(lblLibrarySystem);
		contentPane.add(lblHanoiUniversityOf);
		contentPane.add(btnBack);
		contentPane.add(btnBackToMain);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(184, 91, 1031, 413);
		contentPane.add(scrollPane);
		
		BorrowedTable = new JTable();
		BorrowedTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				try
				{
					int row = BorrowedTable.getSelectedRow();
					String bookID = (BorrowedTable.getModel().getValueAt(row, 0)).toString();
					
					BOOKID = bookID;
					System.out.println(BOOKID);
					ViewborrowController vbc = new ViewborrowController();
					PreparedStatement pst = connection.prepareStatement(vbc.selectBook());
					ResultSet rs = pst.executeQuery();
					while (rs.next())
					{
						BookIDField.setText(rs.getString("BookID"));
					}
					pst.close();
					rs.close();
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
		scrollPane.setViewportView(BorrowedTable);
		
		try
		{
			ViewborrowController vbc = new ViewborrowController();
			pst = connection.prepareStatement(vbc.requestgetbookBorrowed());
			rs = pst.executeQuery();
			BorrowedTable.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		JButton btnDelete = new JButton("Cancel Register");
		btnDelete.setFont(new Font("Arial", Font.PLAIN, 15));
		btnDelete.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
					CancelborrowController cbc = new CancelborrowController();
					PreparedStatement pst = connection.prepareStatement(cbc.requestcancelBorrow());
					pst.execute();
					JOptionPane.showMessageDialog(null, "Cancel successful");
					pst.close();
				} catch (Exception f)
				{
					f.printStackTrace();
				}
				refreshTable();
				if (connection != null)
				{
					try
					{
						connection.close();
					} catch (Exception j)
					{
						j.printStackTrace();
					}
				}
			}
		});
		btnDelete.setBounds(17, 267, 155, 23);
		contentPane.add(btnDelete);
		
		JLabel lblBookid = new JLabel("BookID");
		lblBookid.setFont(new Font("Arial", Font.PLAIN, 15));
		lblBookid.setBounds(17, 218, 56, 15);
		contentPane.add(lblBookid);
		
		BookIDField = new JTextField();
		BookIDField.setFont(new Font("Arial", Font.PLAIN, 15));
		BookIDField.setBounds(17, 235, 155, 21);
		contentPane.add(BookIDField);
		BookIDField.setColumns(10);
		
		QuantityField = new JTextField();
		QuantityField.setFont(new Font("Arial", Font.PLAIN, 15));
		QuantityField.setBounds(1099, 516, 116, 21);
		contentPane.add(QuantityField);
		QuantityField.setColumns(10);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setFont(new Font("Arial", Font.PLAIN, 15));
		lblQuantity.setBounds(1034, 519, 56, 15);
		contentPane.add(lblQuantity);
		
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
	}
}
