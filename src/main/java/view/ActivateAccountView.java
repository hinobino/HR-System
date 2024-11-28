package view;

import interface_adapter.activate_account.ActivateAccountController;
import interface_adapter.activate_account.ActivateAccountState;
import interface_adapter.activate_account.ActivateAccountViewModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The View for the Signup Use Case.
 */
public class ActivateAccountView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "activate account";

    private final ActivateAccountViewModel activateAccountViewModel;
    private final JTextField userIDInputField = new JTextField(15);
    private final JPasswordField passwordInputField = new JPasswordField(15);
    private final JPasswordField repeatPasswordInputField = new JPasswordField(15);
    private ActivateAccountController activateAccountController;

    private final JButton activateAccount;
    private final JButton toWelcome;

    public ActivateAccountView(ActivateAccountViewModel activateAccountViewModel) {
        this.activateAccountViewModel = activateAccountViewModel;
        activateAccountViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel(ActivateAccountViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(title.getFont().deriveFont(Font.BOLD, 24));

        final LabelTextPanel userIDInfo = new LabelTextPanel(
                new JLabel(ActivateAccountViewModel.USERID_LABEL), userIDInputField);
        final LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel(ActivateAccountViewModel.PASSWORD_LABEL), passwordInputField);
        final LabelTextPanel repeatPasswordInfo = new LabelTextPanel(
                new JLabel(ActivateAccountViewModel.REPEAT_PASSWORD_LABEL), repeatPasswordInputField);

        final JPanel buttons = new JPanel();
        buttons.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.weightx = 1.0;

        gbc.gridx = 0;
        gbc.gridy = 0;
//        gbc.anchor = GridBagConstraints.WEST;
        toWelcome = new JButton(ActivateAccountViewModel.TO_WELCOME_BUTTON_LABEL);
        buttons.add(toWelcome, gbc);
        gbc.gridx++;
//        gbc.anchor = GridBagConstraints.EAST;
        activateAccount = new JButton(ActivateAccountViewModel.ACTIVATE_ACCOUNT_BUTTON_LABEL);
        activateAccount.setBackground(new Color(18, 130, 255));
        activateAccount.setForeground(Color.WHITE);
        buttons.add(activateAccount, gbc);


        activateAccount.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        final ActivateAccountState currentState = activateAccountViewModel.getState();

                        // This execute will switch the Signup View to Login View,
                        // see SignupPresenter for implementation (prepareSuccessView method).
                        // It will also reset the view if successful.
                        activateAccountController.execute(
                                currentState.getUserID(),
                                currentState.getPassword(),
                                currentState.getRepeatPassword(),
                                ActivateAccountView.this
                        );
                    }
                }
        );

        toWelcome.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        activateAccountController.switchToWelcomeView();
                        resetView();
                    }
                }
        );

        addUserIDListener();
        addPasswordListener();
        addRepeatPasswordListener();

        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        this.add(title, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy++;
        this.add(userIDInfo, c);
        c.gridy++;
        this.add(passwordInfo, c);
        c.gridy++;
        this.add(repeatPasswordInfo, c);
        c.gridy++;
        this.add(buttons, c);
    }

    private void addUserIDListener() {
        userIDInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final ActivateAccountState currentState = activateAccountViewModel.getState();
                currentState.setUserID(userIDInputField.getText());
                activateAccountViewModel.setState(currentState);
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

    private void addPasswordListener() {
        passwordInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final ActivateAccountState currentState = activateAccountViewModel.getState();
                currentState.setPassword(new String(passwordInputField.getPassword()));
                activateAccountViewModel.setState(currentState);
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

    private void addRepeatPasswordListener() {
        repeatPasswordInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final ActivateAccountState currentState = activateAccountViewModel.getState();
                currentState.setRepeatPassword(new String(repeatPasswordInputField.getPassword()));
                activateAccountViewModel.setState(currentState);
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
        final ActivateAccountState state = (ActivateAccountState) evt.getNewValue();
        if (state.getUserIDError() != null) {
            JOptionPane.showMessageDialog(this, state.getUserIDError());
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setActivateAccountController(ActivateAccountController controller) {
        this.activateAccountController = controller;
    }

    public void resetView() {
        userIDInputField.setText("");
        passwordInputField.setText("");
        repeatPasswordInputField.setText("");

        ActivateAccountState initialState = activateAccountViewModel.getState();
        activateAccountViewModel.setState(initialState);
    }
}
