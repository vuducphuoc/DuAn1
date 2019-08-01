package Frame.Login;

import Contant.CoreConstant;
import Entity.NhanVien;
import Frame.Main.MainFrame;
import Utils.DialogUtils;
import Utils.SingletonDaoUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginFrame extends JFrame {

    public LoginFrame() {
        super("Đăng nhập");
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
        initComponent();
        addControls();
        addEvents();
        showWindows();
    }

    private void showWindows() {
        this.setSize(430, 320);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        ImageIcon icon = new ImageIcon("src/img/logo_apt.png");
        this.setIconImage(icon.getImage());
    }

    public void initComponent() {
        lblTitle = new JLabel("ĐĂNG NHẬP");
        lblUser = new JLabel("Tài khoản");
        lblPassword = new JLabel("Mật khẩu");

        txtUser = new JTextField();
        pwfPassword = new JPasswordField();

        btnLogin = new JButton("Đăng nhập");
        btnExit = new JButton("Đóng");

        // FONT - SIZE
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitle.setForeground(Color.decode("#32488d"));

        lblUser.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 18));
        lblUser.setForeground(Color.decode("#32488d"));
        lblPassword.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 18));
        lblPassword.setForeground(Color.decode("#32488d"));

        btnLogin.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 14));
        btnExit.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 14));

        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnExit.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnExit.setPreferredSize(btnLogin.getPreferredSize());

        txtUser.setHorizontalAlignment(JTextField.CENTER);
        pwfPassword.setHorizontalAlignment(JTextField.CENTER);
    }

    private void addControls() {
        Container con = getContentPane();
        con.setLayout(new BorderLayout());

        JPanel pnMain = new JPanel(new BorderLayout());

        JPanel pnTitle = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 30));
        pnTitle.add(lblTitle);

        JPanel pnContent = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 20, 15, 20);

        gbc.gridx = 0;
        gbc.gridy = 0;
        pnContent.add(lblUser, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        pnContent.add(lblPassword, gbc);

        gbc.ipadx = 200;
        gbc.ipady = 10;
        gbc.gridx = 1;
        gbc.gridy = 0;
        pnContent.add(txtUser, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        pnContent.add(pwfPassword, gbc);

        JPanel pnButton = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 20));
        pnButton.add(btnLogin);
        pnButton.add(btnExit);

        pnMain.add(pnContent, BorderLayout.NORTH);
        pnMain.add(pnButton, BorderLayout.CENTER);

        progressBar = new JProgressBar(0, 100);
        con.add(pnTitle, BorderLayout.NORTH);
        con.add(pnMain, BorderLayout.CENTER);
        con.add(progressBar, BorderLayout.SOUTH);
    }

    private void addEvents() {
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                login();
            }
        });

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        // <editor-fold defaultstate="collapsed" desc="Sự kiện phím enter và down cho txtUser ">
        txtUser.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    login();
                }

                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    pwfPassword.requestFocus();
                }
            }
        });
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Sự kiện phím enter và up cho txtPass ">
        pwfPassword.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    login();
                }

                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    txtUser.requestFocus();
                }
            }
        });
        // </editor-fold>
    }

    public void login() {
        String email = txtUser.getText().trim();
        String password = pwfPassword.getText().trim();

        boolean kiemtra = SingletonDaoUtil.getNhanVienDaoImpl().checkLogin(email, password);
        if (kiemtra == CoreConstant.LOGIN_SUCCESS) {
            mainFrame = new MainFrame();
            mainFrame.setVisible(true);
            frThis.setVisible(false);
        } else {
            pwfPassword.setText("");
            pwfPassword.requestFocus();
        }
    }

    //<editor-fold desc="COMPONENT">
    public JProgressBar progressBar;
    JTextField txtUser;
    JPasswordField pwfPassword;
    JButton btnLogin, btnExit;
    JLabel lblUser, lblPassword, lblTitle;
    public static NhanVien nvLogin;

    JFrame frThis = this;
    public static MainFrame mainFrame;
    public static int role;
    //</editor-fold>
}
