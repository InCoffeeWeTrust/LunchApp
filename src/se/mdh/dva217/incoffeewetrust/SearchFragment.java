package se.mdh.dva217.incoffeewetrust;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.*;
import se.mdh.dva217.incoffeewetrust.db.DatabaseHelper;
import se.mdh.dva217.incoffeewetrust.db.IStorage;

import java.util.*;


/**
 * Created with IntelliJ IDEA.
 * User: d3eniz
 * Date: 10/18/13
 * Time: 10:14 AM
 * To change this template use File | Settings | File Templates.
 */
public class SearchFragment extends Fragment {

    private EditText searchField;
    private ListView listView;
    private SearchArrayAdapter adapter;

    private IStorage storage;

    // http://www.androidhive.info/2012/09/android-adding-search-functionality-to-listview/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return (ViewGroup) inflater.inflate(R.layout.search,container,false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        storage = DatabaseHelper.getInstance(getActivity());
        /*
        boolean flag = storage.addSchools("Smurfskolan", "Särskrivnings skolan", "Knarkskolan", "Hasch och inbrottskolan",
                "Androidskolan", "Våldskolan Lundsberg", "Kungliga knarkakademin");

        Log.d("favorite", "addSchools " + flag);

        storage.addFavorite(storage.getSchools()[1]);
        storage.addFavorite(storage.getSchools()[5]);
          */

        //storage.addMenu("Smurfskolan", 1, new String[]{"sdafsdaf", "sdfasdf", "sadf sdfas fs", "123", "ASDASDFADSFdaf fds"});

        searchField = (EditText)getActivity().findViewById(R.id.search_edittext);
        listView = (ListView)getActivity().findViewById(R.id.search_listview);
        adapter = new SearchArrayAdapter();

        searchField.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                adapter.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {}

            @Override
            public void afterTextChanged(Editable arg0) {}
        });

        listView.setAdapter(adapter);

        // todo listen for storage changes
        storage.addListener(new IStorage.Listener() {
            public void storageChanged(IStorage.Type type) {
                Log.i("favorite storageChanged", SearchFragment.class + " " + type.toString());
            }
        });
    }

    // http://devtut.wordpress.com/2011/06/09/custom-arrayadapter-for-a-listview-android/
    private class SearchArrayAdapter extends ArrayAdapter<String> {

        SearchArrayAdapter() {
            super(getActivity(), android.R.layout.simple_list_item_1, storage.getSchools());
        }

        // We are overriding the getView method here - this is what defines how each
        // list item will look.
        public View getView(int position, View convertView, ViewGroup parent){

            // assign the view we are converting to a local variable
            View v = convertView;

            //final String schoolName = getItem(position);

            // first check to see if the view is null. if so, we have to inflate it.
            // to inflate it basically means to render, or show, the view.
            if (v == null) {
                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = inflater.inflate(R.layout.searchlistitem, null);

                // ListView recycles the row view objects and assigns fresh data on them when "getView" is called,
                // so the approach to use, is to add a listener in the getView function.
                v.findViewById(R.id.searchlistitem_schoolname).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String schoolName = ((TextView)view).getText().toString();

                        Intent menuIntent = new Intent(getActivity(), SingleMenuActivity.class);
                        menuIntent.putExtra("schoolName", schoolName);
                        //String[] menu = new String[]{"one", "pannkana", "ASDF asd sdf as", "sdfa ssda fsdf  sdfdasfa", "sdfdsafdfasdfsDSFASFDf sdfsdf"};

                        // todo fix week number and Toast
                        String[] menu = storage.getMenu(schoolName, 1);
                        if (menu == null) {
                            Toast.makeText(getActivity(), "Ingen menu tillgänglig!", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            menuIntent.putExtra("menu", menu);
                            startActivity(menuIntent);
                        }
                    }
                });

                v.findViewById(R.id.searchlistitem_favorites).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ImageView imageView = (ImageView)view;

                        View parent = (View)imageView.getParent();
                        TextView schooTextView = (TextView)parent.findViewById(R.id.searchlistitem_schoolname);
                        String schoolName = schooTextView.getText().toString();

                        if(!Arrays.asList(storage.getFavorites()).contains(schoolName)) {
                            imageView.setImageResource(R.drawable.star_gold);

                            Log.i("favorite clicked", schoolName + "=true");

                            storage.addFavorite(schoolName);

                            Toast.makeText(getActivity(), schoolName + " tillagd", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            imageView.setImageResource(R.drawable.star_silver);

                            Log.i("favorite clicked", schoolName + "=false");

                            storage.removeFavorite(schoolName);

                            Toast.makeText(getActivity(), schoolName + " borttagen", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

            // Recall that the variable position is sent in as an argument to this method.
            // The variable simply refers to the position of the current object in the list. (The ArrayAdapter
            // iterates through the list we sent it)

            String schoolName = getItem(position);

            TextView nameView = (TextView)v.findViewById(R.id.searchlistitem_schoolname);
            nameView.setText(schoolName);

            ImageView favoriteImage = (ImageView)v.findViewById(R.id.searchlistitem_favorites);
            if (!Arrays.asList(storage.getFavorites()).contains(schoolName))
                favoriteImage.setImageResource(R.drawable.star_silver);
            else
                favoriteImage.setImageResource(R.drawable.star_gold);

            // todo alternate background for list item doesn't work
            if (position % 2 == 0)
                v.setBackgroundColor(R.color.multilinelistlight);
            else
                v.setBackgroundColor(R.color.multilinelistdark);

            // the view must be returned to our activity
            return v;
        }
    }

    /*
    private class DBAsyncTask extends AsyncTask<Void, Void, String[]> {

        @Override
        protected String[] doInBackground(Void... voids) {
            DBAdapter dbAdapter  = new DBAdapter(null);
            List<School> schools = dbAdapter.readSchools(new ArrayList<School>());

            String[] result = new String[schools.size()];
            for (int i = 0; i < result.length; i++) {
                result[i] = schools.get(i).toString();
            }

            return result;
            //return new String[]{"Smurfskolan", "Särskrivningsskolan", "Kraftskolan", "Mälardalens Högskola"};
        }

        @Override
        protected void onPostExecute(String[] strings) {
            ArrayAdapter<String> aa = (ArrayAdapter<String>)searchField.getAdapter();

            aa.addAll(strings);
        }
    }




    /*
    @Override
    public void onResume() {
        //adapter.add(new Date().toString());
    }

    /*
    private class AutoCompleteSearchAdapter extends ArrayAdapter<String> {

        AutoCompleteSearchAdapter() {
            super(getActivity(), android.R.layout.simple_list_item_1,
                    new String[]{"Smurfskolan", "Särskrivningsskolan", "Kraftskolan", "Mälardalens Högskola"});
        }
    }
    */

    // Uses AsyncTask to create a task away from the main UI thread. This task takes a
    // URL string and uses it to create an HttpUrlConnection. Once the connection
    // has been established, the AsyncTask downloads the contents of the webpage as
    // an InputStream. Finally, the InputStream is converted into a string, which is
    // displayed in the UI by the AsyncTask's onPostExecute method.

    // http://developer.android.com/training/basics/network-ops/connecting.html
    /*
    private class MyAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... sqlCommands) {

            // params comes from the execute() call: params[0] is the sql command.
            try {
                //DBAdapter adapter = new DBAdapter(null);

                //List<String> s = adapter.executeQuery(sqlCommands[0]);

                List<String> s = new ArrayList<String>();
                s.add("asdf");
                s.add("dfsdgdfg");
                final SharedPreferences preferences = getActivity().getSharedPreferences("pref", 0);
                preferences.edit().putStringSet("states", new TreeSet<String>(s));

                return "Success storing states";
            } catch (Exception e) {
                e.printStackTrace();
                return "Unable to retrieve states";
            }
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            //textView.setText(result);
            //EditText ed = (EditText)findV    editText_search
        }
    }
    */

}
