package entity;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

public class WorkWeek {

    private List<Shift> shifts;
    private final LocalDate startOfWeek;
    private final LocalDate endOfWeek;
    private final List<LocalDate> daysOfWeek;

    public WorkWeek(LocalDate ofDate, List<Shift> shifts) {
        this.startOfWeek = ofDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
        this.endOfWeek = ofDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY));

        daysOfWeek = new ArrayList<>();
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

            Collections.sort(this.shifts, new Comparator<Shift>() {
                @Override
                public int compare(Shift o1, Shift o2) {
                    int value1 = o1.getDay().compareTo(o2.getDay());
                    if (value1 == 0) {
                        int value2 = o1.getStartTime().compareTo(o2.getStartTime());
                        if (value2 == 0) {
                            return o1.getEndTime().compareTo(o2.getEndTime());
                        }
                        return value2;
                    }
                    return value1;
                }
            });
        }
    }

    public List<Shift> getShifts() {
        return shifts;
    }

    public List<LocalDate> getDaysOfWeek() { return daysOfWeek; }

    @Override
    public String toString() {
        return startOfWeek.getMonth().getDisplayName(TextStyle.FULL, Locale.CANADA) + " "
                + startOfWeek.getDayOfMonth() + ", "
                + startOfWeek.getYear() + " to "
                + endOfWeek.getMonth().getDisplayName(TextStyle.FULL, Locale.CANADA) + " "
                + endOfWeek.getDayOfMonth() + ", "
                + endOfWeek.getYear();
    }

    public static List<List<Shift>> getShiftBlocks(List<Shift> shiftList) {
        // ASSUME SHIFTS ARE ON THE SAME DAY AND SORTED BY START TIME
        if (shiftList.isEmpty()) { return List.of(shiftList); }

        List<List<Shift>> shiftBlocks = new ArrayList<>();

        for (Shift currentShift : shiftList) {
            boolean hasOverlap = false;

            for(List<Shift> shiftBlock : shiftBlocks) {
                Shift lastShift = shiftBlock.get(shiftBlock.size() - 1);

                if (isOverlapping(currentShift, lastShift)) {
                    shiftBlock.add(currentShift);
                    hasOverlap = true;
                    break;
                }
            }
            if (!hasOverlap) {
                List<Shift> newBlock = new ArrayList<>();
                newBlock.add(currentShift);
                shiftBlocks.add(newBlock);
            }
        }
        return shiftBlocks;
    }

    private static boolean isOverlapping(Shift shift1, Shift shift2) {
        // assuming the shifts are sorted by startTime
        if (shift1.getStartTime().isBefore(shift2.getEndTime())
                && shift2.getStartTime().isBefore(shift1.getEndTime())) {
            return true;
        }
        else if (shift1.getEndTime().equals(shift2.getStartTime())) {
            return false;
        }
        else if (shift1.getStartTime().equals(shift2.getEndTime())) {
            return false;
        }
        return false;
    }
}
