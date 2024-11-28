package view;

import data_access.PublicHolidayAPIAccessObject;
import entity.Employee;
import entity.Manager;
import entity.Shift;
import entity.WorkWeek;
import interface_adapter.logged_in.EmployeeController;
import interface_adapter.schedule.ScheduleViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The View where the users can see their schedule.
 */
public class ScheduleWeekView extends JPanel implements ActionListener, PropertyChangeListener {
//    private final String viewName = "schedule";
    private final ScheduleViewModel scheduleViewModel;
    private final PublicHolidayAPIAccessObject publicHolidayAPIAccessObject = new PublicHolidayAPIAccessObject("CA");

    private List<Shift> shiftList = new ArrayList<>();
    private final WorkWeek currentWeek;

    private EmployeeController employeeController;

    public ScheduleWeekView(ScheduleViewModel scheduleViewModel, LocalDate date) {
        this.scheduleViewModel = scheduleViewModel;
        this.scheduleViewModel.addPropertyChangeListener(this);

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        // OLD SOLO WINDOW IMPLEMENTATION-- KEEPING FOR REFERENCE
//        this.addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent e) {
//                scheduleViewModel.getState().getParentState().setScheduleView(null);
//                ScheduleWeekView.this.dispose();
//            }
//
//        });

//        LocalDate today = LocalDate.now();
//        this.currentWeek = new Workweek(today, scheduleViewModel.getState().getShifts());

        // Title
//        final JLabel title = new JLabel(currentWeek.toString());
//        title.setFont(new Font("Calibri", Font.BOLD, 20));
//
//        gbc.gridy = 0; // first row
//        gbc.weightx = 1.0; // allow horizontal expansion
//        gbc.weighty = 0; // don't allow vertical expansion
//        gbc.gridwidth = GridBagConstraints.REMAINDER; // span full grid width
//        gbc.anchor = GridBagConstraints.CENTER; // center title
//        gbc.fill = GridBagConstraints.NONE; // don't stretch
//        gbc.insets = new Insets(1,1,1,1);
//        this.add(title, gbc);

        currentWeek = new WorkWeek(date, scheduleViewModel.getState().getShifts());

        // Schedule Panel
        JPanel schedulePanel = new JPanel();
        schedulePanel.setLayout(new GridBagLayout());
        schedulePanel.setBackground(ScheduleViewModel.GRID_COLOR);
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.insets = new Insets(1,1,1,1);

        // Schedule Headers
        String[] days = {"", "SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
        String[] times = {
                "09:00", //               _______
                "10:00", //              | Shift |_______
                "11:00", //              |_______| Shift |
                "12:00", //                      |       |
                "13:00", //                      |_______|
                "14:00",
                "15:00",
                "16:00",
                "17:00"
        };
        // add column headers (days of the week)
        for (int i = 0; i < days.length; i++) {
            JLabel day = new JLabel(days[i], JLabel.CENTER);
            day.setFont(day.getFont().deriveFont(Font.BOLD));
            day.setBackground(ScheduleViewModel.HEADER_COLOR);
            day.setOpaque(true);
            c.gridx = i;
            c.gridy = 0;
            c.weightx = 1.0;
            c.weighty = 0;
            schedulePanel.add(day, c);
        }
        // add row headers (times of the day)
        for (int i = 0; i < times.length; i++) {
            JLabel time = new JLabel(times[i], JLabel.CENTER);
            time.setVerticalAlignment(SwingConstants.TOP);
            time.setBackground(ScheduleViewModel.HEADER_COLOR);
            time.setFont(time.getFont().deriveFont(Font.BOLD));
            time.setOpaque(true);
            c.gridheight = 2;
            c.gridx = 0;
            c.gridy = i * 2 + 1;
            c.weightx = 0;
            c.weighty = 1.0;
            schedulePanel.add(time, c);
        }

        // get all shifts for this week
        this.shiftList = currentWeek.getShifts();

        // CUSTOM SHIFTS FOR DISPLAY TESTS
//        this.shiftList.add(new Shift(LocalDate.of(2024,11,27), LocalTime.of(9,30), LocalTime.of(10,0), new Employee("e", "e")));
//        this.shiftList.add(new Shift(LocalDate.of(2024,11,27), LocalTime.of(11,0), LocalTime.of(16,30), new Employee("f", "f")));
//        this.shiftList.add(new Shift(LocalDate.of(2024,11,27), LocalTime.of(13,30), LocalTime.of(16,30), new Employee("g", "g")));

//        Collections.sort(shiftList, new Comparator<Shift>() {
//            @Override
//            public int compare(Shift o1, Shift o2) {
//                int value1 = o1.getDay().compareTo(o2.getDay());
//                if (value1 == 0) {
//                    return o1.getStartTime().compareTo(o2.getStartTime());
//                }
//                return value1;
//            }
//        });

        // Manager's Schedule View
        if (scheduleViewModel.getState().getParentState().getUser() instanceof Manager) {
            for (LocalDate day : currentWeek.getDaysOfWeek()) {
                GridBagConstraints constraints = new GridBagConstraints();
                constraints.fill = GridBagConstraints.BOTH;
                constraints.weightx = 1.0;
                constraints.weighty = 1.0;
                constraints.insets = new Insets(1, 1, 1, 1);



                // FOR DISPLAY TESTS-- COMMENT OUT OTHER IF STATEMENT HEADER + holidayName BELOW
//                if (day.getDayOfWeek().equals(currentWeek.getDaysOfWeek().get(0).getDayOfWeek())) {
//                    String holidayName = "New Year's Day";

                // TODO: commented out for now because this throws an error for too many requests
                //  per second... don't know what to do
//                if (publicHolidayAPIAccessObject.holidayOn(day)) {
//                    String holidayName = publicHolidayAPIAccessObject.getHolidayName(day);
//
//                    constraints.gridx = day.getDayOfWeek().getValue() % 7 + 1;
//                    constraints.gridy = 1;
//
//                    constraints.gridwidth = 1;
//                    constraints.gridheight = 18;
//
//                    JPanel holidayBlock = new JPanel(new GridBagLayout());
//                    holidayBlock.setBackground(ScheduleViewModel.GRID_COLOR);
//
//                    JLabel holidayLabel = new JLabel(holidayName, JLabel.CENTER);
//                    holidayLabel.setForeground(Color.BLACK);
//
//                    holidayBlock.add(holidayLabel);
//                    schedulePanel.add(holidayBlock, constraints);
//                    continue;
//                }

                // get all shifts for day
                List<Shift> dayShifts = new ArrayList<>();
                for (Shift shift : shiftList) {
                    if (shift.getDay().isEqual(day)) {
                        dayShifts.add(shift);
                    }
                }
                List<List<Shift>> shiftBlocks = WorkWeek.getShiftBlocks(dayShifts);

                // create a visual shift box that also covers any overlapping shifts
                for (List<Shift> block : shiftBlocks) {
                    if (block.size() > 0) {
                        Shift firstShift = block.get(0);
                        Shift lastShift = block.get(block.size() - 1);

                        constraints.gridx = firstShift.getDay().getDayOfWeek().getValue() % 7 + 1;
                        constraints.gridy = (int) ((firstShift.getStartTime().getHour() +
                                (firstShift.getStartTime().getMinute() / 60.0)) * 2
                                - ScheduleViewModel.TIME_TO_GRIDY_OFFSET);

                        constraints.gridwidth = 1;
                        constraints.gridheight = (int) ((Duration.between(firstShift.getStartTime(), lastShift.getEndTime()).toMinutes() / 60.0) * 2);

                        JPanel shiftBlock = new JPanel();
                        shiftBlock.setBackground(ScheduleViewModel.SHIFT_COLOR);

                        String description = "";
                        for (Shift shift : block) {
                            description += "<html>" + shift.getStartTime() + "-" + shift.getEndTime()
                                    + ": " + shift.getEmployee().getUserID() + "<br>";
                        }
                        if (!description.isEmpty()) {
                            description += "<html>";
                        }

                        JLabel shiftLabel = new JLabel(description, JLabel.CENTER);
                        shiftLabel.setForeground(Color.WHITE);
                        shiftBlock.add(shiftLabel);
                        schedulePanel.add(shiftBlock, constraints);
                    }
                }
            }
        }

        // Employee's Schedule View
        if (scheduleViewModel.getState().getParentState().getUser() instanceof Employee) {
            for (Shift shift : shiftList) {

                GridBagConstraints shiftConstraints = new GridBagConstraints();
                shiftConstraints.fill = GridBagConstraints.BOTH;
                shiftConstraints.weightx = 1.0;
                shiftConstraints.weighty = 1.0;
                shiftConstraints.insets = new Insets(1, 1, 1, 1);

                // get time as an hour float (ex. 09:30 = 9.5 hours), convert to half hour
                // (ex. 09:30 = 19 half hours), and subtract offset to get start index.
                shiftConstraints.gridy = (int) ((shift.getStartTime().getHour() +
                        (shift.getStartTime().getMinute() / 60.0)) * 2
                        - ScheduleViewModel.TIME_TO_GRIDY_OFFSET);

                shiftConstraints.gridx = shift.getDay().getDayOfWeek().getValue() % 7 + 1;
                shiftConstraints.gridwidth = 1;
                shiftConstraints.gridheight = (int) (shift.length() * 2);

                JPanel shiftBlock = new JPanel();
                shiftBlock.setBackground(ScheduleViewModel.SHIFT_COLOR);

                JLabel shiftLabel = new JLabel("Shift", JLabel.CENTER);
                shiftLabel.setForeground(Color.WHITE);
                shiftBlock.add(shiftLabel);
                schedulePanel.add(shiftBlock, shiftConstraints);
            }
        }

        // Empty cells for shift grid
        for (int row = 1; row <= times.length * 2; row++) {
            for (int col = 1; col <= days.length - 1; col++) {
                JPanel cell = new JPanel();

                // differentiate the cell colours based on hour / half hour for clear visuals
                if (row % 2 == 1) { cell.setBackground(ScheduleViewModel.HOUR_COLOR); }
                else { cell.setBackground(ScheduleViewModel.HALF_HOUR_COLOR); }

                cell.setOpaque(true);
                c.insets = new Insets(1,1,1,1);
                c.gridheight = 1;
                c.gridx = col;
                c.gridy = row;
                schedulePanel.add(cell, c);
            }
        }

        // add schedule panel
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        this.add(schedulePanel, gbc);

//        // Download Button
//        JButton downloadButton = new JButton(ScheduleViewModel.DOWNLOAD_LABEL);
//        downloadButton.addActionListener(e -> {});
//        gbc.gridy++;
//        gbc.weightx = 1.0;
//        gbc.weighty = 0;
//        gbc.anchor = GridBagConstraints.EAST;
//        gbc.fill = GridBagConstraints.NONE;
//        this.add(downloadButton, gbc);

        // FOR DISPLAY TEST
//        this.setVisible(true);
    }

    public WorkWeek getWeek() {
        return currentWeek;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
    }

//    public String getViewName() {
//        return viewName;
//    }
}