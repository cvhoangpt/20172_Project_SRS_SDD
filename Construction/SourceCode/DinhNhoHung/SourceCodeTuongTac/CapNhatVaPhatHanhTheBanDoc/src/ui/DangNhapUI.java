package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import io.FileFactory;
import model.DangNhap;
import service.DangNhapService;

public class DangNhapUI extends JFrame {
	JTextField txtUserName;
	JPasswordField txtPassWord;
	JButton btnLogin,btnExit;
	JCheckBox chkSave;
	
	public DangNhapUI(String title) {
		super(title);
		addControls();
		addEvents();
		hienThiLaiThongTinDangNhap();
	}

	private void hienThiLaiThongTinDangNhap() {
		File f=new File("login.data");
		if(f.exists()) {
			Object data=FileFactory.readData("login.data");
			if(data!=null) {
				DangNhap dn=(DangNhap) data;
				txtUserName.setText(dn.getUserNamee());
				txtPassWord.setText(dn.getPassWordd());
				chkSave.setSelected(true);
			}
		}
	}

	private void addEvents() {
		btnLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				xuLyDangNhap();
			}
		});
	}

	protected void xuLyDangNhap() {
		DangNhapService dnService=new DangNhapService();
		DangNhap dn=dnService.login(txtUserName.getText(), txtPassWord.getText());
		if (dn!=null) {
			
			if (chkSave.isSelected()) {
				FileFactory.saveData(dn, "login.data");
				
			}
			else {
				FileFactory.saveData(null, "login.data");
			}
			
			dispose();
			MainUI ui=new MainUI("Quản lý thông tin bạn đọc");
			ui.showWindow();
		}
		else {
			JOptionPane.showMessageDialog(null, "Đăng nhập thất bại");
		}
		
		
	}

	private void addControls() {
		// TODO Auto-generated method stub
		Container con=getContentPane();
		con.setLayout(new BorderLayout());
		JPanel pnNorth=new JPanel();
		con.add(pnNorth,BorderLayout.NORTH);
		JPanel pnCenter=new JPanel();
		con.add(pnCenter,BorderLayout.CENTER);
		JPanel pnSouth=new JPanel();
		con.add(pnSouth,BorderLayout.SOUTH);
		JLabel lblTitle =new JLabel("Đăng nhập hệ thống");
		lblTitle.setFont(new Font("tahoma", Font.BOLD, 15));
		lblTitle.setForeground(Color.BLUE);
		pnNorth.add(lblTitle);
		
		pnCenter.setLayout(new BorderLayout());
		JPanel pnImage=new JPanel();
		JLabel lblIcon=new JLabel(new ImageIcon("images/248928.png"));
		pnImage.add(lblIcon);
		pnCenter.add(pnImage,BorderLayout.WEST);
		
		JPanel pnUser=new JPanel();
		pnUser.setLayout(new BoxLayout(pnUser, BoxLayout.Y_AXIS));
		pnCenter.add(pnUser, BorderLayout.CENTER);
		JPanel pnUserName=new JPanel();
		pnUserName.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblUserName=new JLabel("Tài khoản:");
		txtUserName=new JTextField(20);
		pnUserName.add(lblUserName);
		pnUserName.add(txtUserName);
		pnUser.add(pnUserName);
		
		JPanel pnPassWord=new JPanel();
		pnPassWord.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblPassWord=new JLabel("Mật khẩu:");
		txtPassWord=new JPasswordField(20);
		pnPassWord.add(lblPassWord);
		pnPassWord.add(txtPassWord);
		pnUser.add(pnPassWord);
		
		JPanel pnSave=new JPanel();
		chkSave=new JCheckBox("Lưu thông tin đăng nhập");
		pnSave.add(chkSave);
		pnUser.add(pnSave);
		
		btnLogin=new JButton("Đăng nhập");
		btnExit=new JButton("     Thoát    ");
		
		pnSouth.add(btnLogin);
		pnSouth.add(btnExit);
		
		TitledBorder borderUser=new TitledBorder(BorderFactory.createLineBorder(Color.red),"Thông tin đăng nhập");
		
		pnUser.setBorder(borderUser);
		
		btnLogin.setIcon(new ImageIcon("images/login.png"));
		btnExit.setIcon(new ImageIcon("images/out.png"));
		
		lblPassWord.setPreferredSize(lblUserName.getPreferredSize());
		
		
	}
	public void showWindow() {
		this.setSize(460,270);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
