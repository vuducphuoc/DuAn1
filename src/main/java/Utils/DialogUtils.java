package Utils;

import javax.swing.*;

public class DialogUtils {

    public static void showMessageDialog (String message, int type) {
        switch (type) {
            case 1:
                JOptionPane.showMessageDialog(null, message, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                break;
            case 2:
                JOptionPane.showMessageDialog(null, message, "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                break;
            case 3:
                JOptionPane.showMessageDialog(null, message, "Lỗi", JOptionPane.ERROR_MESSAGE);
                break;
                default: break;
        }
    }

    public static boolean showConfirmDialog(String message) {
        int n = JOptionPane.showConfirmDialog(null, message, "Hỏi",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (n == JOptionPane.YES_OPTION) {
            return true;
        }
         else {
             return false;
        }
    }
}
