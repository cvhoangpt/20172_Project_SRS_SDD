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

import model.NguoiDoc;
import model.NhomNguoiDoc;
import service.NguoiDocService;
import service.NhomNguoiDocService;

public class NguoiDocUI extends JDialog {
	JTextField txtMa,txtTen,txtMaNhom,txtDiaChi;
	JButton btnLuu,btnThoat;
	
	
	public NguoiDocUI(String title) {
		this.setTitle(title);
		addControls();
		addEvents();
	}

	private void addEvents() {
		btnThoat.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnLuu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				xuLyLuuNguoiDoc();
				
			}
		});
	}

	protected void xuLyLuuNguoiDoc() {
		try {
			NguoiDoc nguoi=new NguoiDoc();
			nguoi.setMa(txtMa.getText());
			nguoi.setTen(txtTen.getText());
			nguoi.setMaNhom(txtMaNhom.getText());
			nguoi.setDiaChi(txtDiaChi.getText());
			NguoiDocService nguoiService=new NguoiDocService();
			int x=nguoiService.luuNguoiMoi(nguoi);
			if(x>0) {
				JOptionPane.showMessageDialog(null,"Thêm bạn đọc mới thành công");
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
		JLabel lblTitle=new JLabel("Nhập mới thông tin bạn đọc");
		pnTitle.add(lblTitle);
		con.add(pnTitle);
		
		JPanel pnMa=new JPanel();
		JLabel lblMa=new JLabel("Mã:");
		txtMa=new JTextField(15);
		pnMa.add(lblMa);
		pnMa.add(txtMa);
		con.add(pnMa);
		JPanel pnTen=new JPanel();
		JLabel lblTen=new JLabel("Tên:");
		txtTen=new JTextField(15);
		pnTen.add(lblTen);
		pnTen.add(txtTen);
		con.add(pnTen);
		
		JPanel pnMaNhom=new JPanel();
		JLabel lblMaNhom=new JLabel("Mã nhóm:");
		txtMaNhom=new JTextField(15);
		pnMaNhom.add(lblMaNhom);
		pnMaNhom.add(txtMaNhom);
		con.add(pnMaNhom);
		
		JPanel pnDiaChi=new JPanel();
		JLabel lblDiaChi=new JLabel("Địa chỉ:");
		txtDiaChi=new JTextField(15);
		pnDiaChi.add(lblDiaChi);
		pnDiaChi.add(txtDiaChi);
		con.add(pnDiaChi);
		
		JPanel pnButton=new JPanel();
		btnLuu=new JButton("Lưu");
		btnThoat=new JButton("Thoát");
		pnButton.add(btnLuu);
		pnButton.add(btnThoat);
		con.add(pnButton);
		
		lblDiaChi.setPreferredSize(lblMaNhom.getPreferredSize());
		lblMa.setPreferredSize(lblMaNhom.getPreferredSize());
		lblTen.setPreferredSize(lblMaNhom.getPreferredSize());
	}
	public void showWindow() {
		this.setSize(400, 300);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setModal(true);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
