package se.mdh.dva217.incoffeewetrust;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.*;
import java.util.regex.Pattern;

import se.mdh.dva217.incoffeewetrust.containers.*;
import se.mdh.dva217.incoffeewetrust.db.DatabaseHelper;
import se.mdh.dva217.incoffeewetrust.db.IStorage;


/**
 * Created with IntelliJ IDEA.
 * User: d3eniz
 * Date: 10/18/13
 * Time: 10:14 AM
 * To change this template use File | Settings | File Templates.
 */
public class FavoritesFragmentActivity extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.favorites, null);
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // http://stackoverflow.com/questions/17636735/expandable-listview-in-fragment
        final ExpandableListView elv = (ExpandableListView) getActivity().findViewById(R.id.favExpandableListView);

        final IStorage storage = DatabaseHelper.getInstance(getActivity());

        IStorage.Listener listener = new IStorage.Listener() {
            @Override
            public void storageChanged(IStorage.Type type) {

                Log.d("favorites_storageChanged", type.toString());

                if (type == IStorage.Type.Favorite || type == IStorage.Type.Menu) {

                    //ExpandableListAdapter adapter = new FavoritesExpandableListAdapter(getActivity(), storage);
                    ExpandableListAdapter adapter = createAdapter(storage);

                    elv.setAdapter(adapter);

                    for (int i = 0; i < 5; i++) {
                        elv.expandGroup(i);
                    }
                }
            }
        };
        storage.addListener(listener);

        // mimic a favorite update to initiate the list adapter
        listener.storageChanged(IStorage.Type.Favorite);
    }

    private static enum DayNames {
        Måndag,
        Tisday,
        Onsdag,
        Torsdag,
        Fredag
    }

    private SimpleExpandableListAdapter createAdapter(IStorage storage) {

        final String[] favorites = storage.getFavorites();

        // GROUPS

        // A List of Maps. Each entry in the List corresponds to one group in the list. The Maps contain the data for each group, and should include all the entries specified in "groupFrom"
        List<Map<String, String>> groupData = new ArrayList<Map<String, String>>();
        for (DayNames day : DayNames.values()) {
            groupData.add(Collections.singletonMap("date", day.toString()));
        }

        // resource identifier of a view layout that defines the views for a group. The layout file should include at least those named views defined in "groupTo"
        int groupLayout = R.layout.favoritesgroupitem;

        // A list of keys that will be fetched from the Map associated with each group.
        String[] groupFrom = new String[]{"date"};

        // The group views that should display column in the "groupFrom" parameter. These should all be TextViews. The first N views in this list are given the values of the first N columns in the groupFrom parameter.
        int[] groupTo = new int[]{R.id.date};

        // CHILDREN

        // A List of List of Maps. Each entry in the outer List corresponds to a group (index by group position), each entry in the inner List corresponds to a child within the group (index by child position), and the Map corresponds to the data for a child (index by values in the childFrom array). The Map contains the data for each child, and should include all the entries specified in "childFrom"
        List<List<Map<String, String>>> childData = new ArrayList<List<Map<String, String>>>();
        for (DayNames day : DayNames.values()) {

            List<Map<String, String>> menuData = new ArrayList<Map<String, String>>();

            for (String favorite : favorites) {
                String[] menu = storage.getMenu(favorite, 1);

                Map<String, String> schoolAndDishValues = new HashMap<String, String>();
                schoolAndDishValues.put("schoolname", " " + favorite);
                schoolAndDishValues.put("dish", " " + menu[day.ordinal()]);

                menuData.add(schoolAndDishValues);
            }

            childData.add(menuData);
        }

        // resource identifier of a view layout that defines the views for a child. The layout file should include at least those named views defined in "childTo"
        int childLayout = R.layout.favoriteschilditem; //android.R.layout.simple_expandable_list_item_2;

        // A list of keys that will be fetched from the Map associated with each child.
        String[] childFrom = new String[]{"schoolname", "dish"};

        // The child views that should display column in the "childFrom" parameter. These should all be TextViews. The first N views in this list are given the values of the first N columns in the childFrom parameter.
        int[] childTo = new int[]{R.id.schoolname, R.id.dish};

        return new SimpleExpandableListAdapter(
                getActivity(),
                groupData, groupLayout, groupFrom, groupTo,
                childData, childLayout, childFrom, childTo);
    }

    /*
    private static void populateWithDummyData(FavoritesExpandableListAdapter adapter) {
        SchoolAndCity fryxellska = new SchoolAndCity("Fryxellska skolan", "Västerås");
        adapter.add(new WeeklyMenu(fryxellska, 44, new String[]{
                "Sås med pannkaka\nPlättar med falukorv",
                "Äcklig mat som är god\nVad du vill din sopa",
                "Äcklig mat som är god\nVad du vill din sopa",
                "Äcklig mat som är god\nVad du vill din sopa",
                "Äcklig mat som är god\nVad du vill din sopa"
        }));

        SchoolAndCity smurf = new SchoolAndCity("Smurfskolan", "Köping");
        adapter.add(new WeeklyMenu(smurf, 44, new String[]{
                "Sås med pannkaka\nPlättar med falukorv",
                "Äcklig mat som är god\nVad du vill din sopa",
                "Äcklig mat som är god\nVad du vill din sopa",
                "Äcklig mat som är god\nVad du vill din sopa",
                "Äcklig mat som är god\nVad du vill din sopa"
        }));
    }

    /*
    private void populateWithDummyData() {
        SchoolAndCity fryxellska = new SchoolAndCity("Fryxellska skolan", "Västerås");
        SchoolAndCity smurf = new SchoolAndCity("Smurfskolan", "Köping");
        long today = new Date().getTime();

        DayGroup monday = new DayGroup();
        monday.addSchool(new DailyMenu(fryxellska, "Sås med pannkaka\nPlättar med falukorv", today));
        monday.addSchool(new DailyMenu(smurf, "Äcklig mat som är god\nVad du vill din sopa", today));
        groups.add(monday);

        DayGroup tuesday = new DayGroup();
        tuesday.addSchool(new DailyMenu(fryxellska, "dsakjfSås med pannkaka\nPläasdfttar med falukorv", today + 1000));
        tuesday.addSchool(new DailyMenu(smurf, "adfsdfÄcklig mat som är god\nasdfVad du vill din sopa", today + 1000));
        groups.add(tuesday);
    }
    */
}
