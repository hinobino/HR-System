package view;

import interface_adapter.create_employee.CreateEmployeeController;
import interface_adapter.create_employee.CreateEmployeeState;
import interface_adapter.create_employee.CreateEmployeeViewModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The view for managers creating employee's userIDs.
 */
public class CreateEmployeeView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "create employee";
    private final CreateEmployeeViewModel createEmployeeViewModel;

    private final JTextField userIDInputField = new JTextField(15);
    private final JLabel userIDErrorField = new JLabel();

    private final JButton createEmployee;

    private CreateEmployeeController createEmployeeController;

    public CreateEmployeeView(CreateEmployeeViewModel createEmployeeViewModel) {
        this.createEmployeeViewModel = createEmployeeViewModel;
        this.createEmployeeViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel(createEmployeeViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(title.getFont().deriveFont(Font.BOLD, 24));

        final LabelTextPanel userIDInfo = new LabelTextPanel(
                new JLabel(createEmployeeViewModel.NEW_USERID_LABEL), userIDInputField);

        final JPanel buttons = new JPanel();

        JButton backButton = new JButton(CreateEmployeeViewModel.BACK_BUTTON_LABEL);
        buttons.add(backButton);

        backButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        createEmployeeController.switchToEmployeeListView();
                        CreateEmployeeView.this.resetView();
                    }
                }
        );

        createEmployee = new JButton(createEmployeeViewModel.CREATE_EMPLOYEE_BUTTON_LABEL);
        buttons.add(createEmployee);

        createEmployee.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        final CreateEmployeeState currentState = createEmployeeViewModel.getState();

                        createEmployeeController.execute(
                                currentState.getNewUserID(),
                                CreateEmployeeView.this
                        );
                    }
                }
        );

        addUserIDListener();

        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        this.add(title, c);
        c.gridy++;
        this.add(userIDInfo, c);
        c.gridy++;
        this.add(userIDErrorField, c);
        c.gridy++;
        this.add(buttons, c);
    }

    private void addUserIDListener() {
        userIDInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final CreateEmployeeState currentState = createEmployeeViewModel.getState();
                currentState.setNewUserID(userIDInputField.getText());
                createEmployeeViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent evt) {}

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final CreateEmployeeState state = (CreateEmployeeState) evt.getNewValue();
        if (state.getNewUserIDError() != null) {
            JOptionPane.showMessageDialog(this, state.getNewUserIDError());
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setCreateEmployeeController(CreateEmployeeController createEmployeeController) {
        this.createEmployeeController = createEmployeeController;
    }

    public void resetView() {
        userIDInputField.setText("");
        userIDErrorField.setText("");

        CreateEmployeeState initialState = new CreateEmployeeState();
        createEmployeeViewModel.setState(initialState);
    }

    public CreateEmployeeState getCreateEmployeeState() { return createEmployeeViewModel.getState(); }

}
