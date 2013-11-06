package se.mdh.dva217.incoffeewetrust;

import se.mdh.dva217.incoffeewetrust.containers.DailyMenu;
import se.mdh.dva217.incoffeewetrust.containers.WeeklyMenu;

import java.text.DateFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: Tobias
 * Date: 2013-10-31
 * Time: 23:58
 * To change this template use File | Settings | File Templates.
 */


class DayGroup {

    private static enum DayNames {
        Måndag,
        Tisday,
        Onsdag,
        Torsdag,
        Fredag
    }

    private int day;
    private String[] schools;
    private String[][] menus;

    DayGroup(int day, String[] schools, String[][] menus) {
        this.day = day;
        this.schools = schools;
        this.menus = menus;

        // http://docs.oracle.com/javase/6/docs/api/java/text/SimpleDateFormat.html
        //return new SimpleDateFormat("E d M").format(date);

        //DateFormat df = DateFormat.getDateInstance(DateFormat.FULL, new Locale("sv_SE"));
        //dateText = df.format(new Date(date));
    }

    String getDayAndDateText() {
        return DayNames.values()[day].toString();
    }

    int getChildrenCount() {
        return schools.length * 2;
    }

    public Object getChild(int childPosition) {
        if (childPosition % 2 == 0)
            return schools[childPosition / 2];
        else
            return menus[childPosition / 2][day];
    }
}
/*
class DayGroup implements Comparable<DayGroup> {

    private static final Pattern NEW_LINE_REGEX = Pattern.compile("\n");

    /*
    private static final Comparator<DailyMenu> COMPARATOR = new Comparator<DailyMenu>() {
        @Override
        public int compare(DailyMenu lhs, DailyMenu rhs) {
            return lhs.getSchoolAndCity().compareTo(rhs.getSchoolAndCity());
        }
    };
    /

    private final Collection<DailyMenu> dailyMenus = new ArrayList<DailyMenu>();//COMPARATOR);

    private final long date;

    DayGroup(DailyMenu dm) {
        dailyMenus.add(dm);

        date = dm.getDate();
    }

    void merge(DayGroup other) {
        dailyMenus.addAll(other.dailyMenus);

        if (other.date != date)
            throw new IllegalArgumentException("fucked up date!");
    }

    String getDayAndDateText() {

        // http://docs.oracle.com/javase/6/docs/api/java/text/SimpleDateFormat.html
        //return new SimpleDateFormat("E d M").format(date);

        DateFormat df = DateFormat.getDateInstance(DateFormat.FULL, new Locale("sv_SE"));
        return df.format(new Date(date));
    }

    int getChildrenCount() {

        int result = dailyMenus.size();

        for (DailyMenu dm : dailyMenus)
            result += NEW_LINE_REGEX.split(dm.getDishes()).length;

        return result;
    }

    public Object getChild(int childPosition) {
        int position = 0;
        for (DailyMenu dm : dailyMenus) {

            if (childPosition == position)
                return dm.getSchoolAndCity().getSchoolName();

            position++;

            String[] dishes = NEW_LINE_REGEX.split(dm.getDishes());
            for (String dish : dishes) {
                if (childPosition == position)
                    return dish;

                position++;
            }
        }
        throw new RuntimeException(getDayAndDateText() + " " + childPosition);
    }

    @Override
    public int compareTo(DayGroup another) {
        return (int)(date - another.date);
    }
}
*/