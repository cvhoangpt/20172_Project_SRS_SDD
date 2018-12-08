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
import javax.swing.JList;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;

public class GiaoDienTimKiemSach extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GiaoDienTimKiemSach frame = new GiaoDienTimKiemSach();
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
	public GiaoDienTimKiemSach() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 461, 485);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBchKhoa = new JLabel("B\u00E1ch Khoa");
		lblBchKhoa.setForeground(new Color(255, 0, 0));
		lblBchKhoa.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblBchKhoa.setBounds(30, 11, 84, 32);
		contentPane.add(lblBchKhoa);
		
		textField = new JTextField();
		textField.setBounds(132, 20, 164, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnTmKimNhanh = new JButton("T\u00ECm ki\u1EBFm nhanh");
		btnTmKimNhanh.setBounds(306, 19, 105, 23);
		contentPane.add(btnTmKimNhanh);
		
		JLabel lblKeyWork = new JLabel("Keyword");
		lblKeyWork.setBounds(72, 93, 42, 14);
		contentPane.add(lblKeyWork);
		
		JLabel lblVuiLngChn = new JLabel("Vui l\u00F2ng \u0111i\u1EC1n v\u00E0o \u00EDt nh\u1EA5t m\u1ED9t l\u1EF1a ch\u1ECDn \u0111\u1EC3 t\u00ECm ki\u1EBFm");
		lblVuiLngChn.setForeground(new Color(32, 178, 170));
		lblVuiLngChn.setBounds(93, 54, 243, 14);
		contentPane.add(lblVuiLngChn);
		
		JLabel lblTnSch = new JLabel("T\u00EAn s\u00E1ch");
		lblTnSch.setBounds(68, 130, 46, 14);
		contentPane.add(lblTnSch);
		
		JLabel lblNewLabel = new JLabel("T\u00E1c gi\u1EA3");
		lblNewLabel.setBounds(80, 167, 34, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNgyPhtHnh = new JLabel("N\u0103m ph\u00E1t h\u00E0nh");
		lblNgyPhtHnh.setBounds(41, 208, 73, 14);
		contentPane.add(lblNgyPhtHnh);
		
		textField_1 = new JTextField();
		textField_1.setBounds(132, 90, 204, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(132, 127, 204, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(132, 164, 204, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(132, 205, 204, 20);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblCh = new JLabel("Ch\u1EE7 \u0111\u1EC1");
		lblCh.setBounds(80, 255, 34, 14);
		contentPane.add(lblCh);
		
		JCheckBox chckbxTiuThuyt = new JCheckBox("ti\u1EC3u thuy\u1EBFt");
		chckbxTiuThuyt.setBounds(130, 251, 79, 23);
		contentPane.add(chckbxTiuThuyt);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("khoa h\u1ECDc");
		chckbxNewCheckBox.setBounds(211, 251, 69, 23);
		contentPane.add(chckbxNewCheckBox);
		
		JCheckBox chckbxGioDc = new JCheckBox("gi\u00E1o d\u1EE5c");
		chckbxGioDc.setBounds(132, 277, 65, 23);
		contentPane.add(chckbxGioDc);
		
		JCheckBox chckbxDuLch = new JCheckBox("du l\u1ECBch");
		chckbxDuLch.setBounds(211, 277, 55, 23);
		contentPane.add(chckbxDuLch);
		
		JCheckBox chckbxKNngSng = new JCheckBox("k\u1EF9 n\u0103ng s\u1ED1ng");
		chckbxKNngSng.setBounds(292, 251, 89, 23);
		contentPane.add(chckbxKNngSng);
		
		JCheckBox chckbxThiuNhi = new JCheckBox("thi\u1EBFu nhi");
		chckbxThiuNhi.setBounds(292, 277, 97, 23);
		contentPane.add(chckbxThiuNhi);
		
		JCheckBox chckbxKinhT = new JCheckBox("kinh t\u1EBF");
		chckbxKinhT.setBounds(132, 303, 65, 23);
		contentPane.add(chckbxKinhT);
		
		JCheckBox chckbxVinTng = new JCheckBox("vi\u1EC5n t\u01B0\u1EDFng");
		chckbxVinTng.setBounds(211, 303, 79, 23);
		contentPane.add(chckbxVinTng);
		
		JCheckBox chckbxManga = new JCheckBox("manga");
		chckbxManga.setBounds(292, 303, 65, 23);
		contentPane.add(chckbxManga);
		
		JLabel lblNhSch = new JLabel("Nh\u00E0 s\u00E1ch");
		lblNhSch.setBounds(68, 338, 46, 14);
		contentPane.add(lblNhSch);
		
		JRadioButton rdbtnThiH = new JRadioButton("Th\u00E1i H\u00E0");
		rdbtnThiH.setBounds(132, 334, 65, 23);
		contentPane.add(rdbtnThiH);
		
		JRadioButton rdbtnNhNam = new JRadioButton("Nh\u00E3 Nam");
		rdbtnNhNam.setBounds(211, 334, 69, 23);
		contentPane.add(rdbtnNhNam);
		
		JRadioButton rdbtnKimng = new JRadioButton("Kim \u0110\u1ED3ng");
		rdbtnKimng.setBounds(132, 360, 77, 23);
		contentPane.add(rdbtnKimng);
		
		JRadioButton rdbtnAlphabook = new JRadioButton("Alphabook");
		rdbtnAlphabook.setBounds(211, 360, 85, 23);
		contentPane.add(rdbtnAlphabook);
		
		JButton btnTmKim = new JButton("T\u00ECm ki\u1EBFm");
		btnTmKim.setBounds(132, 412, 89, 23);
		contentPane.add(btnTmKim);
	}
}
