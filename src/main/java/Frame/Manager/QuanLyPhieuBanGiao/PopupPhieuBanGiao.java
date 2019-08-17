package Frame.Manager.QuanLyPhieuBanGiao;

import Frame.Login.LoginFrame;
import Frame.Main.MainFrame;
import Utils.ComponentUtils;
import com.toedter.calendar.JDateChooser;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.renderer.JRendererLabel;
import sun.font.Decoration;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PopupPhieuBanGiao extends JFrame {

    //<editor-fold desc="Component">
    JLabel lblMaPhieu, lblNgayLap, lblBenGiao, lblBenNhan, lblNguoiGiao, lblNguoiNhan;
    JTextField txtMaPhieu, txtNgayLap;
    JComboBox cbxBenGiao, cbxBenNhan, cbxNguoiGiao, cbxNguoiNhan;
    JTable tblChiTiet;
    DefaultTableModel modelTblChiTiet;
    JScrollPane scTblChiTiet;
    JButton[] buttons;
    JButton btnSave, btnNew, btnDelete, btnEdit, btnExit;
    //</editor-fold>

    public PopupTaiSanChooser popupSelectTaiSan = new PopupTaiSanChooser();

    public PopupPhieuBanGiao () {
        super("Phiếu bàn giao chi tiết");
        open();
    }

    public void open() {
        initComponents();
        addControls();
        addEvents();

        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        this.setSize(600, 500);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    public void showWindow() {
        LoginFrame.mainFrame.setEnabled(false);
        this.setVisible(true);
    }

    public void closeWindow() {
        LoginFrame.mainFrame.setEnabled(true);
        this.setVisible(false);
    }

    public void initComponents() {

        //<editor-fold desc="Label">
        lblMaPhieu = new JLabel("Mã Phiếu");
        lblNgayLap = new JLabel("Ngày Lập");
        lblBenGiao = new JLabel("Bên Giao");
        lblBenNhan = new JLabel("Bên Nhận");
        lblNguoiGiao = new JLabel("Người Giao");
        lblNguoiNhan = new JLabel("Người Nhận");
        //</editor-fold>

        //<editor-fold desc="TextField">
        txtMaPhieu = new JTextField();
        txtNgayLap = new JTextField();
        //</editor-fold>

        //<editor-fold desc="ComboBox">
        cbxBenGiao = new JComboBox();
        cbxBenNhan = new JComboBox();
        cbxNguoiGiao = new JComboBox();
        cbxNguoiNhan = new JComboBox();
        //</editor-fold>

        //<editor-fold desc="Button">
        Object[] objects = new Object[] {"X","X","X","X"};
        buttons = ComponentUtils.createButton(objects, new Font("Segoe UI", Font.ROMAN_BASELINE, 12), null, new Cursor(Cursor.HAND_CURSOR));

        btnNew = new JButton("+");
        btnDelete = new JButton("-");
        btnEdit = new JButton("!");
        btnSave = new JButton("Lưu");
        btnExit = new JButton("Thoát");
        //</editor-fold>

        //<editor-fold desc="Table">
        modelTblChiTiet = new DefaultTableModel();
        modelTblChiTiet.setColumnIdentifiers(new Object[]{
              "STT", "Tài Sản", "Năm Sử Dụng", "Thời Gian Khấu Hao","Người Sử Dụng"
        });

        tblChiTiet = new JTable(modelTblChiTiet);
        scTblChiTiet = new JScrollPane(tblChiTiet, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        //</editor-fold>

    }

    public void addControls() {

        JPanel pnChiTiet = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5,10,5,10);

        /* x = 0*/
        gbc.weightx = 0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        pnChiTiet.add(lblMaPhieu, gbc);

        gbc.weightx = 1;
        gbc.gridwidth = 2;
        gbc.gridy = 1;
        pnChiTiet.add(txtMaPhieu, gbc);
        gbc.gridy = 2;
        pnChiTiet.add(lblBenGiao, gbc);

        gbc.gridwidth = 1;
        gbc.insets = new Insets(5,10,5,0);
        gbc.gridy = 3;
        pnChiTiet.add(cbxBenGiao, gbc);
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        pnChiTiet.add(lblNguoiGiao, gbc);
        gbc.gridwidth = 1;
        gbc.gridy = 5;
        pnChiTiet.add(cbxNguoiGiao, gbc);

        gbc.insets = new Insets(5,10,5,10);
        gbc.weightx = 1;
        /* x = 2 */
        gbc.gridx = 2;
        gbc.gridy = 0;
        pnChiTiet.add(lblNgayLap, gbc);
        gbc.gridwidth = 2;
        gbc.gridy = 1;
        pnChiTiet.add(txtNgayLap, gbc);
        gbc.gridy = 2;
        pnChiTiet.add(lblBenNhan, gbc);

        gbc.gridwidth = 1;
        gbc.insets = new Insets(5,10,5,0);
        gbc.gridy = 3;
        pnChiTiet.add(cbxBenNhan, gbc);
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        pnChiTiet.add(lblNguoiNhan, gbc);
        gbc.gridwidth = 1;
        gbc.gridy = 5;
        pnChiTiet.add(cbxNguoiNhan, gbc);

        gbc.insets = new Insets(5,0,5,10);
        /* x = 1 */
        gbc.weightx = 0;
        gbc.gridx = 1;
        gbc.gridy = 3;
        pnChiTiet.add(buttons[0], gbc);
        gbc.gridy = 5;
        pnChiTiet.add(buttons[1], gbc);

        /* x = 3 */
        gbc.weightx = 0;
        gbc.gridx = 3;
        gbc.gridy = 3;
        pnChiTiet.add(buttons[2], gbc);
        gbc.gridy = 5;
        pnChiTiet.add(buttons[3], gbc);

        JPanel pnMenuSideBar = new JPanel(new BorderLayout());
        JPanel pnMenuSideBarItem = new JPanel(new GridBagLayout());
        gbc.insets = new Insets(0,0,0,0);
        gbc.weightx = 1;

        gbc.gridx = 0;
        gbc.gridy = 0;
        pnMenuSideBarItem.add(btnNew, gbc);
        gbc.gridy = 1;
        pnMenuSideBarItem.add(btnEdit, gbc);
        gbc.gridy = 2;
        pnMenuSideBarItem.add(btnDelete, gbc);


        pnMenuSideBar.add(pnMenuSideBarItem, BorderLayout.NORTH);
        pnMenuSideBar.add(new Panel(), BorderLayout.CENTER);
        pnMenuSideBar.setPreferredSize(new Dimension(40, 0));


        JPanel pnTable = new JPanel(new BorderLayout());
        pnTable.add(scTblChiTiet, BorderLayout.CENTER);
        pnTable.add(pnMenuSideBar, BorderLayout.EAST);

        JPanel pnButton = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        pnButton.add(btnSave); pnButton.add(btnExit);
        pnButton.setPreferredSize(new Dimension(0, 40));

        JPanel pnMain = new JPanel(new BorderLayout());
        pnMain.add(pnChiTiet, BorderLayout.NORTH);
        pnMain.add(pnTable, BorderLayout.CENTER);
        pnMain.add(pnButton, BorderLayout.SOUTH);

        this.setLayout(new BorderLayout());
        this.add(pnMain, BorderLayout.CENTER);
    }

    public void addEvents() {

        btnNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                popupSelectTaiSan.showWindow();
            }
        });

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closeWindow();
            }
        });
    }

}
