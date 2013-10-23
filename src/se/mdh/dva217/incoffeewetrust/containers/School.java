package se.mdh.dva217.incoffeewetrust.containers;

/**
 * Not a one-to-one mapping of the database table Schools!
 *                                                                                                     e
 * Immutable.
 *
 * Created with IntelliJ IDEA.
 * User: Tobias
 * Date: 2013-10-22
 * Time: 14:18
 * To change this template use File | Settings | File Templates.
 */
public class School {

    private final int id;
    private final String name, city, state;

    public School(int id, String name, String city, String state)
    {
        this.id = id;
        this.name = name;
        this.city = city;
        this.state = state;
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getCity()
    {
        return city;
    }

    public String getState()
    {
        return state;
    }
}
