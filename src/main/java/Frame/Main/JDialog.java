package Frame.Main;

import Frame.Login.LoginFrame;
import Utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JDialog extends JFrame {
    static JProgressBar progressBar;
    static JDialog chaoJDialogUI;

    public static LoginFrame loginFrame;

    public static void main(String[] args) {
        chaoJDialogUI = new JDialog();
        startProgressbar();
    }

    public JDialog(){
        super();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JDialog.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(JDialog.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(JDialog.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(JDialog.class.getName()).log(Level.SEVERE, null, ex);
        }

        addControls();
        addEvents();
        showWindow();
    }

    private void showWindow() {
        this.setUndecorated(true);
        this.setSize(635, 450);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        ImageIcon icon = new ImageIcon("src/img/logo_apt.png");
        this.setIconImage(icon.getImage());
    }

    private void addControls() {
        Container con = getContentPane();
        con.setLayout(new BorderLayout());

        JLabel lblLogo  = new JLabel(new ImageIcon("src/Image/background-jdialog.jpg"));

        progressBar    = new JProgressBar(0, 100);

        con.add(lblLogo,BorderLayout.CENTER);
        con.add(progressBar,BorderLayout.SOUTH);
    }

    private void addEvents() {
    }

    public static void startProgressbar() {
        int i = 0;

        while (true) {
            i++;

            if (i == 30) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(JDialog.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (i == 70) {
                SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
                Session session = sessionFactory.getCurrentSession();
                if (session != null) {
                    continue;
                }
            }

            if (i > 100) {
                chaoJDialogUI.setVisible(false);
                loginFrame = new LoginFrame();
                loginFrame.setVisible(true);
                return;
            }
            progressBar.setValue(i);

            try {
                Thread.sleep(15);
            } catch (InterruptedException ex) {
                Logger.getLogger(JDialog.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
