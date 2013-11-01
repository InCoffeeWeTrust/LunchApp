package se.mdh.dva217.incoffeewetrust.containers;

import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: Tobias
 * Date: 2013-10-29
 * Time: 10:02
 * To change this template use File | Settings | File Templates.
 */
public class WeeklyMenu {

    /*
    public static enum Days
    {
        Måndag,
        Tisdag,
        Onsdag,
        Torsdag,
        Fredag
    }
    */

    private final SchoolAndCity schoolAndCity;
    private final String[] dishes;
    private final int weekNumber;
    //private long date;

    /**
     * Creates a menu for a given week number
     * @param weekNumber    the given week [1,53]
     * @param dishes        must be of length 5
     */
    public WeeklyMenu(SchoolAndCity schoolAndCity, int weekNumber, String[] dishes)
    {
        if (schoolAndCity == null)
            throw new NullPointerException("schoolAndCity");
        if (weekNumber < 1 || weekNumber > 53)
            throw new IndexOutOfBoundsException("weekNumber = " + weekNumber);
        if (dishes.length != 5)
            throw new IllegalArgumentException("length = " + dishes.length);

        this.schoolAndCity = schoolAndCity;
        this.weekNumber = weekNumber;
        this.dishes = dishes;
    }

    public SchoolAndCity getSchoolAndCity() {
        return schoolAndCity;
    }

    /**
     * Returns a copy
     */
    public String[] getDishes() {
        return dishes.clone();
    }

    public int getWeekNumber() {
        return weekNumber;
    }

    public DailyMenu[] toDailyMenus() {
        DailyMenu[] result = new DailyMenu[5];

        for (int i = 0; i < result.length; i++) {

            // extract date
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.WEEK_OF_YEAR, weekNumber);
            cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY + i);

            cal.set(Calendar.MILLISECOND, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.HOUR, 0);

            result[i] = new DailyMenu(schoolAndCity, dishes[i], cal.getTimeInMillis());
        }
        return result;
    }

    /*
    public String getName()
    {
        return name;
    }

    public int getSchoolId()
    {
        return schoolId;
    }

    public long getDate()
    {
        return date;
    }
    */
}
