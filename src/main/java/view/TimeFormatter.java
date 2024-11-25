package view;

import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.time.LocalTime;

public class TimeFormatter {

    static final String[] VALID_TIMES = {
            "09:00", "09:30",
            "10:00", "10:30",
            "11:00", "11:30",
            "12:00", "12:30",
            "13:00", "13:30",
            "14:00", "14:30",
            "15:00", "15:30",
            "16:00", "16:30",
            "17:00"
    };

    /**
     * Check if a given time string is valid.
     * @param timeString must be in the form "24H:MM" where 00:00 represents midnight, not 24:00.
     * @return true if the given timeString is valid.
     */
    public static boolean verifyTime(String timeString) {
        final String[] parts = timeString.split(":");
        final int hour = Integer.parseInt(parts[0]);
        final int minute = Integer.parseInt(parts[1]);

        return (minute >= 0 && minute <= 59) && (hour >= 0 && hour <= 23);
    }

    public static LocalTime makeTime(String timeString) {
        final String[] parts = timeString.split(":");
        final int hour = Integer.parseInt(parts[0]);
        final int minute = Integer.parseInt(parts[1]);

        return LocalTime.of(hour, minute);
    }

}
