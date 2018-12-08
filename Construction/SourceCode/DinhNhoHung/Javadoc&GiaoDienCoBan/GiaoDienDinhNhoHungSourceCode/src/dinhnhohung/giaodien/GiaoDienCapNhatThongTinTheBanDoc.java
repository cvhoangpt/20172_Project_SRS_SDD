package dinhnhohung.giaodien;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class GiaoDienCapNhatThongTinTheBanDoc extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GiaoDienCapNhatThongTinTheBanDoc frame = new GiaoDienCapNhatThongTinTheBanDoc();
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
	public GiaoDienCapNhatThongTinTheBanDoc() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 423);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBchKhoa = new JLabel("Bách Khoa");
		lblBchKhoa.setForeground(Color.RED);
		lblBchKhoa.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblBchKhoa.setBounds(26, 11, 105, 35);
		contentPane.add(lblBchKhoa);
		
		JLabel lblCpNhtThng = new JLabel("Cập nhật thông tin thẻ bạn đọc");
		lblCpNhtThng.setForeground(new Color(0, 191, 255));
		lblCpNhtThng.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCpNhtThng.setBounds(167, 24, 215, 14);
		contentPane.add(lblCpNhtThng);
		
		JLabel lblHcVTn = new JLabel("Học và tên");
		lblHcVTn.setBounds(73, 80, 52, 14);
		contentPane.add(lblHcVTn);
		
		JLabel lblaCh = new JLabel("Địa Chỉ");
		lblaCh.setBounds(89, 129, 34, 14);
		contentPane.add(lblaCh);
		
		JLabel lblNgySinh = new JLabel("Ngày Sinh");
		lblNgySinh.setBounds(73, 179, 52, 14);
		contentPane.add(lblNgySinh);
		
		JLabel lblGiiTnh = new JLabel("Giới tính");
		lblGiiTnh.setBounds(79, 225, 46, 14);
		contentPane.add(lblGiiTnh);
		
		textField = new JTextField();
		textField.setBounds(163, 77, 154, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(163, 126, 154, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(163, 176, 154, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JRadioButton rdbtnNam = new JRadioButton("Nam");
		rdbtnNam.setBounds(159, 221, 52, 23);
		contentPane.add(rdbtnNam);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Nữ");
		rdbtnNewRadioButton.setBounds(223, 221, 52, 23);
		contentPane.add(rdbtnNewRadioButton);
		
		JLabel lblGhiCh = new JLabel("Ghi chú");
		lblGhiCh.setBounds(85, 273, 46, 14);
		contentPane.add(lblGhiCh);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(318, 273, -147, 17);
		contentPane.add(textArea);
		
		textField_3 = new JTextField();
		textField_3.setBounds(167, 270, 150, 51);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnCpNht = new JButton("Cập nhật");
		btnCpNht.setBounds(82, 350, 89, 23);
		contentPane.add(btnCpNht);
		
		JButton btnXaDLiu = new JButton("Xóa dữ liệu");
		btnXaDLiu.setBounds(186, 350, 89, 23);
		contentPane.add(btnXaDLiu);
		
		JButton btnThot = new JButton("Thoát");
		btnThot.setBounds(285, 350, 89, 23);
		contentPane.add(btnThot);
	}
}
