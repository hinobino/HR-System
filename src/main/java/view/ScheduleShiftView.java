package view;

import interface_adapter.schedule_shift.ScheduleShiftController;
import interface_adapter.schedule_shift.ScheduleShiftState;
import interface_adapter.schedule_shift.ScheduleShiftViewModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Time;
import java.time.LocalDate;

/**
 * The view for the ScheduleShift use case.
 */
public class ScheduleShiftView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "schedule shift";
    private final ScheduleShiftViewModel scheduleShiftViewModel;

    private final JFormattedTextField dateInputField = new JFormattedTextField(new DateFormatter().dateFormat);
    private final JFormattedTextField startTimeInputField = new JFormattedTextField(new TimeFormatter().timeFormat);
    private final JFormattedTextField endTimeInputField = new JFormattedTextField(new TimeFormatter().timeFormat);

    private final JButton back;
    private final JButton submit;

    private ScheduleShiftController scheduleShiftController;

    /**
     * Constructor for the ScheduleShift view.
     */
    public ScheduleShiftView(ScheduleShiftViewModel scheduleShiftViewModel) {
        this.scheduleShiftViewModel = scheduleShiftViewModel;
        scheduleShiftViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel(ScheduleShiftViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        dateInputField.setColumns(10);
        final LabelTextPanel dateInfo = new LabelTextPanel(
                new JLabel(scheduleShiftViewModel.DAY_LABEL), dateInputField
        );

        startTimeInputField.setColumns(5);
        final LabelTextPanel startTimeInfo = new LabelTextPanel(
                new JLabel(scheduleShiftViewModel.START_TIME_LABEL), startTimeInputField
        );

        endTimeInputField.setColumns(5);
        final LabelTextPanel endTimeInfo = new LabelTextPanel(
                new JLabel(scheduleShiftViewModel.END_TIME_LABEL), endTimeInputField
        );

        // TODO: figure out how to get currently logged-in manager
        // TODO: JComboBox for employee select

        final JPanel buttons = new JPanel();
        back = new JButton(scheduleShiftViewModel.BACK_WELCOME_LABEL);
        buttons.add(back);
        submit = new JButton(scheduleShiftViewModel.SCHEDULE_SHIFT_BUTTON_LABEL);
        buttons.add(submit);

        back.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        resetView();
                        scheduleShiftController.returnToManagerView();
                    }
                }
        );

        submit.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Verify date
                        if (!DateFormatter.verifyDate(dateInputField.getText())) {
                            JOptionPane.showMessageDialog(ScheduleShiftView.this,
                                    "Invalid Date.");
                            resetView();
                            return;
                        }

                        // Verify start time
                        if (!TimeFormatter.verifyTime(startTimeInputField.getText())) {
                            JOptionPane.showMessageDialog(ScheduleShiftView.this,
                                    "Invalid start time.");
                            resetView();
                            return;
                        }

                        // Verify end time
                        if (!TimeFormatter.verifyTime(endTimeInputField.getText())) {
                            JOptionPane.showMessageDialog(ScheduleShiftView.this,
                                    "Invalid end time.");
                            resetView();
                            return;
                        }

                        // Execute the ScheduleShift use case and switch back to the manager view
                        final ScheduleShiftState currentState = scheduleShiftViewModel.getState();
                        currentState.setDay(DateFormatter.makeDate(dateInputField.getText()));
                        currentState.setStartTime(TimeFormatter.makeTime(startTimeInputField.getText()));
                        currentState.setEndTime(TimeFormatter.makeTime(endTimeInputField.getText()));

                        scheduleShiftController.execute(
                                currentState.getDay(),
                                currentState.getStartTime(),
                                currentState.getEndTime(),
                                currentState.getEmployee()
                        );

                        resetView();
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(dateInfo);
        this.add(startTimeInfo);
        this.add(endTimeInfo);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {}

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final ScheduleShiftState state = (ScheduleShiftState) evt.getNewValue();
        if (state.getDayError() != null) {
            JOptionPane.showMessageDialog(this, state.getDayError());
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setScheduleShiftController(ScheduleShiftController controller) {
        this.scheduleShiftController = controller;
    }

    public void resetView() {
        dateInputField.setValue(null);
        startTimeInputField.setValue(null);
        endTimeInputField.setValue(null);

        scheduleShiftViewModel.setState(new ScheduleShiftState());
        scheduleShiftViewModel.firePropertyChanged();
    }

}
