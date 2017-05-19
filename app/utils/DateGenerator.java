package utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Fabian on 19.05.17.
 * Utiliies for date generation.
 */
public class DateGenerator {
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Generates a date (now) with an offset.
     * @param dayOffset offset of the day starting from now (can be positive or negative)
     * @return The generated date.
     */
    public static Date generateDate(final int dayOffset){
        return generateDate(dayOffset, 0,0);
    }

    /**
     * Generates a date (now) with an offset.
     * @param dayOffset offset of the day starting from now (can be positive or negative)
     * @param hourOfDay hour of day from 0 - 23.
     * @return The generated date.
     */
    public static Date generateDate(final int dayOffset, final int hourOfDay) {
        return generateDate(dayOffset, hourOfDay,0);
    }

    /**
     * Generates a date (now) with an offset.
     * @param dayOffset offset of the day starting from now (can be positive or negative)
     * @param hourOfDay hour of day from 0 - 23.
     * @param minute minute from 0 - 59
     * @return The generated date.
     */
    public static Date generateDate(final int dayOffset, final int hourOfDay, final int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);
        calendar.add(Calendar.DATE, dayOffset);
        return calendar.getTime();
    }
}
