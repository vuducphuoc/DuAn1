package Frame.Manager.BaoCaoThongKe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class BaoCaoThongKe extends JPanel {
    public static ThongKeTheoPhanLoai thongKeTheoPhanLoai;
    public static ThongKeTheoPhongBan thongKeTheoPhongBan;
    public static ThongKeTaiSanHetKhauHao thongKeTaiSanHetKhauHao;

    public BaoCaoThongKe() {
//        thongKeTheoPhanLoai = new ThongKeTheoPhanLoai();
//        thongKeTheoPhongBan = new ThongKeTheoPhongBan();
//        thongKeTaiSanHetKhauHao = new ThongKeTaiSanHetKhauHao();
//        open();
        doneLoad = true;
    }

    public void open() {
        initComponents();
        addControls();
        addEvents();
    }

    public void initComponents() {
        cbxChoose = new JComboBox(new Object[] {
            " - Thống kê số lượng và tổng giá trị tài sản theo phân loại",
                " - Thống kê số lượng và tổng giá trị tài sản phòng ban",
                " - Liệt kê danh sách tài sản hết khấu hao"
        });

        cbxChoose.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 13));
        cbxChoose.setPreferredSize(new Dimension(800, 30));
    }

    public void addControls() {
        JPanel pnHeader = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
        pnHeader.add(cbxChoose);

        cardLayout = new CardLayout();
        pnContent = new JPanel(cardLayout);

        pnContent.add(thongKeTheoPhanLoai,"ThongKe1");
        pnContent.add(thongKeTheoPhongBan,"ThongKe2");
        pnContent.add(thongKeTaiSanHetKhauHao,"ThongKe3");

        JPanel pnTemp1 = new JPanel();
        JPanel pnTemp2 = new JPanel();

        this.setLayout(new BorderLayout());
        this.add(pnHeader, BorderLayout.NORTH);
        this.add(pnContent, BorderLayout.CENTER);
    }

    public void addEvents() {
        cbxChoose.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
                int i = cbxChoose.getSelectedIndex() + 1;
                switch (i) {
                    case 1:
                        cardLayout.show(pnContent, "ThongKe1");
                        break;
                    case 2:
                        cardLayout.show(pnContent, "ThongKe2");
                        break;
                    case 3:
                        cardLayout.show(pnContent, "ThongKe3");
                        break;
                    default: break;
                }
            }
        });
    }

    public static void refresh() {
        thongKeTheoPhanLoai.refresh();
        thongKeTheoPhongBan.refresh();
        thongKeTaiSanHetKhauHao.refresh();
    }

    //<editor-fold desc="COMPONENT">
    public boolean doneLoad = false;

    JComboBox cbxChoose;
    CardLayout cardLayout;
    JPanel pnContent;
    //</editor-fold>

}
