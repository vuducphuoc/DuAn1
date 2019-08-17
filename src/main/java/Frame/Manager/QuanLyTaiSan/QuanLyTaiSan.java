package Frame.Manager.QuanLyTaiSan;

import Contant.CoreConstant;
import Entity.*;
import Frame.Login.LoginFrame;
import Utils.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class QuanLyTaiSan extends JPanel {

    private static final long serialVersionUID = 1L;

    public PopupTaiSanChiTiet test;

    // Swing
    private JLabel lblTimKiem, lblTimKiemNhaSanXuat;
    private JTextField txtTimKiem, txtTimKiemNhaSanXuat;
    private ButtonGroup btgPhanLoai, btgNguyenGia;
    private JScrollPane scTblTaiSan;
    public JTable tblTaiSan;
    private DefaultTableModel modelTblTaiSan;
    private JRadioButton[] rdoPhanLoaiTS, rdoLocTheoGia;
    private JButton btnAdd;

    private JSlider sldLocNguyenGia;

    // Model
    private PhanLoai phanLoaiSelected = new PhanLoai();

    // ArrayList
    private List<PhanLoai> listPhanLoai = SingletonDaoUtil.getPhanLoaiTaiSanDaoImpl().findAll();
    private List<TaiSan> listTaiSan = new ArrayList<>();
    private List<TaiSan> listTimKiemTaiSan = new ArrayList<>();

    // Primitive
    private int indexTaiSan = 0;
    private int indexTimKiemTaiSan = 0;
    public boolean doneLoad = false;

    public QuanLyTaiSan() {
        open();
        doneLoad = true;
    }

    public void open() {
        initComponents();
        addControls();
        addEvents();


    }

    public void initComponents() {

        lblTimKiem = new JLabel("Tìm kiếm");
        lblTimKiemNhaSanXuat = new JLabel("Tìm kiếm");

        txtTimKiem = new JTextField();
        txtTimKiem.setPreferredSize(new Dimension(250, 25));
        txtTimKiemNhaSanXuat = new JTextField();

        btnAdd = new JButton("Thêm mới");
        btnAdd.setIcon(new ImageIcon((String) CoreConstant.iconButton[0]));

        Object[] phanLoaiTS = new Object[] { "Tất cả", "Công cụ dụng cụ", "Nhà cửa, Vật kiến trúc", "Phương tiện vận tải" };
        rdoPhanLoaiTS = ComponentUtils.createRadioButton(phanLoaiTS, new Font("Segoe UI", Font.ROMAN_BASELINE, 12),
                Color.WHITE);

        Object[] locTheoGia = new Object[] { "Tất cả", "0 - 1.00.000đ", "1.000.000 - 5.000.000đ", "5.000.000 - 50.000.000đ",
                " > 50.000.000đ" };
        rdoLocTheoGia = ComponentUtils.createRadioButton(locTheoGia, new Font("Segoe UI", Font.ROMAN_BASELINE, 12),
                Color.WHITE);

        sldLocNguyenGia = new JSlider();
        sldLocNguyenGia.setPaintLabels(true);

        btgPhanLoai = new ButtonGroup();
        for (JRadioButton item : rdoPhanLoaiTS) {
            btgPhanLoai.add(item);
        }
        rdoPhanLoaiTS[0].setSelected(true);

        btgNguyenGia = new ButtonGroup();
        for (JRadioButton item : rdoLocTheoGia) {
            btgNguyenGia.add(item);
        }
        rdoLocTheoGia[0].setSelected(true);

        Object[] title;
        int loginAs = LoginFrame.accountLogin.getVaiTro().getId();
        if (loginAs != CoreConstant.ROLE_ADMIN ) {
            title = new Object[] { "STT", "Mã tài sản", "Tên tài sản", "Nguyên giá", "Thời gian khấu hao", "Sửa", "Xóa" };
        } else {
            title = new Object[] { "STT", "Mã tài sản", "Tên tài sản", "Nguyên giá", "Thời gian khấu hao", "Xem" };
        }

        modelTblTaiSan = new DefaultTableModel();
        modelTblTaiSan.setColumnIdentifiers(title);
        tblTaiSan = new JTable(modelTblTaiSan) {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                if (column < 5) {
                    return false;
                } else
                    return true;
            }
        };

        TableColumnModel columnModel = tblTaiSan.getColumnModel();
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            if (columnModel.getColumn(i).getIdentifier().equals("Sửa")) {
                tblTaiSan.getColumn("Sửa").setCellRenderer(new ButtonRenderer());
                tblTaiSan.getColumn("Sửa").setCellEditor(new ButtonEditor(new JCheckBox()));
            } else if (columnModel.getColumn(i).getIdentifier().equals("Xóa")) {
                tblTaiSan.getColumn("Xóa").setCellRenderer(new ButtonRenderer());
                tblTaiSan.getColumn("Xóa").setCellEditor(new ButtonEditor(new JCheckBox()));
            } else if (columnModel.getColumn(i).getIdentifier().equals("Xem")) {
                tblTaiSan.getColumn("Xem").setCellRenderer(new ButtonRenderer());
                tblTaiSan.getColumn("Xem").setCellEditor(new ButtonEditor(new JCheckBox()));
            }

        }

        JTableHeader tblHeaderTS = tblTaiSan.getTableHeader();
        ((DefaultTableCellRenderer) tblHeaderTS.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        int columnCount = tblHeaderTS.getColumnModel().getColumnCount();
        int[] columnWidth = { 30, 100, 200, 100, 100, 60, 60 };
        for (int i = 0; i < columnCount; i++) {
            tblHeaderTS.getColumnModel().getColumn(i).setPreferredWidth(columnWidth[i]);
        }

        tblTaiSan.setRowHeight(24);
        tblTaiSan.setSelectionBackground(Color.decode("#3a4d8f"));

        scTblTaiSan = new JScrollPane(tblTaiSan, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    }

    public void addControls() {
        /*
         * Lọc theo giá
         */
        JPanel pnLocTheoGiaItem1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
        JLabel lblTitleLocTheoGia = new JLabel("Nguyên giá");
        lblTitleLocTheoGia.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblTitleLocTheoGia.setForeground(Color.WHITE);
        pnLocTheoGiaItem1.add(lblTitleLocTheoGia);

        JPanel pnLoc = new JPanel(new GridBagLayout());
        GridBagConstraints gbcLoc = new GridBagConstraints();
        gbcLoc.fill = GridBagConstraints.BOTH;
        gbcLoc.insets = new Insets(5, 5, 5, 0);

        gbcLoc.gridx = 0;
        gbcLoc.gridy = 0;
        pnLoc.add(pnLocTheoGiaItem1, gbcLoc);

        for (int i = 0; i < rdoLocTheoGia.length; i++) {
            gbcLoc.insets = new Insets(5, 15, 5, 10);
            gbcLoc.gridx = 0;
            gbcLoc.gridy = i + 1;
            pnLoc.add(rdoLocTheoGia[i], gbcLoc);
        }

        pnLocTheoGiaItem1.setPreferredSize(new Dimension(200, 40));
        pnLocTheoGiaItem1.setBackground(Color.decode("#27ae5f"));


        JPanel pnLocTheoPhanLoaiItem1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
        JLabel lblTitleLocTheoPhanLoai = new JLabel("Phân loại tài sản");
        lblTitleLocTheoPhanLoai.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblTitleLocTheoPhanLoai.setForeground(Color.WHITE);
        pnLocTheoPhanLoaiItem1.add(lblTitleLocTheoPhanLoai);

        gbcLoc.insets = new Insets(5, 5, 5, 0);

        gbcLoc.gridx = 0;
        gbcLoc.gridy = rdoLocTheoGia.length + 1;
        pnLoc.add(pnLocTheoPhanLoaiItem1, gbcLoc);

        for (int i = 0; i < rdoPhanLoaiTS.length; i++) {
            gbcLoc.insets = new Insets(5, 15, 5, 10);
            gbcLoc.gridx = 0;
            gbcLoc.gridy = rdoLocTheoGia.length + i + 2;
            pnLoc.add(rdoPhanLoaiTS[i], gbcLoc);
        }

        pnLocTheoPhanLoaiItem1.setPreferredSize(new Dimension(200, 40));
        pnLocTheoPhanLoaiItem1.setBackground(Color.decode("#27ae5f"));

        pnLoc.setBackground(Color.WHITE);

        /*
         * Lọc theo nhà sản xuất
         */
        JPanel pnTimKiemNhaSanXuat = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        pnTimKiemNhaSanXuat.add(lblTimKiemNhaSanXuat);
        pnTimKiemNhaSanXuat.add(txtTimKiemNhaSanXuat);
        pnTimKiemNhaSanXuat.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY)));

        JPanel pnTem11 = new JPanel();
        pnTem11.setBackground(Color.WHITE);

        JPanel pnLeft = new JPanel(new BorderLayout());
        pnLeft.add(pnLoc, BorderLayout.NORTH);
        pnLeft.add(pnTem11, BorderLayout.CENTER);

        /*
         * Panel table tài sản
         */
        JPanel pnTKTaiSan = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnTKTaiSan.setBackground(Color.WHITE);
        pnTKTaiSan.add(lblTimKiem);
        pnTKTaiSan.add(txtTimKiem);

        if (LoginFrame.accountLogin.getVaiTro().getId() == CoreConstant.ROLE_RESOURCES) {
            pnTKTaiSan.add(btnAdd);
        }

        JPanel pnTableTaiSan = new JPanel(new BorderLayout());
        pnTableTaiSan.add(pnTKTaiSan, BorderLayout.NORTH);
        pnTableTaiSan.add(scTblTaiSan, BorderLayout.CENTER);

        /*
         * Panel right
         */
        JPanel pnRight = new JPanel(new BorderLayout());
        // pnRight.add(pnContent, BorderLayout.NORTH);
        pnRight.add(pnTableTaiSan, BorderLayout.CENTER);

        this.setLayout(new BorderLayout(10, 10));
        this.add(pnLeft, BorderLayout.WEST);
        this.add(pnRight, BorderLayout.CENTER);

    }

    public void addEvents() {

        indexTaiSan = 0;
        loadDataToTblTaiSan();
        tableSelected(indexTaiSan);

        btnAdd.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                PopupTaiSanChiTiet test = new PopupTaiSanChiTiet();
                test.setVisible(true);
            }
        });

        for (int i = 0; i < rdoLocTheoGia.length; i++) {
            rdoLocTheoGia[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                loadDataToTblTaiSan();
                if (txtTimKiem.getText().length() > 0) {
                    indexTimKiemTaiSan =- 0;
                    listTimKiemTaiSan = getListTimKiemTaiSan();
                    timKiemTaiSan();
                } else {
                    indexTaiSan = 0;
                    indexTimKiemTaiSan = 0;
                    tableSelected(indexTaiSan);
                }
                }
            });
        }

        for (int i = 0; i < rdoPhanLoaiTS.length; i++) {
            rdoPhanLoaiTS[i].addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                loadDataToTblTaiSan();
                if (txtTimKiem.getText().length() > 0) {
                    indexTimKiemTaiSan =- 0;
                    listTimKiemTaiSan = getListTimKiemTaiSan();
                    timKiemTaiSan();
                } else {
                    indexTaiSan = 0;
                    indexTimKiemTaiSan = 0;
                    tableSelected(indexTaiSan);
                }
                }

            });
        }

        // <editor-fold defaultstate="collapsed" desc="Tìm kiếm tài sản với txtSearch ">
        txtTimKiem.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (txtTimKiem.getText().length() > 0) {
                    listTimKiemTaiSan = getListTimKiemTaiSan();
                    timKiemTaiSan();
                } else {
                    indexTaiSan = 0;
                    indexTimKiemTaiSan = 0;
                    tableSelected(indexTaiSan);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (txtTimKiem.getText().length() > 0) {
                    listTimKiemTaiSan = getListTimKiemTaiSan();
                    timKiemTaiSan();
                } else {
                    indexTaiSan = 0;
                    indexTimKiemTaiSan = 0;
                    tableSelected(indexTaiSan);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });

        txtTimKiem.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER && listTimKiemTaiSan.size() > 0 && txtTimKiem.getText().length() > 0) {
                    indexTimKiemTaiSan = indexTimKiemTaiSan + 1;
                    timKiemTaiSan();
                }

                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    txtTimKiem.setText("");
                    indexTaiSan = 0;
                    tableSelected(indexTaiSan);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        // </editor-fold>

    }

    public void saveSuccess() {
        loadDataToTblTaiSan();
        for (TaiSan item : listTaiSan) {
            if (item.getId().equalsIgnoreCase(test.taiSan.getId())) {
                indexTaiSan = listTaiSan.indexOf(item);
                tableSelected(indexTaiSan);
                break;
            }
        }
    }

    public void refresh() {
        loadDataToTblTaiSan();
        tableSelected(indexTaiSan);
    }

    public void loadDataToTblTaiSan() {

        modelTblTaiSan.setRowCount(0);
        listTaiSan = SingletonDaoUtil.getTaiSanDaoImpl().searchByProperty(null, getStringLocNguyenGia(),
                getLocPhanLoai());
        if (listTaiSan.size() > 0) {
            TableColumnModel columnModel = tblTaiSan.getColumnModel();
            int columnCount = columnModel.getColumnCount();
            int i = 1;
            if (columnCount == 7) {
                for (TaiSan item : listTaiSan) {
                    long nguyengia = Long.parseLong(String.valueOf(item.getNguyenGia()));
                    modelTblTaiSan.addRow(new Object[] {
                            i,
                            item.getId(),
                            item.getTenTaiSan(),
                            MoneyUtil.castIntToMoney(nguyengia),
                            item.getThoiGianKhauHao() + " năm",
                            String.valueOf("Sửa" + item.getId()),
                            String.valueOf("Xóa" + item.getId()),
                    });
                    i++;
                }
            } else {
                for (TaiSan item : listTaiSan) {
                    long nguyengia = Long.parseLong(String.valueOf(item.getNguyenGia()));
                    modelTblTaiSan.addRow(new Object[] {
                            i,
                            item.getId(),
                            item.getTenTaiSan(),
                            MoneyUtil.castIntToMoney(nguyengia),
                            item.getThoiGianKhauHao() + " năm",
                            String.valueOf("Xem" + item.getId()),
                    });
                    i++;
                }
            }
        } else {

        }
        org.apache.log4j.Logger.getLogger(QuanLyTaiSan.class)
                .warn(listTaiSan);
    }

    public List<TaiSan> getListTimKiemTaiSan() {
        String str = txtTimKiem.getText().trim();
        Map<String, Object> map = new HashMap<>();
        map.put("mats", str);
        map.put("tents", str);
        List<TaiSan> list = new ArrayList<>();
        list = SingletonDaoUtil.getTaiSanDaoImpl().searchByProperty(map, getStringLocNguyenGia(), getLocPhanLoai());
        return list;
    }

    public void timKiemTaiSan() {
        if (listTimKiemTaiSan.size() > 0) {
            if (indexTimKiemTaiSan == listTimKiemTaiSan.size()) {
                indexTimKiemTaiSan = 0;
            }
            for (TaiSan item : listTaiSan) {
                if (item.getId().equalsIgnoreCase(listTimKiemTaiSan.get(indexTimKiemTaiSan).getId())) {
                    indexTaiSan = listTaiSan.indexOf(item);
                    tableSelected(indexTaiSan);
                    break;
                }
            }
        } else {
            indexTaiSan = 0;
            indexTimKiemTaiSan = 0;
            tblTaiSan.clearSelection();
        }
    }

    public String getStringLocNguyenGia() {
        String str = null;
        if (rdoLocTheoGia[0].isSelected())
            str = null;
        if (rdoLocTheoGia[1].isSelected())
            str = " NGUYENGIA < 1000000 ";
        if (rdoLocTheoGia[2].isSelected())
            str = (" NGUYENGIA >= 1000000 AND NGUYENGIA < 00000 ");
        if (rdoLocTheoGia[3].isSelected())
            str = (" NGUYENGIA >= 5000000 AND NGUYENGIA < 50000000 ");
        if (rdoLocTheoGia[4].isSelected())
            str = (" NGUYENGIA >= 50000000 ");
        return str;
    }

    public PhanLoai getLocPhanLoai() {
        PhanLoai phanLoai = new PhanLoai();

        for (int i=0; i<rdoPhanLoaiTS.length; i++) {
            if (rdoPhanLoaiTS[i].isSelected()) {
                if (i==0) {
                    phanLoai = null;
                } else {
                    phanLoai = listPhanLoai.get(i-1);
                }
                break;
            }
        }
        return phanLoai;
    }

    public void tableSelected(int i) {
        if (modelTblTaiSan.getRowCount() > 0) {
            tblTaiSan.setRowSelectionInterval(i, i);
            Rectangle rect = tblTaiSan.getCellRect(i, 0, true);
            tblTaiSan.scrollRectToVisible(rect);
        }
    }

    class ButtonEditor extends DefaultCellEditor {

        private static final long serialVersionUID = 1L;

        protected JButton button;

        private String label;

        private boolean isPushed;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                }
            });
        }

        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int column) {
            if (isSelected) {
                button.setForeground(table.getSelectionForeground());
                button.setBackground(table.getSelectionBackground());
            } else {
                button.setForeground(table.getForeground());
                button.setBackground(table.getBackground());
            }
            label = (value == null) ? "" : value.toString();
            button.setText(label.substring(0, 3));
            isPushed = true;
            return button;
        }

        public Object getCellEditorValue() {
            if (isPushed) {
                String id = label.substring(3, 10);
                if (label.substring(0, 3).equals("Sửa")) {
                    test.fillToForm(id);
                    test.lockByRole();
                    test.showFrame();
                    LoginFrame.mainFrame.setEnabled(false);
                } else if (label.substring(0, 3).equals("Xem")) {
                    test.fillToForm(id);
                    test.lockEdit();
                    test.removeControlBtns();
                    test.showFrame();
                    LoginFrame.mainFrame.setEnabled(false);
                } else {
                    JOptionPane.showMessageDialog(button, id + ": Dont delete me :( !");
                }
            }
            isPushed = false;
            return new String(label);
        }

        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }

        protected void fireEditingStopped() {
            super.fireEditingStopped();
        }
    }

    class ButtonRenderer extends JButton implements TableCellRenderer {

        private static final long serialVersionUID = 1L;

        public ButtonRenderer() {
            setOpaque(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column) {
            if (isSelected) {
                setForeground(table.getSelectionForeground());
                setBackground(table.getSelectionBackground());
            } else {
                setForeground(table.getForeground());
                setBackground(UIManager.getColor("Button.background"));
            }
            setText((value == null) ? "" : value.toString().substring(0, 3));
            return this;
        }
    }
}




