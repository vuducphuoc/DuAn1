package Frame.Manager.BaoCaoThongKe;

import Contant.CoreConstant;
import Entity.PhanLoai;
import Utils.DateUtil;
import Utils.DialogUtils;
import Utils.SingletonDaoUtil;
import com.toedter.calendar.JDateChooser;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


import java.awt.Color;
import java.awt.Font;

public class ThongKeTaiSanHetKhauHao extends JPanel{

    public ThongKeTaiSanHetKhauHao() {
        open();
    }

    public void open() {
        initComponents();
        addControls();
        addEvents();
    }

    public void initComponents() {

        lblPhanLoai = new JLabel("Phân loại");
        lblNgayBDSuDung = new JLabel("Ngày bắt đầu sử dụng");
        lblTuNgay = new JLabel("Từ ngày ");
        lblDenNgay = new JLabel("Đến ngày");

        lblPhanLoai.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblNgayBDSuDung.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblTuNgay.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        lblDenNgay.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));

        lblPhanLoai.setForeground(Color.WHITE);
        lblNgayBDSuDung.setForeground(Color.WHITE);

        dateChooser1 = new JDateChooser() {
            @Override
            public void setLocale(Locale locale) {
                super.setLocale(locale);
            }
        };
        dateChooser2 = new JDateChooser();

        dateChooser1.setDateFormatString("dd-MM-yyyy");
        dateChooser2.setDateFormatString("dd-MM-yyyy");

        dateChooser1.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        dateChooser2.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));

        cbxPhanLoai = new JComboBox();
        cbxPhanLoai.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));

        //<editor-fold desc="Button">
        btnExport = new JButton("Kết xuất");
        btnExport.setFont(new Font("Segoe UI", 0, 12));
        btnExport.setPreferredSize(new Dimension(100, 25));
        btnExport.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnView = new JButton("Xem");
        btnView.setFont(new Font("Segoe UI", 0, 12));
        btnView.setPreferredSize(new Dimension(100, 25));
        btnView.setCursor(new Cursor(Cursor.HAND_CURSOR));
        //</editor-fold>

        //<editor-fold desc="Table">
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[] {
                "STT", "Tên tài sản" ,"Phân loại", "Ngày bàn giao", "Thời gian khấu hao"
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
                c.setHorizontalAlignment(JLabel.CENTER);
                return c;
            }
        };
        scTable = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        JTableHeader tblHeader1 = table.getTableHeader();
        ((DefaultTableCellRenderer) tblHeader1.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        ((DefaultTableCellRenderer) tblHeader1.getDefaultRenderer()).setFont(new Font("Segoe UI", 0, 14));

        table.getColumnModel().getColumn(0).setPreferredWidth(30);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        table.getColumnModel().getColumn(2).setPreferredWidth(150);
        table.getColumnModel().getColumn(3).setPreferredWidth(150);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);

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
    }

    public void addControls() {
        JPanel pnTemp1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnTemp1.add(lblPhanLoai);
        pnTemp1.setBackground(Color.decode("#27ae5f"));
        pnTemp1.setPreferredSize(new Dimension(220, 35));

        JPanel pnTemp2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnTemp2.add(lblNgayBDSuDung);
        pnTemp2.setBackground(Color.decode("#27ae5f"));
        pnTemp2.setPreferredSize(new Dimension(220, 35));

        JPanel pnLoc = new JPanel(new GridBagLayout());
        pnLoc.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        gbc.insets = new Insets(10, 10, 5, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        pnLoc.add(pnTemp1, gbc);

        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.gridx = 0;
        gbc.gridy = 1;
        pnLoc.add(cbxPhanLoai, gbc);

        gbc.insets = new Insets(15, 10, 5, 10);
        gbc.gridx = 0;
        gbc.gridy = 2;
        pnLoc.add(pnTemp2, gbc);

        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.gridx = 0;
        gbc.gridy = 3;
        pnLoc.add(lblTuNgay, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        pnLoc.add(dateChooser1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        pnLoc.add(lblDenNgay, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        pnLoc.add(dateChooser2, gbc);

        JPanel pnTemp = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnTemp.setBackground(Color.WHITE);
        pnTemp.add(btnView);

        JPanel pnLeft = new JPanel(new BorderLayout(10, 10));
        pnLeft.add(pnLoc, BorderLayout.NORTH);
        pnLeft.add(pnTemp, BorderLayout.CENTER);

        JPanel pnThongKe = new JPanel(new BorderLayout());
        pnThongKe.setBackground(Color.WHITE);
        JPanel pnExport = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        pnExport.add(btnExport);

        pnThongKe.add(pnExport, BorderLayout.NORTH);
        pnThongKe.add(scTable, BorderLayout.CENTER);
        pnThongKe.add(pnLeft, BorderLayout.WEST);

        this.setLayout(new BorderLayout());
        this.add(pnThongKe, BorderLayout.CENTER);
    }

    public void addEvents() {
        loadDataToCbx();
        loadDataToTable();

        btnExport.addActionListener(new ActionListener() {
            @Override
//              "STT", "Tên tài s?n", "Lo?i tài s?n" ,"Ngày bàn giao", "Th?i gian kh?u hao"
            public void actionPerformed(ActionEvent e) {
                export();
            }
        });

        btnView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                loadDataToTable();
            }
        });
    }

    public void loadDataToTable() {
        int id = 0;

        if (cbxPhanLoai.getSelectedIndex() != 0) {
            PhanLoai phanLoai = (PhanLoai) cbxPhanLoai.getSelectedItem();
            id = phanLoai.getId();
        }

        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        String date1 = null;
        String date2 = null;

        if (dateChooser1.getDate() != null) {
            date1 = sd.format(dateChooser1.getDate());
        }
        if (dateChooser2.getDate() != null) {
            date2 = sd.format(dateChooser2.getDate());
        }

        List<Object[]> list = SingletonDaoUtil.getBaoCaoThongKeDaoImpl().getListThongKeTaiSanHetKhauHao(id, date1, date2);
        model.setRowCount(0);
        int stt = 1;
        if(list.size() > 0) {
            for (Object[] item : list) {
                String date = null;
                try {
                    date = DateUtil.castDateForm3ToForm1(item[2].toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                model.addRow(new Object[]{
                        stt, item[0], item[1], date , item[3]
                });
                stt++;
            }
        }
    }

    public void loadDataToCbx() {
        cbxPhanLoai.removeAllItems();
        List<PhanLoai> phanLoais = SingletonDaoUtil.getPhanLoaiTaiSanDaoImpl().findAll();
        cbxPhanLoai.addItem("Tất cả");
        for (PhanLoai item : phanLoais) {
            cbxPhanLoai.addItem(item);
        }
    }

    public void export() {

        if(table.getRowCount()<=0){
            DialogUtils.showMessageDialog("Chưa có dữ liệu để xuất ra file Excel. Vui lòng kiểm tra lại!", CoreConstant.TYPE_WARNING);
            return;
        }else{
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Liệt kê danh sách tài sản hết khấu hao");
            //title
            XSSFFont ftit = workbook.createFont();
            ftit.setBold(true);
            ftit.setFontName("Times New Roman");
            ftit.setFontHeight(20);

            CellStyle ctit = workbook.createCellStyle();
            ctit.setFont(ftit);
            ctit.setAlignment(HorizontalAlignment.CENTER);

            Row rtit = sheet.createRow(0);
            Cell ctittle = rtit.createCell(0);
            ctittle.setCellValue("Danh sách tài sản hết khấu hao");
            ctittle.setCellStyle(ctit);
            sheet.addMergedRegion(new CellRangeAddress(0,0,0,4));

            //column names
            XSSFFont fcol = workbook.createFont();
            fcol.setBold(true);
            fcol.setFontName("Times New Roman");
            fcol.setFontHeight(14);
            fcol.setColor(IndexedColors.BLACK.getIndex());

            CellStyle cscol = workbook.createCellStyle();
            cscol.setAlignment(HorizontalAlignment.CENTER);
            cscol.setFont(fcol);
            cscol.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
            cscol.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cscol.setBorderTop(BorderStyle.MEDIUM);
            cscol.setTopBorderColor(IndexedColors.BLACK.getIndex());
            cscol.setBorderBottom(BorderStyle.MEDIUM);
            cscol.setBottomBorderColor(IndexedColors.BLACK.getIndex());
            cscol.setBorderLeft(BorderStyle.MEDIUM);
            cscol.setLeftBorderColor(IndexedColors.BLACK.getIndex());
            cscol.setBorderRight(BorderStyle.MEDIUM);
            cscol.setRightBorderColor(IndexedColors.BLACK.getIndex());
            String[] columns = {"STT","Tên tài sản","Loại tài sản","Ngày bàn giao","Thời gian khấu hao"};

            Row rcol = sheet.createRow(1);
            for(int i=0;i<columns.length;i++){
                Cell cCol = rcol.createCell(i);
                cCol.setCellValue(columns[i]);
                cCol.setCellStyle(cscol);
            }

            sheet.setColumnWidth(0, 4000);
            sheet.setColumnWidth(1, 7000);
            sheet.setColumnWidth(2, 7000);
            sheet.setColumnWidth(3, 7000);
            sheet.setColumnWidth(4, 7000);

            //data
            XSSFFont fdata = workbook.createFont();
            fdata.setBold(false);
            fdata.setFontName("Times New Roman");
            fdata.setFontHeight(14);

            CellStyle cellStyleNormal = workbook.createCellStyle();
            cellStyleNormal.setFont(fdata);
            cellStyleNormal.setAlignment(HorizontalAlignment.CENTER);
            cellStyleNormal.setBorderLeft(BorderStyle.MEDIUM);
            cellStyleNormal.setLeftBorderColor(IndexedColors.BLACK.getIndex());
            cellStyleNormal.setBorderRight(BorderStyle.MEDIUM);
            cellStyleNormal.setRightBorderColor(IndexedColors.BLACK.getIndex());
            cellStyleNormal.setBorderBottom(BorderStyle.MEDIUM);
            cellStyleNormal.setBottomBorderColor(IndexedColors.BLACK.getIndex());

            CellStyle cellStyleNormal1 = workbook.createCellStyle();
            cellStyleNormal1.setFont(fdata);
            cellStyleNormal1.setAlignment(HorizontalAlignment.CENTER);
            cellStyleNormal1.setBorderLeft(BorderStyle.MEDIUM);
            cellStyleNormal1.setLeftBorderColor(IndexedColors.BLACK.getIndex());
            cellStyleNormal1.setBorderRight(BorderStyle.MEDIUM);
            cellStyleNormal1.setRightBorderColor(IndexedColors.BLACK.getIndex());

            for(int i=0;i<table.getRowCount();i++){
                Row row_dl = sheet.createRow(i+2);
                for(int j=0;j<columns.length;j++){
                    Cell cell = row_dl.createCell(j);
                    Object value = table.getValueAt(i, j);
                    if(value.getClass() == Double.class){
                        cell.setCellValue((double)value);
                    }else if(value.getClass() == Integer.class){
                        cell.setCellValue((int)value);
                    }else if(value.getClass() == Date.class){
                        cell.setCellValue((Date)value);
                    }else{
                        cell.setCellValue((String)value);
                    }
                    if(i==table.getRowCount()-1){
                        cell.setCellStyle(cellStyleNormal);
                    }else{
                        cell.setCellStyle(cellStyleNormal1);
                    }
                }
            }

            try {
                String home = System.getProperty("user.home");
                String tenFile = "Liệt kê danh sách tài sản hết khấu hao - " + System.currentTimeMillis() + ".xlsx";
                File fileExcel = new File(home+"\\Desktop", tenFile);
                FileOutputStream outputStream = new FileOutputStream(fileExcel);

                workbook.write(outputStream);
                workbook.close();
                outputStream.close();

                File file = new File(home+"\\Desktop\\"+tenFile);
                Desktop.getDesktop().open(file);
            }
            catch (Exception ex) {
            }
        }
    }

    public void refresh() {
        loadDataToTable();
    }

    //<editor-fold desc="Description">
    JLabel lblPhanLoai, lblNgayBDSuDung, lblTuNgay, lblDenNgay;
    JTextField txtTuNgay, txtDenNgay;
    JComboBox cbxPhanLoai;

    JDateChooser dateChooser1, dateChooser2;

    JTable table;
    DefaultTableModel model;
    JScrollPane scTable;
    JButton btnExport, btnView;
    //</editor-fold>
}
