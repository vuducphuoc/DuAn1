package Frame.Panel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ThongKe01 extends JPanel {
    public ThongKe01 () {

    }

    public void open() {

    }

    public void initComponents() {
        cbxPhanLoai = new JComboBox();
        modelThongKe01 = new DefaultTableModel();
        modelThongKe01.setColumnIdentifiers(new Object[] {
            "STT", "Số lượng", "Tổng giá trị tài sản"
        });
    }

    public void addControls() {

    }

    JComboBox cbxPhanLoai;
    JTable tblThongKe01;
    JScrollPane scThongKe01;
    DefaultTableModel modelThongKe01;
}
