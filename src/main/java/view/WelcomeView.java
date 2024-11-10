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
    private final JButton signUpButton;
    private WelcomeController welcomeController;

    public WelcomeView(WelcomeViewModel welcomeViewModel) {
        this.welcomeViewModel = welcomeViewModel;
        welcomeViewModel.addPropertyChangeListener(this);

        // TODO: use the WelcomeViewModel class to name the following buttons/labels.

        final JLabel title = new JLabel(WelcomeViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create a panel to hold the Login and Sign-up buttons.
        final JPanel buttons = new JPanel();

        // Login button
        loginButton = new JButton(WelcomeViewModel.LOGIN_BUTTON_LABEL);
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttons.add(loginButton);

        // Sign-up button
        signUpButton = new JButton(WelcomeViewModel.SIGNUP_BUTTON_LABEL);
        signUpButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttons.add(signUpButton);

        loginButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        welcomeController.switchToLoginView();
                    }
                }
        );

        signUpButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        welcomeController.switchToSignupView();
                    }
                }
        );

        buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(buttons);

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
