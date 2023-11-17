package gdsc.shine.springlearningsimple;

import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class DateMessageProviderTest {

    @Test
    public void 오전() throws Exception {
        MyCalendar myCalendar = new MyCalendar(Calendar.getInstance());
        Hour hour = myCalendar.getHour(10);
        assertThat(hour.getMessage(), is("오전"));
    }

    @Test
    public void 오후() throws Exception {
        MyCalendar myCalendar = new MyCalendar(Calendar.getInstance());
        Hour hour = myCalendar.getHour(13);
        assertThat(hour.getMessage(), is("오후"));
    }
}