package view;

import interface_adapter.login.LoginController;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The View for when the user is logging into the program.
 */
public class LoginView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "log in";
    private final LoginViewModel loginViewModel;

    private final JTextField userIDInputField = new JTextField(15);
    private final JLabel userIDErrorField = new JLabel();

    private final JPasswordField passwordInputField = new JPasswordField(15);
    private final JLabel passwordErrorField = new JLabel();

    private final JButton logIn;
    private final JButton toWelcome;
    private LoginController loginController;

    public LoginView(LoginViewModel loginViewModel) {

        this.loginViewModel = loginViewModel;
        this.loginViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel(LoginViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(title.getFont().deriveFont(Font.BOLD, 24));

        final LabelTextPanel userIDInfo = new LabelTextPanel(
                new JLabel(LoginViewModel.USERID_LABEL), userIDInputField);
        final LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel(LoginViewModel.PASSWORD_LABEL), passwordInputField);

        final JPanel buttons = new JPanel();
        buttons.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.weightx = 1.0;

        gbc.gridx = 0;
        gbc.gridy = 0;
//        gbc.anchor = GridBagConstraints.WEST;
        toWelcome = new JButton(LoginViewModel.TO_WELCOME_BUTTON_LABEL);
        buttons.add(toWelcome, gbc);

        gbc.gridx++;
//        gbc.anchor = GridBagConstraints.EAST;
        logIn = new JButton(LoginViewModel.LOGIN_BUTTON_LABEL);
        logIn.setBackground(new Color(18, 130, 255));
        logIn.setForeground(Color.WHITE);
        buttons.add(logIn, gbc);


        logIn.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        // there was an if statement here in Lab 5 that checked if the event was
                        // caused by logIn being clicked... which I think is implied once we are
                        // under this actionPerformed...

                        final LoginState currentState = loginViewModel.getState();

                        loginController.execute(
                                currentState.getUserID(),
                                currentState.getPassword(),
                                LoginView.this
                        );
                    }
                }
        );

        toWelcome.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        loginController.switchToWelcomeView();
                        resetView();
                    }
                }
        );

        userIDInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final LoginState currentState = loginViewModel.getState();
                currentState.setUserID(userIDInputField.getText());
                loginViewModel.setState(currentState);
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

        passwordInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final LoginState currentState = loginViewModel.getState();
                currentState.setPassword(new String(passwordInputField.getPassword()));
                loginViewModel.setState(currentState);
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



        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        userIDErrorField.setForeground(LoginViewModel.ERROR_LABEL_COLOR);
        passwordErrorField.setForeground(LoginViewModel.ERROR_LABEL_COLOR);

        c.gridx = 0;
        c.gridy = 0;
        this.add(title, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy++;
        this.add(userIDInfo, c);
        c.gridy++;
        c.fill = GridBagConstraints.NONE;
        this.add(userIDErrorField, c);
        c.gridy++;
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(passwordInfo, c);
        c.gridy++;
        this.add(buttons, c);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {}

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final LoginState state = (LoginState) evt.getNewValue();
        setFields(state);
        userIDErrorField.setText(state.getLoginError());
    }

    private void setFields(LoginState state) {
        userIDInputField.setText(state.getUserID());
        passwordInputField.setText(state.getPassword());
    }

    public String getViewName() {
        return viewName;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    public void resetView() {
        userIDInputField.setText("");
        passwordInputField.setText("");
        userIDErrorField.setText("");
        passwordErrorField.setText("");

        LoginState initialState = new LoginState();
        loginViewModel.setState(initialState);
    }
}
