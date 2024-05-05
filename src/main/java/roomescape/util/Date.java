package roomescape.util;

import java.time.LocalDate;
import java.time.LocalTime;

public class Date {

    public static final LocalDate TODAY = LocalDate.now();
    public static final LocalDate YESTERDAY = LocalDate.now().minusDays(1);
    public static final LocalDate A_WEEK_AGO = LocalDate.now().minusDays(7);
    public static final LocalTime CURRENT_TIME = LocalTime.now();

    public static boolean isPastDateTime(LocalDate date, LocalTime time) {
        boolean isPastDate = date.isBefore(TODAY);
        boolean isPastTime = date.isEqual(TODAY) && time.isBefore(CURRENT_TIME);
        return isPastDate || isPastTime;
    }
}
