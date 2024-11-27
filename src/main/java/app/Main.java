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
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

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
                                            .addManageShiftsView()
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
                                            .addManageShiftsUseCase()
                                            .build();

        application.pack();
        application.setVisible(true);
    }
}
