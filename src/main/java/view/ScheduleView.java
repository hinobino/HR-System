package view;

import entity.WorkWeek;
import interface_adapter.schedule.ScheduleViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
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

    public ScheduleView(ScheduleViewModel scheduleViewModel) {
        this.scheduleViewModel = scheduleViewModel;
        this.scheduleViewModel.addPropertyChangeListener(this);

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

        LocalDate currentWeekDate = LocalDate.now();

        for (int i = 0; i < 4; i++) {
            LocalDate weekStart = currentWeekDate.plusWeeks(i);
//            WorkWeek week = new WorkWeek(weekStart, scheduleViewModel.getState().getShifts());
            ScheduleWeekView scheduleWeekView = new ScheduleWeekView(scheduleViewModel, weekStart);
            weekNames.add(scheduleWeekView.getWeek().toString());
            cardContainer.add(scheduleWeekView, weekNames.get(i));
        }



        previousButton = new JButton("<");
        nextButton = new JButton(">");

        previousButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        showPreviousWeek();
                    }
                }
        );

        nextButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        showNextWeek();
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
        downloadButton.addActionListener(e -> {});
//        gbc.gridy++;
//        gbc.weightx = 1.0;
//        gbc.weighty = 0;
//        gbc.anchor = GridBagConstraints.EAST;
//        gbc.fill = GridBagConstraints.NONE;
//        buttonPanel.add(downloadButton);

        this.add(headerPanel, BorderLayout.NORTH);
        this.add(cardContainer, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);

        cardLayout.show(cardContainer, weekNames.get(0));
//        this.setVisible(true);
    }


    private void showPreviousWeek() {
        weekIndex = (weekIndex - 1 + weekNames.size()) % weekNames.size();
//        this.revalidate();
//        this.repaint();
        cardLayout.show(cardContainer, weekNames.get(weekIndex));
        updateButton();
        weekLabel.setText(weekNames.get(weekIndex));
    }

    private void showNextWeek() {
        weekIndex = (weekIndex + 1) % weekNames.size();
//        this.revalidate();
//        this.repaint();
        cardLayout.show(cardContainer, weekNames.get(weekIndex));
        updateButton();
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

    }

    public String getViewName() {
        return viewName;
    }
}
