package Frame.Login;

import Contant.CoreConstant;
import Entity.NhanVien;
import Utils.DialogUtils;
import Utils.SingletonDaoUtil;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class ChangePassFrame extends JFrame{

    public static JLabel lblShowUser, lblShowName;
    JPasswordField txtOldPass,txtNewPass,txtRepass;
    JButton btnSave, btnCancel;
    JFrame frame    = this;

    public ChangePassFrame() {
        super("Thay đổi mật khẩu");
        addControls();
        addEvents();
        showWindows();
    }
    
    // <editor-fold defaultstate="collapsed" desc="SHOW WINDOW ">
    private void showWindows() {
        this.setSize(500, 400);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        
        ImageIcon icon = new ImageIcon("src/img/logo_apt.png");
        this.setIconImage(icon.getImage());
    }
    // </editor-fold>

    private void addControls() {

        JLabel lblTitle     = new JLabel("ĐỔI MẬT KHẨU");
        JLabel lblUser      = new JLabel("Tên đăng nhập");
        lblShowUser         = new JLabel();
        JLabel lblName      = new JLabel("Họ và tên");
        lblShowName         = new JLabel();
        JLabel lblOldPass   = new JLabel("Mật khẩu cũ");
        txtOldPass          = new JPasswordField(20);
        JLabel lblNewPass   = new JLabel("Mật khẩu mới");
        txtNewPass          = new JPasswordField(20);
        JLabel lblRepass    = new JLabel("Nhập lại mật khẩu");
        txtRepass           = new JPasswordField(20);

        btnSave             = new JButton("Lưu");
        btnCancel           = new JButton("Hủy");

        //<editor-fold desc="SET FONT">
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));

        btnSave.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 13));
        btnCancel.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 13));

        btnSave.setPreferredSize(new Dimension(120, 30));
        btnCancel.setPreferredSize(new Dimension(120, 30));

        lblUser.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 15));
        lblName.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 15));
        lblNewPass.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 15));
        lblOldPass.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 15));
        lblRepass.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 15));

        lblShowName.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 15));
        lblShowUser.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 15));
        txtNewPass.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 15));
        txtOldPass.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 15));
        txtRepass.setFont(new Font("Segoe UI", Font.ROMAN_BASELINE, 15));


        //</editor-fold>


        JPanel pnTitle      = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 30));
        pnTitle.setPreferredSize(new Dimension(0, 70));
        pnTitle.add(lblTitle);

        JPanel pnContent = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 10, 5, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        pnContent.add(lblUser, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        pnContent.add(lblName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        pnContent.add(lblOldPass, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        pnContent.add(lblNewPass, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        pnContent.add(lblRepass, gbc);

        gbc.ipadx = 130;

        gbc.gridx = 1;
        gbc.gridy = 0;
        pnContent.add(lblShowUser, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        pnContent.add(lblShowName, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        pnContent.add(txtOldPass, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        pnContent.add(txtNewPass, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        pnContent.add(txtRepass, gbc);

        JPanel pnButton     = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 30));
        pnButton.setPreferredSize(new Dimension(0, 100));
        pnButton.add(btnSave);
        pnButton.add(btnCancel);

        Container con = getContentPane();
        con.setLayout(new BorderLayout());
        con.add(pnTitle, BorderLayout.NORTH);
        con.add(pnContent, BorderLayout.CENTER);
        con.add(pnButton, BorderLayout.SOUTH);
    }

    private void addEvents() {
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });
        
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (changePassword()) {
                    DialogUtils.showMessageDialog("Đổi mật khẩu thành công!", CoreConstant.TYPE_INFORMATION);
                    frame.setVisible(false);
                }
            }
        });
    }

    //xử lý đổi mật khẩu
    private boolean changePassword(){
        NhanVien nvLogin       = LoginFrame.nvLogin;

        String oldPass  = txtOldPass.getText();
        String newPass  = txtNewPass.getText();
        String rePass   = txtRepass.getText();

        if (checkInfo()) {
            nvLogin.setMatkhau(newPass);
            LoginFrame.nvLogin = SingletonDaoUtil.getNhanVienDaoImpl().update(nvLogin);
            return true;
        }
        
        return false;
    }
    
    //check thông tin đổi mật khẩu
    private boolean checkInfo(){
        if (txtOldPass.getText().length() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập mật khẩu hiện tại!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            txtOldPass.requestFocus();
            return false;
        }
        
        if (!LoginFrame.nvLogin.getMatkhau().equalsIgnoreCase(txtOldPass.getText())) {
            JOptionPane.showMessageDialog(rootPane, "Mật khẩu hiện tại không đúng!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            txtOldPass.requestFocus();
            return false;
        }
        
        if (txtNewPass.getText().length() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập mật khẩu mới!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            txtNewPass.requestFocus();
            return false;
        }
        
        if (txtNewPass.getText().length() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập mật khẩu mới!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            txtNewPass.requestFocus();
            return false;
        }
        
        if (txtNewPass.getText().length() < 6) {
            JOptionPane.showMessageDialog(rootPane, "Mật khẩu mới phải có độ dài ít nhất 6 ký tự và không chứa ký tự khoảng trắng!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            txtNewPass.requestFocus();
            return false;
        }
        
        if (txtRepass.getText().length() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Vui lòng nhập lại mật khẩu mới!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            txtRepass.requestFocus();
            return false;
        }
        
        if (!txtNewPass.getText().equalsIgnoreCase(txtRepass.getText())) {
            JOptionPane.showMessageDialog(rootPane, "Mật khẩu mới và nhập lại mật khẩu không trùng nhau!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        if (txtNewPass.getText().equals(txtOldPass.getText())) {
            JOptionPane.showMessageDialog(rootPane, "Mật khẩu hiện tại và mật khẩu mới không được giống nhau!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            txtNewPass.requestFocus();
            return false;
        }
        
        return true;
    }

}
