package view;

import interface_adapter.schedule_shift.ScheduleShiftController;
import interface_adapter.schedule_shift.ScheduleShiftState;
import interface_adapter.schedule_shift.ScheduleShiftViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The view for the ScheduleShift use case.
 */
public class ScheduleShiftView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "schedule shift";
    private final ScheduleShiftViewModel scheduleShiftViewModel;

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

        // TODO: figure out how to get date and time inputs
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
                        // Execute the ScheduleShift use case and switch back to the manager view
                        final ScheduleShiftState currentState = scheduleShiftViewModel.getState();
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
        scheduleShiftViewModel.setState(new ScheduleShiftState());
        scheduleShiftViewModel.firePropertyChanged();
    }

}
