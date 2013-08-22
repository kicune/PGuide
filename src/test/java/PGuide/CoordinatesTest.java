package PGuide;


import geo.gps.Coordinates;
import org.junit.Test;
import org.junit.Assert;


/**
 * Created with IntelliJ IDEA.
 * User: lisak
 * Date: 13.08.13
 * Time: 12:11
 */
public class CoordinatesTest {

    @Test
    public void testConversion1() {
        Coordinates coord = new Coordinates();
        coord.parse("N50 01.734 E014 29.280");
        Assert.assertEquals(coord.getLatitude(), 50.0289, 0.001);
        Assert.assertEquals(coord.getLongitude(), 14.488, 0.001);
    }

    @Test
    public void testConversion2() {
        Coordinates coord = new Coordinates();
        coord.parse("N 50 01.734 E 014 29.280");
        Assert.assertEquals(coord.getLatitude(), 50.0289, 0.001);
        Assert.assertEquals(coord.getLongitude(), 14.488, 0.001);
    }

    @Test
    public void testConversion3() {
        Coordinates coord = new Coordinates();
        coord.parse("N 50 1 44.040 E 14 29 16.800");
        Assert.assertEquals(coord.getLatitude(), 50.0289, 0.001);
        Assert.assertEquals(coord.getLongitude(), 14.488, 0.001);
    }

    @Test
    public void testConversion4() {
        Coordinates coord = new Coordinates();
        coord.parse("50.0289 14.488");
        Assert.assertEquals(coord.getLatitude(), 50.0289, 0.001);
        Assert.assertEquals(coord.getLongitude(), 14.488, 0.001);
    }
}
