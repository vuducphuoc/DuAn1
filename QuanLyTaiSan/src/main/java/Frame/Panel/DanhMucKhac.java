package Frame.Panel;

import Frame.Panel.QuanLyNhaSanXuat;
import Frame.Panel.QuanLyPhongBan;
import Frame.Panel.QuanLyQuocGia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DanhMucKhac extends JPanel {
    public QuanLyPhongBan quanLyPhongBan = new QuanLyPhongBan();
    public QuanLyNhaSanXuat quanLyNhaSanXuat = new QuanLyNhaSanXuat();
    public QuanLyQuocGia quanLyQuocGia = new QuanLyQuocGia();

    public DanhMucKhac() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DanhMucKhac.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(DanhMucKhac.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DanhMucKhac.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(DanhMucKhac.class.getName()).log(Level.SEVERE, null, ex);
        }
        open();
    }

    public void open() {
        initComponents();
        addControls();
        addEvents();
    }

    public void initComponents() {

    }

    public void addControls() {
        //Panel chức năng
        JPanel pnFunction = new JPanel(new BorderLayout());
        pnFunction.setPreferredSize(new Dimension(300, 0));
        pnFunction.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "CÁC CHỨC NĂNG"));

        listFunc = new JList();
        Object[] func = new Object[] {"Quản lý Phòng ban", "Quản lý Nhà sản xuất", "Quản lý Quốc gia"};
        listFunc.setListData(func);

        listFunc.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 14));
        listFunc.setSelectedIndex(0);
        listFunc.setBackground(Color.WHITE);

        pnFunction.add(listFunc, BorderLayout.CENTER);

        JPanel pnWrapFunc = new JPanel(new BorderLayout());
        pnDetailFunc = new JPanel();
        pnWrapFunc.add(pnDetailFunc, BorderLayout.CENTER);

         cardLayout = new CardLayout();
        pnDetailFunc.setLayout(cardLayout);

        pnDetailFunc.add(quanLyPhongBan, "QuanLyPhongBan");
        pnDetailFunc.add(quanLyNhaSanXuat, "QuanLyNhaSanXuat");
        pnDetailFunc.add(quanLyQuocGia, "QuanLyQuocGia");

        JPanel pnTemp = new JPanel();
        pnTemp.setPreferredSize(new Dimension(0, 10));

        this.setLayout(new BorderLayout());
        this.add(pnTemp, BorderLayout.NORTH);
        this.add(pnFunction, BorderLayout.WEST);
        this.add(pnWrapFunc, BorderLayout.CENTER);
    }

    public void addEvents() {
        // <editor-fold defaultstate="collapsed" desc="Sự kiện click chọn chức năng ">
        listFunc.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showDetailFunc();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Sự kiện chọn chức năng băng key up down ">
        listFunc.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
                    showDetailFunc();
                }
            }
        });
        // </editor-fold>
    }

    // <editor-fold defaultstate="collapsed" desc="HIỂN THỊ CHI TIẾT CHỨC NĂNG ">
    private void showDetailFunc() {
        Object obj = listFunc.getSelectedValue();
        switch (obj.toString()) {
            case "Quản lý Phòng ban":
                cardLayout.show(pnDetailFunc, "QuanLyPhongBan");
                break;
            case "Quản lý Nhà sản xuất":
                cardLayout.show(pnDetailFunc, "QuanLyNhaSanXuat");
                break;
            case "Quản lý Quốc gia":
                cardLayout.show(pnDetailFunc, "QuanLyQuocGia");
                break;
            default:
                break;
        }
//        lblTitleDetailFunc.setText(obj.toString().toUpperCase());
    }
    // </editor-fold>

    JList listFunc;
    CardLayout cardLayout;
    JPanel pnDetailFunc;
}
