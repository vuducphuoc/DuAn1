package Frame.Manager.DanhMucKhac;

import Contant.CoreConstant;
import Entity.QuocGia;
import Frame.Manager.BaoCaoThongKe.BaoCaoThongKe;
import Utils.DialogUtils;
import Utils.SingletonDaoUtil;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QuanLyQuocGia extends JPanel {

    public QuanLyQuocGia() {
        open();
    }

    public void open() {
        initComponents();
        addControls();
        addEvents();
    }

    public void initComponents() {

        //<editor-fold desc="Init label">
        lblId = new JLabel("Mã Quốc gia");
        lblName = new JLabel("Tên Quốc gia");
        lblSearch = new JLabel("Tìm kiếm");
        //</editor-fold>

        //<editor-fold desc="Init text">
        txtId = new JTextField();
        txtName = new JTextField();
        txtSearch = new JTextField();

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
        btnNext.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnExport.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSave.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnDelete.setPreferredSize(btnNew.getPreferredSize());
        btnSave.setPreferredSize(btnNew.getPreferredSize());
        btnCancel.setPreferredSize(btnNew.getPreferredSize());
        btnEdit.setPreferredSize(btnNew.getPreferredSize());
        //</editor-fold>

        //<editor-fold desc="Init bảng quốc gia">
        modelTblNatitonal = new DefaultTableModel();
        modelTblNatitonal.setColumnIdentifiers(new Object[] {"STT", "Mã quốc gia","Tên quốc gia"});
        tblNatitonal = new JTable(modelTblNatitonal){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                JLabel c = (JLabel) super.prepareRenderer(renderer, row, column);
                c.setFont(new Font("Segoe UI", 0, 13));
//                if (column == 2) {
//                    c.setHorizontalAlignment(JLabel.LEFT);
//                } else {
                c.setHorizontalAlignment(JLabel.CENTER);
//                }
                return c;
            }
        };
        JTableHeader tblHeaderE = tblNatitonal.getTableHeader();
        ((DefaultTableCellRenderer) tblHeaderE.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        tblHeaderE.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblHeaderE.getColumnModel().getColumn(1).setPreferredWidth(200);
        tblHeaderE.getColumnModel().getColumn(2).setPreferredWidth(300);
        tblNatitonal.setRowHeight(22);
        tblNatitonal.setSelectionBackground(Color.decode("#3a4d8f"));

        tblNatitonal.setRowSorter(new TableRowSorter(modelTblNatitonal));
        tblNatitonal.setAutoCreateRowSorter(true);
        scTblNatitonal = new JScrollPane(tblNatitonal, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        //</editor-fold>

        //<editor-fold desc="Set font Segoe UI">
        lblId.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        lblName.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        lblSearch.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));

        txtId.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        txtName.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        txtSearch.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));

        btnNew.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        btnEdit.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        btnDelete.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        btnCancel.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        btnSave.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        //</editor-fold>

        txtSearch.setPreferredSize(new Dimension(250, 25));

    }

    public void addControls() {
        JPanel pnDetail = new JPanel(new BorderLayout());

        JPanel pnDetailItem = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        gbc.insets = new Insets(5, 10, 5, 10);

        gbc.gridx = 0; gbc.gridy = 0;
        pnDetailItem.add(lblId, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        pnDetailItem.add(lblName, gbc);

        gbc.weightx =1;
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
//        pnSearch.add(btnNext);

        pnTable.add(pnSearch, BorderLayout.NORTH);
        pnTable.add(scTblNatitonal);

        this.setLayout(new BorderLayout(0, 10));
        this.add(pnDetail, BorderLayout.NORTH);
        this.add(pnTable, BorderLayout.CENTER);;
    }

    public void addEvents() {
        loadDataToTblQuocGia();
        fillToForm(indexQuocGia);
        statusDefault();

        tblNatitonal.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if (flagSave == CoreConstant.FLAG_EMTY) {
                    indexQuocGia = tblNatitonal.getSelectedRow();
                    fillToForm(indexQuocGia);
                } else {
                    boolean answer = DialogUtils.showConfirmDialog(messCancel);

                    if (answer){
                        indexQuocGia = tblNatitonal.getSelectedRow();
                        tblNatitonal.setRowSelectionInterval(indexQuocGia, indexQuocGia);
                        Rectangle rect = tblNatitonal.getCellRect(indexQuocGia, 0, true);
                        tblNatitonal.scrollRectToVisible(rect);
                        fillToForm(indexQuocGia);
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
                if (e.getKeyCode() == KeyEvent.VK_ENTER && quocGiaListTimKiem.size() > 0 && txtSearch.getText().length() > 0) {
                    indexQuocGiaTimKiem = indexQuocGiaTimKiem + 1;
                    search();
                }

                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    txtSearch.setText("");
                    indexQuocGia = 0;
                    fillToForm(indexQuocGia);
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
        tblNatitonal.clearSelection();
    }

    public void setBtnEdit() {
        setStatus(CoreConstant.FLAG_UPDATE);
    }

    public void setBtnSave() {
        if (checkInfor()) {
            QuocGia quocGia = getModel();
            if (flagSave == CoreConstant.FLAG_INSERT) {
                SingletonDaoUtil.getQuocGiaDaoImpl().save(quocGia);
                saveSuccess();
            } else if (flagSave == CoreConstant.FLAG_UPDATE) {
                SingletonDaoUtil.getQuocGiaDaoImpl().update(quocGia);
                saveSuccess();
            }
        }
    }

    public void setBtnDelete() {
        boolean answer = DialogUtils.showConfirmDialog("Bạn có chắc muốn xóa quốc gia "+qgSelected + " không?");
        if (answer) {
            int n = SingletonDaoUtil.getQuocGiaDaoImpl().delete(qgSelected);

            if (n > 0) {
                deleteSuccess();
            }
        }
    }

    public void setBtnCancel() {
        if (DialogUtils.showConfirmDialog(messCancel)) {
            statusDefault();
            fillToForm(indexQuocGia);
        }
    }

    public void setTxtSearch() {
        if (txtSearch.getText().length() > 0) {
            quocGiaListTimKiem = getListQuocGiaTimKiem();
            search();

            if (quocGiaListTimKiem.size() > 0) {
                btnEdit.setEnabled(true);
                btnDelete.setEnabled(true);
            } else {
                btnEdit.setEnabled(false);
                btnDelete.setEnabled(false);
            }

        } else {
            indexQuocGiaTimKiem = 0;
            indexQuocGia = 0;
            fillToForm(indexQuocGia);

            btnEdit.setEnabled(true);
            btnDelete.setEnabled(true);
        }
    }

    //<editor-fold desc="Xóa thành công">
    public void deleteSuccess() {
        if (indexQuocGia == quocGiaList.size() -1) {
            indexQuocGia = indexQuocGia - 1;
        }
        loadDataToTblQuocGia();
        fillToForm(indexQuocGia);

        BaoCaoThongKe.refresh();
        DanhMucKhac.quanLyNhaSanXuat.refresh();
    }
    //</editor-fold>

    //<editor-fold desc="Lưu thành công">
    public void saveSuccess() {
        loadDataToTblQuocGia();
        for (QuocGia item : quocGiaList) {
            if (item.getId().equalsIgnoreCase(getModel().getId())) {
                indexQuocGia = quocGiaList.indexOf(item);
                fillToForm(indexQuocGia);
                break;
            }
        }
        statusDefault();
        BaoCaoThongKe.refresh();
        DanhMucKhac.quanLyNhaSanXuat.refresh();
    }
    //</editor-fold>

    //<editor-fold desc="Lấy danh sách quốc qia tìm kiếm">
    public java.util.List<QuocGia> getListQuocGiaTimKiem() {
        String str = txtSearch.getText().trim();
        Map<String, Object> map = new HashMap<>();
        map.put("maqg", str);
        map.put("tenqg", str);
        List<QuocGia> list = SingletonDaoUtil.getQuocGiaDaoImpl().searchByProperty(map);
        return list;
    }
    //</editor-fold>

    //<editor-fold desc="Tìm kiếm quốc gia">
    public void search() {
        if (quocGiaListTimKiem.size() > 0) {

            if (indexQuocGiaTimKiem == quocGiaListTimKiem.size()) {
                indexQuocGiaTimKiem = 0;
            }

            for (QuocGia item : quocGiaList) {
                if (item.getId().equalsIgnoreCase(quocGiaListTimKiem.get(indexQuocGiaTimKiem).getId())) {
                    indexQuocGia = quocGiaList.indexOf(item);
                    fillToForm(indexQuocGia);
                    break;
                }
            }
        } else {
            clearForm();
            indexQuocGia = 0;
            indexQuocGiaTimKiem = 0;
            tblNatitonal.clearSelection();
        }
    }
    //</editor-fold>

    //<editor-fold desc="Check Information">
    public boolean checkInfor() {
        String id = txtId.getText().trim();
        String name = txtName.getText().trim();

        if (!id.matches("^[A-Za-zÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ\\s\']{2,10}$")) {
            DialogUtils.showMessageDialog("Vui lòng nhập mã quốc gia, không chứa số và các ký tự đặc biệt \n, . / ; < > ? : \" { } - = _ + ` ~ ! @ $ % ^ & * ( ) \\ |", CoreConstant.TYPE_WARNING);
            txtId.requestFocus();
            return false;
        }

        if (!name.matches("^[A-Za-zÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ\\s\']{5,100}$")) {
            DialogUtils.showMessageDialog("Vui lòng nhập đầy đủ tên quốc gia, không chứa số và các ký tự đặc biệt \n, . / ; < > ? : \" { } - = _ + ` ~ ! @ $ % ^ & * ( ) \\ |", CoreConstant.TYPE_WARNING);
            txtName.requestFocus();
            return false;
        }

        return true;
    }
    //</editor-fold>

    //<editor-fold desc="Load dữ liệu vào bảng danh sách quốc gia">
    public void loadDataToTblQuocGia() {
        modelTblNatitonal.setRowCount(0);
        quocGiaList = SingletonDaoUtil.getQuocGiaDaoImpl().findAll();
        int stt = 1;
        for (QuocGia item : quocGiaList) {
            modelTblNatitonal.addRow(new Object[] {
                    stt, item.getId(), item.getTenQuocGia()
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
                txtId.setEditable(false);
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

        txtId.setEditable(true);
        txtName.setEditable(true);
        txtSearch.setEditable(false);
    }
    //</editor-fold>

    //<editor-fold desc="Set model quốc gia">
    public void setModel(QuocGia quocGia) {
        if (quocGia != null) {
            txtId.setText(quocGia.getId());
            txtName.setText(quocGia.getTenQuocGia());
        } else {
            txtId.setText("");
            txtName.setText("");
        }
    }
    //</editor-fold>

    //<editor-fold desc="Lấy model quốc gia từ Form">
    public QuocGia getModel() {
        String maqg = txtId.getText().trim();
        String tenqg = txtName.getText().trim();
        QuocGia quocGia = new QuocGia(maqg, tenqg);
        return  quocGia;
    }
    //</editor-fold>

    //<editor-fold desc="Xóa trắng form">
    public void clearForm() {
        setModel(null);
    }
    //</editor-fold>

    //<editor-fold desc="Fill dữ liệu lên Form">
    public void fillToForm(int i) {
        if (quocGiaList.size() > 0) {
            tblNatitonal.setRowSelectionInterval(i, i);
            Rectangle rect = tblNatitonal.getCellRect(i, 0, true);
            tblNatitonal.scrollRectToVisible(rect);

            String id = (tblNatitonal.getValueAt(i, 1).toString());
            qgSelected = SingletonDaoUtil.getQuocGiaDaoImpl().findById(id);
            setModel(qgSelected);
        } else {
            clearForm();
        }
    }
    //</editor-fold>

    //<editor-fold desc="Description">
    List<QuocGia> quocGiaList = new ArrayList<>();
    List<QuocGia> quocGiaListTimKiem = new ArrayList<>();

    QuocGia qgSelected = new QuocGia();

    int indexQuocGia = 0;
    int indexQuocGiaTimKiem = 0;

    int sttQuocGia = -1;

    int flagSave;
    String messCancel;


    JLabel lblId, lblName, lblSearch;
    JTextField txtId, txtName, txtSearch;
    JButton btnNext, btnNew, btnEdit, btnDelete, btnCancel, btnSave, btnExport;
    JTable tblNatitonal;
    DefaultTableModel modelTblNatitonal;
    JScrollPane scTblNatitonal;
    //</editor-fold>
}
