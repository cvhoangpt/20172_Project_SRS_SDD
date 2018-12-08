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
import javax.swing.JButton;

public class GiaoDienPhatHanhThe extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GiaoDienPhatHanhThe frame = new GiaoDienPhatHanhThe();
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
	public GiaoDienPhatHanhThe() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 524, 413);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBchKhoa = new JLabel("B\u00E1ch Khoa");
		lblBchKhoa.setForeground(new Color(255, 0, 0));
		lblBchKhoa.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblBchKhoa.setBounds(36, 11, 92, 28);
		contentPane.add(lblBchKhoa);
		
		JLabel lblTrngiHc = new JLabel("Tr\u01B0\u1EDDng \u0110\u1EA1i h\u1ECDc B\u00E1ch Khoa H\u00E0 N\u1ED9i c\u1EA5p ph\u00E1t th\u1EBB th\u01B0 vi\u1EC7n cho sinh vi\u00EAn");
		lblTrngiHc.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTrngiHc.setForeground(new Color(0, 191, 255));
		lblTrngiHc.setBounds(67, 50, 402, 24);
		contentPane.add(lblTrngiHc);
		
		JLabel lblThngTinChung = new JLabel("TH\u00D4NG TIN CHUNG");
		lblThngTinChung.setForeground(new Color(0, 191, 255));
		lblThngTinChung.setBackground(new Color(230, 230, 250));
		lblThngTinChung.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblThngTinChung.setBounds(36, 131, 119, 24);
		contentPane.add(lblThngTinChung);
		
		textField = new JTextField();
		textField.setBounds(159, 86, 158, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnTmKimId = new JButton("T\u00ECm ki\u1EBFm ID");
		btnTmKimId.setBounds(36, 85, 89, 23);
		contentPane.add(btnTmKimId);
		
		JLabel lblHVTn = new JLabel("H\u1ECD v\u00E0 t\u00EAn");
		lblHVTn.setBounds(81, 168, 47, 14);
		contentPane.add(lblHVTn);
		
		textField_1 = new JTextField();
		textField_1.setBounds(161, 165, 156, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblCmnd = new JLabel("CMND");
		lblCmnd.setBounds(99, 204, 29, 14);
		contentPane.add(lblCmnd);
		
		textField_2 = new JTextField();
		textField_2.setBounds(159, 201, 158, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblHKhu = new JLabel("H\u1ED9 kh\u1EA9u");
		lblHKhu.setBounds(89, 242, 39, 14);
		contentPane.add(lblHKhu);
		
		textField_3 = new JTextField();
		textField_3.setBounds(159, 239, 158, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNgySinh = new JLabel("Ng\u00E0y sinh");
		lblNgySinh.setBounds(81, 281, 47, 14);
		contentPane.add(lblNgySinh);
		
		textField_4 = new JTextField();
		textField_4.setBounds(159, 278, 158, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnToTh = new JButton("T\u1EA1o th\u1EBB");
		btnToTh.setBounds(159, 323, 89, 23);
		contentPane.add(btnToTh);
		
		JButton btnThot = new JButton("Tho\u00E1t");
		btnThot.setBounds(258, 323, 89, 23);
		contentPane.add(btnThot);
	}

}
