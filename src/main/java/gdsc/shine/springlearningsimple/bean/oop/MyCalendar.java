package gdsc.shine.springlearningsimple.bean.oop;

import java.util.Calendar;

public class MyCalendar {
    private Calendar calendar;

    public MyCalendar() {
        this.calendar = Calendar.getInstance();
    }

    public MyCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    private void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    private Calendar getCalendar() {
        return calendar;
    }

    private String getDateMessage() {
        if (calendar.get(Calendar.HOUR_OF_DAY) < 12) {
            return "오전";
        }

        return "오후";
    }
}
