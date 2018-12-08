package frameGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class SearchInfo extends JFrame {
	Connection cn;
    Statement stmt = null;
    ResultSet rs = null;
    PreparedStatement ptmt = null;
    DefaultTableModel model;
	private JPanel Searchinfo;
	private JTextField SearchText;
	private JTable Table;
	private JButton btnThot;
	
	public void getConnection() {
		try {
			cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/database", "root", "1chapnhandi");
			System.out.println("connect success");
			
		}
		catch(Exception ex){
			ex.printStackTrace();
			System.out.println("fail");
		}
		
	}
	public void clearTable(){
        int rowCount = model.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
    }
	public void search(String text){
		model = (DefaultTableModel) Table.getModel();
		clearTable();
        try {
            stmt = cn.createStatement();
            rs = stmt.executeQuery("select * from borrowlist where id like '"+text+"'");
            while(rs.next()){
                    model.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3)});
                }
        } catch (SQLException ex) {
            System.out.println("cant load");
        }
    }
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					SearchInfo frame = new SearchInfo();
					frame.getConnection();
					frame.setTitle("Search Info");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public SearchInfo() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 375, 445);
		Searchinfo = new JPanel();
		Searchinfo.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(Searchinfo);
		
		JButton ViewButton = new JButton("xem");
		ViewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = SearchText.getText();
				System.out.println(""+text);
				search(text);
			}
		});
		
		SearchText = new JTextField();
		SearchText.setColumns(10);
		
		Table = new JTable();
		
		Table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"New column", "New column", "New column"
			}
		));
		
		JLabel lblIdSinhVin = new JLabel("ID Sinh Viên");
		
		btnThot = new JButton("Thoát");
		GroupLayout gl_Searchinfo = new GroupLayout(Searchinfo);
		gl_Searchinfo.setHorizontalGroup(
			gl_Searchinfo.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_Searchinfo.createSequentialGroup()
					.addGroup(gl_Searchinfo.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_Searchinfo.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblIdSinhVin)
							.addGap(62)
							.addComponent(SearchText, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_Searchinfo.createSequentialGroup()
							.addContainerGap()
							.addComponent(ViewButton)
							.addGap(193)
							.addComponent(btnThot))
						.addGroup(gl_Searchinfo.createSequentialGroup()
							.addGap(24)
							.addComponent(Table, GroupLayout.PREFERRED_SIZE, 282, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(269, Short.MAX_VALUE))
		);
		gl_Searchinfo.setVerticalGroup(
			gl_Searchinfo.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_Searchinfo.createSequentialGroup()
					.addContainerGap(42, Short.MAX_VALUE)
					.addGroup(gl_Searchinfo.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblIdSinhVin)
						.addComponent(SearchText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(44)
					.addGroup(gl_Searchinfo.createParallelGroup(Alignment.BASELINE)
						.addComponent(ViewButton)
						.addComponent(btnThot))
					.addGap(34)
					.addComponent(Table, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
					.addGap(120))
		);
		Searchinfo.setLayout(gl_Searchinfo);
	}
}
