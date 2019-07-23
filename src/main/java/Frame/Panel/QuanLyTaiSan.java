package Frame.Panel;

import Contant.CoreConstant;
import DTO.*;
import Entity.PhieuBanGiao;
import Entity.TaiSan;
import Utils.DateUtil;
import Utils.SingletonDaoUtil;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;

public class QuanLyTaiSan extends JPanel {
    public QuanLyTaiSan() {
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
        initComponents();
        addControls();
        addEvents();

        loadDataToTblPhongBan();
        getPBSelected(indexPhongBan);
        loadDataToTblTaiSan(pbSelected);
        fillToFormNV(indexPhongBan);
    }

    public void initComponents() {
        lblPhanLoai = new JLabel("Phân Loại");
        lblTenTaiSan = new JLabel("Tên tài sản");
        lblHangSanXuat = new JLabel("Hãng sản xuất");
        lblMaTaiSan = new JLabel("Mã tài sản");
        lblMoTa = new JLabel("Mô tả");
        lblNguyenGia = new JLabel("Nguyên giá");
        lblTiLeKhauHao = new JLabel("Tỷ lệ khấu hao");
        lblThoiGianKhauHao = new JLabel("Thời gian khấu hao");
        lblNguoiSuDung = new JLabel("Người sử dụng");
        lblNgayBatDau = new JLabel("Ngày bắt đầu");

        txtTenTaiSan = new JTextField();
        txtMaTaiSan = new JTextField();
        txtNguyenGia = new JTextField();
        txtTiLeKhauHao = new JTextField();
        txtThoiGianKhauHao = new JTextField();
        txtNguoiSuDung = new JTextField();
        txtNgayBatDau = new JTextField();
        txtTimKiem = new JTextField();

        cbxHangSanXuat = new JComboBox();

        rdo1 = new JRadioButton("Nhà cửa, vật kiến trúc");
        rdo2 = new JRadioButton("Phương tiện vận tải");
        rdo3 = new JRadioButton("Công cụ dụng cụ");
        btgPhanLoai = new ButtonGroup(); btgPhanLoai.add(rdo1); btgPhanLoai.add(rdo2); btgPhanLoai.add(rdo3);
        txaMoTa =  new JTextArea();
        txaMoTa.setLineWrap(true);
        scMoTa = new JScrollPane(txaMoTa, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        //<editor-fold desc="TABLE PHÒNG BAN">
        modelTblPhongBan = new DefaultTableModel();
        modelTblPhongBan.setColumnIdentifiers(new Object[] {"STT", "Tên phòng ban"});
        tblPhongBan = new JTable(modelTblPhongBan){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                JLabel c = (JLabel) super.prepareRenderer(renderer, row, column);
                c.setHorizontalAlignment(JLabel.CENTER);
                return c;
            }
        };
        JTableHeader tblHeaderPhongBan = tblPhongBan.getTableHeader();
        ((DefaultTableCellRenderer) tblHeaderPhongBan.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        tblHeaderPhongBan.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblHeaderPhongBan.getColumnModel().getColumn(1).setPreferredWidth(280);
        tblPhongBan.setRowHeight(25);
        tblPhongBan.setSelectionBackground(Color.decode("#3a4d8f"));
        //</editor-fold>

        //<editor-fold desc="TABLE TÀI SẢN">
        modelTblTaiSan = new DefaultTableModel();
        modelTblTaiSan.setColumnIdentifiers(new Object[] {"STT", "Mã tài sản","Tên tài sản", "Nguyên giá", "Năm bắt đầu"});
        tblTaiSan = new JTable(modelTblTaiSan){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                JLabel c = (JLabel) super.prepareRenderer(renderer, row, column);
                    c.setHorizontalAlignment(JLabel.CENTER);
                return c;
            }
        };
        JTableHeader tblHeaderTS = tblTaiSan.getTableHeader();
        ((DefaultTableCellRenderer) tblHeaderTS.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        tblHeaderTS.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblHeaderTS.getColumnModel().getColumn(1).setPreferredWidth(200);
        tblHeaderTS.getColumnModel().getColumn(2).setPreferredWidth(100);
        tblHeaderTS.getColumnModel().getColumn(3).setPreferredWidth(100);
        tblHeaderTS.getColumnModel().getColumn(4).setPreferredWidth(100);
        tblTaiSan.setRowHeight(25);
        tblTaiSan.setSelectionBackground(Color.decode("#3a4d8f"));
        //</editor-fold>

        scTblPhongBan = new JScrollPane(tblPhongBan, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scTblTaiSan = new JScrollPane(tblTaiSan, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        //<editor-fold desc="BUTTON">
        btnCancel = new JButton("Bỏ qua");
        btnDelete = new JButton("Xóa", new ImageIcon("src/Image/icon-delete"));
        btnEdit = new JButton("Sửa");
        btnExport = new JButton("Kết xuất");
        btnNew = new JButton("Thêm mới");
        btnSave = new JButton("Lưu");
        btnTKTS = new JButton("Tìm tiếp");

        btnCancel.setIcon(new ImageIcon("src/Image/icon-cancel.png"));
        btnDelete.setIcon(new ImageIcon("src/Image/icon-delete.png"));
        btnEdit.setIcon(new ImageIcon("src/Image/icon-edit.png"));
        btnNew.setIcon(new ImageIcon("src/Image/icon-new.png"));
        btnSave.setIcon(new ImageIcon("src/Image/icon-save.png"));

        btnCancel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnDelete.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEdit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnNew.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnTKTS.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnExport.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSave.setCursor(new Cursor(Cursor.HAND_CURSOR));
        //</editor-fold>

        chk1 = new JCheckBox("Chỉ hiển thị phòng ban có tài sản");

        txtTimKiem.setPreferredSize(new Dimension(200, 25));

        //<editor-fold desc="CĂN GIỮA TABLE">
        //căn giữa nội dung table
        DefaultTableCellRenderer renderer1 = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? Color.decode("#e7e7e7") : Color.WHITE);
                    c.setFont(new Font("Segoe UI", 0, 14));
                }
                return c;
            }
        };
        renderer1.setHorizontalAlignment((int) JTable.CENTER_ALIGNMENT);
        for (int i = 0; i < tblPhongBan.getColumnCount(); i++) {
            tblPhongBan.setDefaultRenderer(tblPhongBan.getColumnClass(i), renderer1);
        }

        tblPhongBan.setRowSorter(new TableRowSorter(modelTblPhongBan));
        tblPhongBan.setAutoCreateRowSorter(true);

        //căn giữa nội dung table
        DefaultTableCellRenderer renderer2 = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? Color.decode("#e7e7e7") : Color.WHITE);
                    c.setFont(new Font("Segoe UI", 0, 14));
                }
                return c;
            }
        };
        renderer2.setHorizontalAlignment((int) JTable.CENTER_ALIGNMENT);
        for (int i = 0; i < tblTaiSan.getColumnCount(); i++) {
            tblTaiSan.setDefaultRenderer(tblTaiSan.getColumnClass(i), renderer2);
        }

        tblTaiSan.setRowSorter(new TableRowSorter(modelTblTaiSan));
        tblTaiSan.setAutoCreateRowSorter(true);
        //</editor-fold>

        rdo1.setSelected(true);
    }

    public void addControls() {
        // LEFT
        JPanel pnLeftTop = new JPanel();
        pnLeftTop.add(chk1);

        JPanel pnLeft = new JPanel(new BorderLayout());
        pnLeft.add(pnLeftTop, BorderLayout.NORTH);
        pnLeft.add(scTblPhongBan, BorderLayout.CENTER);

        JPanel pnPhanLoai = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        pnPhanLoai.add(rdo1); pnPhanLoai.add(rdo2); pnPhanLoai.add(rdo3);

        JPanel pnRightTop = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 10, 5, 10);

        gbc.gridx = 0; gbc.gridy = 0;
        pnRightTop.add(lblPhanLoai, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        pnRightTop.add(lblTenTaiSan, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        pnRightTop.add(lblHangSanXuat, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        pnRightTop.add(lblMoTa, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        pnRightTop.add(lblNguoiSuDung, gbc);


        gbc.gridwidth = 5;
        gbc.gridx = 1; gbc.gridy = 0;
        pnRightTop.add(pnPhanLoai, gbc);

        gbc.ipadx = 280;
        gbc.ipady = 5;
        gbc.gridwidth = 1;
        gbc.gridx = 1; gbc.gridy = 1;
        pnRightTop.add(txtTenTaiSan, gbc);

        gbc.gridx = 1; gbc.gridy = 2;
        pnRightTop.add(cbxHangSanXuat, gbc);

        gbc.gridheight = 2;
        gbc.gridx = 1; gbc.gridy = 3;
        pnRightTop.add(scMoTa, gbc);

        gbc.gridheight = 1;
        gbc.gridx = 1; gbc.gridy = 5;
        pnRightTop.add(txtNguoiSuDung, gbc);

        gbc.ipadx = 0;
        gbc.gridx = 2; gbc.gridy = 1;
        pnRightTop.add(lblMaTaiSan, gbc);

        gbc.gridx = 2; gbc.gridy = 2;
        pnRightTop.add(lblNguyenGia, gbc);

        gbc.gridx = 2; gbc.gridy = 3;
        pnRightTop.add(lblTiLeKhauHao, gbc);

        gbc.gridx = 2; gbc.gridy = 4;
        pnRightTop.add(lblThoiGianKhauHao, gbc);

        gbc.gridx = 2; gbc.gridy = 5;
        pnRightTop.add(lblNgayBatDau, gbc);

        gbc.gridwidth = 2;
        gbc.gridx = 3; gbc.gridy = 1;
        pnRightTop.add(txtMaTaiSan, gbc);


        gbc.gridwidth = 3;
        gbc.gridx = 3; gbc.gridy = 2;
        pnRightTop.add(txtNguyenGia, gbc);

        gbc.ipadx = 120;
        gbc.gridwidth = 1;
        gbc.gridx = 3; gbc.gridy = 3;
        pnRightTop.add(txtTiLeKhauHao, gbc);

        gbc.gridx = 4; gbc.gridy = 3;
        pnRightTop.add(new JLabel("% / năm"), gbc);

        gbc.gridx = 3; gbc.gridy = 4;
        pnRightTop.add(txtThoiGianKhauHao, gbc);

        gbc.gridx = 4; gbc.gridy = 4;
        pnRightTop.add(new JLabel("năm"), gbc);

        gbc.gridwidth = 3;
        gbc.gridx = 3; gbc.gridy = 5;
        pnRightTop.add(txtNgayBatDau, gbc);

        JPanel pnButton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnButton.add(btnNew);
        pnButton.add(btnEdit);
        pnButton.add(btnDelete);
        pnButton.add(btnSave);
        pnButton.add(btnCancel);

        JPanel pnRightTp = new JPanel(new BorderLayout(0,10));
        pnRightTp.add(pnRightTop, BorderLayout.CENTER);
        pnRightTp.add(pnButton, BorderLayout.NORTH);

        JPanel pnTKTaiSan = new JPanel(new BorderLayout());
        JPanel pnSearchEmployee = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        pnSearchEmployee.add(txtTimKiem);
        pnSearchEmployee.add(btnTKTS);
        JPanel pnExport = new JPanel(new FlowLayout(FlowLayout.RIGHT, 30, 10));
        pnExport.add(btnExport);

        JPanel pnTemp2 = new JPanel();
        pnTKTaiSan.add(pnSearchEmployee, BorderLayout.WEST);
        pnTKTaiSan.add(pnTemp2, BorderLayout.CENTER);
        pnTKTaiSan.add(pnExport, BorderLayout.EAST);

        JPanel pnTblTaiSan = new JPanel(new BorderLayout());
        pnTblTaiSan.add(pnTKTaiSan, BorderLayout.NORTH);
        pnTblTaiSan.add(scTblTaiSan, BorderLayout.CENTER);

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

    public void loadDataToTblPhongBan() {
        modelTblPhongBan.setRowCount(0);
        phongBanList = SingletonDaoUtil.getPhongBanDaoImpl().getAll();

        int i = 1;

        for (PhongBanDTO item : phongBanList) {
            modelTblPhongBan.addRow(new Object[] {
                    i, item.getTenpb()
            });
            i++;
        }
    }

    public void loadDataToTblTaiSan(PhongBanDTO phongBan) {
        modelTblTaiSan.setRowCount(0);

        if (phongBan != null) {
            phieuBanGiaoDTOS = phongBan.getPhieuBanGiaoList();
            modelTblTaiSan.setRowCount(0);
            int i = 1;

            for (PhieuBanGiaoDTO item : phieuBanGiaoDTOS) {
                modelTblTaiSan.addRow(new Object[] {
                        i, item.getTaiSan().getMats(), item.getTaiSan().getTents(), item.getTaiSan().getNguyengia(),item.getNgaybangiao()
                });
                i++;
            }
        }
    }

    public void getPBSelected (int i) {
        if (phongBanList.size() > 0) {
            tblPhongBan.setRowSelectionInterval(i, i);
            Rectangle rect = tblPhongBan.getCellRect(i, 0, true);
            tblPhongBan.scrollRectToVisible(rect);
            sttPhongBan = Integer.parseInt(tblPhongBan.getValueAt(i, 0).toString());
            pbSelected = phongBanList.get(sttPhongBan - 1);
        }
    }

    public void fillToFormNV (int i) {
        if (phieuBanGiaoDTOS.size() > 0) {
            tblTaiSan.setRowSelectionInterval(i, i);
            Rectangle rect = tblTaiSan.getCellRect(i, 0, true);
            tblTaiSan.scrollRectToVisible(rect);

            sttPhongBan = Integer.parseInt(tblTaiSan.getValueAt(i, 0).toString());
            pbgSelected = phieuBanGiaoDTOS.get(sttPhongBan-1);
            setModel(pbgSelected);
        } else {
            clearFormNV();
        }
    }

    public PhieuBanGiaoDTO getModel() {
        PhieuBanGiaoDTO phieuBanGiaoDTO = new PhieuBanGiaoDTO();
        TaiSanDTO taiSanDTO = new TaiSanDTO();

        taiSanDTO.setMats(txtMaTaiSan.getText());
        taiSanDTO.setTents(txtTenTaiSan.getText());
        taiSanDTO.setNhaSanXuat((NhaSanXuatDTO) cbxHangSanXuat.getSelectedItem());
        taiSanDTO.setNguyengia(Integer.parseInt(txtNguyenGia.getText()));
        taiSanDTO.setMota(txaMoTa.getText());
        taiSanDTO.setTilekhauhao(Double.parseDouble(txtTiLeKhauHao.getText()));
        taiSanDTO.setThoigiankhauhao(Double.parseDouble(txtThoiGianKhauHao.getText()));

        phieuBanGiaoDTO.setNguoisudungs(txtNguoiSuDung.getText());
        phieuBanGiaoDTO.setNgaybangiao(txtNgayBatDau.getText());
        phieuBanGiaoDTO.setTaiSan(taiSanDTO);
        phieuBanGiaoDTO.setPhongBan(pbSelected);
        return phieuBanGiaoDTO;
    }

    public void setModel(PhieuBanGiaoDTO phieuBanGiaoDTO) {
        if (phieuBanGiaoDTO.getTaiSan().getPhanLoai().getId() == 1) {
            rdo1.setSelected(true);
        } else if (phieuBanGiaoDTO.getTaiSan().getPhanLoai().getId() == 2){
            rdo2.setSelected(true);
        } else {
            rdo3.setSelected(true);
        }
        txtMaTaiSan.setText(phieuBanGiaoDTO.getTaiSan().getMats());
        txtTenTaiSan.setText(phieuBanGiaoDTO.getTaiSan().getTents());
        cbxHangSanXuat.setSelectedItem(phieuBanGiaoDTO.getTaiSan().getNhaSanXuat());
        txaMoTa.setText(phieuBanGiaoDTO.getTaiSan().getMota());
        txtNguyenGia.setText(String.valueOf(phieuBanGiaoDTO.getTaiSan().getNguyengia()));
        txtTiLeKhauHao.setText(String.valueOf(phieuBanGiaoDTO.getTaiSan().getTilekhauhao()));
        txtThoiGianKhauHao.setText(String.valueOf(phieuBanGiaoDTO.getTaiSan().getThoigiankhauhao()));
        txtNgayBatDau.setText(phieuBanGiaoDTO.getNgaybangiao());
        txtNguoiSuDung.setText(phieuBanGiaoDTO.getNguoisudungs());
    }

    public void clearFormNV() {
        TaiSanDTO taiSanDTO = new TaiSanDTO();
        PhieuBanGiaoDTO phieuBanGiaoDTO = new PhieuBanGiaoDTO();
        setModel(phieuBanGiaoDTO);
    }

    public void loadDataToCbxNSX() {
        List<NhaSanXuatDTO> list = SingletonDaoUtil.getNhaSanXuatDaoImpl().getAll();
        cbxHangSanXuat.removeAllItems();

        if (list.size() > 0) {
            for (NhaSanXuatDTO item : list) {
                cbxHangSanXuat.addItem(item);
            }
        }
    }

    //<editor-fold desc="COMPONENT">
    List<PhongBanDTO> phongBanList = new ArrayList<>();
    List<PhieuBanGiaoDTO> phieuBanGiaoDTOS = new ArrayList<>();
    List<TaiSanDTO> taiSanDTOList = new ArrayList<>();

    PhongBanDTO pbSelected = new PhongBanDTO();
    TaiSanDTO tsSelected = new TaiSanDTO();
    PhieuBanGiaoDTO pbgSelected = new PhieuBanGiaoDTO();

    int sttPhongBan = -1;
    int sttTaiSan = -1;
    int sttPhieuBanGiao = -1;

    int indexPhongBan = 0;
    int indexTaiSan = 0;
    int indexPhieuBanGiao = 0;

    JLabel lblPhanLoai, lblTenTaiSan, lblMaTaiSan, lblHangSanXuat, lblMoTa, lblNguyenGia, lblTiLeKhauHao, lblThoiGianKhauHao, lblNguoiSuDung, lblNgayBatDau;
    JTextField txtTenTaiSan, txtMaTaiSan, txtNguyenGia, txtTiLeKhauHao, txtThoiGianKhauHao, txtNguoiSuDung, txtNgayBatDau, txtTimKiem;
    JComboBox cbxHangSanXuat;
    JCheckBox chk1;
    JTextArea txaMoTa;
    JRadioButton rdo1, rdo2, rdo3;
    ButtonGroup btgPhanLoai;
    JScrollPane scMoTa, scTblPhongBan, scTblTaiSan;
    JTable tblPhongBan, tblTaiSan;
    DefaultTableModel modelTblPhongBan, modelTblTaiSan;
    JButton btnTKTS, btnNew, btnEdit, btnDelete, btnCancel, btnSave, btnExport;
    //</editor-fold>
}
