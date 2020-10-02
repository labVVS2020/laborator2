import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Calendar;

public class PromotieTests {

    private static Promotie promotie;

    @BeforeClass
    public static void setUp(){
        Calendar startDate = Calendar.getInstance ();
        Calendar endDate = Calendar.getInstance ();
        startDate.set (2001, startDate.NOVEMBER,30);
        endDate.set(2001,endDate.DECEMBER,31);
        promotie = new Promotie(startDate,endDate,"2,3", "10:00","12:00");

    }

    @Test
    public void createInstanceOfCalendarObject(){
        Calendar startDate = Calendar.getInstance ();
        Assert.assertNotNull (startDate);
    }

    @Test
    public void verifyCalendarYearCorrectAttribution(){
        Calendar startDate = Calendar.getInstance ();
        startDate.set (2001, startDate.NOVEMBER,30);
        Assert.assertEquals (startDate.get(1),2001);
    }

    @Test
    public void verifyCalendarYearForMoreThan12Months(){
        Calendar startDate = Calendar.getInstance ();
        startDate.set (2001, 12,30);
        Assert.assertEquals (startDate.get(1),2002);
    }

    @Test
    public void ForMoreZeroMonths(){
        Calendar startDate = Calendar.getInstance ();
        startDate.set (2001, 0,30);
        Assert.assertEquals (startDate.get(1),2001);
    }

    @Test
    public void verifyCalendarYearForMoreNegativeMonths(){
        Calendar startDate = Calendar.getInstance ();
        startDate.set (2001, -1,30);
        Assert.assertEquals (startDate.get(1),2000);
    }

    @Test
    public void verifyCalendarMonthCorrectAttribution(){
        Calendar startDate = Calendar.getInstance ();
        startDate.set (2001, startDate.NOVEMBER,30);
        Assert.assertEquals (startDate.get(2),startDate.NOVEMBER);
    }

    @Test
    public void verifyCalendarMonthForNegativeDays(){
        Calendar startDate = Calendar.getInstance ();
        startDate.set (2001, startDate.NOVEMBER,-10);
        Assert.assertEquals (startDate.get(2),startDate.OCTOBER);
    }

    @Test
    public void verifyCalendarMonthForZeroDays(){
        Calendar startDate = Calendar.getInstance ();
        startDate.set (2001, startDate.NOVEMBER,0);
        Assert.assertEquals (startDate.get(2),startDate.OCTOBER);
    }

    @Test
    public void verifyCalendarMonthForWrongPositiveDays(){
        Calendar startDate = Calendar.getInstance ();
        startDate.set (2001, startDate.NOVEMBER,35);
        Assert.assertEquals (startDate.get(2),startDate.DECEMBER);
    }



    @Test
    public void verifyGettingTheCorrectPromotionWeekDays(){
        Assert.assertEquals (promotie.getWeekdays (),"2,3");
    }
    @Test
    public void verifyGettingCorrectPromotionWeekDays(){
        Assert.assertTrue (promotie.verifyWeekDays ());
    }

    @Test
    public void verifyGettingFalsePromotionWeekDays(){
        Assert.assertFalse (new Promotie (null,null,"0","10:00","11:00").verifyWeekDays ());
    }

    @Test
    public void verifyGettingFalsePromotionWithGoodAndBadWeekDays(){
        Assert.assertFalse (new Promotie (null,null,"0,1,3,49","10:00","11:00").verifyWeekDays ());
    }

    @Test
    public void verifyCorrectStartHourAttribution() {
        Assert.assertEquals (new Promotie(null,null,"3","10:00","11:00").getStartHour (),"10:00");
    }

    @Test
    public void verifyGettingCorrectStartHourAndMinutes() {
        Assert.assertTrue (new Promotie(null,null,"3","10:00","11:00").verifyStartHour ());
    }

    @Test
    public void verifyGettingCorrectStartHourAndIncorrectMinutes() {
        Assert.assertFalse (new Promotie(null,null,"3","10:70","11:00").verifyStartHour ());
    }
    @Test
    public void verifyGettingIncorrectStartHourAndCorrectMinutes() {
        Assert.assertFalse (new Promotie(null,null,"3","30:00","11:00").verifyStartHour ());
    }

    @Test
    public void verifyGettingIncorrectStartHourAndIncorrectMinutes() {
        Assert.assertFalse (new Promotie(null,null,"3","30:80","11:00").verifyStartHour ());
    }
    @Test
    public void verifyCorrectEndHourAttribution() {
        Assert.assertEquals (new Promotie(null,null,"3","10:00","11:00").getEndHour (),"11:00");
    }

    @Test
    public void verifyGettingCorrectEndHourAndMinutes() {
        Assert.assertTrue (new Promotie(null,null,"3","10:00","11:00").verifyEndHour ());
    }

    @Test
    public void verifyGettingCorrectEndHourAndIncorrectMinutes() {
        Assert.assertFalse (new Promotie(null,null,"3","10:09","11:70").verifyEndHour ());
    }
    @Test
    public void verifyGettingIncorrectEndHourAndCorrectMinutes() {
        Assert.assertFalse (new Promotie(null,null,"3","30:00","31:00").verifyEndHour ());
    }

    @Test
    public void verifyGettingIncorrectEndHourAndIncorrectMinutes() {
        Assert.assertFalse (new Promotie(null,null,"3","30:80","31:79").verifyEndHour ());
    }
    @Test
    public void verifyCorrectPromotionHoursIntervalForHour(){
        Calendar hoursCalendar = Calendar.getInstance ();
        hoursCalendar.set (2020,1,30,21,13,2);
        Assert.assertTrue (new Promotie (null,null,"1,2,3","20:00","22:00").verifyTimeInterval (hoursCalendar));
    }
    @Test
    public void verifyIncorrectPromotionTimeIntervalForHour(){
        Calendar hoursCalendar = Calendar.getInstance ();
        hoursCalendar.set (2020,1,30,21,13,2);
        Assert.assertFalse (new Promotie (null,null,"1,2,3","23:00","22:00").verifyTimeInterval (hoursCalendar));
    }

    @Test
    public void verifyIncorrectPromotionTimeIntervalForHourCalendarHoursIsSmallerThanStartHour(){
        Calendar hoursCalendar = Calendar.getInstance ();
        hoursCalendar.set (2020,1,30,20,13,2);
        Assert.assertFalse (new Promotie (null,null,"1,2,3","21:00","22:00").verifyTimeInterval (hoursCalendar));
    }

    @Test
    public void verifyIncorrectPromotionTimeIntervalForHourCalendarHoursIsBiggerThanEndHour(){
        Calendar hoursCalendar = Calendar.getInstance ();
        hoursCalendar.set (2020,1,30,23,13,2);
        Assert.assertFalse (new Promotie (null,null,"1,2,3","21:00","22:00").verifyTimeInterval (hoursCalendar));
    }

    @Test
    public void verifyIncorrectPromotionTimeIntervalForMinutes(){
        Calendar hoursCalendar = Calendar.getInstance ();
        hoursCalendar.set (2020,1,30,22,24,2);
        Assert.assertFalse (new Promotie (null,null,"1,2,3","22:00","22:30").verifyTimeInterval (hoursCalendar));
    }

    @Test
    public void verifyIncorrectPromotionTimeIntervalForMinutesCalendarMinutesIsSmallerThanStartMinutes(){
        Calendar hoursCalendar = Calendar.getInstance ();
        hoursCalendar.set (2020,1,30,22,2,2);
        Assert.assertFalse (new Promotie (null,null,"1,2,3","22:15","22:30").verifyTimeInterval (hoursCalendar));
    }

    @Test
    public void verifyIncorrectPromotionTimeIntervalForHourCalendarHoursIsBiggerThanEndMinutes(){
        Calendar hoursCalendar = Calendar.getInstance ();
        hoursCalendar.set (2020,1,30,22,45,2);
        Assert.assertFalse (new Promotie (null,null,"1,2,3","22:00","22:30").verifyTimeInterval (hoursCalendar));
    }

    @Test
    public void verifyCorrectPromotionWeekDaysForCalenderDays(){
        Calendar hoursCalendar = Calendar.getInstance ();
        hoursCalendar.set (2020,1,30);
        Assert.assertTrue (new Promotie (null,null,"1,2,3","22:00","22:30").verifyWeekDaysForASpecificCalendar (hoursCalendar));
    }

    @Test
    public void verifyIncorrectPromotionWeekDaysForCalenderDaysZeroDay(){
        Calendar hoursCalendar = Calendar.getInstance ();
        hoursCalendar.set (2020,1,30);
        Assert.assertFalse (new Promotie (null,null,"0,1,2,3","22:00","22:30").verifyWeekDaysForASpecificCalendar (hoursCalendar));
    }

    @Test
    public void verifyIncorrectPromotionWeekDaysForCalenderDaysOverSevenDays(){
        Calendar hoursCalendar = Calendar.getInstance ();
        hoursCalendar.set (2020,1,30);
        Assert.assertFalse (new Promotie (null,null,"1,2,3,10,11","22:00","22:30").verifyWeekDaysForASpecificCalendar (hoursCalendar));
    }
    @Test
    public void verifyIncorrectPromotionWeekDaysForCalenderDaysNegativeDays(){
        Calendar hoursCalendar = Calendar.getInstance ();
        hoursCalendar.set (2020,1,30);
        Assert.assertFalse (new Promotie (null,null,"-1,1,2,3","22:00","22:30").verifyWeekDaysForASpecificCalendar (hoursCalendar));
    }

    @Test
    public void verifyCorrectPromotionAvailabilityForCorrectInput(){
        Calendar startDate = Calendar.getInstance ();
        startDate.set(2020,1,30);
        Calendar endDate = Calendar.getInstance ();
        endDate.set(2022,2,30);

        Assert.assertTrue (new Promotie (null ,endDate,"1,2,3,4,5","20:00","23:00").isActive ());
    }

    @Test
    public void verifyPromotionAvailabilityForNullStartDate(){
        Calendar startDate = Calendar.getInstance ();
        startDate.set(2020,1,30);
        Calendar endDate = Calendar.getInstance ();
        endDate.set(2022,2,30);

        Assert.assertTrue (new Promotie (null ,endDate,"1,2,3,4,5","20:00","23:00").isActive ());
    }

    @Test
    public void verifyPromotionAvailabilityForNullEndDate(){
        Calendar startDate = Calendar.getInstance ();
        startDate.set(2020,1,30);
        Calendar endDate = Calendar.getInstance ();
        endDate.set(2022,2,30);

        Assert.assertTrue (new Promotie (startDate ,null,"1,2,3,4,5","20:00","23:00").isActive ());
    }

    @Test
    public void verifyPromotionAvailabilityForIncorrectWeekDaysDateNegativeNumber(){
        Calendar startDate = Calendar.getInstance ();
        startDate.set(2020,1,30);
        Calendar endDate = Calendar.getInstance ();
        endDate.set(2022,2,30);
        Assert.assertFalse (new Promotie (startDate ,endDate,"1,2,3,4,5,-1","20:00","23:00").isActive ());
    }

    @Test
    public void verifyPromotionAvailabilityForIncorrectWeekDaysDatePositiveNumber(){
        Calendar startDate = Calendar.getInstance ();
        startDate.set(2020,1,30);
        Calendar endDate = Calendar.getInstance ();
        endDate.set(2022,2,30);
        Assert.assertFalse (new Promotie (startDate ,endDate,"0,1,2,3,4,5,","20:00","23:00").isActive ());
    }

    @Test
    public void verifyPromotionAvailabilityForIncorrectTimeHours(){
        Calendar startDate = Calendar.getInstance ();
        startDate.set(2020,1,30);
        Calendar endDate = Calendar.getInstance ();
        endDate.set(2022,2,30);
        Assert.assertFalse (new Promotie (startDate ,endDate,"1,2,3,4,5,","20:00","12:00").isActive ());
    }

    @Test
    public void verifyPromotionAvailabilityForIncorrectTimeMinutes(){
        Calendar startDate = Calendar.getInstance ();
        startDate.set(2020,1,30);
        Calendar endDate = Calendar.getInstance ();
        endDate.set(2022,2,30);
        Assert.assertFalse (new Promotie (startDate ,endDate,"1,2,3,4,5,","20:30","20:00").isActive ());
    }

    @Test
    public void verifyPromotionAvailabilityForIncorrectDatesOnYear(){
        Calendar startDate = Calendar.getInstance ();
        startDate.set(2022,1,30);
        Calendar endDate = Calendar.getInstance ();
        endDate.set(2021,2,30);
        Assert.assertFalse (new Promotie (startDate ,endDate,"1,2,3,4,5,","20:00","21:00").isActive ());
    }

    @Test
    public void verifyPromotionAvailabilityForIncorrectDatesOnMon(){
        Calendar startDate = Calendar.getInstance ();
        startDate.set(2022,3,30);
        Calendar endDate = Calendar.getInstance ();
        endDate.set(2022,2,30);
        Assert.assertFalse (new Promotie (startDate ,endDate,"1,2,3,4,5,","20:00","21:00").isActive ());
    }

    @Test
    public void verifyPromotionAvailabilityForIncorrectDatesOnDates(){
        Calendar startDate = Calendar.getInstance ();
        startDate.set(2022,3,30);
        Calendar endDate = Calendar.getInstance ();
        endDate.set(2022,3,29);
        Assert.assertFalse (new Promotie (startDate ,endDate,"1,2,3,4,5,","20:00","20:00").isActive ());
    }
}
