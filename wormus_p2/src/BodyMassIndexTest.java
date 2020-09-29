import org.junit.Test;
import static org.junit.Assert.*;

public class BodyMassIndexTest {
    @Test
    public void testBMIunderweight() {
        BodyMassIndex underweightTest = new BodyMassIndex(72, 100);
        assertEquals("underweight", underweightTest.category);
    }
    @Test
    public void testBMInormalweight() {
        BodyMassIndex normalTest = new BodyMassIndex(72, 160);
        assertEquals("normal weight", normalTest.category);
    }
    @Test
    public void testBMIoverweight() {
        BodyMassIndex overweightTest = new BodyMassIndex(72, 200);
        assertEquals("overweight", overweightTest.category);
    }
    @Test
    public void testBMIobese() {
        BodyMassIndex obeseTest = new BodyMassIndex(72, 250);
        assertEquals("obese", obeseTest.category);
    }
    @Test
    public void testGetBMI() {
        BodyMassIndex getBMITest = new BodyMassIndex(72, 150);
        assertEquals(20.341435185185187, getBMITest.BMI, .01);
    }
}

