package ui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.NhomNguoiDoc;
import service.NhomNguoiDocService;

public class NhomUI extends JDialog {
	JTextField txtMaNhom,txtTenNhom;
	JButton btnLuu,btnThoat;
	
	
	public NhomUI(String title) {
		this.setTitle(title);
		addControls();
		addEvents();
	}

	private void addEvents() {
		btnThoat.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				MainUI ui=new MainUI("");
				ui.treeNhom.updateUI();
			}
		});
		btnLuu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				xuLyLuuNhom();
				
				
			}
		});
	}

	protected void xuLyLuuNhom() {
		try {
			NhomNguoiDoc nhom=new NhomNguoiDoc();
			nhom.setMaNhom(txtMaNhom.getText());
			nhom.setTenNhom(txtTenNhom.getText());
			NhomNguoiDocService nhomService=new NhomNguoiDocService();
			int x=nhomService.luuNhomMoi(nhom);
			if(x>0) {
				JOptionPane.showMessageDialog(null,"Thêm nhóm thành công");
			}
			
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	private void addControls() {
		Container con=getContentPane();
		con.setLayout(new BoxLayout(con, BoxLayout.Y_AXIS));
		JPanel pnTitle=new JPanel();
		JLabel lblTitle=new JLabel("Nhập mới thông tin nhóm");
		pnTitle.add(lblTitle);
		con.add(pnTitle);
		
		JPanel pnMaNhom=new JPanel();
		JLabel lblMaNhom=new JLabel("Mã nhóm:");
		txtMaNhom=new JTextField(15);
		pnMaNhom.add(lblMaNhom);
		pnMaNhom.add(txtMaNhom);
		con.add(pnMaNhom);
		JPanel pnTenNhom=new JPanel();
		JLabel lblTenNhom=new JLabel("Tên nhóm:");
		txtTenNhom=new JTextField(15);
		pnTenNhom.add(lblTenNhom);
		pnTenNhom.add(txtTenNhom);
		con.add(pnTenNhom);
		
		JPanel pnButton=new JPanel();
		btnLuu=new JButton("Lưu");
		btnThoat=new JButton("Thoát");
		pnButton.add(btnLuu);
		pnButton.add(btnThoat);
		con.add(pnButton);
	}
	public void showWindow() {
		this.setSize(400, 300);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setModal(true);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
