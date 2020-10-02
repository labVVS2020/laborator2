import java.util.Calendar;

public class Promotie {
    private Calendar startDate = null;
    private Calendar endDate = null;
    private String weekdays;
    private String startHour;
    private String endHour;


    public Promotie( Calendar startDate , Calendar endDate , String weekdays , String startHour , String endHour ) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.weekdays = weekdays;
        this.startHour = startHour;
        this.endHour = endHour;
    }

    public Calendar getStartDate( ) {
        return startDate;
    }

    public Calendar getEndDate( ) {
        return endDate;
    }

    public String getWeekdays( ) {
        return weekdays;
    }

    public String getStartHour( ) {
        return startHour;
    }

    public String getEndHour( ) {
        return endHour;
    }

    public boolean verifyWeekDays( ) {
        String[] weekDaysArray = weekdays.split ( "," );

        for ( String weekDay : weekDaysArray ) {
            if ( Integer.parseInt ( weekDay ) <= 0 || Integer.parseInt ( weekDay ) > 7 )
                return false;
        }
        return true;
    }

    public boolean verifyStartHour( ) {
        String[] startHourArray = startHour.split ( ":" );
        return ( ( Integer.parseInt ( startHourArray[ 0 ] ) < 24 && Integer.parseInt ( startHourArray[ 0 ] ) >= 0 ) && ( Integer.parseInt ( startHourArray[ 1 ] ) < 60 && Integer.parseInt ( startHourArray[ 0 ] ) >= 0 ) );
    }

    public boolean verifyEndHour( ) {
        String[] endHoursArray = endHour.split ( ":" );
        return ( ( Integer.parseInt ( endHoursArray[ 0 ] ) < 24 && Integer.parseInt ( endHoursArray[ 0 ] ) >= 0 ) && ( Integer.parseInt ( endHoursArray[ 1 ] ) < 60 && Integer.parseInt ( endHoursArray[ 0 ] ) >= 0 ) );
    }

    public boolean verifyTimeInterval( Calendar calendar ) {
        String[] startHourArray = startHour.split ( ":" );
        String[] endHoursArray = endHour.split ( ":" );
        if ( calendar.get ( Calendar.HOUR_OF_DAY ) >= ( Integer.parseInt ( startHourArray[ 0 ] ) ) && ( calendar.get ( Calendar.HOUR_OF_DAY ) < ( Integer.parseInt ( endHoursArray[ 0 ] ) ) ) )

            return verifyStartHour ( ) && verifyEndHour ( ) && ( Integer.parseInt ( startHourArray[ 0 ] ) < Integer.parseInt ( endHoursArray[ 0 ] ) );
        else if ( calendar.get ( Calendar.HOUR_OF_DAY ) == ( Integer.parseInt ( startHourArray[ 0 ] ) ) && ( calendar.get ( Calendar.HOUR_OF_DAY ) == ( Integer.parseInt ( endHoursArray[ 0 ] ) ) ) )
            return verifyStartHour ( ) && verifyEndHour ( ) && calendar.get ( Calendar.MINUTE ) > ( Integer.parseInt ( startHourArray[ 1 ] ) ) && ( calendar.get ( Calendar.MINUTE ) < ( Integer.parseInt ( endHoursArray[ 1 ] ) ) ) && ( Integer.parseInt ( startHourArray[ 1 ] ) > ( Integer.parseInt ( endHoursArray[ 1 ] ) ) );

        return false;
    }

    public boolean verifyWeekDaysForASpecificCalendar( Calendar calendar ) {
        String[] weekDaysArray = weekdays.split ( "," );

        for ( String weekDay : weekDaysArray ) {

            return verifyWeekDays ( ) && ( ( Integer.parseInt ( weekDay ) + 1 == calendar.get ( Calendar.MONDAY ) ) ||
                    ( Integer.parseInt ( weekDay ) + 1 == Calendar.TUESDAY ) || ( Integer.parseInt ( weekDay ) + 1 == Calendar.WEDNESDAY )
                    || ( Integer.parseInt ( weekDay ) + 1 == Calendar.THURSDAY ) || ( Integer.parseInt ( weekDay ) == Calendar.FRIDAY ) ||
                    ( Integer.parseInt ( weekDay ) + 1 == Calendar.SATURDAY ) || ( Integer.parseInt ( weekDay ) + 1 == Calendar.SUNDAY ) );
        }
        return false;
    }

    public boolean isActive( ) {
        Calendar now = Calendar.getInstance ( );

        return isActiveHelper ( now );
    }

    public boolean isActiveHelper( Calendar calendar ) {
        if ( startDate == null || endDate == null || weekdays == null || startHour == null || endHour == null )
            return true;
        else if ( ( calendar.get ( Calendar.YEAR ) >= startDate.get ( Calendar.YEAR ) && calendar.get ( Calendar.YEAR ) <= endDate.get ( Calendar.YEAR ) ) && verifyWeekDaysForASpecificCalendar ( calendar ) && verifyTimeInterval ( calendar ) )
            return true;
        else if ( ( calendar.get ( Calendar.YEAR ) == startDate.get ( Calendar.YEAR ) ) &&
                ( calendar.get ( Calendar.YEAR ) == endDate.get ( Calendar.YEAR ) )
                && ( calendar.get ( Calendar.MONTH ) >= startDate.get ( Calendar.MONTH )
                && calendar.get ( Calendar.MONTH ) <= endDate.get ( Calendar.MONTH ) ) && verifyWeekDaysForASpecificCalendar ( calendar ) && verifyTimeInterval ( calendar ) )
            return true;
        else
            return ( calendar.get ( Calendar.DATE ) >= startDate.get ( Calendar.DATE )
                    && calendar.get ( Calendar.DATE ) <= endDate.get ( Calendar.DATE ) && verifyWeekDaysForASpecificCalendar ( calendar ) && verifyTimeInterval ( calendar ) );


    }
}
