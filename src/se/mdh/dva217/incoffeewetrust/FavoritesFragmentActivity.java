package se.mdh.dva217.incoffeewetrust;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;

import java.util.*;
import java.util.regex.Pattern;

import android.widget.TextView;
import se.mdh.dva217.incoffeewetrust.containers.*;


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
        ExpandableListView elv = (ExpandableListView) getActivity().findViewById(R.id.favExpandableListView);

        FavoritesExpandableListAdapter adapter = new FavoritesExpandableListAdapter(getActivity());

        populateWithDummyData(adapter);

        elv.setAdapter(adapter);
    }

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
                "aSås med pannkaka\nPlättar med falukorv",
                "aÄcklig mat som är god\nVad du vill din sopa",
                "aÄcklig mat som är god\nVad du vill din sopa",
                "aÄcklig mat som är god\nVad du vill din sopa",
                "aÄcklig mat som är god\nVad du vill din sopa"
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
