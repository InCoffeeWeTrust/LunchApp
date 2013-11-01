package se.mdh.dva217.incoffeewetrust.containers;

import android.content.SharedPreferences;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Tobias
 * Date: 2013-10-29
 * Time: 11:10
 * To change this template use File | Settings | File Templates.
 */
public class LocalStorage {

    private static final String SCHOOL_SET = "schools";
    //private static final String MENU_SET = "menus";

    private static final Set<String> EMPTY_SET =  Collections.emptySet();

    private final SharedPreferences prefs = null;
    //private final Editor editor = null;

    public void add(SchoolAndCity school) {
        Set<String> schoolSet = new HashSet<String>(prefs.getStringSet(SCHOOL_SET, EMPTY_SET));
        schoolSet.add(school.toString());
        prefs.edit().putStringSet(SCHOOL_SET, schoolSet);
    }

    public void remove(SchoolAndCity school) {
        Set<String> schoolSet = new HashSet<String>(prefs.getStringSet(SCHOOL_SET, EMPTY_SET));
        schoolSet.remove(school.toString());

        if (schoolSet.isEmpty())
            prefs.edit().remove(SCHOOL_SET);
        else
            prefs.edit().putStringSet(SCHOOL_SET, schoolSet);

        // remove all menus using brute force
        for (int i = 1; i < 54; i++)
            prefs.edit().remove(school.toString() + '@' + i);

        prefs.edit().commit();
    }

    public void put(SchoolAndCity school, int weekNumber, String[] dishes)
    {
        if (weekNumber < 1 || weekNumber > 53)
            throw new IndexOutOfBoundsException("weekNumber = " + weekNumber);
        if (dishes.length != 10)
            throw new IllegalArgumentException();

        prefs.edit().putString(school.toString() + '@' + weekNumber, toString(dishes)).commit();
    }

    public void remove(SchoolAndCity school, int weekNumber)
    {
        if (weekNumber < 1 || weekNumber > 53)
            throw new IndexOutOfBoundsException("weekNumber = " + weekNumber);

        prefs.edit().remove(school.toString() + '@' + weekNumber).commit();
    }

    static String toString(String[] dishes) {
        String result = "";
        for (String s : dishes)
            result += s + '\n';

        return result;
    }
}
