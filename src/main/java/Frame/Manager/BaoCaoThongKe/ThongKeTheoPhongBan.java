package Frame.Manager.BaoCaoThongKe;

import Contant.CoreConstant;
import Entity.PhongBan;
import Entity.TaiSan;
import Utils.DialogUtils;
import Utils.ExportUtils;
import Utils.MoneyUtil;
import Utils.SingletonDaoUtil;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class ThongKeTheoPhongBan extends JPanel{

    public ThongKeTheoPhongBan() {
        open();
    }

    public void open() {
        initComponents();
        addControls();
        addEvents();
    }

    public void initComponents() {
        btnExport = new JButton("Kết xuất");
        btnExport.setFont(new Font("Segoe UI", 0, 12));
        btnExport.setPreferredSize(new Dimension(100, 25));
        btnExport.setCursor(new Cursor(Cursor.HAND_CURSOR));

        //<editor-fold desc=" Bảng thống kê">
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[] {
                "STT", "Tên phòng ban" ,"Số lượng", "Tổng giá trị tài sản"
        });
        table =  new JTable(model){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                JLabel c = (JLabel) super.prepareRenderer(renderer, row, column);
                c.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
                if (column == 2 || column == 3) {
                    c.setHorizontalAlignment(JLabel.RIGHT);
                } else {
                    c.setHorizontalAlignment(JLabel.LEFT);
                }
                return c;
            }
        };
        table.setSelectionBackground(Color.decode("#3a4d8f"));
        scTable = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        JTableHeader tblHeader1 = table.getTableHeader();
        ((DefaultTableCellRenderer) tblHeader1.getDefaultRenderer()).setHorizontalAlignment(JLabel.LEFT);
        ((DefaultTableCellRenderer) tblHeader1.getDefaultRenderer()).setFont(new Font("Segoe UI", 0, 14));

        table.getColumnModel().getColumn(0).setPreferredWidth(90);
        table.getColumnModel().getColumn(1).setPreferredWidth(500);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.getColumnModel().getColumn(3).setPreferredWidth(200);

        table.setRowHeight(22);

        DefaultTableCellRenderer renderer1 = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? Color.decode("#e7e7e7") : Color.WHITE);
                    c.setFont(new Font("Segoe UI", 0, 14));
                }
                return c;
            }
        };
        renderer1.setHorizontalAlignment((int) JTable.CENTER_ALIGNMENT);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.setDefaultRenderer(table.getColumnClass(i), renderer1);
        }

        table.setRowSorter(new TableRowSorter(model));
        table.setAutoCreateRowSorter(true);
        //</editor-fold>

        //<editor-fold desc=" Bảng tài sản">
        modelTaiSan = new DefaultTableModel();
        modelTaiSan.setColumnIdentifiers(new Object[] { "STT", "Mã tài sản", "Tên tài sản", "Nguyên giá", "Thời gian khấu hao", "Tỷ lệ khấu hao"});
        tblTaiSan = new JTable(modelTaiSan) {
            private static final long serialVersionUID = 1L;
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                JLabel c = (JLabel) super.prepareRenderer(renderer, row, column);
                c.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 13));
                c.setHorizontalAlignment(JLabel.LEFT);
                return c;
            }
        };
        tblTaiSan.setRowHeight(22);
        tblTaiSan.setSelectionBackground(Color.decode("#3a4d8f"));
        scTblTaiSan = new JScrollPane(tblTaiSan, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JTableHeader tblHeaderTS = tblTaiSan.getTableHeader();
//        ((DefaultTableCellRenderer) tblHeaderTS.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        tblHeaderTS.getColumnModel().getColumn(0).setPreferredWidth(30);
        tblHeaderTS.getColumnModel().getColumn(1).setPreferredWidth(100);
        tblHeaderTS.getColumnModel().getColumn(2).setPreferredWidth(200);
        tblHeaderTS.getColumnModel().getColumn(3).setPreferredWidth(100);
        tblHeaderTS.getColumnModel().getColumn(4).setPreferredWidth(40);
        tblHeaderTS.getColumnModel().getColumn(5).setPreferredWidth(40);
        //</editor-fold>

    }

    public void addControls() {
        JPanel pnTblThongKe = new JPanel(new BorderLayout());
        JPanel pnExport = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        pnExport.add(btnExport);

        pnTblThongKe.add(pnExport, BorderLayout.NORTH);
        pnTblThongKe.add(scTable, BorderLayout.CENTER);

        JPanel pnTblTaiSan = new JPanel(new BorderLayout());
        pnTblTaiSan.add(scTblTaiSan, BorderLayout.CENTER);
        pnTblTaiSan.setPreferredSize(new Dimension(0, 250));

        this.setLayout(new BorderLayout(10, 0));
        this.add(pnTblThongKe, BorderLayout.CENTER);
        this.add(pnTblTaiSan, BorderLayout.SOUTH);
    }

    public void addEvents() {
        loadDataToTable();
        phongBanSelected = getPhongBanSelected(index);
        loadDataToTblTaiSan();

        btnExport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                export();
            }
        });

        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                index = table.getSelectedRow();
                phongBanSelected = getPhongBanSelected(index);
                loadDataToTblTaiSan();
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

    public void loadDataToTable() {
        results = SingletonDaoUtil.getBaoCaoThongKeDaoImpl().getListThongKeTheoPhongBan();
        model.setRowCount(0);
        int stt = 1;
        for (Object[] item : results) {
            String money = null;
            if (item[2] != null) {
                String temp = item[2].toString();
                money = MoneyUtil.castIntToMoney(Long.parseLong(temp));
            } else {
                money = "0";
            }
            model.addRow(new Object[]{
                    stt,item[0], item[1], money
            });
            stt++;
        }
    }

    public void loadDataToTblTaiSan() {
        taiSanList = SingletonDaoUtil.getTaiSanDaoImpl().getByPhongBan(phongBanSelected);
        modelTaiSan.setRowCount(0);

        int stt = 1;
        for (TaiSan item : taiSanList) {
            modelTaiSan.addRow(new Object[] {
            });
            stt++;
        }
    }

    public PhongBan getPhongBanSelected(int i) {
        table.setRowSelectionInterval(i, i);
        Rectangle rect = table.getCellRect(i, 0, true);
        table.scrollRectToVisible(rect);

        String name = (String) (table.getValueAt(i, 1));
        PhongBan phongBan = SingletonDaoUtil.getPhongBanDaoImpl().findEqualUnique("tenpb", name);
        return phongBan;
    }

    public void export() {
        if(table.getRowCount()<=0){
            DialogUtils.showMessageDialog("Chưa có dữ liệu để xuất ra file Excel. Vui lòng kiểm tra lại!", CoreConstant.TYPE_WARNING);
            return;
        }else{
            try {
                String home = System.getProperty("user.home");
                String tenFile = "Thống kê số lượng và tổng giá trị sản phẩm theo phòng ban - " + System.currentTimeMillis() + ".xlsx";
                File fileExcel = new File(home+"\\Desktop", tenFile);

                String[] title = new String[model.getColumnCount()];
                for (int i=0; i<model.getColumnCount(); i++) {
                    title[i] = model.getColumnName(i);
                }

                ExportUtils.exportExcel(fileExcel, "Thống kê số lượng và tổng giá trị sản phẩm theo phòng ban", title, results);
                File file = new File(home+"\\Desktop\\"+tenFile);
                Desktop.getDesktop().open(file);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void refresh() {
        loadDataToTable();
        phongBanSelected = getPhongBanSelected(index);
        loadDataToTblTaiSan();
    }

    //<editor-fold desc="Description">
    List<Object[]> results = new ArrayList<>();

    List<TaiSan> taiSanList = new ArrayList<>();
    PhongBan phongBanSelected = new PhongBan();

    int index = 0;

    JTable table, tblTaiSan;
    DefaultTableModel model, modelTaiSan;
    JScrollPane scTable, scTblTaiSan;
    JButton btnExport;
    //</editor-fold>
}
