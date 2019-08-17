package Utils;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.Set;
import java.util.TreeSet;

public class AutoCompleteUtils {

    public static void autoCompleteEmail(JTextField textField, Set<String> set, KeyEvent e) {

        //<editor-fold desc="GỢI Ý EMAIL SAU KHI BẤM @">
        String text = textField.getText();
        int lengthText = text.length();
        int lengthCheck = text.indexOf("@");;

        int n = lengthText - lengthCheck - 1;

        for (String data : set) {
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
                textField.setText(text + data.substring(n));
                textField.setSelectionStart(lengthText);
                textField.setSelectionEnd(data.length() + lengthText);
            }
        }
        //</editor-fold>
    }

}
