package view;

import interface_adapter.welcome.WelcomeController;
import interface_adapter.welcome.WelcomeViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The View for the Welcome Use Case.
 */
public class WelcomeView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "welcome";
    private final WelcomeViewModel welcomeViewModel;

    private final JButton loginButton;
    private final JButton managerSignUpButton;
    private final JButton activateEmployeeButton;
    private WelcomeController welcomeController;

    public WelcomeView(WelcomeViewModel welcomeViewModel) {
        this.welcomeViewModel = welcomeViewModel;
        welcomeViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel(WelcomeViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(title.getFont().deriveFont(Font.BOLD, 24));
        final JPanel titlePanel = new JPanel();
        titlePanel.add(title);

        // Create a panel to hold the Login and Sign-up buttons.
        final JPanel buttons = new JPanel();
        buttons.setLayout(new GridBagLayout());
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Login button
        gbc.gridx = 0;
        gbc.gridy = 0;
        loginButton = new JButton(WelcomeViewModel.LOGIN_BUTTON_LABEL);
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttons.add(loginButton, gbc);

        // Manager Sign-up button
        gbc.gridy++;
        managerSignUpButton = new JButton(WelcomeViewModel.MANAGER_SIGNUP_BUTTON_LABEL);
        managerSignUpButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttons.add(managerSignUpButton, gbc);

        // Activate Employee button
        gbc.gridy++;
        activateEmployeeButton = new JButton(WelcomeViewModel.ACTIVATE_EMPLOYEE_BUTTON_LABEL);
        activateEmployeeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttons.add(activateEmployeeButton, gbc);

        loginButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        welcomeController.switchToLoginView();
                    }
                }
        );

        managerSignUpButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        welcomeController.switchToSignupView();
                    }
                }
        );

        activateEmployeeButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        welcomeController.switchToActivateAccountView();
                    }
                }
        );

        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        this.add(titlePanel, c);
        c.gridy++;
        c.anchor = GridBagConstraints.CENTER;
        this.add(buttons, c);
    }

    public String getViewName() {
        return viewName;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
    }

    public void setWelcomeController(WelcomeController welcomeController) {
        this.welcomeController = welcomeController;
    }
}
