package Frame.Manager.QuanLyPhieuBanGiao;

import Frame.Main.MainFrame;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PopupTaiSanChooser extends JFrame {

    //<editor-fold desc="Component">
    JLabel lblTaiSan = new JLabel("Tài Sản");
    JLabel lblNguoiSuDung = new JLabel("Người Sử Dụng");

    JComboBox cbxTaiSan = new JComboBox();
    JTextArea txaNguoiSuDung = new JTextArea();

    JButton btnAdd = new JButton("THÊM");
    JButton btnShowPopupSelectNhanVien = new JButton("...");

    PopupNhanVienChooser popupNhanVienChooser = new PopupNhanVienChooser();
    //</editor-fold>

    public PopupTaiSanChooser() {
        open();
    }

    public void open() {
        initComponents();
        addControls();
        addEvents();

        this.setVisible(false);
        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        this.setSize(400, 250);
        this.setResizable(false);
    }

    public void showWindow() {
        MainFrame.quanLyPhieuBanGiao.popupPhieuBanGiao.setEnabled(false);
        this.setVisible(true);
    }

    public void closeWindow() {
        MainFrame.quanLyPhieuBanGiao.popupPhieuBanGiao.setEnabled(true);
        this.setVisible(false);
    }

    public void initComponents() {
        lblTaiSan.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        lblNguoiSuDung.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 12));
        AutoCompleteDecorator.decorate(cbxTaiSan);
        txaNguoiSuDung.setLineWrap(true);
        txaNguoiSuDung.setMargin(new Insets(5,10,5,10));
    }

    public void addControls() {
        JPanel pnMain = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5,10,5,10);

        gbc.ipady = 4;
        gbc.weightx = 1;

        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        pnMain.add(lblTaiSan, gbc);

        gbc.gridy = 1;
        pnMain.add(cbxTaiSan, gbc);

        gbc.gridy = 2;
        pnMain.add(lblNguoiSuDung, gbc);

        gbc.gridheight = 2;
        gbc.gridwidth = 1;
        gbc.gridy = 3;
        pnMain.add(new JScrollPane(txaNguoiSuDung), gbc);

        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.gridy = 5;
        pnMain.add(btnAdd, gbc);


        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.gridx = 1;
        gbc.gridy = 3;
        pnMain.add(btnShowPopupSelectNhanVien, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        pnMain.add(new Label(), gbc);

        this.setLayout(new BorderLayout());
        this.add(pnMain, BorderLayout.CENTER);
    }

    public void addEvents() {

        btnShowPopupSelectNhanVien.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                popupNhanVienChooser.showWindow();
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