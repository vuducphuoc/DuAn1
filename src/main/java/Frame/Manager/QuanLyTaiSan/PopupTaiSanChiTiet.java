package Frame.Manager.QuanLyTaiSan;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import Contant.CoreConstant;
import Entity.*;
import Frame.Login.LoginFrame;
import Frame.Main.MainFrame;
import Utils.DialogUtils;
import Utils.SingletonDaoUtil;
import org.apache.log4j.Logger;

import com.toedter.calendar.JDateChooser;


public class PopupTaiSanChiTiet extends JFrame {

	private static final long serialVersionUID = 1L;

	private JLabel lblTemp1, lblTemp2, lblPhongBan, lblNguoiSuDung;
	private JLabel lblPhanLoai, lblNhaSanXuat, lblTenTaiSan, lblMaTaiSan,
			lblMoTa, lblNguyenGia, lblTiLeKhauHao, lblThoiGianKhauHao,
			lblTimKiem, lblNgayBDSuDung, lblTimKiemNhaSanXuat;
	private JTextField txtTenTaiSan, txtMaTaiSan, txtNguyenGia,
			txtTiLeKhauHao, txtThoiGianKhauHao, txtTimKiem,
			txtTimKiemNhaSanXuat, txtNgayBDSuDung;
	private JTextArea txtNguoiSuDung;
	private JComboBox<Object> cbxNhaSanXuat, cbxPhanLoai, cbxPhongBan;
	private JTextArea txaMoTa;
	private JScrollPane scMoTa;
	private JButton btnEscape, btnSave;
	private JDateChooser dateNgayBDSuDung;
	private JButton btnShowMoreUsers;
	// ArrayList
	private List<PhongBan> listPhongBan = new ArrayList<>();
	private HashMap<String, PhongBan> hmPhongBan = new HashMap<String, PhongBan>();
	private List<PhanLoai> listPhanLoai = new ArrayList<PhanLoai>();
	private List<NhaSanXuat> listNhaSanXuat = new ArrayList<>();

	public TaiSan taiSan = new TaiSan();

	public PopupTaiSanChiTiet() {
		initComponents();
		addControls();
		addEvents();
		showWindow();
	}

	private void showWindow() {
		this.setSize(780, 330);
		this.setResizable(false);
		this.setDefaultCloseOperation(PopupTaiSanChiTiet.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}

	private void initComponents() {
		lblPhanLoai = new JLabel("Phân Loại");
		lblNhaSanXuat = new JLabel("Nhà sản xuất");
		lblTenTaiSan = new JLabel("Tên tài sản");
		lblMaTaiSan = new JLabel("Mã tài sản");
		lblMoTa = new JLabel("Mô tả");
		lblNguyenGia = new JLabel("Nguyên giá");
		lblTiLeKhauHao = new JLabel("Tỷ lệ khấu hao");
		lblThoiGianKhauHao = new JLabel("Thời gian khấu hao");
		lblTimKiem = new JLabel("Tìm kiếm");
		lblNgayBDSuDung = new JLabel("Ngày sử dụng");
		lblTimKiemNhaSanXuat = new JLabel("Tìm kiếm");
		lblTemp1 = new JLabel("% / năm");
		lblTemp2 = new JLabel("năm");
		lblPhongBan = new JLabel("Phòng ban");
		lblNguoiSuDung = new JLabel("Người sử dụng");

		lblPhanLoai.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
		lblTenTaiSan.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
		lblMaTaiSan.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
		lblNhaSanXuat.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
		lblNguyenGia.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
		lblMoTa.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
		lblTiLeKhauHao.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
		lblThoiGianKhauHao.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
		lblNgayBDSuDung.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
		lblTimKiem.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
		lblTimKiemNhaSanXuat.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
		lblTemp1.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
		lblTemp2.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
		lblPhongBan.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
		lblNguoiSuDung.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));

		txtTenTaiSan = new JTextField();
		txtMaTaiSan = new JTextField();
		txtNguyenGia = new JTextField();
		txtTiLeKhauHao = new JTextField();
		txtThoiGianKhauHao = new JTextField();
		txtNgayBDSuDung = new JTextField();
		txtTimKiem = new JTextField();
		txtTimKiemNhaSanXuat = new JTextField();
		txtNguoiSuDung = new JTextArea();
		dateNgayBDSuDung = new JDateChooser();

		dateNgayBDSuDung.setDateFormatString("dd/MM/yyyy");

		btnShowMoreUsers = new JButton("...");

		txtTenTaiSan.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
		txtMaTaiSan.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
		txtNguyenGia.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
		txtTiLeKhauHao.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
		txtThoiGianKhauHao.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
		txtTimKiem.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
		txtTimKiemNhaSanXuat.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
		txtNguoiSuDung.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));

		cbxNhaSanXuat = new JComboBox<Object>();
		cbxPhanLoai = new JComboBox<Object>();
		cbxPhongBan = new JComboBox<Object>();

		cbxNhaSanXuat.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
		cbxPhanLoai.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
		cbxPhongBan.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));

		txaMoTa = new JTextArea();
		txaMoTa.setLineWrap(true);
		scMoTa = new JScrollPane(txaMoTa, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		txaMoTa.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));

		btnSave = new JButton("Lưu");
		btnEscape = new JButton("Bỏ qua");

		btnEscape.setIcon(new ImageIcon((String) CoreConstant.iconButton[4]));
		btnSave.setIcon(new ImageIcon((String) CoreConstant.iconButton[3]));

		btnEscape.setFocusPainted(false);
		btnSave.setFocusPainted(false);

		txtMaTaiSan.setEnabled(false);
		txtTiLeKhauHao.setEnabled(false);
		txtNguoiSuDung.setEnabled(false);
	}

	public void showFrame() {
		MainFrame.quanLyTaiSan.setEnabled(false);
		this.setVisible(true);
	}

	private void addControls() {
		/*
		 * Panel chi tiết
		 */
		JPanel pnChiTiet = new JPanel(new GridBagLayout());
		pnChiTiet.setBackground(Color.WHITE);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(5, 10, 5, 10);

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0;
		pnChiTiet.add(lblPhanLoai, gbc);
		gbc.gridy = 1;
		pnChiTiet.add(lblMaTaiSan, gbc);
		gbc.gridy = 2;
		pnChiTiet.add(lblTenTaiSan, gbc);
		gbc.gridy = 3;
		pnChiTiet.add(lblNgayBDSuDung, gbc);
		gbc.gridy = 4;
		pnChiTiet.add(lblNhaSanXuat, gbc);
		gbc.gridy = 5;
		pnChiTiet.add(lblPhongBan, gbc);
		gbc.gridy = 6;
		pnChiTiet.add(lblNguyenGia, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 1;
		pnChiTiet.add(cbxPhanLoai, gbc);
		gbc.gridy = 1;
		pnChiTiet.add(txtMaTaiSan, gbc);
		gbc.gridy = 2;
		pnChiTiet.add(txtTenTaiSan, gbc);
		gbc.gridy = 3;
		pnChiTiet.add(dateNgayBDSuDung, gbc);
		gbc.gridy = 4;
		pnChiTiet.add(cbxNhaSanXuat, gbc);
		gbc.gridy = 5;
		pnChiTiet.add(cbxPhongBan, gbc);
		gbc.gridy = 6;
		pnChiTiet.add(txtNguyenGia, gbc);

		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.weightx = 0;
		pnChiTiet.add(lblThoiGianKhauHao, gbc);
		gbc.gridy = 1;
		pnChiTiet.add(lblTiLeKhauHao, gbc);
		gbc.gridy = 2;
		pnChiTiet.add(lblMoTa, gbc);
		gbc.gridy = 4;
		pnChiTiet.add(lblNguoiSuDung, gbc);

		gbc.gridx = 3;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.gridwidth = 1;
		pnChiTiet.add(txtThoiGianKhauHao, gbc);
		gbc.gridy = 1;
		pnChiTiet.add(txtTiLeKhauHao, gbc);
		gbc.gridy = 2;
		gbc.gridheight = 2;
		gbc.gridwidth = 1;
		pnChiTiet.add(scMoTa, gbc);

		gbc.gridy = 4;
		gbc.gridheight = 3;
		gbc.gridwidth = 1;
		pnChiTiet.add(new JScrollPane(txtNguoiSuDung), gbc);
		gbc.gridx = 4;
		gbc.gridy = 5;
		gbc.gridheight = 1;
		gbc.weightx = 0;
		pnChiTiet.add(btnShowMoreUsers, gbc);
		gbc.gridx = 4;
		gbc.gridy = 0;
		pnChiTiet.add(lblTemp2, gbc);
		gbc.gridx = 4;
		gbc.gridy = 1;
		pnChiTiet.add(lblTemp1, gbc);

		/*
		 * Panel button
		 */
		JPanel pnButton = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pnButton.setBackground(Color.WHITE);
		pnButton.add(btnSave);
		pnButton.add(btnEscape);

		/*
		 * Panel content
		 */
		JPanel pnContent = new JPanel(new BorderLayout(0, 10));
		pnContent.setBackground(Color.WHITE);
		pnContent.add(pnChiTiet, BorderLayout.CENTER);
		pnContent.add(pnButton, BorderLayout.SOUTH);

		this.add(pnContent);
	}

	private void addEvents() {

		loadDataToCbx();

		btnShowMoreUsers.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				final PopupNhanVienChooser frameNhanVienChooser = new PopupNhanVienChooser();
				Logger.getLogger(PopupNhanVienChooser.class.getName()).warn(cbxPhongBan.getSelectedItem());
				frameNhanVienChooser.loadDataUserOfPhongBan(
						hmPhongBan.get(String.valueOf(cbxPhongBan.getSelectedItem())),
						txtNguoiSuDung.getText());
				frameNhanVienChooser.setVisible(true);
				frameNhanVienChooser.btnPopupConfirm.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						txtNguoiSuDung.setText(frameNhanVienChooser.selectedUsersToString());
						LoginFrame.mainFrame.quanLyTaiSan.test.setEnabled(true);
						frameNhanVienChooser.dispose();
					}
				});
			}
		});

		btnSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			if (validator()) {
				if (txtMaTaiSan.getText().isEmpty()) {
					TaiSan model = getModel();
					model.setId(createID());

					taiSan = SingletonDaoUtil.getTaiSanDaoImpl().save(model);
					saveSuccess();
				} else {
					TaiSan model = getModel();
					taiSan = SingletonDaoUtil.getTaiSanDaoImpl().update(model);
					saveSuccess();
				}
			}
			}
		});

		btnEscape.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				LoginFrame.mainFrame.setEnabled(true);
				closeFrame();
			}
		});

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				LoginFrame.mainFrame.setEnabled(true);
				closeFrame();
			}
		});

		txtThoiGianKhauHao.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				if (txtThoiGianKhauHao.getText().matches("^[0-9]*\\.{0,1}[0-9]*$")) {
					if (txtThoiGianKhauHao.getText().length() > 0) {
						double thoigiankhauhao = Double.parseDouble(txtThoiGianKhauHao.getText());
						double thoigian = 100 / thoigiankhauhao;
						double tylekhauhao = (double) Math.round(thoigian * 100) / 100;
						txtTiLeKhauHao.setText("" + tylekhauhao);
					} else {
						txtTiLeKhauHao.setText("");
					}
				}
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
                if (txtThoiGianKhauHao.getText().matches("^[0-9]*\\.{0,1}[0-9]*$")) {
                    if (txtThoiGianKhauHao.getText().length() > 0) {
                        double thoigiankhauhao = Double.parseDouble(txtThoiGianKhauHao.getText());
                        double thoigian = 100 / thoigiankhauhao;
                        double tylekhauhao = (double) Math.round(thoigian * 100) / 100;
                        txtTiLeKhauHao.setText("" + tylekhauhao);
                    } else {
                        txtTiLeKhauHao.setText("");
                    }
                }
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
                if (txtThoiGianKhauHao.getText().matches("^[0-9]*\\.{0,1}[0-9]*$")) {
                    if (txtThoiGianKhauHao.getText().length() > 0) {
                        double thoigiankhauhao = Double.parseDouble(txtThoiGianKhauHao.getText());
                        double thoigian = 100 / thoigiankhauhao;
                        double tylekhauhao = (double) Math.round(thoigian * 100) / 100;
                        txtTiLeKhauHao.setText("" + tylekhauhao);
                    } else {
                        txtTiLeKhauHao.setText("");
                    }
                }
			}
		});

		cbxPhongBan.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent itemEvent) {
				if (cbxPhongBan.getSelectedIndex() >= 0) {
					txtNguoiSuDung.setText(null);
				}
			}
		});
	}

	public void fillToForm(String id) {
		TaiSan taiSan = SingletonDaoUtil.getTaiSanDaoImpl().findById(id);
		setModel(taiSan);
	}

	public void setModel(TaiSan taiSan) {
		if (taiSan != null) {
			cbxPhanLoai.setSelectedIndex(taiSan.getPhanLoai().getId() - 1);
			txtMaTaiSan.setText(taiSan.getId());
			txtTenTaiSan.setText(taiSan.getTenTaiSan());
			dateNgayBDSuDung.setDate(null);

			for (NhaSanXuat item : listNhaSanXuat) {
				if (item.getId() == taiSan.getNhaSanXuat().getId()) {
					cbxNhaSanXuat.setSelectedIndex(listNhaSanXuat.indexOf(item));
				}
			}
			txtNguyenGia.setText(String.valueOf(taiSan.getNguyenGia()));
			txtTiLeKhauHao.setText(String.valueOf(taiSan.getTyLeKhauHao()));
			txtThoiGianKhauHao.setText(String.valueOf(taiSan.getThoiGianKhauHao()));
			txaMoTa.setText(taiSan.getMoTa());
		} else {
			cbxPhanLoai.setSelectedIndex(0);
			cbxNhaSanXuat.setSelectedIndex(0);
			cbxPhongBan.setSelectedIndex(0);

			txtMaTaiSan.setText("");
			txtTenTaiSan.setText("");
			txaMoTa.setText("");
			txtNguyenGia.setText("");
			txtTiLeKhauHao.setText("");
			txtThoiGianKhauHao.setText("");
			txtNgayBDSuDung.setText("");
			txtNguoiSuDung.setText("");
		}
	}

	public TaiSan getModel() {
		TaiSan taiSan = new TaiSan();
		taiSan.setPhanLoai((PhanLoai) cbxPhanLoai.getSelectedItem());
		taiSan.setId(txtMaTaiSan.getText());
		taiSan.setTenTaiSan(txtTenTaiSan.getText());
		taiSan.setNhaSanXuat((NhaSanXuat) cbxNhaSanXuat.getSelectedItem());
		taiSan.setNguyenGia(Integer.parseInt(txtNguyenGia.getText()));
		taiSan.setTyLeKhauHao(Double.parseDouble(txtTiLeKhauHao.getText()));
		taiSan.setThoiGianKhauHao(Integer.parseInt(txtThoiGianKhauHao.getText()));
		taiSan.setMoTa(txaMoTa.getText());
		return taiSan;
	}

	public void lockByRole() {
		if (LoginFrame.accountLogin.getVaiTro().getId() == CoreConstant.ROLE_RESOURCES) {
			txtNguyenGia.setEnabled(false);
			txtTiLeKhauHao.setEnabled(false);
			txtThoiGianKhauHao.setEnabled(false);
		} else {
			cbxPhanLoai.setEnabled(false);
			cbxNhaSanXuat.setEnabled(false);
			cbxPhongBan.setEnabled(false);

			txtMaTaiSan.setEnabled(false);
			txtTenTaiSan.setEnabled(false);
			txaMoTa.setEnabled(false);

			txtNgayBDSuDung.setEnabled(false);
			dateNgayBDSuDung.setEnabled(false);

			txtNguyenGia.setEnabled(true);
			txtTiLeKhauHao.setEnabled(false);
			txtThoiGianKhauHao.setEnabled(true);
		}
	}

	public void lockEdit() {
		cbxPhanLoai.setEnabled(false);
		cbxNhaSanXuat.setEnabled(false);
		cbxPhongBan.setEnabled(false);

		txtMaTaiSan.setEnabled(false);
		txtTenTaiSan.setEnabled(false);
		txaMoTa.setEnabled(false);

		txtNguyenGia.setEnabled(false);
		txtTiLeKhauHao.setEnabled(false);
		txtThoiGianKhauHao.setEnabled(false);


		dateNgayBDSuDung.setEnabled(false);
		txtNguoiSuDung.setEnabled(false);

		btnShowMoreUsers.setEnabled(false);
	}

	public void saveSuccess() {
		MainFrame.quanLyTaiSan.saveSuccess();
		LoginFrame.mainFrame.setEnabled(true);
		closeFrame();
	}

	public void closeFrame() {
		this.dispose();
	}

	public void removeControlBtns() {
		btnSave.setVisible(false);
	}

	public void loadDataToCbx() {

		cbxPhanLoai.removeAllItems();
		cbxNhaSanXuat.removeAllItems();
		cbxPhongBan.removeAllItems();

		listPhanLoai = SingletonDaoUtil.getPhanLoaiTaiSanDaoImpl().findAll();
		if (listPhanLoai.size() > 0) {
			for (PhanLoai item : listPhanLoai) {
				cbxPhanLoai.addItem(item);
			}
		}

		listNhaSanXuat = SingletonDaoUtil.getNhaSanXuatDaoImpl().findAll();
		if (listNhaSanXuat.size() > 0) {
			for (NhaSanXuat item : listNhaSanXuat) {
				cbxNhaSanXuat.addItem(item);
			}
		}

		listPhongBan = SingletonDaoUtil.getPhongBanDaoImpl().findAll();
		if (listPhongBan.size() > 0) {
			for (PhongBan item : listPhongBan) {
				cbxPhongBan.addItem(item);
				hmPhongBan.put(item.getTenPhongBan(), item);
			}
		}
	}

	private String createID() {
		String lastID = SingletonDaoUtil.getTaiSanDaoImpl().getLastID();

		if (lastID != null) {
			StringBuilder ID = new StringBuilder();
			ID.append("TS");

			int pathNumber = Integer.parseInt(lastID.substring(2)) + 1;

			int pathNumberLength = String.valueOf(pathNumber).length();

			for (int i = 0; i < 5 - pathNumberLength; i++) {
				ID.append("0");
			}

			ID.append(pathNumber);

			System.out.println(ID.toString());
			return ID.toString();
		} else {
			return "TS00001";
		}
	}

	public boolean validator () {
		String txtTenTS = txtTenTaiSan.getText();
		String txtGia = txtNguyenGia.getText();
		String txtTime = txtThoiGianKhauHao.getText();
		String ngay = dateNgayBDSuDung.getDateFormatString();

		if (txtTenTS.length() == 0) {
			DialogUtils.showMessageDialog("Tên tài sản không được trống!", CoreConstant.TYPE_WARNING);
			txtTenTaiSan.requestFocus();
			return false;
		}
		if (txtGia.length() == 0) {
			DialogUtils.showMessageDialog("Nguyên giá không được trống!", CoreConstant.TYPE_WARNING);
			txtNguyenGia.requestFocus();
			return false;
		}

		if (txtTime.length() == 0) {
			DialogUtils.showMessageDialog("Thời gian khấu hao không được trống!", CoreConstant.TYPE_WARNING);
			txtThoiGianKhauHao.requestFocus();
			return false;
		}

		if (ngay.length() == 0) {
			DialogUtils.showMessageDialog("Ngày bắt đầu không được trống!", CoreConstant.TYPE_WARNING);
			dateNgayBDSuDung.requestFocus();
			return false;
		}

		if (!txtGia.matches("^[0-9]*$")) {
			DialogUtils.showMessageDialog("Nguyên giá phải là số > 0!", CoreConstant.TYPE_WARNING);
			txtNguyenGia.requestFocus();
			return false;
		}

		if (!txtTime.matches("^[0-9]*\\.{0,1}[0-9]*$")) {
			DialogUtils.showMessageDialog("Thời gian khấu hao phải là số > 0!", CoreConstant.TYPE_WARNING);
			txtThoiGianKhauHao.requestFocus();
			return false;
		}

		return true;
	}

}
