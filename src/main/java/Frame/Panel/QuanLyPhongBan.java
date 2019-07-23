package Frame.Panel;

import DTO.PhongBanDTO;
import Utils.SingletonDaoUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;

public class QuanLyPhongBan extends JPanel {
    public QuanLyPhongBan() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(QuanLyPhongBan.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(QuanLyPhongBan.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(QuanLyPhongBan.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(QuanLyPhongBan.class.getName()).log(Level.SEVERE, null, ex);
        }
        initComponents();
        addControls();
        loadDataToTblPhongBan();
        this.setSize(1200, 600);
    }

    public void initComponents() {
        lblId = new JLabel("Mã phòng ban");
        lblName = new JLabel("Tên phòng ban");
        lblSearch = new JLabel("Tìm kiếm");

        txtId = new JTextField();
        txtName = new JTextField();
        txtSearch = new JTextField();

        btnCancel = new JButton("Bỏ qua");
        btnDelete = new JButton("Xóa");
        btnEdit = new JButton("Sửa");
        btnExport = new JButton("Kết xuất");
        btnNew = new JButton("Thêm mới");
        btnSave = new JButton("Lưu");
        btnNext = new JButton("Tìm tiếp");

        btnCancel.setIcon(new ImageIcon("src/Image/icon-cancel.png"));
        btnDelete.setIcon(new ImageIcon("src/Image/icon-delete.png"));
        btnEdit.setIcon(new ImageIcon("src/Image/icon-edit.png"));
        btnNew.setIcon(new ImageIcon("src/Image/icon-new.png"));
        btnSave.setIcon(new ImageIcon("src/Image/icon-save.png"));

        btnCancel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnDelete.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEdit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnNew.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnExport.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSave.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnNext.setCursor(new Cursor(Cursor.HAND_CURSOR));

        modelTalDepartment = new DefaultTableModel();
        modelTalDepartment.setColumnIdentifiers(new Object[] {"STT", "Mã phòng ban","Tên phòng ban"});
        tblDepartment = new JTable(modelTalDepartment);
        scTalDepartment = new JScrollPane(tblDepartment, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        txtId.setPreferredSize(new Dimension(150, 25));
        txtName.setPreferredSize(new Dimension(300, 25));

        txtSearch.setPreferredSize(new Dimension(200, 25));
    }

    public void addControls() {
        JPanel pnDetail = new JPanel(new BorderLayout(0, 10));

        JPanel pnDetailItem = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        gbc.insets = new Insets(5, 10, 5, 10);

        gbc.ipadx = 50;
        gbc.ipady = 5;

        gbc.gridx = 0; gbc.gridy = 0;
        pnDetailItem.add(lblId, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        pnDetailItem.add(lblName, gbc);

        gbc.ipadx = 200;
        gbc.ipady = 5;

        gbc.gridx = 1; gbc.gridy = 0;
        pnDetailItem.add(txtId, gbc);

        gbc.gridx = 1; gbc.gridy = 1;
        pnDetailItem.add(txtName, gbc);

        JPanel pnButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        pnButtons.add(btnNew);
        pnButtons.add(btnEdit);
        pnButtons.add(btnEdit);
        pnButtons.add(btnDelete);
        pnButtons.add(btnSave);
        pnButtons.add(btnCancel);

        pnDetail.add(pnDetailItem, BorderLayout.CENTER);
        pnDetail.add(pnButtons, BorderLayout.SOUTH);

        JPanel pnTable = new JPanel(new BorderLayout());

        JPanel pnSearch = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        pnSearch.add(lblSearch);
        pnSearch.add(txtSearch);
        pnSearch.add(btnNext);

        pnTable.add(pnSearch, BorderLayout.NORTH);
        pnTable.add(scTalDepartment);

        this.setLayout(new BorderLayout(0, 10));
        this.add(pnDetail, BorderLayout.NORTH);
        this.add(pnTable, BorderLayout.CENTER);;
    }

    public void loadDataToTblPhongBan() {
        phongBanDTOList = SingletonDaoUtil.getPhongBanDaoImpl().getAll();
        modelTalDepartment.setRowCount(0);
        int i = 1;
        for (PhongBanDTO item : phongBanDTOList) {
            modelTalDepartment.addRow(new Object[] {
                i, item.getMapb(), item.getTenpb()
            });
            i++;
        }
    }

    //<editor-fold desc="COMPONENT">
    List<PhongBanDTO> phongBanDTOList = new ArrayList<>();

    JLabel lblId, lblName, lblSearch;
    JTextField txtId, txtName, txtSearch;
    JButton btnNext, btnNew, btnEdit, btnDelete, btnCancel, btnSave, btnExport;
    JTable tblDepartment;
    DefaultTableModel modelTalDepartment;
    JScrollPane scTalDepartment;
    //</editor-fold>
}
