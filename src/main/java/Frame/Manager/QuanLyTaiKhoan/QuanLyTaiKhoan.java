package Frame.Manager.QuanLyTaiKhoan;

import Contant.CoreConstant;
import Entity.NhanVien;
import Entity.PhongBan;
import Entity.TaiKhoan;
import Entity.VaiTro;
import Frame.Login.LoginFrame;
import Utils.*;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.autocomplete.AutoCompleteDocument;
import org.jdesktop.swingx.autocomplete.AutoCompleteStyledDocument;
import sun.font.Decoration;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class QuanLyTaiKhoan extends JPanel {

    private static final long serialVersionUID = 1L;

    //<editor-fold desc="Component">
    // Swing
    private JLabel lblTenTaiKhoan, lblThongTinChiTiet, lblNhanVien, lblMatKhau, lblPhongBan,
            lblTinhTrang, lblQuyenHan, lblTimKiem, lblLocTheoVaiTro;

    private JComboBox<Object> cbxPhongBan, cbxVaiTro, cbxNhanVien,  cbxLocTheoVaiTro;
    private JTextField txtTenTaiKhoan, txtTimKiem;
    private JPasswordField pwfMatKhau;
    private JTable tblTaiKhoan;
    private DefaultTableModel model;
    private JScrollPane scTblTaiKhoan;
    private JButton[] button;
    private JCheckBox chkTinhTrang;

    // ArrayList
    private List<TaiKhoan> taiKhoanList = new ArrayList<>();
    private List<TaiKhoan> taiKhoanListTimKiem = new ArrayList<>();
    private List<VaiTro> vaiTroList = new ArrayList<>();
    private List<PhongBan> phongBanList = new ArrayList<>();
    private List<NhanVien> nhanVienList = new ArrayList<>();

    // Object
    private PhongBan phongBanSelected = new PhongBan();
    private NhanVien nhanVienSelected = new NhanVien();
    private TaiKhoan taiKhoanSelected = new TaiKhoan();
    private VaiTro vaiTroSelected = null;
    // Primitive
    private int stt;
    private int index = 0;
    private int indexSearch = 0;
    private int flagSave;
    private String messCancel;
    private boolean doneLoadCbx = false;
    //</editor-fold>

    public QuanLyTaiKhoan() {
        super();
        open();
    }

    public void open() {
        initComponents();
        addControls();
        addEvents();
    }

    public void initComponents() {

        //<editor-fold desc="Label">
        lblTenTaiKhoan = new JLabel("Tên tài khoản");
        lblPhongBan = new JLabel("Phòng ban");
        lblThongTinChiTiet = new JLabel("Thông tin chi tiết");
        lblNhanVien = new JLabel("Nhân viên");
        lblMatKhau = new JLabel("Mật khẩu");
        lblTinhTrang = new JLabel("Tình trạng");
        lblQuyenHan = new JLabel("Vai trò");
        lblTimKiem = new JLabel("Tìm kiếm");
        lblLocTheoVaiTro = new JLabel("Vai trò");
        lblThongTinChiTiet.setForeground(Color.WHITE);
        lblLocTheoVaiTro.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 15));
        lblThongTinChiTiet.setFont(new Font("Segoe UI", Font.BOLD, 15));

        lblQuyenHan.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        lblPhongBan.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        lblNhanVien.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        lblTenTaiKhoan.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        lblMatKhau.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        lblTinhTrang.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        lblTimKiem.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        //</editor-fold>

        //<editor-fold desc="Text Field">
        // TextField
        txtTenTaiKhoan = new JTextField();
        txtTimKiem = new JTextField();

        txtTenTaiKhoan.setMargin(new Insets(2, 5, 5, 2));
        txtTimKiem.setMargin(new Insets(2, 5, 2, 5));

        txtTenTaiKhoan.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        txtTimKiem.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        //</editor-fold>

        //<editor-fold desc="ComboBox">
        cbxPhongBan = new JComboBox<Object>();
        cbxNhanVien = new JComboBox<Object>();
        cbxLocTheoVaiTro = new JComboBox<Object>();
        cbxVaiTro = new JComboBox<Object>();
        cbxVaiTro = new JComboBox<Object>();

        cbxPhongBan.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        cbxNhanVien.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        cbxVaiTro.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        cbxLocTheoVaiTro.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));

        cbxLocTheoVaiTro.setEditable(false);
        cbxPhongBan.setEditable(true);

//        AutoCompleteDecorator.decorate(cbxLocTheoVaiTro);
        AutoCompleteDecorator.decorate(cbxNhanVien);
        AutoCompleteDecorator.decorate(cbxPhongBan);
        //</editor-fold>

        //<editor-fold desc="Password Field">
        pwfMatKhau = new JPasswordField();
        pwfMatKhau.setMargin(new Insets(2, 5, 5, 2));
        pwfMatKhau.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        //</editor-fold>

        //<editor-fold desc="Checkbox">
        chkTinhTrang = new JCheckBox("Khóa");
        chkTinhTrang.setBackground(Color.WHITE);
        chkTinhTrang.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        //</editor-fold>

        //<editor-fold desc="Button">
        Object[] nameButton = new Object[] { "Thêm mới", "Sửa", "Lưu", "Bỏ qua" };
        button = ComponentUtils.createButton(nameButton, new Font("Segoe UI", Font.ROMAN_BASELINE, 12),
                null, new Cursor(Cursor.HAND_CURSOR));
        for (int i = 1; i < button.length; i++) {
             button[i].setPreferredSize(button[0].getPreferredSize());
        }
        //</editor-fold>

        //<editor-fold desc="Description">
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[] {
                "STT", "TÊN TÀI KHOẢN", "NHÂN VIÊN", "VAI TRÒ"
        });
        tblTaiKhoan = new JTable(model) {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                JLabel c = (JLabel) super.prepareRenderer(renderer, row, column);
                c.setFont(new Font("Segoe UI", 0, 13));
                c.setHorizontalAlignment(JLabel.LEFT);
                return c;
            }
        };
        tblTaiKhoan.setRowHeight(22);
        // tblTaiKhoan.setSelectionBackground(Color.decode("#3a4d8f"));
        tblTaiKhoan.setIntercellSpacing(new Dimension(0, 0));
//        tblTaiKhoan.setShowGrid(false);
        tblTaiKhoan.setRowSorter(new TableRowSorter<DefaultTableModel>(model));
        tblTaiKhoan.setAutoCreateRowSorter(true);
        tblTaiKhoan.setSelectionBackground(Color.decode("#3a4d8f"));

        scTblTaiKhoan = new JScrollPane(tblTaiKhoan, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JTableHeader tblHeaderD = tblTaiKhoan.getTableHeader();
        ((DefaultTableCellRenderer) tblHeaderD.getDefaultRenderer()).setHorizontalAlignment(JLabel.LEFT);
         tblHeaderD.getColumnModel().getColumn(0).setPreferredWidth(30);
         tblHeaderD.getColumnModel().getColumn(1).setPreferredWidth(200);
         tblHeaderD.getColumnModel().getColumn(2).setPreferredWidth(100);
         tblHeaderD.getColumnModel().getColumn(3).setPreferredWidth(100);
        //</editor-fold>
    }

    public void addControls() {

        //<editor-fold desc="Panel Header">
        JPanel pnLeftHeader = new JPanel(new GridBagLayout());
        pnLeftHeader.setBackground(Color.WHITE);
        GridBagConstraints gbcPnLeftHeader = new GridBagConstraints();
        gbcPnLeftHeader.fill = GridBagConstraints.BOTH;

        gbcPnLeftHeader.insets = new Insets(5, 10, 5, 10);

        gbcPnLeftHeader.weightx = 0;
        gbcPnLeftHeader.gridx = 0;
        gbcPnLeftHeader.gridy = 0;
        pnLeftHeader.add(lblLocTheoVaiTro, gbcPnLeftHeader);
        gbcPnLeftHeader.gridy = 1;
        pnLeftHeader.add(lblTimKiem, gbcPnLeftHeader);

        gbcPnLeftHeader.ipady = 5;
        gbcPnLeftHeader.weightx = 1;
        gbcPnLeftHeader.gridx = 1;
        gbcPnLeftHeader.gridy = 0;
        pnLeftHeader.add(cbxLocTheoVaiTro, gbcPnLeftHeader);
        gbcPnLeftHeader.gridy = 1;
        pnLeftHeader.add(txtTimKiem, gbcPnLeftHeader);

        pnLeftHeader.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        //</editor-fold>

        //<editor-fold desc="Panel Table">
        JPanel pnTable = new JPanel(new BorderLayout());
        pnTable.setBackground(Color.WHITE);
        pnTable.add(scTblTaiKhoan, BorderLayout.CENTER);
        //</editor-fold>

        //<editor-fold desc="Panel Left">
        JPanel pnLeft = new JPanel(new BorderLayout(10, 10));
        pnLeft.add(pnLeftHeader, BorderLayout.NORTH);
        pnLeft.add(pnTable, BorderLayout.CENTER);
        //</editor-fold>

        //<editor-fold desc="Panel content">
            //<editor-fold desc="Panel Chi tiết">
            JPanel pnChiTiet = new JPanel(new GridBagLayout());
            pnChiTiet.setBackground(Color.WHITE);
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.BOTH;
            gbc.insets = new Insets(5, 10, 5, 10);

            JPanel pnTitle = new JPanel(new FlowLayout(FlowLayout.CENTER));
            pnTitle.setBackground(Color.decode("#27ae5f"));
            pnTitle.add(lblThongTinChiTiet);

            gbc.gridwidth = 2;
            gbc.gridx = 0;
            gbc.gridy = 0;
            pnChiTiet.add(pnTitle, gbc);

            gbc.gridwidth = 1;
            gbc.gridx = 0;
            gbc.gridy = 1;
            pnChiTiet.add(lblQuyenHan, gbc);

            gbc.gridy = 2;
            pnChiTiet.add(lblPhongBan, gbc);
            gbc.gridy = 3;
            pnChiTiet.add(lblNhanVien, gbc);
            gbc.gridy = 4;
            pnChiTiet.add(lblTenTaiKhoan, gbc);
            gbc.gridy = 5;
            pnChiTiet.add(lblMatKhau, gbc);
            gbc.gridy = 6;
            pnChiTiet.add(lblTinhTrang, gbc);

            gbc.weightx = 1;
            gbc.gridx = 1;
            gbc.gridy = 1;
            pnChiTiet.add(cbxVaiTro, gbc);
            gbc.gridy = 2;
            pnChiTiet.add(cbxPhongBan, gbc);
            gbc.gridy = 3;
            pnChiTiet.add(cbxNhanVien, gbc);
            gbc.gridy = 4;
            pnChiTiet.add(txtTenTaiKhoan, gbc);
            gbc.gridy = 5;
            pnChiTiet.add(pwfMatKhau, gbc);
            gbc.gridy = 6;
            pnChiTiet.add(chkTinhTrang, gbc);
            //</editor-fold>

            /* pnQuyenHan.setBorder( new MatteBorder(0, 1, 0, 0, Color.LIGHT_GRAY)); */

            //<editor-fold desc="Panel button">
            // Panel Content
            JPanel pnButton = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
            pnButton.setBackground(Color.WHITE);
            for (JButton item : button) {
                pnButton.add(item);
            }
            //</editor-fold>

        JPanel pnContent = new JPanel(new BorderLayout(10, 10));
        pnContent.setBackground(Color.WHITE);
        pnContent.add(pnChiTiet, BorderLayout.NORTH);
        pnContent.add(pnButton, BorderLayout.CENTER);
        //</editor-fold>

        this.setLayout(new BorderLayout(10, 10));
        this.add(pnLeft, BorderLayout.CENTER);
        this.add(pnContent, BorderLayout.EAST);

    }

    public void addEvents() {
        statusDefault();
        loadDataToCbxVaiTro();
        loadDataToCbxPhongBan();
        loadDataToCbxNhanVien();
        loadDataToTable();
        fillToForm(index);

        // Action CbxPhongBan
        cbxPhongBan.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
                if (cbxPhongBan.getSelectedIndex() > 0) {
                    loadDataToCbxNhanVien();
                }
            }
        });

        cbxLocTheoVaiTro.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
                loadDataToTable();
                index = 0;
                fillToForm(index);
            }
        });


        // Action Button
        for (int i = 0; i < button.length; i++) {
            final int finalI = i;
            button[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    switch (finalI) {
                        case 0:
                            setBtnNew();
                            break;
                        case 1:
                            setBtnEdit();
                            break;
                        case 2:
                            setBtnSave();
                            break;
                        case 3:
                            setBtnCancel();
                            break;
                    }
                }
            });
        }

        // Table Mouse Click
        tblTaiKhoan.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if (flagSave == CoreConstant.FLAG_EMTY) {
                    index = tblTaiKhoan.getSelectedRow();
                    fillToForm(index);
                    statusDefault();
                } else if (flagSave == CoreConstant.FLAG_INSERT){
                    boolean answer = DialogUtils.showConfirmDialog(messCancel);
                    if (answer){
                        index = tblTaiKhoan.getSelectedRow();
                        fillToForm(index);
                        statusDefault();
                    } else {
                        tblTaiKhoan.clearSelection();
                    }
                } else {
                    boolean answer = DialogUtils.showConfirmDialog(messCancel);

                    if (answer){
                        index = tblTaiKhoan.getSelectedRow();
                        fillToForm(index);
                        statusDefault();
                    } else {
                        tblTaiKhoan.setRowSelectionInterval(index, index);
                        Rectangle rect = tblTaiKhoan.getCellRect(index, 0, true);
                        tblTaiKhoan.scrollRectToVisible(rect);
                    }
                }
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

        // Tìm kiếm Nhân viên với txtSearch
        txtTimKiem.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (txtTimKiem.getText().length() > 0) {
                    taiKhoanListTimKiem = getTaiKhoanListTimKiem();
                    search();

                    if (taiKhoanListTimKiem.size() > 0) {
                        button[1].setEnabled(true);
                    } else {
                        button[1].setEnabled(false);
                    }

                } else {
                    index = 0;
                    indexSearch = 0;
                    fillToForm(index);
                    button[1].setEnabled(true);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (txtTimKiem.getText().length() > 0) {
                    taiKhoanListTimKiem = getTaiKhoanListTimKiem();
                    search();
                    if (taiKhoanListTimKiem.size() > 0) {
                        button[1].setEnabled(true);
                    } else {
                        button[1].setEnabled(false);
                    }

                } else {
                    index = 0;
                    indexSearch = 0;
                    fillToForm(index);
                    button[1].setEnabled(true);
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
                if (e.getKeyCode() == KeyEvent.VK_ENTER && taiKhoanListTimKiem.size() > 0
                        && txtTimKiem.getText().length() > 0) {
                    indexSearch = indexSearch + 1;
                    search();
                }

                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    txtTimKiem.setText("");
                    index = 0;
                    fillToForm(index);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    public void setBtnNew() {
        setStatus(CoreConstant.FLAG_INSERT);
        clearForm();
        tblTaiKhoan.clearSelection();
    }

    public void setBtnEdit() {
        setStatus(CoreConstant.FLAG_UPDATE);
    }

    public void setBtnSave() {
        if (validator()) {
            if (flagSave == CoreConstant.FLAG_INSERT) {
                TaiKhoan taiKhoan = getModel();
                taiKhoanSelected = SingletonDaoUtil.getTaiKhoanDaoImpl().save(taiKhoan);
                saveSuccess();
            } else if (flagSave == CoreConstant.FLAG_UPDATE) {
                TaiKhoan taiKhoan = getModel();
                taiKhoan.setId(taiKhoanSelected.getId());
                taiKhoanSelected = SingletonDaoUtil.getTaiKhoanDaoImpl().update(taiKhoan);
                saveSuccess();
            }
        }
    }

    public void setBtnCancel() {
        if (DialogUtils.showConfirmDialog(messCancel)) {
            statusDefault();
            fillToForm(index);
        } else {
            fillToForm(index);
        }
    }

    public void saveSuccess() {
        loadDataToTable();
        if(taiKhoanList.size() > 0) {
            for (TaiKhoan item : taiKhoanList) {
                if (item.getId() == taiKhoanSelected.getId()) {
                    index = taiKhoanList.indexOf(item);
                    fillToForm(index);
                    break;
                }
            }
        }
        statusDefault();
    }

    public void search() {
        if (taiKhoanListTimKiem.size() > 0) {

            if (indexSearch == taiKhoanListTimKiem.size()) {
                indexSearch = 0;
            }

            for (TaiKhoan item : taiKhoanList) {
                if (item.getId() == (taiKhoanListTimKiem.get(indexSearch).getId())) {
                    index = taiKhoanList.indexOf(item);
                    fillToForm(index);
                    break;
                }
            }
        } else {
            clearForm();
            index = 0;
            indexSearch = 0;
            tblTaiKhoan.clearSelection();
        }
    }

    public List<TaiKhoan> getTaiKhoanListTimKiem() {
        Map<String, Object> map = new HashMap<>();
        map.put("TAIKHOAN", txtTimKiem.getText());
        map.put("NHANVIEN", txtTimKiem.getText());
        List<TaiKhoan> list = new ArrayList<>();

        if (cbxLocTheoVaiTro.getSelectedIndex() == 0) {
            list = SingletonDaoUtil.getTaiKhoanDaoImpl().searchByProperty(map, null);
        } else {
            list = SingletonDaoUtil.getTaiKhoanDaoImpl().searchByProperty(map, vaiTroSelected);
        }
        return list;
    }

    /* Load dữ liệu vào ComboBox Phòng ban */
    public void loadDataToCbxPhongBan() {
        doneLoadCbx = false;
        cbxPhongBan.removeAllItems();

        phongBanList = SingletonDaoUtil.getPhongBanDaoImpl().findAll();
        for (PhongBan item : phongBanList) {
            cbxPhongBan.addItem(item);
        }
        doneLoadCbx = true;
    }

    public void loadDataToCbxNhanVien() {
        cbxNhanVien.removeAllItems();
        PhongBan phongBan = (PhongBan) cbxPhongBan.getSelectedItem();
        if (phongBan != null) {
            nhanVienList = SingletonDaoUtil.getNhanVienDaoImpl().getByPhongBan(phongBan);
            for (NhanVien item : nhanVienList) {
                cbxNhanVien.addItem(item);
            }
        }
    }

    public void loadDataToCbxVaiTro() {
        cbxVaiTro.removeAllItems();
        cbxLocTheoVaiTro.removeAllItems();
        vaiTroList = SingletonDaoUtil.getVaiTroDaoImpl().findAll();

        cbxLocTheoVaiTro.addItem("Tất cả");
        if (vaiTroList.size() > 0) {
            for (VaiTro item : vaiTroList) {
                cbxVaiTro.addItem(item);
                cbxLocTheoVaiTro.addItem(item);
            }
        }
    }

    public void loadDataToTable() {

        if (cbxLocTheoVaiTro.getSelectedIndex() == 0) {
            taiKhoanList = SingletonDaoUtil.getTaiKhoanDaoImpl().findAll();
        } else {
            vaiTroSelected = (VaiTro) cbxLocTheoVaiTro.getSelectedItem();
            taiKhoanList = SingletonDaoUtil.getTaiKhoanDaoImpl().getListByVaiTro(vaiTroSelected);
        }

        model.setRowCount(0);
        if (taiKhoanList.size() > 0) {
            button[1].setEnabled(true);

            int stt = 1;
            for (TaiKhoan item : taiKhoanList) {
                NhanVien nhanVien = SingletonDaoUtil.getNhanVienDaoImpl().findById(item.getNhanVien());
                model.addRow(new Object[] { stt, item.getTenTaiKhoan(), nhanVien.getTenNhanVien(), item.getVaiTro().getTenvaitro() });
                stt++;
            }
        } else {
            clearForm();
            button[1].setEnabled(false);
        }
    }

    public void statusDefault() {
        flagSave = CoreConstant.FLAG_EMTY;

        button[0].setVisible(true);
        button[1].setVisible(true);
        button[2].setVisible(false);
        button[3].setVisible(false);

        cbxVaiTro.setEnabled(false);
        cbxPhongBan.setEnabled(false);
        cbxNhanVien.setEnabled(false);
        cbxLocTheoVaiTro.setEnabled(true);

        txtTenTaiKhoan.setEditable(false);
        pwfMatKhau.setEditable(false);
        chkTinhTrang.setEnabled(false);

        txtTimKiem.setEditable(true);

    }

    public void setStatus(int insertable) {
        flagSave = insertable;

        switch (insertable) {
            case CoreConstant.FLAG_INSERT:
                messCancel = "Bạn có muốn bỏ qua thao tác thêm mới không?";
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
        button[0].setVisible(false);
        button[1].setVisible(false);

        button[2].setVisible(true);
        button[3].setVisible(true);

        cbxVaiTro.setEnabled(true);
        cbxPhongBan.setEnabled(true);
        cbxNhanVien.setEnabled(true);
        txtTenTaiKhoan.setEditable(true);
        pwfMatKhau.setEditable(true);
        chkTinhTrang.setEnabled(true);

        cbxLocTheoVaiTro.setEnabled(false);

        txtTimKiem.setEditable(false);
        cbxPhongBan.requestFocus();
    }

    public void setModel(TaiKhoan taiKhoan) {
        if (taiKhoan != null) {
            NhanVien nhanVien = SingletonDaoUtil.getNhanVienDaoImpl().findById(taiKhoan.getNhanVien());

            for (VaiTro item : vaiTroList) {
                if (item.getId() == taiKhoan.getVaiTro().getId()) {
                    cbxVaiTro.setSelectedItem(item);
                    break;
                }
            }

            for (PhongBan item : phongBanList) {
                if (item.getId().equalsIgnoreCase(nhanVien.getPhongBan().getId())) {
                    cbxPhongBan.setSelectedItem(item);
                    loadDataToCbxNhanVien();
                    for (NhanVien item1 : nhanVienList) {
                        if (item1.getId().equalsIgnoreCase(nhanVien.getId())) {
                            cbxNhanVien.setSelectedItem(item1);
                            break;
                        }
                    }
                    break;
                }
            }

            txtTenTaiKhoan.setText(taiKhoan.getTenTaiKhoan());
            pwfMatKhau.setText(taiKhoan.getMatKhau());
            chkTinhTrang.setSelected(taiKhoan.isTinhTrang() == true ? true : false);

        } else {
            cbxVaiTro.setSelectedIndex(0);
            cbxPhongBan.setSelectedIndex(-1);
            cbxNhanVien.setSelectedIndex(-1);
            txtTenTaiKhoan.setText("");
            pwfMatKhau.setText("");
        }
    }

    public TaiKhoan getModel() {
        NhanVien nhanVien = (NhanVien) cbxNhanVien.getSelectedItem();

        TaiKhoan taiKhoan = new TaiKhoan();
        taiKhoan.setVaiTro((VaiTro) cbxVaiTro.getSelectedItem());
        taiKhoan.setNhanVien(nhanVien.getId());
        taiKhoan.setTenTaiKhoan(txtTenTaiKhoan.getText());
        taiKhoan.setMatKhau(String.valueOf(pwfMatKhau.getPassword()));
        taiKhoan.setTinhTrang(chkTinhTrang.isSelected() == true ? true : false);
        return taiKhoan;
    }

    public void clearForm() {
        setModel(null);
    }

    public void fillToForm(int i) {
        if (taiKhoanList.size() > 0) {
            tblTaiKhoan.setRowSelectionInterval(i, i);
            Rectangle rect = tblTaiKhoan.getCellRect(i, 0, true);
            tblTaiKhoan.scrollRectToVisible(rect);

            stt = Integer.parseInt(tblTaiKhoan.getValueAt(i, 0).toString());
            taiKhoanSelected = taiKhoanList.get(stt - 1);
            setModel(taiKhoanSelected);
            if (taiKhoanSelected.getId() == LoginFrame.accountLogin.getId()) {
                button[1].setEnabled(false);
            } else {
                button[1].setEnabled(true);
            }
        } else {
            clearForm();
        }
    }

    private boolean validator() {
        String pwf = String.valueOf(pwfMatKhau.getPassword());

        if (cbxVaiTro.getSelectedIndex() < 0) {
            DialogUtils.showMessageDialog("Vui lòng chọn vai trò!", CoreConstant.TYPE_WARNING);
            cbxVaiTro.requestFocus();
            return false;
        }

        if (cbxPhongBan.getSelectedIndex() < 0) {
            DialogUtils.showMessageDialog("Vui lòng chọn phòng ban!", CoreConstant.TYPE_WARNING);
            cbxPhongBan.requestFocus();
            return false;
        }

        if (cbxNhanVien.getSelectedIndex() < 0) {
            DialogUtils.showMessageDialog("Vui lòng chọn nhân viên!", CoreConstant.TYPE_WARNING);
            cbxNhanVien.requestFocus();
            return false;
        }

        if (txtTenTaiKhoan.getText().length() == 0) {
            DialogUtils.showMessageDialog("Tên tài khoản không được trống, vui lòng nhập đầy đủ", CoreConstant.TYPE_WARNING);
            txtTenTaiKhoan.requestFocus();
            return false;
        }

        if (pwf.length() < 6) {
            DialogUtils.showMessageDialog("Mật khẩu phải chứa ít nhất 6 ký tự, vui lòng nhập đầy đủ!", CoreConstant.TYPE_WARNING);
            pwfMatKhau.requestFocus();
            return false;
        }

        if (flagSave == CoreConstant.FLAG_INSERT) {
            if (SingletonDaoUtil.getTaiKhoanDaoImpl().checkTenTaiKhoanExist(txtTenTaiKhoan.getText())) {
                DialogUtils.showMessageDialog("Tên tài khoản đã tồn tại! Vui lòng nhập lại!", CoreConstant.TYPE_WARNING);
                txtTenTaiKhoan.requestFocus();
                return false;
            }
        }

        if (flagSave == CoreConstant.FLAG_UPDATE) {
            if (!txtTenTaiKhoan.getText().equalsIgnoreCase(taiKhoanSelected.getTenTaiKhoan())) {
                if (SingletonDaoUtil.getTaiKhoanDaoImpl().checkTenTaiKhoanExist(txtTenTaiKhoan.getText())) {
                    DialogUtils.showMessageDialog("Tên tài khoản đã tồn tại! Vui lòng nhập lại!", CoreConstant.TYPE_WARNING);
                    txtTenTaiKhoan.requestFocus();
                    return false;
                }
            }
        }

        return true;
    }

}
