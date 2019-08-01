package Frame.Panel;

import DAO.DAOImpl.ThongKe1DaoImpl;
import DAO.DAOImpl.ThongKe2DaoImpl;
import DAO.DAOImpl.ThongKe3DaoImpl;
import Utils.DateUtil;
import Utils.MoneyUtil;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaoCaoThongKe extends JPanel {

    public BaoCaoThongKe() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BaoCaoThongKe.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(BaoCaoThongKe.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(BaoCaoThongKe.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(BaoCaoThongKe.class.getName()).log(Level.SEVERE, null, ex);
        }
        open();
    }

    public void open() {
        initComponents();
        addControls();
        addEvents();
        loadDataToTblThongKe1();
        loadDataToTblThongKe2();
        loadDataToTblThongKe3();
    }

    public void initComponents() {
        cbxChoose = new JComboBox(new Object[] {
            " - Thống kê số lượng và tổng giá trị tài sản theo phân loại",
                " - Thống kê số lượng và tổng giá trị tài sản phòng ban",
                " - Liệt kê danh sách tài sản hết khấu hao"
        });

        cbxChoose.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 14));


        //<editor-fold desc="BẢNG THỐNG KÊ 1">
        modelTK1 = new DefaultTableModel();
        modelTK1.setColumnIdentifiers(new Object[] {
                "STT", "Loại tài sản" ,"Số lượng", "Tổng giá trị tài sản"
        });
        tblThongKe1 =  new JTable(modelTK1){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                JLabel c = (JLabel) super.prepareRenderer(renderer, row, column);
                    c.setHorizontalAlignment(JLabel.CENTER);
                return c;
            }
        };
        scTK1 = new JScrollPane(tblThongKe1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        JTableHeader tblHeader1 = tblThongKe1.getTableHeader();
        ((DefaultTableCellRenderer) tblHeader1.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        tblThongKe1.getColumnModel().getColumn(0).setPreferredWidth(90);
        tblThongKe1.getColumnModel().getColumn(1).setPreferredWidth(500);
        tblThongKe1.getColumnModel().getColumn(2).setPreferredWidth(100);
        tblThongKe1.getColumnModel().getColumn(3).setPreferredWidth(200);

        tblThongKe1.setRowHeight(25);
        //</editor-fold>

        //<editor-fold desc="BẢNG THỐNG KÊ 2">
        modelTK2 = new DefaultTableModel();
        modelTK2.setColumnIdentifiers(new Object[] {
                "STT", "Tên phòng ban" ,"Số lượng", "Tổng giá trị tài sản"
        });
        tblThongKe2 =  new JTable(modelTK2){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                JLabel c = (JLabel) super.prepareRenderer(renderer, row, column);
                c.setHorizontalAlignment(JLabel.CENTER);
                return c;
            }
        };
        scTK2 = new JScrollPane(tblThongKe2, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        JTableHeader tblHeader2 = tblThongKe2.getTableHeader();
        ((DefaultTableCellRenderer) tblHeader2.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        tblThongKe2.getColumnModel().getColumn(0).setPreferredWidth(90);
        tblThongKe2.getColumnModel().getColumn(1).setPreferredWidth(500);
        tblThongKe2.getColumnModel().getColumn(2).setPreferredWidth(100);
        tblThongKe2.getColumnModel().getColumn(3).setPreferredWidth(200);

        tblThongKe2.setRowHeight(25);
        //</editor-fold>

        //<editor-fold desc="BẢNG THỐNG KÊ 3">
        modelTK3 = new DefaultTableModel();
        modelTK3.setColumnIdentifiers(new Object[] {
                "STT", "Tên tài sản", "Loại tài sản" ,"Ngày bàn giao", "Thời gian khấu hao"
        });
        tblThongKe3 =  new JTable(modelTK3){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                JLabel c = (JLabel) super.prepareRenderer(renderer, row, column);
                c.setHorizontalAlignment(JLabel.CENTER);
                return c;
            }
        };
        scTK3 = new JScrollPane(tblThongKe3, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        JTableHeader tblHeader3 = tblThongKe3.getTableHeader();
        ((DefaultTableCellRenderer) tblHeader3.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        tblThongKe3.getColumnModel().getColumn(0).setPreferredWidth(90);
        tblThongKe3.getColumnModel().getColumn(1).setPreferredWidth(300);
        tblThongKe3.getColumnModel().getColumn(2).setPreferredWidth(200);
        tblThongKe3.getColumnModel().getColumn(3).setPreferredWidth(200);
        tblThongKe3.getColumnModel().getColumn(4).setPreferredWidth(100);

        tblThongKe3.setRowHeight(25);
        //</editor-fold>


        //<editor-fold desc="Căn giữa tbl1">
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
        for (int i = 0; i < tblThongKe1.getColumnCount(); i++) {
            tblThongKe1.setDefaultRenderer(tblThongKe1.getColumnClass(i), renderer1);
        }

        tblThongKe1.setRowSorter(new TableRowSorter(modelTK1));
        tblThongKe1.setAutoCreateRowSorter(true);
        //</editor-fold>

        //<editor-fold desc="Căn giữa tbl2">
        DefaultTableCellRenderer renderer2 = new DefaultTableCellRenderer() {
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
        renderer2.setHorizontalAlignment((int) JTable.CENTER_ALIGNMENT);
        for (int i = 0; i < tblThongKe1.getColumnCount(); i++) {
            tblThongKe2.setDefaultRenderer(tblThongKe2.getColumnClass(i), renderer2);
        }

        tblThongKe2.setRowSorter(new TableRowSorter(modelTK2));
        tblThongKe2.setAutoCreateRowSorter(true);
        //</editor-fold>

        //<editor-fold desc="Căn giữa tbl3">
        DefaultTableCellRenderer renderer3 = new DefaultTableCellRenderer() {
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
        renderer3.setHorizontalAlignment((int) JTable.CENTER_ALIGNMENT);
        for (int i = 0; i < tblThongKe1.getColumnCount(); i++) {
            tblThongKe3.setDefaultRenderer(tblThongKe3.getColumnClass(i), renderer3);
        }

        tblThongKe3.setRowSorter(new TableRowSorter(modelTK3));
        tblThongKe3.setAutoCreateRowSorter(true);
        //</editor-fold>

        cbxChoose.setPreferredSize(new Dimension(800, 30));

        btnExport1 = new JButton("Kết xuất");
        btnExport2 = new JButton("Kết xuất");
        btnExport3 = new JButton("Kết xuất");

    }

    public void addControls() {
        JPanel pnHeader = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnHeader.add(cbxChoose);

        cardLayout = new CardLayout();
        pnContent = new JPanel(cardLayout);

        JPanel pnTK1 = new JPanel(new BorderLayout());
        JPanel pnTK1Item1 = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        pnTK1Item1.add(btnExport1);
        pnTK1.add(pnTK1Item1, BorderLayout.NORTH);
        pnTK1.add(scTK1, BorderLayout.CENTER);

        JPanel pnTK2 = new JPanel(new BorderLayout());
        JPanel pnTK2Item = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        pnTK2Item.add(btnExport2);
        pnTK2.add(pnTK2Item, BorderLayout.NORTH);
        pnTK2.add(scTK2, BorderLayout.CENTER);

        JPanel pnTK3 = new JPanel(new BorderLayout());
        JPanel pnTK3Item = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        pnTK3Item.add(btnExport3);
        pnTK3.add(pnTK3Item, BorderLayout.NORTH);
        pnTK3.add(scTK3, BorderLayout.CENTER);

        pnContent.add(pnTK1,"ThongKe1");
        pnContent.add(pnTK2,"ThongKe2");
        pnContent.add(pnTK3,"ThongKe3");

        JPanel pnTemp1 = new JPanel();
        JPanel pnTemp2 = new JPanel();

        this.setLayout(new BorderLayout(10, 10));
        this.add(pnTemp1, BorderLayout.WEST);
        this.add(pnTemp2, BorderLayout.EAST);
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
                        loadDataToTblThongKe2();
                        break;
                    case 3:
                        cardLayout.show(pnContent, "ThongKe3");
                        loadDataToTblThongKe3();
                        break;
                    default: break;
                }
            }
        });
    }

    public void loadDataToTblThongKe1() {
        List<Object[]> list = new ThongKe1DaoImpl().getListThongKe1();
        modelTK1.setRowCount(0);
        int stt = 1;

        for (Object[] item : list) {
            String money = null;
            if (item[2]!= null) {
                String temp = item[2].toString();
                System.out.println(temp);
                money = MoneyUtil.castIntToMoney(Long.parseLong(temp));
            } else {
                money = "0";
            }
            modelTK1.addRow(new Object[]{
                    stt,item[0], item[1], money
            });
            stt++;
        }
    }

    public void loadDataToTblThongKe2() {
        List<Object[]> list = new ThongKe2DaoImpl().getListThongKe2();
        modelTK2.setRowCount(0);
        int stt = 1;
        for (Object[] item : list) {
            String money = null;
            if (item[2] != null) {
                String temp = item[2].toString();
                money = MoneyUtil.castIntToMoney(Long.parseLong(temp));
            } else {
                money = "0";
            }
            modelTK2.addRow(new Object[]{
                    stt,item[0], item[1], money
            });
            stt++;
        }
    }

    public void loadDataToTblThongKe3() {
        List<Object[]> list = new ThongKe3DaoImpl().getListThongKe3();
        modelTK3.setRowCount(0);
        int stt = 1;
        for (Object[] item : list) {
            String date = null;
            try {
                 date = DateUtil.castDateForm3ToForm1(item[2].toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            modelTK3.addRow(new Object[]{
                    stt,item[0], item[1], date , item[3]
            });
            stt++;
        }
    }

    //<editor-fold desc="COMPONENT">
    JComboBox cbxChoose;
    JTable tblThongKe1, tblThongKe2, tblThongKe3;
    DefaultTableModel modelTK1, modelTK2, modelTK3;
    JScrollPane scTK1, scTK2, scTK3;
    CardLayout cardLayout;
    JPanel pnContent;
    JButton btnExport1,btnExport2,btnExport3;
    //</editor-fold>
}
