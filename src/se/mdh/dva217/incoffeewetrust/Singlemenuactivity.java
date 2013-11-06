package se.mdh.dva217.incoffeewetrust;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;
//import se.mdh.dva217.incoffeewetrust.db.DatabaseHelper;

/**
 * Created with IntelliJ IDEA.
 * User: d3eniz
 * Date: 11/5/13
 * Time: 6:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class SingleMenuActivity extends Activity {

    /*
    String schoolName;
    TextView school;
    TextView monday;
    TextView tuesday;
    TextView wednesday;
    TextView thursday;
    TextView friday;

    DatabaseHelper db;
      */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.singlemenu);

        //bind all controls
        /*
        school = (TextView) findViewById(R.id.singleMenuSchoolname);
        monday = (TextView) findViewById(R.id.singleMenuMondayLunch);
        tuesday = (TextView) findViewById(R.id.singleMenuTuesdayLunch);
        wednesday = (TextView) findViewById(R.id.singleMenuWednesdayLunch);
        thursday = (TextView) findViewById(R.id.singleMenuThursdayLunch);
        friday = (TextView) findViewById(R.id.singleMenuFridayLunch);
          */
        //create databasehelper with this context
        //db = new DatabaseHelper(this);

        // get schoolname, passed with intent
        TextView school = (TextView) findViewById(R.id.singleMenuSchoolname);
        school.setText(getIntent().getExtras().getString("schoolName"));

        setLunchText(R.id.singleMenuMondayLunch, 0);
        setLunchText(R.id.singleMenuTuesdayLunch, 1);
        setLunchText(R.id.singleMenuWednesdayLunch, 2);
        setLunchText(R.id.singleMenuThursdayLunch, 3);
        setLunchText(R.id.singleMenuFridayLunch, 4);

        //get the menu from the sqlite db
        //String[] menu = db.getMenu(schoolName,0);

        //set text for all textviews
        /*
        monday.setLunchText(menu[2]);
        tuesday.setLunchText(menu[3]);
        wednesday.setLunchText(menu[4]);
        thursday.setLunchText(menu[5]);
        friday.setLunchText(menu[6]);
        */
    }

    private void setLunchText(int id, int index) {
        TextView textView = (TextView) findViewById(id);
        textView.setText(getIntent().getExtras().getStringArray("menu")[index]);
    }
}