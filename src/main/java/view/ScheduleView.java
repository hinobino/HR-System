package view;

import entity.WorkWeek;
import interface_adapter.view_schedule.ScheduleController;
import interface_adapter.view_schedule.ScheduleState;
import interface_adapter.view_schedule.ScheduleViewModel;
import use_case.view_schedule.ScheduleInputBoundary;
import use_case.view_schedule.ScheduleInputData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ScheduleView extends JFrame implements ActionListener, PropertyChangeListener {
    private final String viewName = "schedule";
    private final ScheduleViewModel scheduleViewModel;

    private final CardLayout cardLayout;
    private Container cardContainer;
    private List<String> weekNames = new ArrayList<>();
    private int weekIndex = 0;
    JButton previousButton;
    JButton nextButton;
    JLabel weekLabel;

    private ScheduleController scheduleController;

    public ScheduleView(ScheduleViewModel scheduleViewModel) {
        this.scheduleViewModel = scheduleViewModel;
        this.scheduleViewModel.addPropertyChangeListener(this);

        setScheduleController(scheduleViewModel.getState().getScheduleController());

        this.setTitle(ScheduleViewModel.VIEW_LABEL);
        this.setSize(900, 540);

        cardLayout = new CardLayout();
        cardContainer = new Container();
        cardContainer.setLayout(cardLayout);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                scheduleViewModel.getState().getParentState().setScheduleView(null);
                ScheduleView.this.dispose();
            }

        });

//        WorkWeek currentWeek = new WorkWeek(LocalDate.now(), scheduleViewModel.getState().getShifts());

        // create and add week pages for 4 weeks (including current week)
//        for (int i = 0; i < 4; i++) {
////            LocalDate weekStart = currentWeekDate.plusWeeks(i);
//            WorkWeek week = new WorkWeek(currentWeek.getStartOfWeek().plusWeeks(i),
//                    scheduleViewModel.getState().getShifts());
////            WorkWeek week = new WorkWeek(weekStart, scheduleViewModel.getState().getShifts());
//            ScheduleWeekView scheduleWeekView = new ScheduleWeekView(scheduleViewModel, week);
//            weekNames.add(scheduleWeekView.getWeek().toString());
//            cardContainer.add(scheduleWeekView, weekNames.get(i));
//        }

        for (int i = 0; i < scheduleViewModel.getState().getWeeks().size(); i++) {
            WorkWeek week = scheduleViewModel.getState().getWeeks().get(i);
            ScheduleWeekView scheduleWeekView = new ScheduleWeekView(scheduleViewModel, week);
            weekNames.add(week.toString());
            cardContainer.add(scheduleWeekView, weekNames.get(i));
        }

//        scheduleController = scheduleViewModel.getState().getScheduleController();

//        final ScheduleState currentState = scheduleViewModel.getState();
//        scheduleController.showStartWeek(
//                currentState.getWeekContainer(),
//                currentState.getWeek(),
//                currentState.getShifts());

        scheduleViewModel.getState().setWeekContainer(cardContainer);
//        scheduleViewModel.getState().setWeek(scheduleViewModel.getState().getWeeks().get(0));



        previousButton = new JButton("<");
        nextButton = new JButton(">");

        previousButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // calculate the index of the previous week
                        weekIndex = (weekIndex - 1 + weekNames.size()) % weekNames.size();
                        // grab the week name for the respective index; similar to state name
//                        String weekName = weekNames.get(weekIndex);

                        // pass in cardContainer so we can control it in other files,
                        // weekName is the state name we are changing to.
//                        scheduleController.showPreviousWeek(cardContainer, weekName);
                        final ScheduleState currentState = scheduleViewModel.getState();

                        scheduleController.showPreviousWeek(
                                currentState.getWeekContainer(),
                                currentState.getWeek(),
                                currentState.getShifts());

                        updateButton();
                    }
                }
        );

        nextButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // see comments for previousButton action listener
                        weekIndex = (weekIndex + 1) % weekNames.size();
//                        String weekName = weekNames.get(weekIndex);

                        final ScheduleState currentState = scheduleViewModel.getState();

//                        WorkWeek nextWeek = new WorkWeek(currentState.getWeek()
//                                .getStartOfWeek().plusWeeks(1),
//                                scheduleViewModel.getState().getShifts());
                        scheduleController.showNextWeek(
                                currentState.getWeekContainer(),
                                currentState.getWeek(),
                                currentState.getShifts());

                        updateButton();
                    }
                }
        );
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5, 5, 5, 5);

        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.WEST;
        c.weightx = 0;
        headerPanel.add(previousButton, c);

        if (!weekNames.isEmpty()) {
            weekLabel = new JLabel(weekNames.get(weekIndex));
            weekLabel.setFont(weekLabel.getFont().deriveFont(Font.BOLD, 24));
            c.gridx++;
            c.fill = GridBagConstraints.NONE;
            c.anchor = GridBagConstraints.CENTER;
            c.weightx = 1.0;
            headerPanel.add(weekLabel, c);


            c.gridx++;
            c.anchor = GridBagConstraints.EAST;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.weightx = 0.0;
            headerPanel.add(nextButton, c);

            updateButton();


            // Download Button
            JButton downloadButton = new JButton(ScheduleViewModel.DOWNLOAD_LABEL);
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new GridBagLayout());
            GridBagConstraints c2 = new GridBagConstraints();
//        c2.fill = GridBagConstraints.NONE;
            c2.weightx = 1.0;
            c2.insets = new Insets(5, 5, 5, 5);
            c2.anchor = GridBagConstraints.EAST;
            buttonPanel.add(downloadButton, c2);

            // TODO implement download button
            downloadButton.addActionListener(e -> {
            });

            this.add(headerPanel, BorderLayout.NORTH);
            this.add(cardContainer, BorderLayout.CENTER);
            this.add(buttonPanel, BorderLayout.SOUTH);

            cardLayout.show(cardContainer, weekNames.get(0));

        }

        // FOR TESTING
//        this.setVisible(true);
    }

    private void updateButton() {
        previousButton.setEnabled(weekIndex > 0);
        nextButton.setEnabled(weekIndex < weekNames.size() - 1);
        weekLabel.setText(weekNames.get(weekIndex));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final ScheduleState state = (ScheduleState) evt.getNewValue();
            scheduleController.showStartWeek(
                    state.getWeekContainer(),
                    state.getWeek(),
                    state.getShifts());
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void getScheduleController(ScheduleController scheduleController) {
        this.scheduleController = scheduleController;
    }

    public void setScheduleController(ScheduleController scheduleController) {
        this.scheduleController = scheduleController;
    }
}
