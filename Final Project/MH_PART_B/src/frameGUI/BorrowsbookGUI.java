package frameGUI;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.Driver;
import com.mysql.cj.xdevapi.Statement;

import entity.Book;
import entity.SinhVien;

import java.awt.Font;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.SystemColor;

public class BorrowsbookGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;
	private JTable table_1;
	Connection 	cn= null;
    Statement stmt = null;
    ResultSet rs = null;
    PreparedStatement ptmt = null;
    DefaultTableModel model;
    private JTextField textField_2;
	public Connection getConnection() {
		try {
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/database", "root", "1chapnhandi");
			System.out.println("connect success");
			
		}
		catch(Exception ex){
			ex.printStackTrace();
			System.out.println("fail");
		}
		return cn;
		
	}
	public ArrayList<SinhVien>lds(){
		ArrayList<SinhVien>lstSinhVien = new ArrayList<SinhVien>();
		try {
			java.sql.Statement st =  cn.createStatement();
			String sql="SELECT * FROM `database`.`id sinh viên`";
			ResultSet rs= ((java.sql.Statement) st).executeQuery(sql);
			SinhVien sv;
			while (rs.next()) {
				sv = new SinhVien(rs.getString("Tên sinh viên"),rs.getInt("ID sinh Viên"));
				lstSinhVien.add(sv);
				
			}
		}
		catch(Exception ex1){
			ex1.printStackTrace();
		}
		return lstSinhVien;
	}
	public void showlistSV() {
		String colTieuDe[]= new String[] {"id","name"};
		ArrayList<SinhVien>lstSinhVien = lds();
		DefaultTableModel model = new DefaultTableModel(colTieuDe ,0 );
		Object[]row;
		for(int i = 0 ; i  <lstSinhVien.size() ; i++) 
		{
			row = new Object[3];
			// Gán giá trị 
			row[0]= lstSinhVien.get(i).getIdsinhvien();
			row[1]= lstSinhVien.get(i).getTensinhvien();
			
			//add
			model.addRow(row);
			table.setModel(model);
		}
	}
	public void View(){
        clearTable();
        try{
            stmt = (Statement) cn.createStatement();
            rs = ((java.sql.Statement) stmt).executeQuery("select * from borrowlist");
            while (rs.next()) {
                /// hien thi tren table
                model.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3)}); 
            }
        }catch(Exception ex){}
    }
    public void clearTable(){
        int rowCount = model.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
    }
    public void search(){
        clearTable(); //dọn bảng
        String text = textField_1.getText();
        if(text.equals("")){
           View();
        }else
        try {
            stmt = (Statement) cn.createStatement();
            rs = ((java.sql.Statement) stmt).executeQuery("select * from borrowlist where id like '"+text+"'");
            while(rs.next()){
                    model.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3)});
                }
        } catch (SQLException ex) {
            System.out.println("cant load");
        }
    }
	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the frame.
	 */
	
	public BorrowsbookGUI() {
		showlistSV();
		getConnection();
		
		//tạo frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 754, 616);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setForeground(Color.DARK_GRAY);
		panel.setToolTipText("Library System");
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Th\u00F4ng tin sinh vi\u00EAn", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.activeCaption));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Cho m\u01B0\u1EE3n s\u00E1ch ", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.activeCaption));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 330, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
							.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 351, GroupLayout.PREFERRED_SIZE))
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 719, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
					.addGap(7)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 461, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_2, 0, 0, Short.MAX_VALUE))
					.addGap(24))
		);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
		
		JLabel lblDanhSchMn = new JLabel("Danh sách mượn mới");
		
		JButton btnNewButton = new JButton("Đồng ý");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnNewButton_1 = new JButton("Từ chối");
		
		JLabel lblTnSch = new JLabel("tên sách");
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(33)
					.addComponent(btnNewButton)
					.addPreferredGap(ComponentPlacement.RELATED, 131, Short.MAX_VALUE)
					.addComponent(btnNewButton_1)
					.addGap(41))
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(table_1, GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(107)
					.addComponent(lblDanhSchMn)
					.addContainerGap(133, Short.MAX_VALUE))
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTnSch)
					.addPreferredGap(ComponentPlacement.RELATED, 136, Short.MAX_VALUE)
					.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(66))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblDanhSchMn)
					.addGap(27)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblTnSch)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(96)
					.addComponent(table_1, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
					.addGap(27)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_1))
					.addContainerGap(64, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);
		
		JLabel lblTnSinhVin = new JLabel("Tên sinh viên");
		
		JLabel lblId = new JLabel("ID");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JButton btnXem = new JButton("Xem ");
		btnXem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				search();
			}
		});
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap(180, Short.MAX_VALUE)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(27)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(lblId, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTnSinhVin)
								.addComponent(btnXem))
							.addPreferredGap(ComponentPlacement.RELATED, 118, Short.MAX_VALUE)))
					.addGap(110))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(table, GroupLayout.PREFERRED_SIZE, 272, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(94, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(53)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTnSinhVin))
					.addGap(29)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblId, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnXem)
					.addGap(18)
					.addComponent(table, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(120, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		JLabel lblLibrarySystem = new JLabel("Library System");
		lblLibrarySystem.setEnabled(false);
		lblLibrarySystem.setFont(new Font("Arial", Font.BOLD, 30));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap(259, Short.MAX_VALUE)
					.addComponent(lblLibrarySystem, GroupLayout.PREFERRED_SIZE, 239, GroupLayout.PREFERRED_SIZE)
					.addGap(221))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(22)
					.addComponent(lblLibrarySystem)
					.addContainerGap(25, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		panel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{lblLibrarySystem}));
		contentPane.setLayout(gl_contentPane);
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BorrowsbookGUI frame = new BorrowsbookGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}

