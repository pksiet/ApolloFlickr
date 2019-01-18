package in.apollo;

import org.junit.Assert;
import org.junit.Test;

import in.apollo.utils.Utils;

public class UtilsTest {

    @Test
    public void testValidInputLongFromDate() {
        String date = "2004-06-05T13:38:03-08:00";
        long time = Utils.getLongFromDate(date);
        Assert.assertTrue(time > 0);
    }

    @Test
    public void testInvalidInputLongFromDate() {
        String date = "2004-06-05T13";
        long time = Utils.getLongFromDate(date);
        Assert.assertTrue(time == 0);
    }

    @Test
    public void testNullInputLongFromDate() {
        String date = null;
        long time = Utils.getLongFromDate(date);
        Assert.assertTrue(time == 0);

    }
}
