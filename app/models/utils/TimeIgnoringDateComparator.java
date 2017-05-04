package models.utils;

import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Marco Steiner on 18.04.2017.
 * This comparator for {@link Date} compares only the date and not the time.
 * Using Calendar like explained in JavaDoc and in this explanation:
 * http://stackoverflow.com/a/33940612/2632991
 */
public class TimeIgnoringDateComparator implements Comparator<Date> {
    public int compare(final Date d1, final Date d2) {
        final Calendar calendar1 = new GregorianCalendar();
        final Calendar calendar2 = new GregorianCalendar();
        calendar1.setTime(d1);
        calendar2.setTime(d2);

        final int yearDiff = calendar1.get(Calendar.YEAR) - calendar2.get(Calendar.YEAR);
        final int monthDiff = calendar1.get(Calendar.MONTH) - calendar2.get(Calendar.MONTH);
        final int dayDiff = calendar1.get(Calendar.DAY_OF_MONTH) - calendar2.get(Calendar.DAY_OF_MONTH);

        // Weigh in the years, months and days.
        // A year-difference is always more important than a month-difference, etc.
        return yearDiff * 10000 + monthDiff * 100 + dayDiff;
    }
}

