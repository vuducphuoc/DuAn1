package Frame.Main;

import Frame.Login.LoginFrame;
import Frame.Panel.BaoCaoThongKe;
import Frame.Panel.DanhMucKhac;
import Frame.Panel.QuanLyNhanVien;
import Frame.Panel.QuanLyTaiSan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainFrame extends JFrame {
    public static QuanLyNhanVien quanLyNhanVien = new QuanLyNhanVien();
    public static QuanLyTaiSan quanLyTaiSan = new QuanLyTaiSan();
    public static DanhMucKhac danhMucKhac = new DanhMucKhac();
    public static BaoCaoThongKe baoCaoThongKe = new BaoCaoThongKe();

    public MainFrame () {
        super("PHẦN MỀM QUẢN LÝ TÀI SẢN");
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        open();

    }

    public void open() {
        initComponents();
        addControls();
        addEvents();
        showWindow();
    }

    public void showWindow() {
        this.setSize(1250, 674);
        this.setResizable(true);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setLocationRelativeTo(null);
//        ImageIcon icon = new ImageIcon("");
//        this.setIconImage(icon.getImage());
    }

    public void initComponents() {
        //MENU BAR
        mnuBar = new JMenuBar();
        mnuSystem = new JMenu("Tùy chọn");
        mnuSys_showUsername = new JMenuItem("Nguyễn Văn A");
        mnuSys_Changepass = new JMenuItem("Thay đổi mật khẩu");
        mnuSys_Signout = new JMenuItem("Đăng xuất");
        mnuSys_Exit = new JMenuItem("Thoát");

        mnuSystem.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 15));
        mnuSys_Changepass.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 14));
        mnuSys_Signout.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 14));
        mnuSys_Exit.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 14));
        mnuSys_showUsername.setFont(new Font("Segoe UI", Font.BOLD, 16));
        mnuSys_showUsername.setForeground(Color.BLUE);

        mnuSystem.add(mnuSys_showUsername);
        mnuSystem.addSeparator();
        mnuSystem.add(mnuSys_Changepass);
        mnuSystem.add(mnuSys_Signout);
        mnuSystem.addSeparator();
        mnuSystem.add(mnuSys_Exit);
        mnuBar.add(mnuSystem);

        mnuBar.setPreferredSize(new Dimension(0, 25));
    }

    public void addControls() {
        Container container = getContentPane();
        container.setLayout(new BorderLayout(10,0));

        // PANEL MAIN
        JPanel pnMain = new JPanel(new BorderLayout());

        //TABBED PANE
        tabbedPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
        tabbedPane.setBounds(50,50, 300, 300);

        tabbedPane.addTab("Quản lý Tài Sản", new ImageIcon("src/Image/icon-employee.png"), quanLyTaiSan);
        tabbedPane.addTab("Quản lý Nhân Viên", new ImageIcon("src/Image/icon-employee.png"), quanLyNhanVien);
        tabbedPane.addTab("Báo cáo - Thống Kê", new ImageIcon("src/Image/icon-statistical.png"), baoCaoThongKe);
        tabbedPane.addTab("Danh mục khác", new ImageIcon("src/Image/icon-other-category.png"), danhMucKhac);

        pnMain.add(tabbedPane, BorderLayout.CENTER);

        //PANEL FOOTER
        JPanel pnFooter = new JPanel(new BorderLayout());
        pnFooter.setPreferredSize(new Dimension(0, 30));

        JLabel lblInfo = new JLabel("", new ImageIcon("src/img/Info.png"), JLabel.CENTER);
        lblTime = new JLabel("");
        lblTime.setPreferredSize(new Dimension(150, 30));
        lblTime.setFont(new Font("Segoe UI", 0, 14));
        lblInfo.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 14));

        pnFooter.add(lblInfo, BorderLayout.WEST);
        pnFooter.add(lblTime, BorderLayout.EAST);

        container.add(mnuBar, BorderLayout.NORTH);
        container.add(pnMain, BorderLayout.CENTER);
        container.add(pnFooter, BorderLayout.SOUTH);
    }

    public void addEvents() {
        mnuSys_showUsername.setText(LoginFrame.nvLogin.getTennv());
        setTime();

        // <editor-fold defaultstate="collapsed" desc="Sự kiện mnuSys_Signout ">
        mnuSys_Signout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signOut();
            }
        });
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Sự kiện click vào nút x ">
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int i = JOptionPane.showConfirmDialog(null, "Bạn có muốn đóng chương trình không", "Thông báo", JOptionPane.YES_NO_OPTION);

                if (i == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Sự kiện mnuSys_Exit ">
        mnuSys_Exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = JOptionPane.showConfirmDialog(rootPane, "Bạn có muốn thoát chương trình không?", "Thông báo", JOptionPane.YES_NO_OPTION);

                if (i == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
        // </editor-fold>

    }


    // <editor-fold defaultstate="collapsed" desc="SET TIME ">
    public void setTime() {
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    Date now = new Date();
                    SimpleDateFormat fomater = new SimpleDateFormat();
                    fomater.applyPattern("dd/MM/yyyy HH:mm");
                    String time = fomater.format(now);
                    lblTime.setText(time);

                    try {
                        Thread.sleep(60000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }.start();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="SIGN OUT ">
    private void signOut() {
        int i = JOptionPane.showConfirmDialog(rootPane, "Bạn có muốn đăng xuất không?", "Thông báo", JOptionPane.YES_NO_OPTION);

        if (i == JOptionPane.YES_OPTION) {
            this.setVisible(false);
            JDialog.loginFrame.setVisible(true);
            JDialog.loginFrame.progressBar.setValue(0);
        }
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="COMPONENT ">
    JLabel lblTime;
    JMenuBar mnuBar;
    JMenu mnuSystem;
    JMenuItem mnuSys_showUsername, mnuSys_Changepass, mnuSys_Signout, mnuSys_Exit;
    JTabbedPane tabbedPane;
    // </editor-fold>
}
