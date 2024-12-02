package view;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class CustomTableCellRenderer extends DefaultTableCellRenderer {

    private Color red = new Color(204,0,0);
    private Color green = new Color(0,204,0);

    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected,
                                                   boolean hasFocus,
                                                   int row, int column) {
        Component cell = super.getTableCellRendererComponent(table, value,
                isSelected, hasFocus, row, column);
        if (column == 1) {
            String status = (String) value;
            if("INACTIVE".equals(status)) {
                cell.setForeground(red);
            }
            else if("ACTIVE".equals(status)) {
                cell.setForeground(green);
            }
        }
        else if (column == 2) {
            JButton button = (JButton) value;
            return button;
        }
        return cell;
    }
}
