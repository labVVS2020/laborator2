
public class VVSDate {

    private int day,month,year;

    public VVSDate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getMonth() {
    return month;
    }

    public boolean verifyMonth() {
        return getMonth()>0 && getMonth ()<=12;
    }

    public int getDay() {
        return day;
    }

//    public boolean verifyDay() {
//        // 1, 3, 5, 7,8,10, 12 au 31
//        if( month!=1 && month!=3 && month!=5 && month!=7 && month!=8 && month!=10 && month!=12 ) {
//            if ( month == 2 && day > 28 )
//                return false;
//        }
//        else return day <= 31 && day >= 1;
//
//        return false;
//    }

    public boolean verifyDay() {
        int [] daysOfMonth;
        if ((year % 4 ==0 && year % 100 != 0 ) || year % 400 == 0 )
            daysOfMonth = new int[] { 31,29,31,30,31,30,31,31,30,31,30,31};
        else
            daysOfMonth = new int[] { 31,28,31,30,31,30,31,31,30,31,30,31};
        return day >= 1 && day <= daysOfMonth[ month-1];
     }

    public boolean isLeap() {
        return (  year % 4 == 0 &&  year % 100 != 0 ) ||  year % 400 == 0;
    }

    public int daysSinceZero() {
        int[] daySums = new int[]{0,31,59,90,120,151,181,212,243,273,304,334,365};
        int d = day + daySums[month-1] + (year*365) + year/4 - year/100 + year/400;
        if ((year%400==0 || (year%4==0 && year%100!=0)) && month<=2)
            d--;
        return d;
    }

    public int getDays( VVSDate other ) {
        return daysSinceZero() - other.daysSinceZero();
    }
}
