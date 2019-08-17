package Frame.Manager.QuanLyTaiSan;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Entity.NhanVien;
import Entity.PhongBan;
import Frame.Login.LoginFrame;
import Utils.SingletonDaoUtil;

public class PopupNhanVienChooser extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTable tblPopupTable;
	public JButton btnPopupConfirm;
	private DefaultTableModel modelPopupTable;
	private List<NhanVien> nhanVienBelongToPB = new ArrayList<NhanVien>();
	private HashMap<String, NhanVien> hmNhanVienBelongToPB = new HashMap<String, NhanVien>();

	public PopupNhanVienChooser() {
		initComponents();
		addControls();
		addEvents();
		showWindow();
	}

	private void showWindow() {
		this.setSize(400, 400);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		this.add(new JScrollPane(tblPopupTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.NORTH);
		this.add(btnPopupConfirm, BorderLayout.SOUTH);
		this.pack();
		LoginFrame.mainFrame.quanLyTaiSan.test.setEnabled(false);
	}

	private void initComponents() {

		btnPopupConfirm = new JButton("OK");

		Object[] columnNames = { "MANV", "TENNV", "ISSELECTED" };
		Object[][] data = {};

		modelPopupTable = new DefaultTableModel(data, columnNames);
		tblPopupTable = new JTable(modelPopupTable) {
			private static final long serialVersionUID = 1L;
			@SuppressWarnings({ "unchecked", "rawtypes" })
			@Override
			public Class getColumnClass(int column) {
				switch (column) {
					case 0:
					case 1:
						return String.class;
					default:
						return Boolean.class;
				}
			}
		};
	}

	private void addEvents() {
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				LoginFrame.mainFrame.quanLyTaiSan.test.setEnabled(true);
				closeFrame();
			}
		});
	}

	public void closeFrame() {
		this.dispose();
	}

	private void addControls() {

	}

	public void loadDataUserOfPhongBan(PhongBan phongban, String nguoiSuDung) {
		nhanVienBelongToPB.clear();
		hmNhanVienBelongToPB.clear();
		nhanVienBelongToPB = SingletonDaoUtil.getNhanVienDaoImpl().getByPhongBan(phongban);
		modelPopupTable.setRowCount(0);
		HashMap<String, String> output = new HashMap<String, String>();
		if (nguoiSuDung.isEmpty()) {
			output.clear();
		} else {
			output = checkUsersAreUsingAssets(nguoiSuDung);
		}
		for (NhanVien nv : nhanVienBelongToPB) {
			hmNhanVienBelongToPB.put(nv.getId(), nv);
			modelPopupTable.addRow(new Object[] { nv.getId(), nv.getTenNhanVien(), output.containsKey(nv.getId()) });
		}
	}

	public HashMap<String, String> checkUsersAreUsingAssets(String input) {
		HashMap<String, String> result = new HashMap<String, String>();
		String[] temp = input.split("\n");
		for (int i = 0; i < temp.length; i++) {
			result.put(temp[i].substring(0, 7), temp[i]);
		}
		return result;
	}

	public String selectedUsersToString() {
		String nguoisudung = "";
		int tblRowCount = tblPopupTable.getRowCount();
		for (int i = 0; i < tblRowCount; i++) {
			if (nhanVienBelongToPB.size() > 0) {
				NhanVien nvCheck = nhanVienBelongToPB.get(i);
				if ((boolean) tblPopupTable.getValueAt(i, 2)) {
					nguoisudung += nvCheck.getId() + " - " + nvCheck.getTenNhanVien() + "\n";
				}
			}
		}
		if (!nguoisudung.isEmpty()) {
			nguoisudung = nguoisudung.substring(0, nguoisudung.length() - 1);
		}
		return nguoisudung;
	}

}
