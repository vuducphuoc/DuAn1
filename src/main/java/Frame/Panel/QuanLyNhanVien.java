package Frame.Panel;

import Contant.CoreConstant;
import Entity.NhanVien;
import Entity.PhongBan;
import Utils.DateUtil;
import Utils.DialogUtils;
import Utils.SingletonDaoUtil;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.util.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QuanLyNhanVien extends JPanel{
    public QuanLyNhanVien() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(QuanLyNhanVien.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(QuanLyNhanVien.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(QuanLyNhanVien.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(QuanLyNhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
        open();
        doneLoad = true;
    }

    public void open() {
        initComponents();
        addControls();
        statusDefault();
        loadDataToTblDepartment();
        getPBSelected(indexDepartment);
        loadDataToTblEmployee(pbSelected);
        fillToFormNV(indexEmployee);
        addEvents();
    }

    public void initComponents() {

        //<editor-fold desc="LABEL">
        lblTitle = new JLabel("QUẢN LÝ NHÂN VIÊN");
        lblId = new JLabel("Mã nhân viên");
        lblName = new JLabel("Tên nhân viên");
        lblSex = new JLabel("Giới tính");
        lblBirthDay = new JLabel("Ngày sinh");
        lblUsername = new JLabel("Email");
        lblRole = new JLabel("Vai trò");
        lblBirthDay = new JLabel("Ngày sinh");
        lblAddress = new JLabel("Địa chỉ");
        lblSearchEmployee = new JLabel("Tìm kiếm");
        lblSearchDepartment = new JLabel("Tìm kiếm");
        //</editor-fold>

        //<editor-fold desc="TEXT FIELD">
        txtId = new JTextField();
        txtName = new JTextField();
        txtBirthday = new JTextField();
        txtUsername = new JTextField();
        txtSearchEmployee = new JTextField();
        txtSearchDepartment = new JTextField();

        txaAddress = new JTextArea();
        scAddress = new JScrollPane(txaAddress,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        txtSearchDepartment.setPreferredSize(new Dimension(200, 25));
        txtSearchEmployee.setPreferredSize(new Dimension(200, 25));

        // Set Margin
        txtId.setMargin(new Insets(0, 5, 0, 5));
        txtUsername.setMargin(new Insets(0, 5, 0, 5));
        txtName.setMargin(new Insets(0, 5, 0, 5));
        txtBirthday.setMargin(new Insets(0, 5, 0, 5));
        txtSearchDepartment.setMargin(new Insets(0, 5, 0, 5));
        txtSearchEmployee.setMargin(new Insets(0, 5, 0, 5));
        txaAddress.setMargin(new Insets(0, 5, 0, 5));
        //</editor-fold>

        //<editor-fold desc="CUSTOM FONT - SIZE">
        lblId.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 13));
        lblName.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 13));
        lblSex.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 13));
        lblRole.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 13));
        lblAddress.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 13));
        lblBirthDay.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 13));
        lblUsername.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 13));
        lblSearchEmployee.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 13));
        //</editor-fold>

        //<editor-fold desc="RADIO BUTTON">
        rdoAdmin = new JRadioButton("Quản trị");
        rdoEmployee = new JRadioButton("Nhân viên");
        rdoMale = new JRadioButton("Nam");
        rdoFemale = new JRadioButton("Nữ");

        btgSex = new ButtonGroup();
        btgSex.add(rdoMale);
        btgSex.add(rdoFemale);

        btgRole = new ButtonGroup();
        btgRole.add(rdoAdmin);
        btgRole.add(rdoEmployee);

        rdoEmployee.setSelected(true);
        rdoMale.setSelected(true);

        rdoAdmin.setMargin(new Insets(0, 0, 0, 20));
        rdoMale.setMargin(new Insets(0, 0, 0, 20));
        //</editor-fold>

        //<editor-fold desc="BẢNG PHÒNG BAN">
        modelDepartment = new DefaultTableModel();
        modelDepartment.setColumnIdentifiers(new Object[] {"STT", "Tên phòng ban"});
        tblDepartment = new JTable(modelDepartment){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                JLabel c = (JLabel) super.prepareRenderer(renderer, row, column);
                if (column == 0) {
                    c.setHorizontalAlignment(JLabel.CENTER);
                } else {
                    c.setHorizontalAlignment(JLabel.LEFT);
                }
                return c;
            }
        };
        JTableHeader tblHeaderD = tblDepartment.getTableHeader();
        ((DefaultTableCellRenderer) tblHeaderD.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        tblHeaderD.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblHeaderD.getColumnModel().getColumn(1).setPreferredWidth(280);
        tblDepartment.setRowHeight(25);
        tblDepartment.setSelectionBackground(Color.decode("#3a4d8f"));
        //</editor-fold>

        //<editor-fold desc="BẢNG NHÂN VIÊN">
        modelEmployee = new DefaultTableModel();
        modelEmployee.setColumnIdentifiers(new Object[] {"STT", "Mã nhân viên","Tên nhân viên", "Giới tính", "Chức vụ"});
        tblEmployee = new JTable(modelEmployee){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                JLabel c = (JLabel) super.prepareRenderer(renderer, row, column);
                if (column == 2) {
                    c.setHorizontalAlignment(JLabel.LEFT);
                } else {
                    c.setHorizontalAlignment(JLabel.CENTER);
                }
                return c;
            }
        };
        JTableHeader tblHeaderE = tblEmployee.getTableHeader();
        ((DefaultTableCellRenderer) tblHeaderE.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        tblHeaderE.getColumnModel().getColumn(0).setPreferredWidth(20);
        tblHeaderE.getColumnModel().getColumn(1).setPreferredWidth(150);
        tblHeaderE.getColumnModel().getColumn(2).setPreferredWidth(300);
        tblHeaderE.getColumnModel().getColumn(3).setPreferredWidth(50);
        tblHeaderE.getColumnModel().getColumn(4).setPreferredWidth(100);
        tblEmployee.setRowHeight(25);
        tblEmployee.setSelectionBackground(Color.decode("#3a4d8f"));
        //</editor-fold>

        //<editor-fold desc="Description">
        scTblDepartment = new JScrollPane(tblDepartment, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scTblEmployee = new JScrollPane(tblEmployee, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        tblDepartment.setRowSorter(new TableRowSorter(modelDepartment));
        tblDepartment.setAutoCreateRowSorter(true);
        tblEmployee.setRowSorter(new TableRowSorter(modelEmployee));
        tblEmployee.setAutoCreateRowSorter(true);
        //</editor-fold>

        //<editor-fold desc="BUTTON">
        btnSearchDepartment = new JButton("Tìm tiếp");
        btnSearchEmployee = new JButton("Tìm tiếp");
        btnCancel = new JButton("Bỏ qua");
        btnDelete = new JButton("Xóa");
        btnEdit = new JButton("Sửa");
        btnExport = new JButton("Kết xuất");
        btnNew = new JButton("Thêm mới");
        btnSave = new JButton("Lưu");

        btnCancel.setIcon(new ImageIcon("src/Image/icon-cancel.png"));
        btnDelete.setIcon(new ImageIcon("src/Image/icon-delete.png"));
        btnEdit.setIcon(new ImageIcon("src/Image/icon-edit.png"));
        btnNew.setIcon(new ImageIcon("src/Image/icon-new.png"));
        btnSave.setIcon(new ImageIcon("src/Image/icon-save.png"));

        btnCancel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnDelete.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEdit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnNew.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSearchEmployee.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSearchDepartment.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnExport.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSave.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnDelete.setPreferredSize(btnNew.getPreferredSize());
        btnSearchEmployee.setPreferredSize(btnNew.getPreferredSize());
        btnSave.setPreferredSize(btnNew.getPreferredSize());
        btnCancel.setPreferredSize(btnNew.getPreferredSize());
        btnEdit.setPreferredSize(btnNew.getPreferredSize());
        //</editor-fold>

        lblId.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 14));
        lblName.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 14));
        lblSex.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 14));
        lblUsername.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 14));
        lblBirthDay.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 14));
        lblAddress.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 14));
        lblRole.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 14));
        lblSearchDepartment.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 14));

        txtId.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 13));
        txtName.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 13));
        rdoMale.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 13));
        rdoFemale.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 13));
        rdoAdmin.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 13));
        rdoEmployee.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 13));
        txtBirthday.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 13));
        txaAddress.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 13));
        txtUsername.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 13));


    }

    public void addControls() {
        // LEFT
        JPanel pnSearchDepartment = new JPanel();
        pnSearchDepartment.add(txtSearchDepartment);
        pnSearchDepartment.add(btnSearchDepartment);

        JPanel pnTblDepartment = new JPanel();
        pnTblDepartment.add(scTblDepartment);

        JPanel pnLeft = new JPanel(new BorderLayout());
        pnLeft.add(pnSearchDepartment, BorderLayout.NORTH);
        pnLeft.add(scTblDepartment, BorderLayout.CENTER);

        //RIGHT
        JPanel pnSex = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        pnSex.add(rdoMale); pnSex.add(rdoFemale);

        JPanel pnRole = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        pnRole.add(rdoAdmin); pnRole.add(rdoEmployee);

        pnRole.setPreferredSize(new Dimension(300, 15));
        pnSex.setPreferredSize(pnRole.getPreferredSize());

        JPanel pnDetailEmployee = new JPanel();
        pnDetailEmployee.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        gbc.insets = new Insets(5, 10, 5, 10);

        gbc.ipady = 7;

        gbc.gridx = 0;
        gbc.gridy = 0;
        pnDetailEmployee.add(lblId, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        pnDetailEmployee.add(lblSex, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        pnDetailEmployee.add(lblAddress, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        pnDetailEmployee.add(txtId, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        pnDetailEmployee.add(pnSex, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridheight = 2;
        pnDetailEmployee.add(scAddress, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        pnDetailEmployee.add(lblName, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        pnDetailEmployee.add(lblRole, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        pnDetailEmployee.add(lblBirthDay, gbc);

        gbc.gridx = 2;
        gbc.gridy = 3;
        pnDetailEmployee.add(lblUsername, gbc);


        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;

        gbc.gridx = 3;
        gbc.gridy = 0;
        pnDetailEmployee.add(txtName, gbc);

        gbc.gridx = 3;
        gbc.gridy = 1;
        pnDetailEmployee.add(pnRole, gbc);

        gbc.gridx = 3;
        gbc.gridy = 2;
        pnDetailEmployee.add(txtBirthday, gbc);

        gbc.gridx = 3;
        gbc.gridy = 3;
        pnDetailEmployee.add(txtUsername, gbc);

        JPanel pnButtonEmployee = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        pnButtonEmployee.add(btnNew);
        pnButtonEmployee.add(btnEdit);
        pnButtonEmployee.add(btnDelete);
        pnButtonEmployee.add(btnSave);
        pnButtonEmployee.add(btnCancel);

        JPanel pnRightTop = new JPanel(new BorderLayout(0, 20));
        pnRightTop.add(pnDetailEmployee, BorderLayout.CENTER);
        pnRightTop.add(pnButtonEmployee, BorderLayout.SOUTH);

        //
        JPanel pnTblEmployeeTop = new JPanel(new BorderLayout());
        JPanel pnSearchEmployee = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        pnSearchEmployee.add(lblSearchEmployee);
        pnSearchEmployee.add(txtSearchEmployee);
        pnSearchEmployee.add(btnSearchEmployee);
        JPanel pnExport = new JPanel(new FlowLayout(FlowLayout.RIGHT, 30, 10));

        JPanel pnTemp2 = new JPanel();
        pnTblEmployeeTop.add(pnSearchEmployee, BorderLayout.WEST);
        pnTblEmployeeTop.add(pnTemp2, BorderLayout.CENTER);
        pnTblEmployeeTop.add(pnExport, BorderLayout.EAST);

        JPanel pnTblEmployee = new JPanel(new BorderLayout());
        pnTblEmployee.add(pnTblEmployeeTop, BorderLayout.NORTH);
        pnTblEmployee.add(scTblEmployee, BorderLayout.CENTER);

        JPanel pnRight = new JPanel(new BorderLayout());
        pnRight.add(pnRightTop, BorderLayout.NORTH);
        pnRight.add(pnTblEmployee, BorderLayout.CENTER);

        JPanel pnMain = new JPanel(new BorderLayout(10, 10));
        pnMain.add(pnLeft, BorderLayout.WEST);
        pnMain.add(pnRight, BorderLayout.CENTER);

        JPanel pnTemp3 = new JPanel();
        pnTemp3.setPreferredSize(new Dimension(0, 10));

        this.setLayout(new BorderLayout(0,10));
        this.add(pnTemp3, BorderLayout.NORTH);;
        this.add(pnMain, BorderLayout.CENTER);;

        pnLeft.setPreferredSize(new Dimension(340, 0));
        pnSearchDepartment.setPreferredSize(new Dimension(0,40));
    }

    public void addEvents() {

        tblDepartment.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                indexDepartment = tblDepartment.getSelectedRow();
                getPBSelected(indexDepartment);
                loadDataToTblEmployee(pbSelected);
                indexEmployee = 0;
                fillToFormNV(indexEmployee);
                statusDefault();
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

        tblEmployee.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if (flagSave == CoreConstant.FLAG_EMTY) {
                    indexEmployee = tblEmployee.getSelectedRow();
                    fillToFormNV(indexEmployee);
                } else if (flagSave == CoreConstant.FLAG_INSERT){
                    boolean answer = DialogUtils.showConfirmDialog(messCancel);

                    if (answer){
                        indexEmployee = tblEmployee.getSelectedRow();
                        fillToFormNV(indexEmployee);
                        statusDefault();
                    } else {
                        tblEmployee.clearSelection();
                    }
                } else {
                    boolean answer = DialogUtils.showConfirmDialog(messCancel);

                    if (answer){
                        indexEmployee = tblEmployee.getSelectedRow();
                        fillToFormNV(indexEmployee);
                        statusDefault();
                    } else {
                        tblEmployee.setRowSelectionInterval(indexEmployee, indexEmployee);
                        Rectangle rect = tblEmployee.getCellRect(indexEmployee, 0, true);
                        tblEmployee.scrollRectToVisible(rect);
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

        btnNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                addNew();
            }
        });

        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setStatus(CoreConstant.FLAG_UPDATE);
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (DialogUtils.showConfirmDialog(messCancel)) {
                    statusDefault();
                    fillToFormNV(indexEmployee);
                }
            }
        });

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                save();
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                delete();
            }
        });

        // <editor-fold defaultstate="collapsed" desc="Tìm kiếm Nhân viên với txtSearch ">
        txtSearchEmployee.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (txtSearchEmployee.getText().length() > 0) {
                    nhanVienListSearch = getListEmployeeSearch();
                    search();
                } else {
                    indexEmployeeSearch = 0;
                    indexEmployee = 0;
                    fillToFormNV(indexEmployee);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (txtSearchEmployee.getText().length() > 0) {
                    nhanVienListSearch = getListEmployeeSearch();
                    search();
                    if (nhanVienListSearch.size() > 0) {
                        btnNew.setEnabled(true);
                        btnEdit.setEnabled(true);
                        btnDelete.setEnabled(true);
                    } else {
                        btnNew.setEnabled(false);
                        btnEdit.setEnabled(false);
                        btnDelete.setEnabled(false);
                    }
                } else {
                    indexEmployeeSearch = 0;
                    indexEmployee = 0;
                    fillToFormNV(indexEmployee);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });

        txtSearchEmployee.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER && nhanVienListSearch.size() > 0 && txtSearchEmployee.getText().length() > 0) {
                    indexEmployeeSearch = indexEmployeeSearch + 1;
                    search();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Sự kiện clear txtSearch với phím ESC ">
        txtSearchEmployee.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    txtSearchEmployee.setText("");
                    indexEmployee = 0;
                    fillToFormNV(indexEmployee);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        // </editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Sự kiện TÌM CD TIẾP THEO VỚI btnNextSearch">
        btnSearchEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtSearchEmployee.getText().length() > 0) {
                    indexEmployeeSearch = indexEmployeeSearch + 1;
                    search();
                }
            }
        });
        //</editor-fold>
    }

    public void addNew() {
        setStatus(CoreConstant.FLAG_INSERT);
        clearFormNV();
        txtId.setText(createID());
        tblEmployee.clearSelection();
    }

    public void save() {
        NhanVien nhanVien = getModel();
        if (flagSave == CoreConstant.FLAG_INSERT) {
            SingletonDaoUtil.getNhanVienDaoImpl().save(nhanVien);
            saveSuccess();
        } else if (flagSave == CoreConstant.FLAG_UPDATE) {
            SingletonDaoUtil.getNhanVienDaoImpl().update(nhanVien);
            saveSuccess();
        }
    }

    public void delete() {
        boolean answer = DialogUtils.showConfirmDialog("Bạn có muốn xóa nhân viên "+nvSelected + " không?");
        if (answer) {
            int n = SingletonDaoUtil.getNhanVienDaoImpl().delete(nvSelected);

            if (n > 0) {
                deleteSuccess();
            }
        }
    }


    public void saveSuccess() {
        loadDataToTblEmployee(pbSelected);
        for (NhanVien item : nhanVienList) {
            if (item.getManv().equalsIgnoreCase(getModel().getManv())) {
                indexEmployee = nhanVienList.indexOf(item);
                fillToFormNV(indexEmployee);
                break;
            }
        }
        statusDefault();
    }

    public void deleteSuccess() {
        if (indexEmployee == nhanVienList.size() -1) {
            indexEmployee = indexEmployee - 1;
        }
        loadDataToTblEmployee(pbSelected);
        fillToFormNV(indexEmployee);
    }


    public List<NhanVien> getListEmployeeSearch() {
        String str = txtSearchEmployee.getText().trim();
        Map<String, Object> map = new HashMap<>();
        map.put("manv", str);
        map.put("tennv", str);
        List<NhanVien> list = SingletonDaoUtil.getNhanVienDaoImpl().searchByProperty(map, pbSelected);
        System.out.println(list.size());
        return list;
    }

    public void search() {
        if (nhanVienListSearch.size() > 0) {

            if (indexEmployeeSearch == nhanVienListSearch.size()) {
                indexEmployeeSearch = 0;
            }

            for (NhanVien item : nhanVienList) {
                if (item.getManv().equalsIgnoreCase(nhanVienListSearch.get(indexEmployeeSearch).getManv())) {
                    indexEmployee = nhanVienList.indexOf(item);
                    fillToFormNV(indexEmployee);
                    break;
                }
            }
        } else {
            clearFormNV();
            indexEmployeeSearch = 0;
            indexEmployee = 0;
            tblEmployee.clearSelection();
        }
    }


    public void statusDefault() {
        flagSave = CoreConstant.FLAG_EMTY;

        btnNew.setVisible(true);
        btnEdit.setVisible(true);
        btnDelete.setVisible(true);

        btnSave.setVisible(false);
        btnCancel.setVisible(false);

        txtId.setEditable(false);
        txtName.setEditable(false);
        rdoMale.setEnabled(false);
        rdoFemale.setEnabled(false);
        rdoAdmin.setEnabled(false);
        rdoEmployee.setEnabled(false);
        txaAddress.setEditable(false);
        txtBirthday.setEditable(false);
        txtUsername.setEditable(false);
    }

    public void setStatus (int insertable) {
        flagSave = insertable;

        switch (insertable) {
            case CoreConstant.FLAG_INSERT:
                messCancel = "Bạn có muốn bỏ qua thao tác thêm mới không?";
                clearFormNV();
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

        txtId.setEditable(false);
        txtName.setEditable(true);
        rdoMale.setEnabled(true);
        rdoFemale.setEnabled(true);
        rdoAdmin.setEnabled(true);
        rdoEmployee.setEnabled(true);
        txaAddress.setEditable(true);
        txtBirthday.setEditable(true);
        txtUsername.setEditable(true);
    }


    public void loadDataToTblDepartment() {
        modelDepartment.setRowCount(0);
        phongBanList = SingletonDaoUtil.getPhongBanDaoImpl().findAll();

        int i = 1;

        for (PhongBan item : phongBanList) {
            modelDepartment.addRow(new Object[] {
                    i, item.getTenpb()
            });
            i++;
        }
    }

    public void loadDataToTblEmployee(PhongBan phongBan) {
        modelEmployee.setRowCount(0);

        if (phongBan != null) {
            nhanVienList = SingletonDaoUtil.getNhanVienDaoImpl().getByPhongBan(pbSelected);
            modelEmployee.setRowCount(0);
            int j = 1;

            for (NhanVien item : nhanVienList) {
                modelEmployee.addRow(new Object[] {
                        j, item.getManv(), item.getTennv(), item.isGioitinh() == true ? "Nam":"Nữ", item.isVaitro() == CoreConstant.ADMIN ? "Quản trị" : "Nhân viên"
                });
                j++;
            }
        }
    }

    public void getPBSelected (int i) {
        if (phongBanList.size() > 0) {
            tblDepartment.setRowSelectionInterval(i, i);
            Rectangle rect = tblDepartment.getCellRect(i, 0, true);
            tblDepartment.scrollRectToVisible(rect);
            sttDepartment = Integer.parseInt(tblDepartment.getValueAt(i, 0).toString());
            pbSelected = phongBanList.get(sttDepartment - 1);
        }
    }

    public void fillToFormNV (int i) {
        if (nhanVienList.size() > 0) {
            tblEmployee.setRowSelectionInterval(i, i);
            Rectangle rect = tblEmployee.getCellRect(i, 0, true);
            tblEmployee.scrollRectToVisible(rect);

            String id = (tblEmployee.getValueAt(i, 1).toString());
            nvSelected = SingletonDaoUtil.getNhanVienDaoImpl().findById(id);
            setModel(nvSelected);
        } else {
            clearFormNV();
        }
    }


    public NhanVien getModel() {
        NhanVien nv = null;
        String manv = txtId.getText().trim();
        String tennv = txtName.getText().trim();
        boolean gioitinh = rdoMale.isSelected() == true ? true : false;
        String diachi = txaAddress.getText().trim();
        Date ngaysinh = null;
        try {
            ngaysinh = DateUtil.toDate(txtBirthday.getText().trim());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String taikhoan = txtUsername.getText().trim();
        String matkhau = null;
        boolean vaitro = rdoAdmin.isSelected() == true ? CoreConstant.ADMIN : CoreConstant.EMPLOYEE;

        if (flagSave == CoreConstant.FLAG_INSERT) {
            if (vaitro == CoreConstant.ADMIN) {
                matkhau = "123@123a";
            }
        }

        if (flagSave == CoreConstant.FLAG_UPDATE) {
            if (vaitro == CoreConstant.ADMIN) {
                matkhau = nvSelected.getMatkhau();
            } else {
                matkhau = null;
            }
        }

        nv = new NhanVien(manv,tennv,gioitinh, ngaysinh, diachi, taikhoan,matkhau,vaitro,pbSelected);
        return  nv;
    }

    public void setModel(NhanVien nhanVien) {
        txtId.setText(nhanVien.getManv());
        txtName.setText(nhanVien.getTennv());
        rdoMale.setSelected(nhanVien.isGioitinh() == true ? true : false);
        rdoAdmin.setSelected(nhanVien.isVaitro() == CoreConstant.ADMIN ? CoreConstant.ADMIN : CoreConstant.EMPLOYEE);
        txaAddress.setText(nhanVien.getDiachi());
        if (nhanVien.getNgaysinh() != null) {
            txtBirthday.setText(DateUtil.toString(nhanVien.getNgaysinh()));
        } else  {
            txtBirthday.setText("");
        }
        txtUsername.setText(nhanVien.getTaikhoan());
    }

    public void clearFormNV() {
        NhanVien nv = new NhanVien();
        setModel(nv);
    }

    // <editor-fold defaultstate="collapsed" desc="TẠO MÃ NHÂN VIÊN">
    private String createID() {
        String lastID = SingletonDaoUtil.getNhanVienDaoImpl().getLastID();

        if (lastID != null) {
            StringBuilder ID = new StringBuilder();
            ID.append("NV");

            int pathNumber = Integer.parseInt(lastID.substring(2)) + 1;

            for (int i = 0; i < 5 - String.valueOf(pathNumber).length(); i++) {
                ID.append("0");
            }

            ID.append(pathNumber);

            return ID.toString();
        } else {
            return "NV00001";
        }
    }

    public boolean doneLoad = false;

    List<NhanVien> nhanVienList = new ArrayList<>();
    List<PhongBan> phongBanList = new ArrayList<>();

    List<NhanVien> nhanVienListSearch = new ArrayList<>();
    List<PhongBan> phongBanListSearch = new ArrayList<>();

    NhanVien nvSelected =  new NhanVien();
    PhongBan pbSelected = new PhongBan();

    String messCancel;

    int flagSave = CoreConstant.FLAG_EMTY;

    int indexEmployee = 0;
    int indexEmployeeSearch = 0;

    int indexDepartment = 0;
    int sttEmployee = -1;
    int sttDepartment= -1;

    //<editor-fold desc="COMPONENT">
    JLabel lblTitle, lblId, lblName, lblSex, lblBirthDay, lblUsername, lblRole, lblAddress, lblSearchEmployee, lblSearchDepartment;
    JTextField txtId, txtName, txtBirthday, txtUsername, txtSearchEmployee, txtSearchDepartment;
    JTextArea txaAddress;
    JRadioButton rdoMale, rdoFemale, rdoAdmin, rdoEmployee;
    ButtonGroup btgSex, btgRole;
    JScrollPane scAddress, scTblEmployee, scTblDepartment;
    JTable tblEmployee, tblDepartment;
    DefaultTableModel modelEmployee, modelDepartment;
    JButton btnSearchDepartment, btnSearchEmployee, btnNew, btnEdit, btnDelete, btnCancel, btnSave, btnExport;
    //</editor-fold>
}
