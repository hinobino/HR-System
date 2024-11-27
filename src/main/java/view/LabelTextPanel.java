package view;

import javax.swing.*;
import java.awt.*;

/**
 * A panel containing a label and a text field.
 */
class LabelTextPanel extends JPanel {
    LabelTextPanel(JLabel label, JTextField textField) {
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.WEST;
        c.weightx = 1.0;
        this.add(label, c);
        c.gridx++;
        c.anchor = GridBagConstraints.EAST;
        c.weightx = 0.0;
        this.add(textField, c);
    }

    LabelTextPanel(JLabel label, JComboBox<String> comboBox) {
        this.add(label);
        this.add(comboBox);
    }
}
