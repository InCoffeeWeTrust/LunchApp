package se.mdh.dva217.incoffeewetrust;

import android.app.Fragment;
import android.os.Bundle;
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
public class SettingsFragmentActivity extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.settings, null);

        ExpandableListView elv = (ExpandableListView) v.findViewById(R.id.settingsExpandableListView);
        elv.setAdapter(new settingsAdapter());
        return v;
    }
    public class settingsAdapter extends BaseExpandableListAdapter {

        private String[] groups = { "Hantera favoriter", "Exportera till kalender", "Hantera meddelanden"};

        private String[][] subheaders = {
                { "LeChuck", "Montserrat", "Lafitte", "Lafayette" },
                { "Bob", "Shadowmere", "Shadowfax", "Mirol" },
                { "D20", "D12", "D8" },
        };

        @Override
        public int getGroupCount() {
            return groups.length;
        }

        @Override
        public int getChildrenCount(int i) {
            return subheaders[i].length;
        }

        @Override
        public Object getGroup(int i) {
            return groups[i];
        }

        @Override
        public Object getChild(int i, int i1) {
            return subheaders[i][i1];
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
            TextView textView = new TextView(SettingsFragmentActivity.this.getActivity());
            textView.setText(getGroup(i).toString());
            return textView;
        }

        @Override
        public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {

            //View v = inflater.inflate(R.layout.settingschildren, null);
            //TextView tv = (TextView)v.findViewById(R.id.subheaders);
            TextView textView = new TextView(SettingsFragmentActivity.this.getActivity());
            textView.setText(getChild(i, i1).toString());
            return textView;
        }

        @Override
        public boolean isChildSelectable(int i, int i1) {
            return true;
        }

    }


}
