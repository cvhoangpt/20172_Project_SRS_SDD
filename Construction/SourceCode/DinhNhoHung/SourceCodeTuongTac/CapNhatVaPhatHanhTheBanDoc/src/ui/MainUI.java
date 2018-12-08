package ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;

import com.mysql.jdbc.Driver;

import model.NguoiDoc;
import model.NhomNguoiDoc;
import service.NguoiDocService;
import service.NhomNguoiDocService;

public class MainUI extends JFrame {
	DefaultTableModel dtmNguoiDoc;
	JTable tblNguoiDoc;
	
	DefaultMutableTreeNode root;
	JTree treeNhom;
	
	JTextField txtMa,txtTen,txtMaNhom,txtDiaChi;
	
	JButton btnThemMoi,btnLuu,btnXoa;
	
	JMenuItem mnuNew,mnuEdit,mnuXoa;
	JPopupMenu popup;
	
	ArrayList<NhomNguoiDoc>dsNhomNguoiDoc;
	NhomNguoiDocService nhomService;
	NhomNguoiDoc selectedNhom;
	
	ArrayList<NguoiDoc>dsNguoiDoc;
	NguoiDocService ndService;
	
	NguoiDoc selectedND,saveND;
	Connection conn=null;
	
	
	public MainUI(String title) {
		super(title);
		addControls();
		ketNoi();
		addEvents();
		hienThiToanBoNhomNguoiDocLenCay();
		
	}
	
	private void ketNoi() {
		try {
			String strlConn="jdbc:mysql://localhost/csdlthongtinnguoidoc?useUnicode=true&characterEncoding=utf-8";
			Properties pro=new Properties();
			pro.put("user", "root");
			pro.put("password", "");
			Driver driver=new Driver();
			conn=driver.connect(strlConn, pro);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	public void hienThiToanBoNhomNguoiDocLenCay() {
		if (nhomService==null) {
			nhomService=new NhomNguoiDocService();
			dsNhomNguoiDoc=nhomService.layToanBoNhom();
			root.removeAllChildren();
			for(NhomNguoiDoc nhom:dsNhomNguoiDoc) {
				DefaultMutableTreeNode nodeNhom=new DefaultMutableTreeNode(nhom);
				root.add(nodeNhom);
			}
			treeNhom.expandRow(0);
		}
	}

	



	private void addEvents() {
		btnXoa.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				xuLyXoaNguoiDoc();
			}
		});
		
		mnuNew.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				NhomUI ui=new NhomUI("Thêm một nhóm mới");
				ui.showWindow();
				hienThiToanBoNhomNguoiDocLenCay();
				treeNhom.updateUI();
			}
		});
		mnuXoa.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				xuLyXoaNhom();
			}
		});
		
		
		treeNhom.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				if(e.isPopupTrigger()) {
					int row=treeNhom.getClosestRowForLocation(e.getX(), e.getY());
					treeNhom.setSelectionRow(row);
							
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				DefaultMutableTreeNode nodeSelected =(DefaultMutableTreeNode) treeNhom.getLastSelectedPathComponent();
				if(nodeSelected==null)return;
				if(nodeSelected.getLevel()==0)return;
				if(ndService==null)
					ndService=new NguoiDocService();
				selectedNhom = (NhomNguoiDoc) nodeSelected.getUserObject();
				if(selectedNhom!=null)
				dsNguoiDoc=ndService.layToanBoNguoiDocTheoMa(selectedNhom.getMaNhom());
				hienThiNguoiDocLenTable();
			}
		});
		tblNguoiDoc.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int row=tblNguoiDoc.getSelectedRow();
				if(row==-1)return;
				selectedND=dsNguoiDoc.get(row);
				txtMa.setText(selectedND.getMa());
				txtTen.setText(selectedND.getTen());
				txtMaNhom.setText(selectedND.getMaNhom());
				txtDiaChi.setText(selectedND.getDiaChi());
				txtMa.setEditable(false);
			}
			
		});
		btnThemMoi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				NguoiDocUI ui=new NguoiDocUI("Thêm mới bạn đọc");
				ui.showWindow();
				
				
			}
			
		});
		btnLuu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				xuLyLuuThongTin();
			}
		});
		
	}





	protected void xuLyLuuThongTin() {
		try {
			String sql="update nguoidoc set ten=?,manhom=?,diachi=? where ma=?";
			PreparedStatement preStatement=conn.prepareStatement(sql);
			preStatement.setString(1, txtTen.getText());
			preStatement.setString(2, txtMaNhom.getText());
			preStatement.setString(3, txtDiaChi.getText());
			
			preStatement.setString(4, txtMa.getText());
			
			
			
			
			int x=preStatement.executeUpdate();
			if(x>0) {
				JOptionPane.showMessageDialog(null, "Cập nhật thông tin tài sản thành công");
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	protected void xuLyXoaNhom() {
		DefaultMutableTreeNode nodeSelected =(DefaultMutableTreeNode) treeNhom.getLastSelectedPathComponent();
		if(nodeSelected==null)return;
		if(nodeSelected.getLevel()==0)return;
		if(nhomService==null)
			nhomService=new NhomNguoiDocService();
		selectedNhom = (NhomNguoiDoc) nodeSelected.getUserObject();
		if (nhomService.xoaNhomNguoiDoc(selectedNhom)>0) {
			JOptionPane.showMessageDialog(null, "Xóa nhóm bạn đọc thành công");
			if(selectedNhom!=null)
				dsNhomNguoiDoc=nhomService.layToanBoNhom();
				hienThiToanBoNhomNguoiDocLenCay();
			
			
		}
		

	}
	protected void xuLyXoaNguoiDoc() {
		if(selectedND==null)return;
		int ret=JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa dữ liệu của người này ?","Hãy suy nghĩ kĩ !",JOptionPane.YES_NO_CANCEL_OPTION);
		if(ret==JOptionPane.YES_OPTION) {
			if(ndService==null)
				ndService=new NguoiDocService();
			if(ndService.xoaNguoiDoc(selectedND)>0)
			{
				if(selectedNhom!=null)
					dsNguoiDoc=ndService.layToanBoNguoiDocTheoMa(selectedNhom.getMaNhom());
					hienThiNguoiDocLenTable();
			}
		}
	}





	public void hienThiNguoiDocLenTable() {
		dtmNguoiDoc.setRowCount(0);
		for(NguoiDoc nd:dsNguoiDoc) {
			Vector<Object>vec =new Vector<Object>();
			vec.add(nd.getMa());
			vec.add(nd.getTen());
			vec.add(nd.getDiaChi());
			dtmNguoiDoc.addRow(vec);
		}
	}





	private void addControls() {
		Container con=getContentPane();
		con.setLayout(new BorderLayout());
		JPanel pnLeft=new JPanel();
		pnLeft.setPreferredSize(new Dimension(300, 0));
		JPanel pnRight=new JPanel();
		JSplitPane sp=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,pnLeft,pnRight);
		con.add(sp,BorderLayout.CENTER);
		
		pnLeft.setLayout(new BorderLayout());
		root=new DefaultMutableTreeNode("Danh sách nhóm");
		treeNhom=new JTree(root);
		JScrollPane scTree=new JScrollPane(treeNhom,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnLeft.add(scTree,BorderLayout.CENTER);
		
		pnRight.setLayout(new BorderLayout());
		JPanel pnTopOfRight=new JPanel();
		pnTopOfRight.setLayout(new BorderLayout());
		pnTopOfRight.setPreferredSize(new Dimension(0, 500));
		JPanel pnBottomOfRight=new JPanel();
		JSplitPane spNguoiDoc=new JSplitPane(JSplitPane.VERTICAL_SPLIT,pnTopOfRight,pnBottomOfRight);
		pnRight.add(spNguoiDoc,BorderLayout.CENTER);
		
		dtmNguoiDoc=new DefaultTableModel();
		dtmNguoiDoc.addColumn("Mã bạn đọc");
		dtmNguoiDoc.addColumn("Tên bạn đọc");
		
		dtmNguoiDoc.addColumn("Địa chỉ");
		tblNguoiDoc=new JTable(dtmNguoiDoc);
		JScrollPane scTable=new JScrollPane(tblNguoiDoc,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnTopOfRight.add(scTable,BorderLayout.CENTER);
		
		pnBottomOfRight.setLayout(new BoxLayout(pnBottomOfRight, BoxLayout.Y_AXIS));
		
		JPanel pnMa=new JPanel();
		pnMa.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblMa=new JLabel("Mã bạn đọc:");
		txtMa=new JTextField(30);
		pnMa.add(lblMa);
		pnMa.add(txtMa);
		pnBottomOfRight.add(pnMa);
		
		JPanel pnTen=new JPanel();
		pnTen.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblTen=new JLabel("Tên bạn đọc:");
		txtTen=new JTextField(30);
		pnTen.add(lblTen);
		pnTen.add(txtTen);
		pnBottomOfRight.add(pnTen);
		
		JPanel pnMaNhom=new JPanel();
		pnMaNhom.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblMaNhom=new JLabel("Mã nhóm:");
		txtMaNhom=new JTextField(30);
		pnMaNhom.add(lblMaNhom);
		pnMaNhom.add(txtMaNhom);
		pnBottomOfRight.add(pnMaNhom);
		
		JPanel pnDiaChi=new JPanel();
		pnDiaChi.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblDiaChi=new JLabel("Địa chỉ:");
		txtDiaChi=new JTextField(30);
		pnDiaChi.add(lblDiaChi);
		pnDiaChi.add(txtDiaChi);
		pnBottomOfRight.add(pnDiaChi);
		
		lblMa.setPreferredSize(lblTen.getPreferredSize());
		lblDiaChi.setPreferredSize(lblTen.getPreferredSize());
		lblMaNhom.setPreferredSize(lblTen.getPreferredSize());
		
		JPanel pnButton=new JPanel();
		pnButton.setLayout(new FlowLayout(FlowLayout.RIGHT));
		btnThemMoi=new JButton("Thêm mới");
		btnLuu=new JButton("     Lưu    ");
		btnXoa=new JButton("     Xóa    ");
		pnButton.add(btnThemMoi);
		pnButton.add(btnLuu);
		pnButton.add(btnXoa);
		pnBottomOfRight.add(pnButton);
		
		btnLuu.setIcon(new ImageIcon("images/save.png"));
		btnXoa.setIcon(new ImageIcon("images/delete.png"));
		btnThemMoi.setIcon(new ImageIcon("images/button_white_add.png"));
		
		setupMenu();
	}




	private void setupMenu() {
		mnuNew=new JMenuItem("Thêm nhóm mới");
		mnuNew.setIcon(new ImageIcon("images/add_nhom.png"));
		mnuEdit=new JMenuItem("Sửa thông tin nhóm");
		mnuEdit.setIcon(new ImageIcon("images/edit_nhom.png"));
		mnuXoa=new JMenuItem("Xóa nhóm");
		mnuXoa.setIcon(new ImageIcon("images/delete_nhom.png"));
		popup=new JPopupMenu();
		popup.add(mnuNew);
		popup.addSeparator();
		popup.add(mnuEdit);
		popup.addSeparator();
		popup.add(mnuXoa);
	}





	public void showWindow() {
//		this.setSize(800,600);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
