package app;

import javax.swing.*;

/**
 * The Main class of our application.
 */
public class Main {
    /**
     * Builds and runs the CA architecture of the application.
     * @param args unused arguments
     */
    public static void main(String[] args) {
        final AppBuilder appBuilder = new AppBuilder();
        final JFrame application = appBuilder
                                            .addWelcomeView()
                                            .addLoginView()
                                            .addSignupView()
                                            .addLoggedInView()
                                            .addEmployeeView()
                                            .addActivateAccountView()
                                            .addWelcomeUseCase()
                                            .addSignupUseCase()
                                            .addLoginUseCase()
                                            .addActivateAccountUseCase()
                                            .build();

        application.pack();
        application.setVisible(true);
    }
}
