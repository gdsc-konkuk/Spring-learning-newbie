package gdsc.shine.springlearningsimple;

import java.util.Calendar;

public class MyCalendar {
    private Calendar calendar;

    public MyCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public Hour getHour(int hour) {
        return new Hour(calendar.get(hour));
    }
}