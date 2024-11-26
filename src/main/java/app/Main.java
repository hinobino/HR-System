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
                                            .addManagerView()
                                            .addEmployeeView()
                                            .addActivateAccountView()
                                            .addCreateEmployeeView()
                                            .addEmployeeListView()
                                            .addManageEmployeeView()
                                            .addScheduleShiftView()
                                            .addScheduleView()
                                            .addWelcomeUseCase()
                                            .addSignupUseCase()
                                            .addLoginUseCase()
                                            .addActivateAccountUseCase()
                                            .addManagerUseCase()
                                            .addEmployeeUseCase()
                                            .addCreateEmployeeUseCase()
                                            .addLogoutUseCase()
                                            .addEmployeeListUseCase()
                                            .addManageEmployeeUseCase()
                                            .addScheduleShiftUseCase()
                                            .build();

        application.pack();
        application.setVisible(true);
    }
}
