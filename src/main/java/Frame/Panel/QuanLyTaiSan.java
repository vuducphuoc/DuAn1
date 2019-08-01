package Frame.Panel;

import Contant.CoreConstant;
import DTO.*;
import Entity.NhaSanXuat;
import Entity.PhieuBanGiao;
import Entity.PhongBan;
import Entity.TaiSan;
import Utils.DateUtil;
import Utils.DialogUtils;
import Utils.MoneyUtil;
import Utils.SingletonDaoUtil;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
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
        doneLoad = true;
    }

    public void open() {
        initComponents();
        addControls();
        addEvents();
        statusDefault();

        loadDataToTblNhaSanXuat();
        getNSXSelected(indexNhaSanXuat);
        loadDataToCbxNSX();
        loadDataToTblTaiSan(nsxSelected);
        fillToForm(indexTaiSan);
    }

    public void initComponents() {

        //<editor-fold desc="Description">
        lblPhanLoai = new JLabel("Phân Loại");
        lblTenTaiSan = new JLabel("Tên tài sản");
        lblNhaSanXuat = new JLabel("Hãng sản xuất");
        lblMaTaiSan = new JLabel("Mã tài sản");
        lblMoTa = new JLabel("Mô tả");
        lblNguyenGia = new JLabel("Nguyên giá");
        lblTiLeKhauHao = new JLabel("Tỷ lệ khấu hao");
        lblThoiGianKhauHao = new JLabel("Thời gian khấu hao");

        txtTenTaiSan = new JTextField();
        txtMaTaiSan = new JTextField();
        txtNguyenGia = new JTextField();
        txtTiLeKhauHao = new JTextField();
        txtThoiGianKhauHao = new JTextField();
        txtTimKiem = new JTextField();

        chk1 = new JCheckBox("Chỉ hiển thị phòng ban có tài sản");

        cbxNhaSanXuat = new JComboBox();

        rdo1 = new JRadioButton("Nhà cửa, vật kiến trúc");
        rdo2 = new JRadioButton("Phương tiện vận tải");
        rdo3 = new JRadioButton("Công cụ dụng cụ");
        btgPhanLoai = new ButtonGroup(); btgPhanLoai.add(rdo1); btgPhanLoai.add(rdo2); btgPhanLoai.add(rdo3);
        rdo1.setSelected(true);

        txaMoTa =  new JTextArea();
        txaMoTa.setLineWrap(true);
        scMoTa = new JScrollPane(txaMoTa, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        //</editor-fold>

        //<editor-fold desc="TABLE NHÀ SẢN XUẤT">
        modelTblNhaSanXuat = new DefaultTableModel();
        modelTblNhaSanXuat.setColumnIdentifiers(new Object[] {"STT", "Nhà Sản Xuất"});
        tblNhaSanXuat = new JTable(modelTblNhaSanXuat){
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
        JTableHeader tblHeaderPhongBan = tblNhaSanXuat.getTableHeader();
        ((DefaultTableCellRenderer) tblHeaderPhongBan.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        /* Chỉnh sửa kích thước từng cột */
        tblHeaderPhongBan.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblHeaderPhongBan.getColumnModel().getColumn(1).setPreferredWidth(280);

        /* Chỉnh sửa độ cao dòng */
        tblNhaSanXuat.setRowHeight(20);

        /* Chỉnh sửa màu nền khi chọn */
        tblNhaSanXuat.setSelectionBackground(Color.decode("#3a4d8f"));

        tblNhaSanXuat.setIntercellSpacing(new Dimension(0, 0));
        tblNhaSanXuat.setShowGrid(false);

        tblNhaSanXuat.setRowSorter(new TableRowSorter(modelTblNhaSanXuat));
        tblNhaSanXuat.setAutoCreateRowSorter(true);
        scTblNhaSanXuat = new JScrollPane(tblNhaSanXuat, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        //</editor-fold>

        //<editor-fold desc="TABLE TÀI SẢN">
        modelTblTaiSan = new DefaultTableModel();
        modelTblTaiSan.setColumnIdentifiers(new Object[] {"STT", "Mã tài sản","Tên tài sản", "Nguyên giá", "Thời gian khấu hao"});
        tblTaiSan = new JTable(modelTblTaiSan){
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

        JTableHeader tblHeaderTS = tblTaiSan.getTableHeader();
        ((DefaultTableCellRenderer) tblHeaderTS.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        tblHeaderTS.getColumnModel().getColumn(0).setPreferredWidth(30);
        tblHeaderTS.getColumnModel().getColumn(1).setPreferredWidth(100);
        tblHeaderTS.getColumnModel().getColumn(2).setPreferredWidth(200);
        tblHeaderTS.getColumnModel().getColumn(3).setPreferredWidth(100);
        tblHeaderTS.getColumnModel().getColumn(4).setPreferredWidth(100);

        tblTaiSan.setRowHeight(20);
        tblTaiSan.setSelectionBackground(Color.decode("#3a4d8f"));

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

        tblTaiSan.setRowSorter(new TableRowSorter(modelTblTaiSan));
        tblTaiSan.setAutoCreateRowSorter(true);

        scTblTaiSan = new JScrollPane(tblTaiSan, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        //</editor-fold>

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

        btnDelete.setPreferredSize(btnNew.getPreferredSize());
        btnTKTS.setPreferredSize(btnNew.getPreferredSize());
        btnSave.setPreferredSize(btnNew.getPreferredSize());
        btnCancel.setPreferredSize(btnNew.getPreferredSize());
        btnEdit.setPreferredSize(btnNew.getPreferredSize());
        //</editor-fold>

        //<editor-fold desc="Set Font Segoe UI">
        btnNew.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        btnEdit.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        btnDelete.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        btnCancel.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        btnSave.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        btnExport.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));

        lblPhanLoai.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        lblTenTaiSan.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        lblMaTaiSan.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        lblNhaSanXuat.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        lblNguyenGia.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        lblMoTa.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        lblTiLeKhauHao.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        lblThoiGianKhauHao.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));

        rdo1.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        rdo2.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        rdo3.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));

        txtTenTaiSan.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        txtMaTaiSan.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        txtNguyenGia.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        txaMoTa.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        txtTiLeKhauHao.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        txtThoiGianKhauHao.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));

        cbxNhaSanXuat.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));

        chk1.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        //</editor-fold>

        txtTimKiem.setPreferredSize(new Dimension(200, 25));
    }

    public void addControls() {
        // LEFT
        JPanel pnLeftTop = new JPanel();
        pnLeftTop.add(chk1);

        JPanel pnLeft = new JPanel(new BorderLayout());
        pnLeft.add(pnLeftTop, BorderLayout.NORTH);
        pnLeft.add(scTblNhaSanXuat, BorderLayout.CENTER);

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
        pnRightTop.add(lblNhaSanXuat, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        pnRightTop.add(lblMoTa, gbc);

        gbc.gridwidth = 5;
        gbc.gridx = 1; gbc.gridy = 0;
        pnRightTop.add(pnPhanLoai, gbc);

        gbc.ipadx = 180;
        gbc.ipady = 5;

        gbc.gridwidth = 1;
        gbc.gridx = 1; gbc.gridy = 1;
        pnRightTop.add(txtTenTaiSan, gbc);

        gbc.gridx = 1; gbc.gridy = 2;
        pnRightTop.add(cbxNhaSanXuat, gbc);

        gbc.gridheight = 2;
        gbc.gridx = 1; gbc.gridy = 3;
        pnRightTop.add(scMoTa, gbc);

        gbc.ipadx = 0;
        gbc.gridheight = 1;
        gbc.gridx = 2; gbc.gridy = 1;
        pnRightTop.add(lblMaTaiSan, gbc);

        gbc.gridx = 2; gbc.gridy = 2;
        pnRightTop.add(lblNguyenGia, gbc);

        gbc.gridx = 2; gbc.gridy = 3;
        pnRightTop.add(lblTiLeKhauHao, gbc);

        gbc.gridx = 2; gbc.gridy = 4;
        pnRightTop.add(lblThoiGianKhauHao, gbc);

        gbc.gridwidth = 2;
        gbc.gridheight = 1;
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
        pnSearchEmployee.add(txtTimKiem);
        pnSearchEmployee.add(btnTKTS);
        JPanel pnExport = new JPanel(new FlowLayout(FlowLayout.RIGHT, 30, 10));

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
        /* Mouse Click bảng Tài Sản */
        tblTaiSan.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                indexTaiSan = tblTaiSan.getSelectedRow();
                fillToForm(indexTaiSan);
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        });

        /* Mouse Click bảng Nhà Sản Xuất */
        tblNhaSanXuat.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                indexNhaSanXuat = tblNhaSanXuat.getSelectedRow();
                getNSXSelected(indexNhaSanXuat);
                loadDataToTblTaiSan(nsxSelected);
                indexTaiSan = 0;
                fillToForm(indexTaiSan);
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        });

        /* Thêm mới */
        btnNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setBtnNew();
            }
        });

        /* Sửa */
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setBtnEdit();
            }
        });

        /* Xóa */
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setBtnDelete();
            }
        });

        /* Lưu */
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setBtnSave();
            }
        });

        /* Bỏ qua */
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setBtnCancel();
            }
        });
    }

    public void setBtnNew() {
        clearForm();
        tblTaiSan.clearSelection();
        setStatus(CoreConstant.FLAG_INSERT);
        txtMaTaiSan.setText(createID());
        txtTenTaiSan.requestFocus();
    }

    public void setBtnEdit() {
        setStatus(CoreConstant.FLAG_UPDATE);
    }

    public void setBtnDelete() {
        boolean i = DialogUtils.showConfirmDialog("Bạn có chắc muốn xóa tài sản " + tsSelected + " không ?");
        if (i == true) {
            int n = SingletonDaoUtil.getTaiSanDaoImpl().delete(tsSelected);
            if (n > 0) {
                if (indexTaiSan == taiSanList.size() -1) {
                    indexTaiSan = indexTaiSan - 1;
                }
                deleteSuccess();
            }
        }
    }

    public void setBtnSave() {
        TaiSan taiSan = getModel();
        if (flagSave == CoreConstant.FLAG_INSERT) {
            SingletonDaoUtil.getTaiSanDaoImpl().save(taiSan);
            saveSuccess();
        } else if (flagSave == CoreConstant.FLAG_UPDATE) {
            SingletonDaoUtil.getTaiSanDaoImpl().update(taiSan);
            saveSuccess();
        }
    }

    public void setBtnCancel() {
        boolean n = DialogUtils.showConfirmDialog(messCancel);
        if (n == true) {
            fillToForm(indexTaiSan);
        }
    }
    /*-------------------------------------------------------*/

    /* Xóa thành công */
    public void deleteSuccess() {
        loadDataToTblTaiSan(nsxSelected);
        fillToForm(indexTaiSan);
    }

    /* Lưu thành công */
    public void saveSuccess() {
        loadDataToTblTaiSan(nsxSelected);
        for (TaiSan item : taiSanList) {
            if (item.getMats().equalsIgnoreCase(txtMaTaiSan.getText())) {
                indexTaiSan = taiSanList.indexOf(item);
                fillToForm(indexTaiSan);
                break;
            }
        }
        statusDefault();
    }

    /* Load dữ liệu cho bảng Phòng Ban */
    public void loadDataToTblNhaSanXuat() {
        modelTblNhaSanXuat.setRowCount(0);
        nhaSanXuatList = SingletonDaoUtil.getNhaSanXuatDaoImpl().findAll();

        int i = 1;

        for (NhaSanXuat item : nhaSanXuatList) {
            modelTblNhaSanXuat.addRow(new Object[] {
                    i, item.getTennsx() + " - " + item.getQuocGia().getTenqg()
            });
            i++;
        }
    }

    /* Load dữ liệu cho bảng Tài Sản */
    public void loadDataToTblTaiSan(NhaSanXuat nhaSanXuat) {
        modelTblTaiSan.setRowCount(0);

        if (nhaSanXuat != null) {
            taiSanList = SingletonDaoUtil.getTaiSanDaoImpl().getByNhaSanXuat(nsxSelected);
            modelTblTaiSan.setRowCount(0);
            if (taiSanList.size() > 0) {
                int i = 1;
                for (TaiSan item : taiSanList) {
                    long nguyengia = Long.parseLong(String.valueOf(item.getNguyengia()));
                    modelTblTaiSan.addRow(new Object[] {
                            i, item.getMats(), item.getTents(), MoneyUtil.castIntToMoney(nguyengia),item.getThoigiankhauhao() + " năm"
                    });
                    i++;
                }
            } else {
                tsSelected = null;
            }
        }
    }

    /* Lấy nhà sản xuất selected */
    public void getNSXSelected (int i) {
        if (nhaSanXuatList.size() > 0) {
            tblNhaSanXuat.setRowSelectionInterval(i, i);
            Rectangle rect = tblNhaSanXuat.getCellRect(i, 0, true);
            tblNhaSanXuat.scrollRectToVisible(rect);
            sttNhaSanXuat = Integer.parseInt(tblNhaSanXuat.getValueAt(i, 0).toString());
            nsxSelected = nhaSanXuatList.get(sttNhaSanXuat - 1);
        }
    }

    /* Fill dữ liệu lên form Tài Sản */
    public void fillToForm (int i) {
        if (taiSanList.size() > 0) {
            tblTaiSan.setRowSelectionInterval(i, i);
            Rectangle rect = tblTaiSan.getCellRect(i, 0, true);
            tblTaiSan.scrollRectToVisible(rect);

            sttTaiSan = Integer.parseInt(tblTaiSan.getValueAt(i, 0).toString());
            tsSelected = taiSanList.get(sttTaiSan-1);
            setModel(tsSelected);
        } else {
            clearForm();
        }
    }

    /* Lấy model của Tài Sản */
    public TaiSan getModel() {
        TaiSan taiSan = new TaiSan();
        taiSan.setMats(txtMaTaiSan.getText());
        taiSan.setTents(txtTenTaiSan.getText());
        taiSan.setNhaSanXuat(nsxSelected);
        taiSan.setNguyengia(Integer.parseInt(txtNguyenGia.getText()));
        taiSan.setMota(txaMoTa.getText());
        taiSan.setTilekhauhao(Double.parseDouble(txtTiLeKhauHao.getText()));
        taiSan.setThoigiankhauhao(Double.parseDouble(txtThoiGianKhauHao.getText()));
        return taiSan;
    }

    /* Fill dữ liệu thông tin chi tiết Tài Sản */
    public void setModel(TaiSan taiSan) {
        if (taiSan != null) {

            if (taiSan.getPhanLoai().getId() == 1) {
                rdo1.setSelected(true);
            } else if (taiSan.getPhanLoai().getId() == 2){
                rdo2.setSelected(true);
            } else {
                rdo3.setSelected(true);
            }
            txtMaTaiSan.setText(taiSan.getMats());
            txtTenTaiSan.setText(taiSan.getTents());
            cbxNhaSanXuat.setSelectedItem(taiSan.getNhaSanXuat());
            txaMoTa.setText(taiSan.getMota());
            txtNguyenGia.setText(String.valueOf(taiSan.getNguyengia()));
            txtTiLeKhauHao.setText(String.valueOf(taiSan.getTilekhauhao()));
            txtThoiGianKhauHao.setText(String.valueOf(taiSan.getThoigiankhauhao()));
        } else {
            rdo1.setSelected(true);
            txtMaTaiSan.setText("");
            txtTenTaiSan.setText("");
            cbxNhaSanXuat.setSelectedItem("");
            txaMoTa.setText("");
            txtNguyenGia.setText("");
            txtTiLeKhauHao.setText("");
            txtThoiGianKhauHao.setText("");
        }
    }

    /* Xóa trắng form*/
    public void clearForm() {
        setModel(null);
    }

    /* Load dữ liệu vào ComboBox Nhà Sản Xuất*/
    public void loadDataToCbxNSX() {
        List<NhaSanXuat> list = SingletonDaoUtil.getNhaSanXuatDaoImpl().findAll();
        cbxNhaSanXuat.removeAllItems();

        if (list.size() > 0) {
            for (NhaSanXuat item : list) {
                cbxNhaSanXuat.addItem(item);
            }
        }
    }

    /* Trạng thái ban đầu của phần mềm */
    public void statusDefault() {
        flagSave = CoreConstant.FLAG_EMTY;

        btnNew.setVisible(true);
        btnEdit.setVisible(true);
        btnDelete.setVisible(true);

        btnSave.setVisible(false);
        btnCancel.setVisible(false);

        txtTenTaiSan.setEditable(false);
        txtThoiGianKhauHao.setEnabled(false);
        txtTiLeKhauHao.setEnabled(false);
        txtNguyenGia.setEnabled(false);
        txtMaTaiSan.setEditable(false);
        txaMoTa.setEditable(false);
        cbxNhaSanXuat.setEnabled(false);
        rdo1.setEnabled(false);
        rdo2.setEnabled(false);
        rdo3.setEnabled(false);
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

    public void btnChange() {
        btnSave.setVisible(true);
        btnNew.setVisible(false);
        btnEdit.setVisible(false);
        btnDelete.setVisible(false);
        btnCancel.setVisible(true);

        txtMaTaiSan.setEditable(false);
        txtTenTaiSan.setEditable(true);
        txtNguyenGia.setEditable(true);
        txtTiLeKhauHao.setEditable(true);
        txaMoTa.setEditable(true);
    }

    private String createID() {
        String lastID = SingletonDaoUtil.getTaiSanDaoImpl().getLastID();

        if (lastID != null) {
            StringBuilder ID = new StringBuilder();
            ID.append("TS");

            int pathNumber = Integer.parseInt(lastID.substring(2)) + 1;

            for (int i = 0; i < 4 - String.valueOf(pathNumber).length(); i++) {
                ID.append("0");
            }

            ID.append(pathNumber);

            return ID.toString();
        } else {
            return "TS00001";
        }
    }

    //<editor-fold desc="COMPONENT">
    public boolean doneLoad = false;

    List<NhaSanXuat> nhaSanXuatList = new ArrayList<>();
    List<TaiSan> taiSanList = new ArrayList<>();

    NhaSanXuat nsxSelected = new NhaSanXuat();
    TaiSan tsSelected = new TaiSan();

    int flagSave = CoreConstant.FLAG_EMTY;

    int sttTaiSan = -1;
    int sttNhaSanXuat = -1;

    int indexTaiSan = 0;
    int indexNhaSanXuat = 0;

    String messCancel;

    JLabel lblPhanLoai, lblTenTaiSan, lblMaTaiSan, lblNhaSanXuat, lblMoTa, lblNguyenGia, lblTiLeKhauHao, lblThoiGianKhauHao;
    JTextField txtTenTaiSan, txtMaTaiSan, txtNguyenGia, txtTiLeKhauHao, txtThoiGianKhauHao, txtTimKiem;
    JComboBox cbxNhaSanXuat;
    JCheckBox chk1;
    JTextArea txaMoTa;
    JRadioButton rdo1, rdo2, rdo3;
    ButtonGroup btgPhanLoai;
    JScrollPane scMoTa, scTblNhaSanXuat, scTblTaiSan;
    JTable tblNhaSanXuat, tblTaiSan;
    DefaultTableModel modelTblNhaSanXuat, modelTblTaiSan;
    JButton btnTKTS, btnNew, btnEdit, btnDelete, btnCancel, btnSave, btnExport;
    //</editor-fold>
}
