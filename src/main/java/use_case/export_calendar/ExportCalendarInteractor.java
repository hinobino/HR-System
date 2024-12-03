package use_case.export_calendar;


import entity.Employee;

public class ExportCalendarInteractor implements ExportCalendarInputBoundary{
    private final ExportCalendarUserDataAccessInterface userDataAccessObject;
    private final ExportCalendarOutputBoundary exportCalendarPresenter;


    public ExportCalendarInteractor(ExportCalendarUserDataAccessInterface userDataAccessObject,
                                    ExportCalendarOutputBoundary exportCalendarOutputBoundary) {
        this.userDataAccessObject = userDataAccessObject;
        this.exportCalendarPresenter = exportCalendarOutputBoundary;

    }
    /**
     * Executes the export_calendar use case.
     *
     * @param exportCalendarInputData the input data
     */
    @Override
    public void execute(ExportCalendarInputData exportCalendarInputData) {
        final Employee employee = userDataAccessObject.getEmployee();
        final ExportCalendarOutputData exportCalendarOutputData = new ExportCalendarOutputData(employee);
        exportCalendarPresenter.prepareSuccessView(exportCalendarOutputData);

    }
}
