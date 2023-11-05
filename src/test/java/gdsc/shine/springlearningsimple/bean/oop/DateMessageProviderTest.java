package gdsc.shine.springlearningsimple.bean.oop;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class DateMessageProviderTest {
    @Test
    public void 오전() throws Exception {
        DateMessageProvider provider = new DateMessageProvider();
        assertThat(provider.getDateMessage(), is("오전"));
    }

    @Test
    public void 오후() throws Exception {
        DateMessageProvider provider = new DateMessageProvider();
        assertThat(provider.getDateMessage(), is("오후"));
    }
}