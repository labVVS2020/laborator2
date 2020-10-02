import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class VVSDateTests {
    private static VVSDate vvsDate;
    // prea multe variabile statice ar insemna un memory leak destul de mare, care chiar daca in aplicatii mici e ok nu e un obicei bun
    @BeforeClass
    public static void setUp(){
        vvsDate = new VVSDate(2,3,1997);
    //pastram doar cazul favorabil ca si static(de dragul demonstratiei) chit ca putem renunta si la el
    }

    @Test
    public void createInstanceOfVVSDateObject(){

        Assert.assertNotNull (vvsDate);
    }

    @Test
    public void verifyGetMonth(){
        Assert.assertEquals (vvsDate.getMonth (),3);
    }

    @Test
    public void verifyMonthTrue(){
        Assert.assertTrue (vvsDate.verifyMonth());
    }


    @Test
    public void verifyNegativeMonthFalse(){
        Assert.assertFalse (new VVSDate (1,-2,1997).verifyMonth());
    }

    @Test
    public void verifyPositiveMonthFalse(){
        Assert.assertFalse (new VVSDate (1,15,1997).verifyMonth());
    }

    @Test
    public void verifyZeroMonthFalse(){
        Assert.assertFalse (new VVSDate (1,0,1997).verifyMonth());
    }

    @Test
    public void verifyFebruaryDaysForLeapYear(){
        Assert.assertTrue (new VVSDate (29,2,2000).verifyMonth());
    }

    @Test
    public void verifyFebruaryDaysForNonLeapYear(){
        Assert.assertTrue (new VVSDate (28,2,2100).verifyMonth());
    }

    @Test
    public void verifyGetDay(){
        Assert.assertEquals (vvsDate.getDay(),2);
    }

    @Test
    public void verifyDayTrue(){
        Assert.assertTrue (vvsDate.verifyDay());
    }

    @Test
    public void verifyNegativeDayFalse(){
        Assert.assertFalse (new VVSDate (-2,1,1997).verifyDay());
    }

    @Test
    public void verifyPositiveDayFalse(){
        Assert.assertFalse (new VVSDate (32,1,1997).verifyDay());
    }

    @Test
    public void verifyCorrectAttributionOfADayFalse(){
        Assert.assertFalse (new VVSDate (31,2,1997).verifyDay());
    }

    @Test
    public void verifyZeroDayFalse(){
        Assert.assertFalse (new VVSDate (0,1,1997).verifyDay());
    }

    @Test
    public void verifyLeapYearTrue(){
        Assert.assertTrue (new VVSDate (29,2,0).isLeap());
    }

    @Test
    public void verifyLeapYearFalse(){
        Assert.assertFalse (new VVSDate (2,3,2100).isLeap());
    }

    @Test
    public void verifyDifferenceOfStartGen2017DateAndCurrentDate(){
        Assert.assertEquals (new VVSDate (20,9,2017).getDays(new VVSDate (29,9,2020)),-1105);
    }

    @Test
    public void verifyDifferenceOfCurrentDateAndStartGen2017Date(){
        Assert.assertEquals (new VVSDate (29,9,2020).getDays(new VVSDate (20,9,2017)),1105);
    }

    @Test
    public void verifyDifferenceOfSameDateGivesZero(){
        Assert.assertEquals (vvsDate.getDays(vvsDate),0);
    }
}
