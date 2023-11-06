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

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public String getDateMessage() {
        if (calendar.get(Calendar.HOUR_OF_DAY) < 12) {
            return "오전";
        }

        return "오후";
    }

    public void setCurrentHour(int hour) {
        calendar.set(calendar.get(Calendar.HOUR_OF_DAY), hour);
    }
}
