package se.mdh.dva217.incoffeewetrust.containers;

/**
 * Created with IntelliJ IDEA.
 * User: Tobias
 * Date: 2013-10-29
 * Time: 10:17
 * To change this template use File | Settings | File Templates.
 */
public class DailyMenu {

    private final SchoolAndCity schoolAndCity;
    private final String dishes;
    private final long date;

    public DailyMenu(SchoolAndCity schoolAndCity, String dishes, long date)
    {
        if (schoolAndCity == null)
            throw new NullPointerException("schoolAndCity");
        if (dishes == null)
            throw new NullPointerException("dishes");

        this.schoolAndCity = schoolAndCity;
        this.dishes = dishes;
        this.date = date;
    }

    public SchoolAndCity getSchoolAndCity() {
        return schoolAndCity;
    }

    public String getDishes() {
        return dishes;
    }

    public long getDate()
    {
        return date;
    }
}
