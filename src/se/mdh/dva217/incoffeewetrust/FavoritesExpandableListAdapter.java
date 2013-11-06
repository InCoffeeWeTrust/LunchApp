package se.mdh.dva217.incoffeewetrust;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import se.mdh.dva217.incoffeewetrust.containers.DailyMenu;
import se.mdh.dva217.incoffeewetrust.containers.WeeklyMenu;
import se.mdh.dva217.incoffeewetrust.db.DatabaseHelper;
import se.mdh.dva217.incoffeewetrust.db.IStorage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Tobias
 * Date: 2013-11-01
 * Time: 00:20
 * To change this template use File | Settings | File Templates.
 */
class FavoritesExpandableListAdapter extends BaseExpandableListAdapter {

    private final List<DayGroup> groups = new ArrayList<DayGroup>();

    private final Activity activity;

    FavoritesExpandableListAdapter(Activity owner, IStorage storage) {
        this.activity = owner;

        String[] favorites = storage.getFavorites();
        String[][] menus = storage.getMenusForFavorites();

        for (int i = 0; i < 5; i++) {
            groups.add(new DayGroup(i, favorites, menus));
        }
    }

    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return groups.get(groupPosition).getChildrenCount();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return groups.get(groupPosition).getChild(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = LayoutInflater.from(activity);
            v = inflater.inflate(R.layout.grouprow, parent, false);
        }

        TextView tv = (TextView)v.findViewById(R.id.laptop);
        tv.setText(groups.get(groupPosition).getDayAndDateText());

        return v;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = LayoutInflater.from(activity);
            v = inflater.inflate(R.layout.childrow, parent, false);
        }

        return v;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    /*
    void add(WeeklyMenu wm) {

        for (DailyMenu dm : wm.toDailyMenus()) {

            DayGroup newGroup = new DayGroup(dm);

            int index = Collections.binarySearch(groups, newGroup);
            if (index < 0)  // key not found
                groups.add(newGroup);
            else
                groups.get(index).merge(newGroup);

            Collections.sort(groups); // keep list sorted
        }
    }
    */


    /*
    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return groups.get(groupPosition).getChildrenCount();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return groups.get(groupPosition).getChild(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(activity);
        View v = inflater.inflate(R.layout.parentrow, parent, false);
        TextView tv = (TextView)v.findViewById(R.id.headers);
        tv.setText(groups.get(groupPosition).getDayAndDateText());

        return tv;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(activity);
        View v = inflater.inflate(R.layout.childrow, parent, false);

        /*
        if (!newSchool)
        {
            if (rows == 0)
            {
                TextView tv = (TextView)v.findViewById(R.id.schoolnameEven);
                tv.setText(getChild(groupPosition, childPosition).toString());
                rows++;
                return tv;
            }
            else
            {
                TextView tv = (TextView)v.findViewById(R.id.evenRow);
                tv.setText(getChild(groupPosition, childPosition).toString());
                rows++;
                if (rows == 3)
                {
                    newSchool = true;
                    rows = 0;
                }
                return tv;
            }
        }
        else
        {
            if (rows == 0)
            {
                TextView tv = (TextView)v.findViewById(R.id.schoolnameOdd);
                tv.setText(getChild(groupPosition, childPosition).toString());
                rows++;
                return tv;
            }
            else
            {
                TextView tv = (TextView)v.findViewById(R.id.oddRow);
                tv.setText(getChild(groupPosition, childPosition).toString());
                rows++;
                if (rows == 3)
                {
                    newSchool = false;
                    rows = 0;
                }
                return tv;
            }
        }
        *

        return v;
    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
    */
}
