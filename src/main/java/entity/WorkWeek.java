package entity;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class WorkWeek {

    private final List<Shift> shifts;
    private final LocalDate startOfWeek;
    private final LocalDate endOfWeek;

    public WorkWeek(LocalDate ofDate, List<Shift> shifts) {
        this.startOfWeek = ofDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
        this.endOfWeek = ofDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY));

        List<LocalDate> daysOfWeek = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            daysOfWeek.add(startOfWeek.plusDays(i));
        }

        this.shifts = new ArrayList<>();
        if (shifts != null) {
            for (Shift shift : shifts) {
                if (daysOfWeek.contains(shift.getDay())) {
                    this.shifts.add(shift);
                }
            }
        }
    }

    public List<Shift> getShifts() {
        return shifts;
    }
    @Override
    public String toString() {
        return startOfWeek.getMonth().getDisplayName(TextStyle.FULL, Locale.CANADA) + " "
                + startOfWeek.getDayOfMonth() + ", "
                + startOfWeek.getYear() + " to "
                + endOfWeek.getMonth().getDisplayName(TextStyle.FULL, Locale.CANADA) + " "
                + endOfWeek.getDayOfMonth() + ", "
                + endOfWeek.getYear();
    }
}
