package models.utils;

import java.util.Comparator;
import java.util.Date;

/**
 * Created by Marco Steiner on 18.04.2017.
 * This comparator for {@link Date} compares only the date and not the time.
 */
public class TimeIgnoringDateComparator implements Comparator<Date> {
    public int compare(Date d1, Date d2) {
        if (d1.getYear() != d2.getYear())
            return d1.getYear() - d2.getYear();
        if (d1.getMonth() != d2.getMonth())
            return d1.getMonth() - d2.getMonth();
        return d1.getDate() - d2.getDate();
    }
}

