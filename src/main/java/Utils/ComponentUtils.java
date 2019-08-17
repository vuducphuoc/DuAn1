package Utils;

import Contant.CoreConstant;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ComponentUtils {

    public static JButton[] createButton(Object[] name, Font font, Object[] icon, Cursor cursor) {
        JButton[] jButtons = new JButton[name.length];
        for (int i = 0; i < name.length; i++) {
            jButtons[i] = new JButton( (String) name[i]);
            if (font != null) jButtons[i].setFont(font);
            if (cursor != null) jButtons[i].setCursor(cursor);
            if (icon != null) jButtons[i].setIcon(new ImageIcon( (String) icon[i]));
        }
        return jButtons;
    }

    public static JLabel[] createLabel(Object[] objects, Font font) {
        JLabel[] jLabels = new JLabel[objects.length];
        for (int i = 0; i < objects.length; i++) {
            jLabels[i] = new JLabel( (String) objects[i]);
            jLabels[i].setFont(font);
        }
        return jLabels;
    }

    public static JTextField[] createTextField(Object[] objects, Font font) {
        JTextField[] jTextFields = new JTextField[objects.length];
        for (int i = 0; i < objects.length; i++) {
            jTextFields[i] = new JTextField( (String) objects[i]);
            if (font != null) jTextFields[i].setFont(font);
        }
        return jTextFields;
    }

    public static JRadioButton[] createRadioButton(Object[] name, Font font, Color bgColor) {
        JRadioButton[] jRadioButtons = new JRadioButton[name.length];
        for (int i = 0; i < name.length; i++) {
            jRadioButtons[i] = new JRadioButton( (String) name[i]);
            if (font != null) jRadioButtons[i].setFont(font);
            if (bgColor != null) jRadioButtons[i].setBackground(bgColor);
        }
        return jRadioButtons;
    }

}
