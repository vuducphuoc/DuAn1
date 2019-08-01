package Frame.Panel;

import Contant.CoreConstant;
import Entity.PhieuBanGiao;
import Entity.PhongBan;
import Entity.TaiSan;
import Utils.SingletonDaoUtil;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QuanLyPhieuBanGiao extends JPanel {

    public QuanLyPhieuBanGiao() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(QuanLyTaiSan.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(QuanLyTaiSan.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(QuanLyTaiSan.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(QuanLyTaiSan.class.getName()).log(Level.SEVERE, null, ex);
        }
        open();
    }

    public void open() {
        this.setVisible(true);
        initComponent();
        addControls();
        addEvents();

        statusDefault();
    }

    public void initComponent() {
        lblTaiSan = new JLabel("Tài Sản");
        lblPhongBan = new JLabel("Phòng Ban");
        lblNguoiSuDung = new JLabel("Người Sử Dụng");
        lblNgayBatDau = new JLabel("Ngày Bàn Giao");

        chk1 = new JCheckBox("Chỉ hiển thị phòng ban có tài sản");

        cbxTaiSan = new JComboBox();
        cbxPhongBan = new JComboBox();

        txtNguoiSuDung = new JTextField();
        txtNgayBatDau = new JTextField();
        txtTimKiemPBG = new JTextField();

        //<editor-fold desc="BUTTON">
        btnCancel = new JButton("Bỏ qua");
        btnDelete = new JButton("Xóa", new ImageIcon("src/Image/icon-delete"));
        btnEdit = new JButton("Sửa");
        btnNew = new JButton("Thêm mới");
        btnSave = new JButton("Lưu");
        btnSearch = new JButton("Tìm tiếp");

        btnCancel.setIcon(new ImageIcon("src/Image/icon-cancel.png"));
        btnDelete.setIcon(new ImageIcon("src/Image/icon-delete.png"));
        btnEdit.setIcon(new ImageIcon("src/Image/icon-edit.png"));
        btnNew.setIcon(new ImageIcon("src/Image/icon-new.png"));
        btnSave.setIcon(new ImageIcon("src/Image/icon-save.png"));

        btnCancel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnDelete.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEdit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnNew.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSearch.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSave.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnDelete.setPreferredSize(btnNew.getPreferredSize());
        btnSearch.setPreferredSize(btnNew.getPreferredSize());
        btnSave.setPreferredSize(btnNew.getPreferredSize());
        btnCancel.setPreferredSize(btnNew.getPreferredSize());
        btnEdit.setPreferredSize(btnNew.getPreferredSize());
        //</editor-fold>

        //<editor-fold desc="TABLE PHÒNG BAN">
        modelTblPhongBan = new DefaultTableModel();
        modelTblPhongBan.setColumnIdentifiers(new Object[] {"STT", "TÊN PHÒNG BAN"});
        tblPhongBan = new JTable(modelTblPhongBan){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                JLabel c = (JLabel) super.prepareRenderer(renderer, row, column);
                c.setFont(new Font("Segoe UI", 0, 13));
                if (column == 0) {
                    c.setHorizontalAlignment(JLabel.CENTER);
                }else {
                    c.setHorizontalAlignment(JLabel.LEFT);
                }
                return c;
            }
        };

        /* Căn giữa header table */
        JTableHeader tblHeaderPhongBan = tblPhongBan.getTableHeader();
        ((DefaultTableCellRenderer) tblHeaderPhongBan.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        /* Chỉnh sửa kích thước từng cột */
        tblHeaderPhongBan.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblHeaderPhongBan.getColumnModel().getColumn(1).setPreferredWidth(280);

        /* Chỉnh sửa độ cao dòng */
        tblPhongBan.setRowHeight(20);

        /* Chỉnh sửa màu nền khi chọn */
        tblPhongBan.setSelectionBackground(Color.decode("#3a4d8f"));

        tblPhongBan.setIntercellSpacing(new Dimension(0, 0));
        tblPhongBan.setShowGrid(false);

        tblPhongBan.setRowSorter(new TableRowSorter(modelTblPhongBan));
        tblPhongBan.setAutoCreateRowSorter(true);
        scTblPhongBan = new JScrollPane(tblPhongBan, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        //</editor-fold>

        //<editor-fold desc="TABLE PHIẾU BÀN GIAO">
        modelTblPhieuBanGiao = new DefaultTableModel();
        modelTblPhieuBanGiao.setColumnIdentifiers(new Object[] {"STT", "Mã tài sản","Tên tài sản", "Người sử dụng", "Ngày bàn giao"});
        tblPhieuBanGiao = new JTable(modelTblPhieuBanGiao){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                JLabel c = (JLabel) super.prepareRenderer(renderer, row, column);
                c.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 13));
                c.setHorizontalAlignment(JLabel.CENTER);
                return c;
            }
        };

        JTableHeader tblHeaderTS = tblPhieuBanGiao.getTableHeader();
        ((DefaultTableCellRenderer) tblHeaderTS.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        tblHeaderTS.getColumnModel().getColumn(0).setPreferredWidth(30);
        tblHeaderTS.getColumnModel().getColumn(1).setPreferredWidth(100);
        tblHeaderTS.getColumnModel().getColumn(2).setPreferredWidth(200);
        tblHeaderTS.getColumnModel().getColumn(3).setPreferredWidth(200);
        tblHeaderTS.getColumnModel().getColumn(4).setPreferredWidth(100);

        tblPhieuBanGiao.setRowHeight(20);
        tblPhieuBanGiao.setSelectionBackground(Color.decode("#3a4d8f"));

        //<editor-fold desc="Hover table">
        //        DefaultTableCellRenderer renderer2 = new DefaultTableCellRenderer() {
//            @Override
//            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//                final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
//                if (!isSelected) {
//                    c.setBackground(row % 2 == 0 ? Color.decode("#e7e7e7") : Color.WHITE);
//                    c.setFont(new Font("Segoe UI", 0, 14));
//                }
//                return c;
//            }
//        };
//        renderer2.setHorizontalAlignment((int) JTable.CENTER_ALIGNMENT);
//        for (int i = 0; i < tblTaiSan.getColumnCount(); i++) {
//            tblTaiSan.setDefaultRenderer(tblTaiSan.getColumnClass(i), renderer2);
//        }
        //</editor-fold>

        tblPhieuBanGiao.setRowSorter(new TableRowSorter(modelTblPhieuBanGiao));
        tblPhieuBanGiao.setAutoCreateRowSorter(true);

        scTblPhieuBanGiao = new JScrollPane(tblPhieuBanGiao, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        //</editor-fold>

        //<editor-fold desc="Set Font Segoe UI">
        btnNew.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        btnEdit.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        btnDelete.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        btnCancel.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        btnSave.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        btnSearch.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));

        lblNgayBatDau.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 14));
        lblNguoiSuDung.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 14));
        lblTaiSan.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 14));
        lblPhongBan.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 14));

        cbxPhongBan.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 14));
        cbxTaiSan.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 14));
        txtNgayBatDau.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 14));
        txtNguoiSuDung.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 14));

        txtTimKiemPBG.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 13));

        chk1.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));

        txtTimKiemPBG.setPreferredSize(new Dimension(300, 25));
        //</editor-fold>
    }

    public void addControls() {
        // LEFT
        JPanel pnLeftTop = new JPanel();
        pnLeftTop.add(chk1);

        JPanel pnLeft = new JPanel(new BorderLayout());
        pnLeft.add(pnLeftTop, BorderLayout.NORTH);
        pnLeft.add(scTblPhongBan, BorderLayout.CENTER);

        JPanel pnRightTop = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 20, 5, 10);

        gbc.ipady = 8;

        gbc.gridx = 0; gbc.gridy = 0;
        pnRightTop.add(lblPhongBan, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        pnRightTop.add(lblTaiSan, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        pnRightTop.add(lblNguoiSuDung, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        pnRightTop.add(lblNgayBatDau, gbc);


        gbc.ipadx = 600;

        gbc.gridx = 1; gbc.gridy = 0;
        pnRightTop.add(cbxPhongBan, gbc);

        gbc.gridx = 1; gbc.gridy = 1;
        pnRightTop.add(cbxTaiSan, gbc);

        gbc.gridx = 1; gbc.gridy = 2;
        pnRightTop.add(txtNguoiSuDung, gbc);

        gbc.gridx = 1; gbc.gridy = 3;
        pnRightTop.add(txtNgayBatDau, gbc);

        JPanel pnButton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnButton.add(btnNew);
        pnButton.add(btnEdit);
        pnButton.add(btnDelete);
        pnButton.add(btnSave);
        pnButton.add(btnCancel);

        JPanel pnRightTp = new JPanel(new BorderLayout(0,10));
        pnRightTp.add(pnRightTop, BorderLayout.CENTER);
        pnRightTp.add(pnButton, BorderLayout.SOUTH);

        JPanel pnTKTaiSan = new JPanel(new BorderLayout());
        JPanel pnSearchEmployee = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        pnSearchEmployee.add(txtTimKiemPBG);
        pnSearchEmployee.add(btnSearch);
        JPanel pnExport = new JPanel(new FlowLayout(FlowLayout.RIGHT, 30, 10));

        JPanel pnTemp2 = new JPanel();
        pnTKTaiSan.add(pnSearchEmployee, BorderLayout.WEST);
        pnTKTaiSan.add(pnTemp2, BorderLayout.CENTER);
        pnTKTaiSan.add(pnExport, BorderLayout.EAST);

        JPanel pnTblTaiSan = new JPanel(new BorderLayout());
        pnTblTaiSan.add(pnTKTaiSan, BorderLayout.NORTH);
        pnTblTaiSan.add(scTblPhieuBanGiao, BorderLayout.CENTER);

        JPanel pnRight = new JPanel(new BorderLayout());
        pnRight.add(pnRightTp, BorderLayout.NORTH);
        pnRight.add(pnTblTaiSan, BorderLayout.CENTER);

        JPanel pnTitle = new JPanel();
        pnTitle.setPreferredSize(new Dimension(0, 5));

        this.setLayout(new BorderLayout(10, 10));
        this.add(pnTitle, BorderLayout.NORTH);
        this.add(pnLeft, BorderLayout.WEST);
        this.add(pnRight, BorderLayout.CENTER);

        pnLeft.setPreferredSize(new Dimension(340, 0));
    }

    public void addEvents() {

    }


    //<editor-fold desc="NEW EDIT DELETE SAVE CANCEL">
    public void setBtnNew () {
        clearForm();
        setStatus(CoreConstant.FLAG_INSERT);
        tblPhieuBanGiao.clearSelection();
    }

    public void setBtnEdit() {
        setStatus(CoreConstant.FLAG_UPDATE);
    }
    //</editor-fold>

    //<editor-fold desc="LOAD DATA">
    public void loadDataToCbxTaiSan() {
        List<TaiSan> taiSanList = SingletonDaoUtil.getTaiSanDaoImpl().findAll();
        cbxTaiSan.removeAllItems();

        if (taiSanList.size() > 0) {
            for (TaiSan item : taiSanList) {
                cbxTaiSan.addItem(item);
            }
        }
    }

    public void loadDataToCbxPhongBan() {
        List<PhongBan> phongBanList = SingletonDaoUtil.getPhongBanDaoImpl().findAll();
        cbxPhongBan.removeAllItems();

        if (phongBanList.size() > 0) {
            for (PhongBan item : phongBanList) {
                cbxPhongBan.addItem(item);
            }
        }
    }

    public void loadDataToTblPhongBan() {
        modelTblPhongBan.setRowCount(0);

        phongBanList = SingletonDaoUtil.getPhongBanDaoImpl().findAll();
        int stt = 1;

        for (PhongBan item : phongBanList) {
            modelTblPhongBan.addRow(new Object[] {
                stt, item.getTenpb()
            });
        }
    }

    public void loadDataToTblPhieuBanGiao(PhongBan phongBan) {
        modelTblPhieuBanGiao.setRowCount(0);

        if (phongBan != null) {
            phieuBanGiaoList = SingletonDaoUtil.getPhieuBanGiaoDaoImpl().getByPhongBan(phongBan);
            int stt = 1;

            for (PhieuBanGiao item : phieuBanGiaoList) {
                modelTblPhieuBanGiao.addRow(new Object[] {
                        stt, item.getTaiSan().getMats(), item.getTaiSan().getTents(), item.getNguoisudungs(), item.getNgaybangiao()
                });
            }
        }
    }
    //</editor-fold>

    /* Lấy phòng ban selected */
    public void getPBSelected (int i) {
        if (phongBanList.size() > 0) {
            tblPhongBan.setRowSelectionInterval(i, i);
            Rectangle rect = tblPhongBan.getCellRect(i, 0, true);
            tblPhongBan.scrollRectToVisible(rect);
            sttPhongBan = Integer.parseInt(tblPhongBan.getValueAt(i, 0).toString());
            pbSelected = phongBanList.get(sttPhongBan - 1);
        }
    }

    /* Trạng thái ban đầu */
    public void statusDefault() {
        flagSave = CoreConstant.FLAG_EMTY;

        btnNew.setVisible(true);
        btnEdit.setVisible(true);
        btnDelete.setVisible(true);

        btnSave.setVisible(false);
        btnCancel.setVisible(false);

        txtNguoiSuDung.setEditable(false);
        txtNgayBatDau.setEditable(false);
        cbxTaiSan.setEnabled(false);
        cbxPhongBan.setEnabled(false);
    }

    /* Thay đổi trạng thái */
    public void setStatus (int insertable) {
        flagSave = insertable;

        switch (insertable) {
            case CoreConstant.FLAG_INSERT:
                messCancel = "Bạn có muốn bỏ qua thao tác thêm mới không?";
                clearForm();
                btnChange();
                break;
            case CoreConstant.FLAG_UPDATE:
                messCancel = "Bạn có muốn bỏ qua thao tác cập nhật thông tin không?";
                btnChange();
                break;
            default:
                statusDefault();
                break;
        }
    }

    /* Xóa trắng Form */
    public void clearForm() {
        setModel(null);
    }

    /* Fill dữ liệu lên form */
    public void fillToForm (int i) {
        if (phieuBanGiaoList.size() > 0) {
            tblPhieuBanGiao.setRowSelectionInterval(i, i);
            Rectangle rect = tblPhieuBanGiao.getCellRect(i, 0, true);
            tblPhieuBanGiao.scrollRectToVisible(rect);

            sttPhieuBanGiao = Integer.parseInt(tblPhieuBanGiao.getValueAt(i, 0).toString());
            pbgSelected = phieuBanGiaoList.get(sttPhieuBanGiao-1);
            setModel(pbgSelected);
        } else {
            clearForm();
        }
    }

    public void btnChange() {
        btnSave.setVisible(true);
        btnNew.setVisible(false);
        btnEdit.setVisible(false);
        btnDelete.setVisible(false);
        btnCancel.setVisible(true);

        cbxTaiSan.setEditable(false);
        txtNguoiSuDung.setEditable(true);
        txtNgayBatDau.setEditable(true);
    }

    //<editor-fold desc="GET MODEL & SET MODEL">
    /* Set Model lên form */
    public void setModel(PhieuBanGiao phieuBanGiao) {
        if (phieuBanGiao != null) {
            cbxTaiSan.setSelectedItem(phieuBanGiao.getTaiSan());
            cbxPhongBan.setSelectedItem(pbSelected);
            txtNguoiSuDung.setText(phieuBanGiao.getNguoisudungs());
            txtNgayBatDau.setText(phieuBanGiao.getNgaybangiao());
        } else {
            cbxPhongBan.setSelectedIndex(0);
            cbxTaiSan.setSelectedIndex(0);
            txtNguoiSuDung.setText("");
            txtNgayBatDau.setText("");
        }
    }

    /* Lấy model của Phiếu Bàn Giao */
    public PhieuBanGiao getModel() {
        PhieuBanGiao phieuBanGiao = new PhieuBanGiao();
        phieuBanGiao.setNguoisudungs(txtNguoiSuDung.getText());
        phieuBanGiao.setNgaybangiao(txtNgayBatDau.getText());
        phieuBanGiao.setPhongBan(pbSelected);
        return phieuBanGiao;
    }
    //</editor-fold>

    //<editor-fold desc="Component">
    int flagSave;
    String messCancel;

    int sttPhongBan = -1;
    int sttPhieuBanGiao = -1;

    int indexPhongBan = 0;
    int indexPhieuBanGiao = 0;

    PhongBan pbSelected = new PhongBan();
    PhieuBanGiao pbgSelected = new PhieuBanGiao();

    List<PhieuBanGiao> phieuBanGiaoList = new ArrayList<>();
    List<PhongBan> phongBanList = new ArrayList<>();

    JTable tblPhongBan, tblPhieuBanGiao;
    DefaultTableModel modelTblPhongBan, modelTblPhieuBanGiao;
    JScrollPane scTblPhongBan, scTblPhieuBanGiao;

    JLabel lblTaiSan, lblPhongBan, lblNguoiSuDung, lblNgayBatDau;
    JTextField txtNguoiSuDung, txtTimKiemPBG, txtNgayBatDau;
    JButton btnNew, btnEdit, btnDelete, btnSave, btnCancel, btnSearch;

    JComboBox cbxTaiSan, cbxPhongBan;

    JCheckBox chk1;
    //</editor-fold>
}
