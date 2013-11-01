package se.mdh.dva217.incoffeewetrust.lunchapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;


/**
 * Created with IntelliJ IDEA.
 * User: d3eniz
 * Date: 10/18/13
 * Time: 10:14 AM
 * To change this template use File | Settings | File Templates.
 */
public class FavoritesFragmentActivity extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.favorites, null);

        ExpandableListView elv = (ExpandableListView) v.findViewById(R.id.favExpandableListView);
        elv.setAdapter(new FavoritesAdapter());
        return v;
    }
    public class FavoritesAdapter extends BaseExpandableListAdapter {

        private LayoutInflater inflater;
        private Context context;


        private String[] groups = { "People Names", "Dog Names", "Cat Names", "Fish Names" };

        private String[][] children = {
                { "Arnold", "Barry", "Chuck", "David" },
                { "Ace", "Bandit", "Cha-Cha", "Deuce" },
                { "Fluffy", "Snuggles" },
                { "Goldy", "Bubbles" }
        };

        public FavoritesAdapter (Context context, String[] parent, String[][] children)
        {
           this.context = context;
            this.groups = parent;
            this.children = children;
            inflater = LayoutInflater.from(context);

        }

        public FavoritesAdapter ()
        {


        }
        @Override
        public int getGroupCount() {
            return groups.length;
        }

        @Override
        public int getChildrenCount(int i) {
            return children[i].length;
        }

        @Override
        public Object getGroup(int i) {
            return groups[i];
        }

        @Override
        public Object getChild(int i, int i1) {
            return children[i][i1];
        }

        @Override
        public long getGroupId(int i) {
            return i;
        }

        @Override
        public long getChildId(int i, int i1) {
            return i1;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
            View v = View.inflate(getActivity(),R.layout.parentrow, null);
            TextView tv = (TextView)v.findViewById(R.id.laptop);
            tv.setText("Fisksoppa");
            //tv.setText(getGroup(i).toString());
            TextView textView = new TextView(FavoritesFragmentActivity.this.getActivity());
            textView.setText(getGroup(i).toString());
            return tv;
        }

        @Override
        public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
            TextView textView = new TextView(FavoritesFragmentActivity.this.getActivity());
            textView.setText(getChild(i, i1).toString());
            return textView;
        }

        @Override
        public boolean isChildSelectable(int i, int i1) {
            return true;
        }

    }


}
