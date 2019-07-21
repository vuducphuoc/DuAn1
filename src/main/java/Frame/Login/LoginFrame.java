package Frame.Login;

import Frame.Main.MainFrame;

import javax.swing.*;
import java.awt.*;
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
        this.setSize(450, 320);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
//        this.setVisible(true);
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
    }

    public JProgressBar progressBar;
    JTextField txtUser;
    JPasswordField pwfPassword;
    JButton btnLogin, btnExit;
    JLabel lblUser, lblPassword, lblTitle;

    JFrame frThis = this;
    public static MainFrame mainFrame;
    public static int role;
    final int ADMIN = 1;
}
