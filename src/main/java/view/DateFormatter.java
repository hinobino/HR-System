package view;

import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.YearMonth;

public class DateFormatter {

    MaskFormatter dateFormat;

    public DateFormatter() {
        try {
            this.dateFormat = new MaskFormatter("##/##/####");
            this.dateFormat.setPlaceholderCharacter('_');
            this.dateFormat.setAllowsInvalid(false);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Check if a given date string is valid.
     * @param dateString must be in the form "dd/mm/yyyy"
     * @return true if the given dateString translates to a valid date
     */
    public static boolean verifyDate(String dateString) {
        final String[] parts = dateString.split("/");
        final int day = Integer.parseInt(parts[0]);
        final int month = Integer.parseInt(parts[1]);
        final int year = Integer.parseInt(parts[2]);

        if (month <= 0 || month >= 13) {
            return false;
        }

        YearMonth yearMonth = YearMonth.of(year, month);
        return day > 0 && day <= yearMonth.lengthOfMonth();
    }

    public static LocalDate makeDate(String dateString) {
        final String[] parts = dateString.split("/");
        final int day = Integer.parseInt(parts[0]);
        final int month = Integer.parseInt(parts[1]);
        final int year = Integer.parseInt(parts[2]);

        return LocalDate.of(year, month, day);
    }

}
