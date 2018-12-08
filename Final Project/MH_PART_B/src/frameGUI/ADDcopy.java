package frameGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import entity.Book;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ADDcopy extends JFrame {

	private JPanel contentPane;
	Connection con = null;
	Statement  st= null;
	private JTable table;
	private JTextField txtten;
	private JTextField txtsoluon;
	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/database", "root", "1chapnhandi");
		System.out.println("connect success");
		}catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("fail");
		}
		return con ;
		
	}
	public ArrayList<Book>layDanhSach(){
		ArrayList<Book>lstBook = new ArrayList<Book>();
		try {
			st = con.createStatement();
			String sql="SELECT*FROM book";
			ResultSet rs= st.executeQuery(sql);
			Book bk;
			while (rs.next()) {
				bk = new Book(rs.getInt("idbook"), rs.getString("bookname"),rs.getInt("soluong"));
				lstBook.add(bk);
			}
		}
		catch(Exception ex1){
			ex1.printStackTrace();
		}
		return lstBook;
	}
	public void showlistbook() {
		String colTieuDe[]= new String[] {"idbook","bookname","soluong"};
		
		ArrayList<Book>lstBook = layDanhSach();
		DefaultTableModel model = new DefaultTableModel(colTieuDe ,0 );
		Object[]row;
		for(int i = 0 ; i  <lstBook.size() ; i++) 
		{
			row = new Object[4];
		
			
			row[0]= lstBook.get(i).getidbook();
			row[1]= lstBook.get(i).getbookname();
			row[2]= lstBook.get(i).getsoluong();
			//add
			model.addRow(row);
		
		}
		table.setModel(model);
	}
	

	// Tạo giao diện 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ADDcopy frame = new ADDcopy();
					frame.showlistbook();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ADDcopy() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 996, 522);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(0, 0, 0)));
		
		JPanel panel_1 = new JPanel();
		
		JPanel panel_2 = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 960, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 316, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addContainerGap())
		);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
					int i = table.getSelectedRow();
					TableModel model = table.getModel();
					txtten.setText(model.getValueAt(i, 0).toString());
					txtsoluon.setText(model.getValueAt(i, 1).toString());
				
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"idbook", "bookname", "soluong"
			}
		));
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
					.addGap(20)
					.addComponent(table, GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
					.addGap(22)
					.addComponent(table, GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);
		
		JLabel lblPhiuThmBn = new JLabel("Phiếu thêm bản sao ");
		
		JButton btnThm = new JButton("Thêm");
		btnThm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection cn = getConnection();
				try {
					st=con.createStatement();
					
					String query = "INSERT INTO book(idbook,bookname, soluong) VALUES('"+txtten.getText()+"','"+txtsoluon.getText()+"')";
					st.executeQuery(query);
					showlistbook();
				}
				catch(Exception ex){
					ex.printStackTrace();
				}
			}
		});
		
		txtten = new JTextField();
		txtten.setColumns(10);
		
		JLabel lblTnSch = new JLabel("Tên sách ");
		
		JLabel lblSLng = new JLabel("số lượng");
		
		txtsoluon = new JTextField();
		txtsoluon.setColumns(10);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap(114, Short.MAX_VALUE)
					.addComponent(lblPhiuThmBn)
					.addGap(105))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(44)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblTnSch)
						.addComponent(lblSLng))
					.addPreferredGap(ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(txtsoluon, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtten, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(52))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(123)
					.addComponent(btnThm)
					.addContainerGap(134, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(27)
					.addComponent(lblPhiuThmBn)
					.addGap(42)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblTnSch)
						.addComponent(txtten, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(24)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblSLng)
						.addComponent(txtsoluon, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(75)
					.addComponent(btnThm)
					.addContainerGap(109, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		JLabel lblNewLabel = new JLabel("THÊM BẢN SAO ");
		lblNewLabel.setEnabled(false);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 30));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(355)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 278, GroupLayout.PREFERRED_SIZE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(20)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}

}
