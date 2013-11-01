package se.mdh.dva217.incoffeewetrust.containers;

/**
 * Not a one-to-one mapping of the database table DailyMenu!
 *                                                                                                     e
 * Immutable.
 *
 * Created with IntelliJ IDEA.
 * User: Tobias
 * Date: 2013-10-22
 * Time: 14:26
 * To change this template use File | Settings | File Templates.
 */
public class Dish {

    private final int schoolId;
    private final String name;
    private long date;

    public Dish(String name, int schoolId, long date)
    {
        this.name = name;
        this.schoolId = schoolId;
        this.date = date;
    }

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
}
