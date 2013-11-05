package se.mdh.dva217.incoffeewetrust;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TableRow;
import android.widget.TextView;


/**
 * Created with IntelliJ IDEA.
 * User: d3eniz
 * Date: 10/18/13
 * Time: 10:14 AM
 * To change this template use File | Settings | File Templates.
 */
public class SettingsFragmentActivity extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View v = inflater.inflate(R.layout.settings, null);

        ExpandableListView elv = (ExpandableListView) v.findViewById(R.id.settingsExpandableListView);
        elv.setAdapter(new settingsAdapter());
        return v;
    }

    public class settingsAdapter extends BaseExpandableListAdapter {

            private String[] groups = { "Hantera favoriter", "Exportera till kalender", "Hantera meddelanden"};

            private String[][] subheaders = {
                    { "Fryxellska skolan", "Fria Gymnasiet", "Barkaröskolan" },
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
            public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
                View v = View.inflate(getActivity(),R.layout.settingsparent, null);
                TextView tv = (TextView)v.findViewById(R.id.headerText);
                tv.setText(getGroup(groupPosition).toString());
                return tv;
            }

            @Override
            public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent)
            {
                View v = View.inflate(getActivity(),R.layout.settingschildren, null);
                TextView tv;
                if ((childPosition % 2)== 0)
                {
                    tv = (TextView)v.findViewById(R.id.subheader_even);
                    tv.setText(getChild(groupPosition, childPosition).toString());
                }
                else
                {
                    tv = (TextView)v.findViewById(R.id.subheader_odd);
                    tv.setText(getChild(groupPosition, childPosition).toString());
                }
                return tv;
            }

            @Override
            public boolean isChildSelectable(int i, int i1) {
                return true;
            }
    }
}
