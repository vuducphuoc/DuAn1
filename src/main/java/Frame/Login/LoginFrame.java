package Frame.Login;

import Contant.CoreConstant;
import Entity.NhanVien;
import Entity.TaiKhoan;
import Frame.Main.MainFrame;
import Utils.DialogUtils;
import Utils.SingletonDaoUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.util.*;
import java.util.List;
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
        this.setSize(432, 370);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        ImageIcon icon = new ImageIcon("src/Image/logo-fpl.png");
        this.setIconImage(icon.getImage());
    }

    public void initComponent() {
        lblTitle = new JLabel("ĐĂNG NHẬP");

//        lblTitle.setIcon(new ImageIcon("src/Image/icon-user.png"));

        lblUser = new JLabel("Tài khoản");
        lblUser.setIcon(new ImageIcon("src/Image/icon-user.png"));
        lblUser.setFont(new Font("Segoe UI", Font.BOLD, 15));
//        lblUser.setForeground(Color.decode("#32488d"));
        lblUser.setForeground(Color.decode("#000"));

        lblPassword = new JLabel("Mật khẩu");
        lblPassword.setIcon(new ImageIcon("src/Image/icon-password.png"));
        lblPassword.setFont(new Font("Segoe UI", Font.BOLD, 15));
//        lblPassword.setForeground(Color.decode("#32488d"));
        lblPassword.setForeground(Color.decode("#000"));

        lblForget = new JLabel("Quên mật khẩu");
        lblForget.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblForget.setForeground(Color.decode("#32488d"));
        lblForget.setCursor(new Cursor(Cursor.HAND_CURSOR));

        txtUser = new JTextField();
        pwfPassword = new JPasswordField();

        btnLogin = new JButton("ĐĂNG NHẬP");
        btnExit = new JButton("Đóng");

        //<editor-fold desc="Set Font Segoe UI">
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitle.setForeground(Color.decode("#32488d"));

        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnLogin.setForeground(Color.decode("#32488d"));
        btnLogin.setPreferredSize(new Dimension(140, 35));

        btnExit.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 14));
        //</editor-fold>

        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnExit.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnExit.setPreferredSize(btnLogin.getPreferredSize());

        txtUser.setMargin(new Insets(5, 10, 5, 0));
        pwfPassword.setMargin(new Insets(5, 10, 5, 0));

//        txtUser.setHorizontalAlignment(JTextField.CENTER);
//        pwfPassword.setHorizontalAlignment(JTextField.CENTER);
    }

    private void addControls() {
        Container con = getContentPane();
        con.setLayout(new BorderLayout());

        JPanel pnMain = new JPanel(new BorderLayout());

        JPanel pnTitle = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 20));
        pnTitle.add(lblTitle);

        JPanel pnContent = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 0, 10, 0);

        gbc.ipadx = 200;
        gbc.ipady = 5;

        gbc.gridx = 0;
        gbc.gridy = 0;
        pnContent.add(lblUser, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        pnContent.add(txtUser, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        pnContent.add(lblPassword, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        pnContent.add(pwfPassword, gbc);

        JPanel pnForget = new JPanel(new FlowLayout(FlowLayout.RIGHT,0,0));
        pnForget.add(lblForget);

        gbc.gridx = 0;
        gbc.gridy = 4;
        pnContent.add(pnForget, gbc);

        JPanel pnButton = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 5));
        pnButton.add(btnLogin);

        JPanel pnTemp = new JPanel();
        pnTemp.setPreferredSize(new Dimension(0, 20));

        pnMain.add(pnContent, BorderLayout.NORTH);
        pnMain.add(pnButton, BorderLayout.CENTER);
        pnMain.add(pnTemp, BorderLayout.SOUTH);

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
                if (e.getKeyCode() == KeyEvent.VK_ENTER && frThis.isFocusable() == true) {
                    login();
                }

                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    pwfPassword.requestFocus();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                //<editor-fold desc="GỢI Ý EMAIL SAU KHI BẤM @">
                Set<String> s = new TreeSet<>();
                s.add("gmail.com");
                s.add("fpt.edu.vn");
                s.add("facebook.com");

                String text = txtUser.getText();
                int lengthText = text.length();
                int lengthCheck = text.indexOf("@");
                int n = lengthText - lengthCheck - 1;

                for (String data : s) {
                    String str = "";

                    if (lengthCheck >= 0) {
                        str = text.substring(0, lengthCheck + 1);

                        for (int j = 0; j < n; j++) {
                            if (n < data.length()) {
                                str += data.charAt(j);
                            }
                        }
                    }

                    if (str.equals(text) && str.length() > 0 && e.getKeyCode() != 8) {
                        txtUser.setText(text + data.substring(n));
                        txtUser.setSelectionStart(lengthText);
                        txtUser.setSelectionEnd(data.length() + lengthText);
                    }
                }
                //</editor-fold>
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
                if (e.getKeyCode() == KeyEvent.VK_ENTER && frThis.isFocusable() == true) {
                    login();
                }

                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    txtUser.requestFocus();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        // </editor-fold>

        lblForget.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {

            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                Font font = lblForget.getFont();
                Map attributes = font.getAttributes();
                attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                lblForget.setFont(font.deriveFont(attributes));
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                Font font = lblForget.getFont();
                Map attributes = font.getAttributes();
                attributes.put(TextAttribute.UNDERLINE, -1);
                lblForget.setFont(font.deriveFont(attributes));
            }
        });
    }

    public void login() {
        if (checkInfor()) {
            String email = txtUser.getText().trim();
            String password = pwfPassword.getText().trim();

            accountLogin = SingletonDaoUtil.getTaiKhoanDaoImpl().checkLogin(email, password);
            if (LoginFrame.accountLogin == null) {
                DialogUtils.showMessageDialog("Tài khoản hoặc mật khẩu không chính xác! Vui lòng thử lại", CoreConstant.TYPE_WARNING);
                pwfPassword.setText("");
                pwfPassword.requestFocus();
            } else {
                if (accountLogin.isTinhTrang() == true) {
                    DialogUtils.showMessageDialog("Xin lỗi! Tài khoản của bạn đã bị khóa!", CoreConstant.TYPE_WARNING);
                    txtUser.requestFocus();
                } else {
                    runProgressbar();

                }
            }
        }
    }

    public boolean checkInfor() {
        String email = txtUser.getText().trim();
        String password = pwfPassword.getText().trim();

        if (email.length() == 0) {
            DialogUtils.showMessageDialog("Tài khoản không được trống, vui lòng nhập tài khoản!", CoreConstant.TYPE_WARNING);
            txtUser.requestFocus();
            return false;
        }

        if (password.length() == 0) {
            DialogUtils.showMessageDialog("Mật khẩu không được trống, vui lòng nhập mật khẩu!", CoreConstant.TYPE_WARNING);
            pwfPassword.requestFocus();
            return false;
        }

        return true;
    }

    // <editor-fold defaultstate="collapsed" desc="Progressbar ">
    private void runProgressbar() {
        int valueProgress = 0;

        while (valueProgress <= 100) {
            progressBar.setValue(valueProgress);
            valueProgress++;

            if (valueProgress == 100) {
                mainFrame = new MainFrame();
                mainFrame.setVisible(true);
                frThis.setVisible(false);
                break;
            }

            try {
                Thread.sleep(15);
            } catch (InterruptedException ex) {
                Logger.getLogger(LoginFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    // </editor-fold>

    //<editor-fold desc="COMPONENT">
    boolean enter = true;

    public JProgressBar progressBar;
    JTextField txtUser;
    JPasswordField pwfPassword;
    JButton btnLogin, btnExit;
    JLabel lblUser, lblPassword, lblTitle;
    JLabel lblForget;

    public static TaiKhoan accountLogin;

    JFrame frThis = this;
    public static MainFrame mainFrame;
    public static int role;
    //</editor-fold>
}
