package Frame.Manager.DanhMucKhac;

import Contant.CoreConstant;
import Entity.PhongBan;
import Frame.Main.MainFrame;
import Utils.DialogUtils;
import Utils.SingletonDaoUtil;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class QuanLyPhongBan extends JPanel {
    public QuanLyPhongBan() {
        open();
    }

    public void open() {
        initComponents();
        addControls();
        addEvents();
    }

    public void initComponents() {

        //<editor-fold desc="Init label">
        lblId = new JLabel("Mã phòng ban");
        lblName = new JLabel("Tên phòng ban");
        lblSearch = new JLabel("Tìm kiếm");
        //</editor-fold>

        //<editor-fold desc="Init text">
        txtId = new JTextField();
        txtName = new JTextField();
        txtSearch = new JTextField();

        txtId.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        txtName.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        txtSearch.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));

        txtId.setMargin(new Insets(5, 10, 5, 10));
        txtName.setMargin(new Insets(5, 10, 5, 10));
        txtSearch.setMargin(new Insets(5, 10, 5, 10));
        //</editor-fold>

        //<editor-fold desc="Init button">
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


        btnDelete.setPreferredSize(btnNew.getPreferredSize());
        btnSave.setPreferredSize(btnNew.getPreferredSize());
        btnCancel.setPreferredSize(btnNew.getPreferredSize());
        btnEdit.setPreferredSize(btnNew.getPreferredSize());
        //</editor-fold>

        //<editor-fold desc="Bảng phòng ban">
        modelTalDepartment = new DefaultTableModel();
        modelTalDepartment.setColumnIdentifiers(new Object[] {"STT", "Mã phòng ban","Tên phòng ban"});
        tblDepartment = new JTable(modelTalDepartment){
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
        JTableHeader tblHeaderE = tblDepartment.getTableHeader();
        ((DefaultTableCellRenderer) tblHeaderE.getDefaultRenderer()).setHorizontalAlignment(JLabel.LEFT);
        tblHeaderE.getColumnModel().getColumn(0).setPreferredWidth(30);
        tblHeaderE.getColumnModel().getColumn(1).setPreferredWidth(200);
        tblHeaderE.getColumnModel().getColumn(2).setPreferredWidth(400);
        tblDepartment.setRowHeight(22);
        tblDepartment.setSelectionBackground(Color.decode("#3a4d8f"));

        tblDepartment.setRowSorter(new TableRowSorter(modelTalDepartment));
        tblDepartment.setAutoCreateRowSorter(true);
        scTalDepartment = new JScrollPane(tblDepartment, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        //</editor-fold>

        //<editor-fold desc="Set font Segoe UI">
        lblId.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        lblName.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        lblSearch.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));



        btnNew.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        btnEdit.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        btnDelete.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        btnCancel.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        btnSave.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        //</editor-fold>

        txtSearch.setPreferredSize(new Dimension(250, 25));

    }

    public void addControls() {
        JPanel pnDetail = new JPanel(new BorderLayout(0, 10));

        JPanel pnDetailItem = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        gbc.insets = new Insets(5, 10, 5, 10);

        gbc.gridx = 0; gbc.gridy = 0;
        pnDetailItem.add(lblId, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        pnDetailItem.add(lblName, gbc);

        gbc.weightx = 1;
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

        pnTable.add(pnSearch, BorderLayout.NORTH);
        pnTable.add(scTalDepartment);

        this.setLayout(new BorderLayout(0, 10));
        this.add(pnDetail, BorderLayout.NORTH);
        this.add(pnTable, BorderLayout.CENTER);;
    }

    public void addEvents() {
        statusDefault();
        loadDataToTblPhongBan();
        fillToForm(indexPhongBan);

        tblDepartment.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if (flagSave == CoreConstant.FLAG_EMTY) {
                    indexPhongBan = tblDepartment.getSelectedRow();
                    fillToForm(indexPhongBan);
                } else {
                    boolean answer = DialogUtils.showConfirmDialog(messCancel);

                    if (answer){
                        indexPhongBan = tblDepartment.getSelectedRow();
                        tblDepartment.setRowSelectionInterval(indexPhongBan, indexPhongBan);
                        Rectangle rect = tblDepartment.getCellRect(indexPhongBan, 0, true);
                        tblDepartment.scrollRectToVisible(rect);
                        fillToForm(indexPhongBan);
                        statusDefault();

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
                setBtnNew();
            }
        });

        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setBtnEdit();
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setBtnDelete();
            }
        });

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setBtnSave();
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setBtnCancel();
            }
        });

        txtSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                setTxtSearch();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                setTxtSearch();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });

        txtSearch.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER && phongBanListTimKiem.size() > 0 && txtSearch.getText().length() > 0) {
                    indexPhongBanTimKiem = indexPhongBanTimKiem + 1;
                    search();
                }

                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    txtSearch.setText("");
                    indexPhongBan = 0;
                    fillToForm(indexPhongBan);
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
        tblDepartment.clearSelection();
    }

    public void setBtnEdit() {
        setStatus(CoreConstant.FLAG_UPDATE);
    }

    public void setBtnSave() {
        if (checkInfor()) {
            PhongBan phongBan = getModel();
            phongBan.setId(createID());
            if (flagSave == CoreConstant.FLAG_INSERT) {
                pbSelected = SingletonDaoUtil.getPhongBanDaoImpl().save(phongBan);
                saveSuccess();
            } else if (flagSave == CoreConstant.FLAG_UPDATE) {
                SingletonDaoUtil.getPhongBanDaoImpl().update(phongBan);
                saveSuccess();
            }
        }
    }

    public void setBtnDelete() {
        if (SingletonDaoUtil.getPhongBanDaoImpl().checkNhanVienExistInPhongBan(pbSelected.getId())) {
            DialogUtils.showMessageDialog("Phòng ban " + pbSelected + " đang tồn tại nhân viên, không thể xóa!",CoreConstant.TYPE_WARNING);
        } else {
            boolean answer = DialogUtils.showConfirmDialog("Bạn có chắc muốn xóa phòng ban "+pbSelected + " không?");
            if (answer) {
                int n = SingletonDaoUtil.getPhongBanDaoImpl().delete(pbSelected);

                if (n > 0) {
                    deleteSuccess();
                }
            }
        }
    }

    public void setBtnCancel() {
        if (DialogUtils.showConfirmDialog(messCancel)) {
            statusDefault();
            fillToForm(indexPhongBan);
        }
    }

    public void setTxtSearch() {
        if (txtSearch.getText().length() > 0) {
            phongBanListTimKiem = getListPhongBanTimKiem();
            search();

            if (phongBanListTimKiem.size() > 0) {
                btnEdit.setEnabled(true);
                btnDelete.setEnabled(true);
            } else {
                btnEdit.setEnabled(false);
                btnDelete.setEnabled(false);
            }

        } else {
            indexPhongBanTimKiem = 0;
            indexPhongBan = 0;
            fillToForm(indexPhongBan);

            btnEdit.setEnabled(true);
            btnDelete.setEnabled(true);
        }
    }

    //<editor-fold desc="Xóa thành công">
    public void deleteSuccess() {

        if (indexPhongBan == phongBanList.size() -1) {
            indexPhongBan = indexPhongBan - 1;
        }
        loadDataToTblPhongBan();
        fillToForm(indexPhongBan);
        MainFrame.quanLyNhanVien.refresh();;
        MainFrame.quanLyTaiSan.refresh();
    }
    //</editor-fold>

    //<editor-fold desc="Lưu thành công">
    public void saveSuccess() {
        statusDefault();
        loadDataToTblPhongBan();

        for (PhongBan item : phongBanList) {
            if (item.getId().equalsIgnoreCase(pbSelected.getId())) {
                indexPhongBan = phongBanList.indexOf(item);
                fillToForm(indexPhongBan);
                break;
            }
        }

        MainFrame.quanLyNhanVien.refresh();;
        MainFrame.quanLyTaiSan.refresh();
    }
    //</editor-fold>

    //<editor-fold desc="Lấy danh sách phòng ban tìm kiếm">
    public List<PhongBan> getListPhongBanTimKiem() {
        String str = txtSearch.getText().trim();
        Map<String, Object> map = new HashMap<>();
        map.put("mapb", str);
        map.put("tenpb", str);
        List<PhongBan> list = SingletonDaoUtil.getPhongBanDaoImpl().searchByProperty(map);
        return list;
    }
    //</editor-fold>

    //<editor-fold desc="Tìm kiếm phòng ban">
    public void search() {
        if (phongBanListTimKiem.size() > 0) {

            if (indexPhongBanTimKiem == phongBanListTimKiem.size()) {
                indexPhongBanTimKiem = 0;
            }

            for (PhongBan item : phongBanList) {
                if (item.getId().equalsIgnoreCase(phongBanListTimKiem.get(indexPhongBanTimKiem).getId())) {
                    indexPhongBan = phongBanList.indexOf(item);
                    fillToForm(indexPhongBan);
                    break;
                }
            }
        } else {
            clearForm();
            indexPhongBan = 0;
            indexPhongBanTimKiem = 0;
            tblDepartment.clearSelection();
        }
    }
    //</editor-fold>

    //<editor-fold desc="Check Information">
    public boolean checkInfor() {
        String name = txtName.getText().trim();
        if (name.length() == 0) {
            DialogUtils.showMessageDialog("Tên phòng ban không được trống, vui lòng nhập đầy đủ!", CoreConstant.TYPE_WARNING);
            txtName.requestFocus();
            return false;
        }

        if (SingletonDaoUtil.getPhongBanDaoImpl().checkPhongBanExist(name) == true) {
            DialogUtils.showMessageDialog("Tên phòng ban đã tồn tại, vui lòng nhập lại!", CoreConstant.TYPE_WARNING);
            txtName.requestFocus();
            return false;
        }
        return true;
    }
    //</editor-fold>

    //<editor-fold desc="Load dữ liệu vào bảng danh sách phòng ban">
    public void loadDataToTblPhongBan() {
        modelTalDepartment.setRowCount(0);
        phongBanList = SingletonDaoUtil.getPhongBanDaoImpl().findAll();
        int stt = 1;
        for (PhongBan item : phongBanList) {
            modelTalDepartment.addRow(new Object[] {
                stt, item.getId(), item.getTenPhongBan()
            });
            stt++;
        }
    }
    //</editor-fold>

    //<editor-fold desc="Status mặc định">
    public void statusDefault() {
        flagSave = CoreConstant.FLAG_EMTY;

        btnNew.setVisible(true);
        btnEdit.setVisible(true);
        btnDelete.setVisible(true);

        btnSave.setVisible(false);
        btnCancel.setVisible(false);

        txtId.setEditable(false);
        txtName.setEditable(false);
        txtSearch.setEditable(true);
    }
    //</editor-fold>

    //<editor-fold desc="Set status theo flagSave">
    public void setStatus (int insertable) {
        flagSave = insertable;

        switch (insertable) {
            case CoreConstant.FLAG_INSERT:
                messCancel = "Bạn có muốn bỏ qua thao tác thêm mới không?";
                clearForm();
                btnChange();
                txtId.requestFocus();
                break;
            case CoreConstant.FLAG_UPDATE:
                messCancel = "Bạn có muốn bỏ qua thao tác cập nhật thông tin không?";
                btnChange();
                txtName.requestFocus();
                break;
            default:
                statusDefault();
                break;
        }
    }
    //</editor-fold>

    //<editor-fold desc="Button thay đổi theo flagSave">
    public void btnChange() {
        btnSave.setVisible(true);
        btnNew.setVisible(false);
        btnEdit.setVisible(false);
        btnDelete.setVisible(false);
        btnCancel.setVisible(true);

        txtId.setEditable(false);
        txtName.setEditable(true);
        txtSearch.setEditable(false);

        txtName.requestFocus();
    }
    //</editor-fold>

    //<editor-fold desc="Set model Phòng ban">
    public void setModel(PhongBan phongBan) {
        if (phongBan != null) {
            txtId.setText(phongBan.getId());
            txtName.setText(phongBan.getTenPhongBan());
        } else {
            txtId.setText("");
            txtName.setText("");
        }
    }
    //</editor-fold>

    //<editor-fold desc="Lấy model Phòng ban từ Form">
    public PhongBan getModel() {
        String mapb = txtId.getText().trim();
        String tenpb = txtName.getText().trim();
        PhongBan phongBan = new PhongBan(mapb, tenpb);
        return  phongBan;
    }
    //</editor-fold>

    //<editor-fold desc="Xóa trắng form">
    public void clearForm() {
        setModel(null);
    }
    //</editor-fold>

    //<editor-fold desc="Fill dữ liệu lên Form">
    public void fillToForm(int i) {
        if (phongBanList.size() > 0) {
            tblDepartment.setRowSelectionInterval(i, i);
            Rectangle rect = tblDepartment.getCellRect(i, 0, true);
            tblDepartment.scrollRectToVisible(rect);

            String id = (tblDepartment.getValueAt(i, 1).toString());
            pbSelected = SingletonDaoUtil.getPhongBanDaoImpl().findById(id);
            setModel(pbSelected);
        } else {
            clearForm();
        }
    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Tạo mã phòng ban">
    private String createID() {
        String lastID = SingletonDaoUtil.getPhongBanDaoImpl().getLastID();

        if (lastID != null) {
            StringBuilder ID = new StringBuilder();
            ID.append("PB");

            int pathNumber = Integer.parseInt(lastID.substring(2)) + 1;

            for (int i = 0; i < 5 - String.valueOf(pathNumber).length(); i++) {
                ID.append("0");
            }

            ID.append(pathNumber);

            return ID.toString();
        } else {
            return "PB00001";
        }
    }
    //</editor-fold>

    //<editor-fold desc="COMPONENT">
    List<PhongBan> phongBanList = new ArrayList<>();
    List<PhongBan> phongBanListTimKiem = new ArrayList<>();

    PhongBan pbSelected = new PhongBan();

    int indexPhongBan = 0;
    int indexPhongBanTimKiem = 0;

    int sttPhongBan = -1;

    int flagSave;
    String messCancel;

    JLabel lblId, lblName, lblSearch;
    JTextField txtId, txtName, txtSearch;
    JButton btnNext, btnNew, btnEdit, btnDelete, btnCancel, btnSave, btnExport;
    JTable tblDepartment;
    DefaultTableModel modelTalDepartment;
    JScrollPane scTalDepartment;
    //</editor-fold>
}
