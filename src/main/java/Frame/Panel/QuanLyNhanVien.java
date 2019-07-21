package Frame.Panel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
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
    }

    public void open() {
        initComponents();
        addControls();
    }

    public void initComponents() {
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

        txtId = new JTextField();
        txtName = new JTextField();
        txtBirthday = new JTextField();
        txtUsername = new JTextField();
        txtSearchEmployee = new JTextField();
        txtSearchDepartment = new JTextField();

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

        txaAddress = new JTextArea();
        scAddress = new JScrollPane(txaAddress,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Table Department
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
                if (column == 2) {
                    c.setHorizontalAlignment(JLabel.LEFT);
                } else {
                    c.setHorizontalAlignment(JLabel.CENTER);

                }
                return c;
            }
        };
        JTableHeader tblHeaderD = tblDepartment.getTableHeader();
        ((DefaultTableCellRenderer) tblHeaderD.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
//        tblHeaderD.getColumnModel().getColumn(0).setPreferredWidth(30);
//        tblHeaderD.getColumnModel().getColumn(1).setPreferredWidth(160);

        // Table Employee
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

        scTblDepartment = new JScrollPane(tblDepartment, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scTblEmployee = new JScrollPane(tblEmployee, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

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

        txtSearchDepartment.setPreferredSize(new Dimension(200, 25));
        txtSearchEmployee.setPreferredSize(new Dimension(200, 25));

        rdoEmployee.setSelected(true);
        rdoMale.setSelected(true);
    }

    public void addControls() {
        // LEFT
        JPanel pnSearchDepartment = new JPanel();
//        pnSearchDepartment.add(lblSearchDepartment);
        pnSearchDepartment.add(txtSearchDepartment);
        pnSearchDepartment.add(btnSearchDepartment);

        JPanel pnTblDepartment = new JPanel();
        pnTblDepartment.add(scTblDepartment);

        JPanel pnLeft = new JPanel(new BorderLayout());
        pnLeft.add(pnSearchDepartment, BorderLayout.NORTH);
        pnLeft.add(scTblDepartment, BorderLayout.CENTER);

        //RIGHT
        JPanel pnSex = new JPanel(new FlowLayout(FlowLayout.LEADING, 10, 0));
        pnSex.add(rdoMale); pnSex.add(rdoFemale);

        JPanel pnRole = new JPanel(new FlowLayout(FlowLayout.LEADING, 10, 0));
        pnRole.add(rdoAdmin); pnRole.add(rdoEmployee);

        JPanel pnDetailEmployee = new JPanel();
        pnDetailEmployee.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        gbc.insets = new Insets(5, 10, 5, 10);

        gbc.ipady = 5;

        gbc.gridx = 0;
        gbc.gridy = 0;
        pnDetailEmployee.add(lblId, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        pnDetailEmployee.add(lblSex, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        pnDetailEmployee.add(lblAddress, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
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

        gbc.ipadx = 200;
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

        gbc.ipadx = 180;
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridheight = 1;
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

        JPanel pnButtonEmployee = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnButtonEmployee.add(btnNew);
        pnButtonEmployee.add(btnEdit);
        pnButtonEmployee.add(btnDelete);
        pnButtonEmployee.add(btnSave);
        pnButtonEmployee.add(btnCancel);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 4;
        pnDetailEmployee.add(pnButtonEmployee, gbc);

        JPanel pnRightTop = new JPanel(new BorderLayout());
        pnRightTop.add(pnDetailEmployee, BorderLayout.CENTER);

        //
        JPanel pnTblEmployeeTop = new JPanel(new BorderLayout());
        JPanel pnSearchEmployee = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        pnSearchEmployee.add(lblSearchEmployee);
        pnSearchEmployee.add(txtSearchEmployee);
        pnSearchEmployee.add(btnSearchEmployee);
        JPanel pnExport = new JPanel(new FlowLayout(FlowLayout.RIGHT, 30, 10));
        pnExport.add(btnExport);

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

        this.setLayout(new BorderLayout());
        this.add(pnTemp3, BorderLayout.NORTH);;
        this.add(pnMain, BorderLayout.CENTER);;

        pnLeft.setPreferredSize(new Dimension(320, 0));
        pnSearchDepartment.setPreferredSize(new Dimension(0,40));
    }

    // COMPONENT
    JLabel lblTitle, lblId, lblName, lblSex, lblBirthDay, lblUsername, lblRole, lblAddress, lblSearchEmployee, lblSearchDepartment;
    JTextField txtId, txtName, txtBirthday, txtUsername, txtSearchEmployee, txtSearchDepartment;
    JTextArea txaAddress;
    JRadioButton rdoMale, rdoFemale, rdoAdmin, rdoEmployee;
    ButtonGroup btgSex, btgRole;
    JScrollPane scAddress, scTblEmployee, scTblDepartment;
    JTable tblEmployee, tblDepartment;
    DefaultTableModel modelEmployee, modelDepartment;
    JButton btnSearchDepartment, btnSearchEmployee, btnNew, btnEdit, btnDelete, btnCancel, btnSave, btnExport;
}
