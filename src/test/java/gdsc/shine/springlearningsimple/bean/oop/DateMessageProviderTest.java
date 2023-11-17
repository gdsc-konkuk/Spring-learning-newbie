package gdsc.shine.springlearningsimple.bean.oop;

import static org.hamcrest.CoreMatchers.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateMessageProviderTest {
    @Test
    public void 오전() throws Exception {
        MyCalendar myCalendar = new MyCalendar();
        myCalendar.setCurrentHour(11);

        assertEquals("오전", myCalendar.getDateMessage());
    }

    @Test
    public void 오후() throws Exception {
        MyCalendar myCalendar = new MyCalendar();
        myCalendar.setCurrentHour(12);

        assertEquals("오후", myCalendar.getDateMessage());
    }
}