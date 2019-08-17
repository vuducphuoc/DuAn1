package Frame.Manager.QuanLyPhieuBanGiao;

import Entity.PhieuBanGiao;
import Utils.SingletonDaoUtil;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

public class QuanLyPhieuBanGiao extends JPanel{

    public QuanLyPhieuBanGiao() {
        open();
    }

    public void open() {
        initComponents();
        addControls();
        addEvents();
    }

    public void initComponents() {
        //<editor-fold desc="Label">
        lblTitle1 = new JLabel("Kho");
        lblTitle2 = new JLabel("Ngày lập");

        lblDateStart = new JLabel("Từ ngày");
        lblDateEnd = new JLabel("Đến ngày");
        lblTitleSearch = new JLabel("Tìm kiếm");

        lblTitle1.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle2.setHorizontalAlignment(SwingConstants.CENTER);
        //</editor-fold>

        //<editor-fold desc="Text Field">
        txtSearch = new JTextField();
        //</editor-fold>

        //<editor-fold desc="ComboBoxs">
        cbxKho = new JComboBox();
        //</editor-fold>

        //<editor-fold desc="Date Chooser">
        dateChooser1 = new JDateChooser();
        dateChooser2 = new JDateChooser();

        dateChooser1.setDateFormatString("dd-MM-yyyy");
        dateChooser2.setDateFormatString("dd-MM-yyyy");
        //</editor-fold>

        //<editor-fold desc="Button">
        btnView = new JButton("XEM");
        btnNew = new JButton("THÊM MỚI");
        //</editor-fold>

        //<editor-fold desc="Bảng phiếu bàn giao">
        modelTblPBG = new DefaultTableModel();
        modelTblPBG.setColumnIdentifiers(new Object[] {
                "STT", "MÃ PHIẾU", "NGÀY LẬP", "NGƯỜI BÀN GIAO", "NGƯỜI NHẬN"
        });
        tblPhieuBanGiao = new JTable(modelTblPBG);
        sc1 = new JScrollPane(tblPhieuBanGiao, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        //</editor-fold>

        //<editor-fold desc=" Bảng tài sản theo phiếu bàn giao">
        modelTblPBGCT = new DefaultTableModel();
        modelTblPBGCT.setColumnIdentifiers(new Object[] {
                "STT","MÃ TÀI SẢN", "TÊN TÀI SẢN", "NGÀY BÀN GIAO"
        });
        tblPhieuBanGiaoChiTiet = new JTable(modelTblPBGCT);
        sc2 = new JScrollPane(tblPhieuBanGiaoChiTiet, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        //</editor-fold>
    }

    public void addControls() {
        JPanel pnLeft = new JPanel(new BorderLayout());
        pnLeft.setPreferredSize(new Dimension(250, 0));

        JPanel pnLeftContent = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,10,5,10);
        gbc.fill = GridBagConstraints.BOTH;

        gbc.ipady = 5;
        gbc.weightx = 1;
        gbc.weighty = 1;

        gbc.gridx = 0;

        gbc.gridy = 0;
        pnLeftContent.add(lblTitle1, gbc);
        gbc.gridy = 1;
        pnLeftContent.add(cbxKho, gbc);
        gbc.gridy = 2;
        pnLeftContent.add(lblTitle2, gbc);
        gbc.gridy = 3;
        pnLeftContent.add(lblDateStart, gbc);
        gbc.gridy = 4;
        pnLeftContent.add(dateChooser1, gbc);
        gbc.gridy = 5;
        pnLeftContent.add(lblDateEnd, gbc);
        gbc.gridy = 6;
        pnLeftContent.add(dateChooser2, gbc);
        gbc.gridy = 7;
        pnLeftContent.add(btnView, gbc);

        pnLeft.add(pnLeftContent, BorderLayout.NORTH);
        pnLeft.add(new JPanel(), BorderLayout.CENTER);

        JPanel pnRight = new JPanel(new BorderLayout());

        JPanel pnSearch = new JPanel(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        pnSearch.add(lblTitleSearch, gbc);
        gbc.gridx = 1;
        gbc.weightx = 1;
        pnSearch.add(txtSearch, gbc);

        JPanel pnButton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnButton.add(btnNew);

        JPanel pnRightItem1 = new JPanel(new GridLayout(1, 2));
        pnRightItem1.add(pnSearch);
        pnRightItem1.add(pnButton);

        JPanel pnRightItem2 = new JPanel(new BorderLayout());
        pnRightItem2.add(sc1, BorderLayout.CENTER);
        pnRightItem2.add(sc2, BorderLayout.SOUTH);

        sc2.setPreferredSize(new Dimension(0, 200));

        pnRight.add(pnRightItem1, BorderLayout.NORTH);
        pnRight.add(pnRightItem2, BorderLayout.CENTER);

        this.setLayout(new BorderLayout());
        this.add(pnLeft, BorderLayout.WEST);
        this.add(pnRight, BorderLayout.CENTER);
    }

    public void addEvents() {
        loadDataToTblPhieuBanGiao();

        tblPhieuBanGiao.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 2) {
                    popupPhieuBanGiao.showWindow();
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
    }

    public void loadDataToTblPhieuBanGiao() {
        modelTblPBG.setRowCount(0);
        phieuBanGiaoList = SingletonDaoUtil.getPhieuBanGiaoDaoImpl().findAll();

        if (phieuBanGiaoList.size() > 0) {
            int stt = 1;
            for (PhieuBanGiao item : phieuBanGiaoList) {
                modelTblPBG.addRow(new Object[] {
                        stt, item.getId(), item.getNgayLap(), item.getNhanVienBanGiao().getTenNhanVien(), item.getNhanVienNhan().getTenNhanVien()
                });
                stt++;
            }
        }

    }

    //<editor-fold desc="Component">
    public PopupPhieuBanGiao popupPhieuBanGiao = new PopupPhieuBanGiao();

    // Index
    int indexPhieuBanGiao = 0;

    // Model
    PhieuBanGiao phieuBanGiaoSelected = new PhieuBanGiao();

    // ArrayList
    List<PhieuBanGiao> phieuBanGiaoList = new ArrayList<>();


    JLabel lblTitle1, lblTitle2, lblDateStart, lblDateEnd, lblTitleSearch;
    JTextField txtSearch;
    JDateChooser dateChooser1,dateChooser2;

    JTable tblPhieuBanGiao, tblPhieuBanGiaoChiTiet;
    JScrollPane sc1, sc2;
    DefaultTableModel modelTblPBG, modelTblPBGCT;
    JComboBox cbxKho;
    JButton btnView, btnNew;
    //</editor-fold>
}
