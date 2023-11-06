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
}
