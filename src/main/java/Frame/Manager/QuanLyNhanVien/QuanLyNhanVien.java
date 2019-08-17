package Frame.Manager.QuanLyNhanVien;

import Contant.CoreConstant;
import Entity.NhanVien;
import Entity.PhongBan;
import Frame.Login.LoginFrame;
import Utils.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
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
        open();
        doneLoad = true;
    }

    public void open() {
        initComponents();
        addControls();

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
        lblBirthDay = new JLabel("Ngày sinh");
        lblSearchEmployee = new JLabel("Tìm kiếm");
        lblSearchDepartment = new JLabel("Tìm kiếm");
        //</editor-fold>

        //<editor-fold desc="TEXT FIELD">
        txtId = new JTextField();
        txtName = new JTextField();
        txtBirthday = new JTextField();
        txtUsername = new JTextField();
        txtSearchEmployee = new JTextField(25);
        txtSearchDepartment = new JTextField();

        // Set Margin
        txtId.setMargin(new Insets(0, 5, 0, 5));
        txtUsername.setMargin(new Insets(0, 5, 0, 5));
        txtName.setMargin(new Insets(0, 5, 0, 5));
        txtBirthday.setMargin(new Insets(0, 5, 0, 5));
        txtSearchDepartment.setMargin(new Insets(0, 5, 0, 5));
        txtSearchEmployee.setMargin(new Insets(0, 5, 0, 5));
        //</editor-fold>

        //<editor-fold desc="CUSTOM FONT - SIZE">

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

        rdoMale.setBackground(Color.WHITE);
        rdoFemale.setBackground(Color.WHITE);
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
                c.setFont(new Font("Segoe UI", 0, 13));
                if (column == 0) {
                    c.setHorizontalAlignment(JLabel.CENTER);
                }else {
                    c.setHorizontalAlignment(JLabel.LEFT);
                }
                return c;
            }
        };
        JTableHeader tblHeaderD = tblDepartment.getTableHeader();
        ((DefaultTableCellRenderer) tblHeaderD.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        tblHeaderD.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblHeaderD.getColumnModel().getColumn(1).setPreferredWidth(280);
        tblDepartment.setRowHeight(22);
        tblDepartment.setSelectionBackground(Color.decode("#3a4d8f"));
        tblDepartment.setIntercellSpacing(new Dimension(0, 0));
        tblDepartment.setShowGrid(false);

        scTblDepartment = new JScrollPane(tblDepartment, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        tblDepartment.setRowSorter(new TableRowSorter(modelDepartment));
        tblDepartment.setAutoCreateRowSorter(true);
        //</editor-fold>

        //<editor-fold desc="BẢNG NHÂN VIÊN">
        modelEmployee = new DefaultTableModel();
        modelEmployee.setColumnIdentifiers(new Object[] {"STT", "Mã nhân viên","Tên nhân viên", "Giới tính", "Email"});
        tblEmployee = new JTable(modelEmployee){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                JLabel c = (JLabel) super.prepareRenderer(renderer, row, column);
                c.setFont(new Font("Segoe UI", 0, 13));
                if (column == 3) {
                    c.setHorizontalAlignment(JLabel.CENTER);
                } else {
                    c.setHorizontalAlignment(JLabel.LEFT);
                }
                return c;
            }
        };
        JTableHeader tblHeaderE = tblEmployee.getTableHeader();
        ((DefaultTableCellRenderer) tblHeaderE.getDefaultRenderer()).setHorizontalAlignment(JLabel.LEFT);
        tblHeaderE.getColumnModel().getColumn(0).setPreferredWidth(30);
        tblHeaderE.getColumnModel().getColumn(1).setPreferredWidth(150);
        tblHeaderE.getColumnModel().getColumn(2).setPreferredWidth(200);
        tblHeaderE.getColumnModel().getColumn(3).setPreferredWidth(50);
        tblHeaderE.getColumnModel().getColumn(4).setPreferredWidth(200);
        tblEmployee.setRowHeight(22);
        tblEmployee.setSelectionBackground(Color.decode("#3a4d8f"));

        scTblEmployee = new JScrollPane(tblEmployee, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
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

        btnNew.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        btnEdit.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        btnDelete.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        btnCancel.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        btnSave.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        //</editor-fold>

        //<editor-fold desc="Set Font Segoe UI">
        lblId.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        lblName.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        lblSex.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        lblBirthDay.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        lblUsername.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        lblSearchEmployee.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        lblSearchDepartment.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));

        txtId.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        txtName.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        rdoMale.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        rdoFemale.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        rdoAdmin.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        rdoEmployee.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        txtBirthday.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        txtUsername.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        //</editor-fold>

        txtSearchEmployee.setPreferredSize(new Dimension(250, 25));
    }

    public void addControls() {

        // LEFT
        JPanel pnSearchDepartment = new JPanel(new GridBagLayout());
        pnSearchDepartment.setBackground(Color.WHITE);
        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.insets = new Insets(5, 10, 5, 10);
        gbc1.fill = GridBagConstraints.BOTH;
        gbc1.ipady = 5;

        gbc1.gridx = 0;
        gbc1.gridy = 0;
        pnSearchDepartment.add(lblSearchDepartment, gbc1);

        gbc1.gridx = 1;
        gbc1.weightx = 1;
        pnSearchDepartment.add(txtSearchDepartment, gbc1);
        pnSearchDepartment.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY)));

        JPanel pnTblDepartment = new JPanel();
        pnTblDepartment.setBackground(Color.WHITE);
        pnTblDepartment.add(scTblDepartment);

        JPanel pnLeft = new JPanel(new BorderLayout(0,5));
        pnLeft.setBackground(Color.WHITE);
        pnLeft.add(pnSearchDepartment, BorderLayout.NORTH);
        pnLeft.add(scTblDepartment, BorderLayout.CENTER);

        JPanel pnDetailEmployee = new JPanel();
        pnDetailEmployee.setBackground(Color.WHITE);
        pnDetailEmployee.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.ipady = 5;

        /* x = 0*/
        gbc.gridx = 0;
        gbc.gridy = 0;
        pnDetailEmployee.add(lblId, gbc);
        gbc.gridy = 1;
        pnDetailEmployee.add(lblName, gbc);
        gbc.gridy = 2;
        pnDetailEmployee.add(lblSex, gbc);

        /* x = 1*/
        gbc.weightx = 1;
        gbc.gridx = 1;
        gbc.gridy = 0;
        pnDetailEmployee.add(txtId, gbc);
        gbc.gridy = 1;
        pnDetailEmployee.add(txtName, gbc);

        JPanel temp = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        temp.setBackground(Color.WHITE);
        temp.add(rdoMale);
        temp.add(rdoFemale);

        gbc.gridx = 1;
        gbc.gridy = 2;
        pnDetailEmployee.add(temp, gbc);

        /* x = 2*/
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 0;
        pnDetailEmployee.add(lblBirthDay, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        pnDetailEmployee.add(lblUsername, gbc);

        /* x = 3 */
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.weightx = 1;
        pnDetailEmployee.add(txtBirthday, gbc);

        gbc.gridx = 3;
        gbc.gridy = 1;
        pnDetailEmployee.add(txtUsername, gbc);


        JPanel pnButtonEmployee = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        pnButtonEmployee.setBackground(Color.WHITE);
        pnButtonEmployee.add(btnNew);
        pnButtonEmployee.add(btnEdit);
        pnButtonEmployee.add(btnDelete);
        pnButtonEmployee.add(btnSave);
        pnButtonEmployee.add(btnCancel);

        JPanel pnRightTop = new JPanel(new BorderLayout(0, 20));
        pnRightTop.setBackground(Color.WHITE);
        pnRightTop.add(pnDetailEmployee, BorderLayout.CENTER);
        pnRightTop.add(pnButtonEmployee, BorderLayout.SOUTH);

        //
        JPanel pnTblEmployeeTop = new JPanel(new BorderLayout());
        pnTblEmployeeTop.setBackground(Color.WHITE);

        JPanel pnSearchEmployee = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        pnSearchEmployee.setBackground(Color.WHITE);
        pnSearchEmployee.add(lblSearchEmployee);
        pnSearchEmployee.add(txtSearchEmployee);

        JPanel pnExport = new JPanel(new FlowLayout(FlowLayout.RIGHT, 30, 10));
        pnExport.setBackground(Color.WHITE);

        JPanel pnTemp2 = new JPanel();
        pnTemp2.setBackground(Color.WHITE);
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
        pnTemp3.setPreferredSize(new Dimension(0, 0));

        this.setLayout(new BorderLayout(0,10));
        this.add(pnTemp3, BorderLayout.NORTH);;
        this.add(pnMain, BorderLayout.CENTER);;

        pnLeft.setPreferredSize(new Dimension(300, 0));

        pnLeft.setBackground(Color.WHITE);
        pnButtonEmployee.setBackground(Color.WHITE);
        pnDetailEmployee.setBackground(Color.WHITE);
        pnTblDepartment.setBackground(Color.WHITE);
        pnTblEmployee.setBackground(Color.WHITE);
        pnTblEmployeeTop.setBackground(Color.WHITE);
        pnRight.setBackground(Color.WHITE);
        pnRightTop.setBackground(Color.WHITE);
    }

    public void addEvents() {
        statusDefault();
        loadDataToTblDepartment();
        getPBSelected(indexDepartment);
        loadDataToTblEmployee(pbSelected);
        fillToFormNV(indexEmployee);

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

        txtUsername.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {

            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {

            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                Set<String> s = new TreeSet<>();
                s.add("gmail.com");
                s.add("fpt.edu.vn");
                s.add("facebook.com");
                AutoCompleteUtils.autoCompleteEmail(txtUsername,s, keyEvent );
            }
        });

        // <editor-fold defaultstate="collapsed" desc="Tìm kiếm Nhân viên với txtSearch ">
        txtSearchEmployee.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (txtSearchEmployee.getText().length() > 0) {
                    nhanVienListSearch = getListEmployeeSearch();
                    search();

                    if (nhanVienListSearch.size() > 0) {
                        btnEdit.setEnabled(true);
                        btnDelete.setEnabled(true);
                    } else {
                        btnEdit.setEnabled(false);
                        btnDelete.setEnabled(false);
                    }

                } else {
                    indexEmployeeSearch = 0;
                    indexEmployee = 0;
                    fillToFormNV(indexEmployee);

                    btnEdit.setEnabled(true);
                    btnDelete.setEnabled(true);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (txtSearchEmployee.getText().length() > 0) {
                    nhanVienListSearch = getListEmployeeSearch();
                    search();
                    if (nhanVienListSearch.size() > 0) {
                        btnEdit.setEnabled(true);
                        btnDelete.setEnabled(true);
                    } else {
                        btnEdit.setEnabled(false);
                        btnDelete.setEnabled(false);
                    }
                } else {
                    indexEmployeeSearch = 0;
                    indexEmployee = 0;
                    fillToFormNV(indexEmployee);

                    btnEdit.setEnabled(true);
                    btnDelete.setEnabled(true);
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

        // <editor-fold defaultstate="collapsed" desc="Tìm kiếm Phòng ban với txtSearch ">
        txtSearchDepartment.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (txtSearchDepartment.getText().length() > 0) {
                    phongBanListSearch = getListEmployeeDepartment();
                    searchPhongBan();

                    if (phongBanListSearch.size() > 0) {
                        btnEdit.setEnabled(true);
                        btnDelete.setEnabled(true);
                    } else {
                        btnEdit.setEnabled(false);
                        btnDelete.setEnabled(false);
                    }

                } else {
                    indexEmployeeSearch = 0;
                    indexEmployee = 0;
                    indexEmployeeSearch = 0;
                    indexDepartmentSearch = 0;
                    getPBSelected(indexDepartment);
                    loadDataToTblEmployee(pbSelected);
                    fillToFormNV(0);

                    btnEdit.setEnabled(true);
                    btnDelete.setEnabled(true);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (txtSearchDepartment.getText().length() > 0) {
                    phongBanListSearch = getListEmployeeDepartment();
                    searchPhongBan();

                    if (phongBanListSearch.size() > 0) {
                        btnEdit.setEnabled(true);
                        btnDelete.setEnabled(true);
                    } else {
                        btnEdit.setEnabled(false);
                        btnDelete.setEnabled(false);
                    }

                } else {
                    indexEmployeeSearch = 0;
                    indexEmployee = 0;
                    indexEmployeeSearch = 0;
                    indexDepartmentSearch = 0;
                    getPBSelected(indexDepartment);
                    loadDataToTblEmployee(pbSelected);
                    fillToFormNV(0);

                    btnEdit.setEnabled(true);
                    btnDelete.setEnabled(true);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });

        txtSearchDepartment.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER && phongBanListSearch.size() > 0 && txtSearchDepartment.getText().length() > 0) {
                    indexDepartmentSearch = indexDepartmentSearch + 1;
                    search();
                }

                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    txtSearchDepartment.setText("");
                    indexEmployee = 0;
                    indexDepartment = 0;
                    getPBSelected(indexDepartment);
                    loadDataToTblEmployee(pbSelected);
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
        tblEmployee.clearSelection();
    }

    public void save() {
        if (checkInfor()) {
            NhanVien nhanVien = getModel();
            nhanVien.setId(createID());
            if (flagSave == CoreConstant.FLAG_INSERT) {
                nvSelected = SingletonDaoUtil.getNhanVienDaoImpl().save(nhanVien);
                saveSuccess();
            } else if (flagSave == CoreConstant.FLAG_UPDATE) {
                SingletonDaoUtil.getNhanVienDaoImpl().update(nhanVien);
                saveSuccess();
            }
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
            if (item.getId().equalsIgnoreCase(nvSelected.getId())) {
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
        return list;
    }

    public List<PhongBan> getListEmployeeDepartment() {
        String str = txtSearchDepartment.getText().trim();
        Map<String, Object> map = new HashMap<>();
        map.put("mapb", str);
        map.put("tenpb", str);
        List<PhongBan> list = SingletonDaoUtil.getPhongBanDaoImpl().searchByProperty(map);
        return list;
    }

    public void search() {
        if (nhanVienListSearch.size() > 0) {

            if (indexEmployeeSearch == nhanVienListSearch.size()) {
                indexEmployeeSearch = 0;
            }

            for (NhanVien item : nhanVienList) {
                if (item.getId().equalsIgnoreCase(nhanVienListSearch.get(indexEmployeeSearch).getId())) {
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

    public void searchPhongBan() {
        if (phongBanListSearch.size() > 0) {

            if (indexDepartmentSearch == phongBanListSearch.size()) {
                indexDepartmentSearch = 0;
            }

            for (PhongBan item : phongBanList) {
                if (item.getId().equalsIgnoreCase(phongBanListSearch.get(indexDepartmentSearch).getId())) {
                    indexDepartment = phongBanList.indexOf(item);
                    getPBSelected(indexDepartment);
                    loadDataToTblEmployee(pbSelected);
                    indexEmployee = 0;
                    fillToFormNV(indexEmployee);
                    break;
                }
            }
        } else {
            clearFormNV();
            indexEmployeeSearch = 0;
            indexEmployee = 0;
            indexDepartmentSearch = 0;
            indexDepartment = 0;
            tblDepartment.clearSelection();
            loadDataToTblEmployee(null);
        }
    }


    public void statusDefault() {
        flagSave = CoreConstant.FLAG_EMTY;

        if (LoginFrame.accountLogin.getVaiTro().getId() == CoreConstant.ROLE_ADMIN) {
            btnNew.setVisible(false);
            btnEdit.setVisible(false);
            btnDelete.setVisible(false);
        } else {
            btnNew.setVisible(true);
            btnEdit.setVisible(true);
            btnDelete.setVisible(true);
        }

        btnSave.setVisible(false);
        btnCancel.setVisible(false);

        txtId.setEditable(false);
        txtName.setEditable(false);
        rdoMale.setEnabled(false);
        rdoFemale.setEnabled(false);
        rdoAdmin.setEnabled(false);
        rdoEmployee.setEnabled(false);
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
        txtBirthday.setEditable(true);
        txtUsername.setEditable(true);
    }


    public void loadDataToTblDepartment() {
        modelDepartment.setRowCount(0);
        phongBanList = SingletonDaoUtil.getPhongBanDaoImpl().findAll();

        int i = 1;

        for (PhongBan item : phongBanList) {
            modelDepartment.addRow(new Object[] {
                    i, item.getTenPhongBan()
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
            if (nhanVienList.size() > 0) {
                btnEdit.setEnabled(true);
                btnDelete.setEnabled(true);

                for (NhanVien item : nhanVienList) {
                    modelEmployee.addRow(new Object[] {
                            j, item.getId(), item.getTenNhanVien(), item.isGioiTinh() == true ? "Nam":"Nữ", item.getEmail()});
                    j++;
                }
            } else {
                btnEdit.setEnabled(false);
                btnDelete.setEnabled(false);
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
        Date ngaysinh = null;
        try {
            ngaysinh = DateUtil.toDate(txtBirthday.getText().trim());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String email = txtUsername.getText().trim();
        nv = new NhanVien(manv,tennv,gioitinh, ngaysinh,email,pbSelected);
        return  nv;
    }

    public void setModel(NhanVien nhanVien) {
        if (nhanVien != null) {
            txtId.setText(nhanVien.getId());
            txtName.setText(nhanVien.getTenNhanVien());
            rdoMale.setSelected(nhanVien.isGioiTinh() == true ? true : false);
            if (nhanVien.getNgaySinh() != null) {
                txtBirthday.setText(DateUtil.toString(nhanVien.getNgaySinh()));
            } else  {
                txtBirthday.setText("");
            }
            txtUsername.setText(nhanVien.getEmail());
        } else {
            txtId.setText("");
            txtName.setText("");
            rdoMale.setSelected(true);
            rdoAdmin.setSelected(false);
            txtBirthday.setText("");
            txtUsername.setText("");
        }

    }

    public void clearFormNV() {
        setModel(null);
    }

    public boolean checkInfor() {

        String name = txtName.getText().trim();
        String birthday = txtBirthday.getText().trim();
        String email = txtUsername.getText().trim();

        if (!name.matches("^[A-Za-zÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ\\s\']{5,100}$")) {
            DialogUtils.showMessageDialog("Vui lòng nhập họ tên đầy đủ, không chứa số và các ký tự đặc biệt \n, . / ; < > ? : \" { } - = _ + ` ~ ! @ $ % ^ & * ( ) \\ |", CoreConstant.TYPE_WARNING);
            txtName.requestFocus();
            return false;
        }

        if (birthday.length() == 0) {
            DialogUtils.showMessageDialog("Ngày sinh không được trống, vui lòng nhập đầy đủ thông tin!", CoreConstant.TYPE_WARNING);
            txtBirthday.requestFocus();
            return false;
        }
        if (email.length() == 0) {
            DialogUtils.showMessageDialog("Email không được trống, vui lòng nhập đầy đủ thông tin!", CoreConstant.TYPE_WARNING);
            txtUsername.requestFocus();
            return false;
        }

        if (!birthday.matches("^[0-9\\-]+$")) {
            DialogUtils.showMessageDialog("Ngày sinh phải có định dạng ngày - tháng - năm! Vui lòng nhập lại", CoreConstant.TYPE_WARNING);
            txtBirthday.requestFocus();
            return false;
        }

        if (!email.matches("^([\\w\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{1})+$")) {
            JOptionPane.showMessageDialog(null, "Email không hợp lệ!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            txtUsername.requestFocus();
            return false;
        }


        try {
            if (MailUtils.checkEmail(email).equals("false")) {
                JOptionPane.showMessageDialog(null, "Email không tồn tại. Vui lòng kiểm tra lại!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                txtUsername.requestFocus();
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (flagSave == CoreConstant.FLAG_INSERT) {
            if (SingletonDaoUtil.getNhanVienDaoImpl().checkEmailExist(email)) {
                DialogUtils.showMessageDialog("Email đã được sử dụng, vui lòng nhập email khác!", CoreConstant.TYPE_WARNING);
                txtUsername.requestFocus();
                return false;
            }
        }


        return true;
    }

    public void refresh() {
        loadDataToTblDepartment();
        getPBSelected(indexDepartment);
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
    //</editor-fold>

    //<editor-fold desc="COMPONENT">
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
    int indexDepartmentSearch = 0;

    int sttEmployee = -1;
    int sttDepartment= -1;

    JLabel lblTitle, lblId, lblName, lblSex, lblBirthDay, lblUsername, lblSearchEmployee, lblSearchDepartment;
    JTextField txtId, txtName, txtBirthday, txtUsername, txtSearchEmployee, txtSearchDepartment;
    JRadioButton rdoMale, rdoFemale, rdoAdmin, rdoEmployee;
    ButtonGroup btgSex, btgRole;
    JScrollPane scTblEmployee, scTblDepartment;
    JTable tblEmployee, tblDepartment;
    DefaultTableModel modelEmployee, modelDepartment;
    JButton btnSearchDepartment, btnSearchEmployee, btnNew, btnEdit, btnDelete, btnCancel, btnSave, btnExport;
    //</editor-fold>
}
