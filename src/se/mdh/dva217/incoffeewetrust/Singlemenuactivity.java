package se.mdh.dva217.incoffeewetrust;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;
import se.mdh.dva217.incoffeewetrust.db.DatabaseHelper;

/**
 * Created with IntelliJ IDEA.
 * User: d3eniz
 * Date: 11/5/13
 * Time: 6:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class Singlemenuactivity extends Activity {

    String schoolName;
    TextView school;
    TextView monday;
    TextView tuesday;
    TextView wednesday;
    TextView thursday;
    TextView friday;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.singlemenu);

        //bind all controlls
        school = (TextView) findViewById(R.id.singleMenuSchoolname);
        monday = (TextView) findViewById(R.id.singleMenuMondayLunch);
        tuesday = (TextView) findViewById(R.id.singleMenuTuesdayLunch);
        wednesday = (TextView) findViewById(R.id.singleMenuWednesdayLunch);
        thursday = (TextView) findViewById(R.id.singleMenuThursdayLunch);
        friday = (TextView) findViewById(R.id.singleMenuFridayLunch);

        //create databasehelper with this context
        db = new DatabaseHelper(this);

        //get schoolname, passed with intent
        schoolName = getIntent().getExtras().getString("schoolName");

        //set schoolname
        school.setText(schoolName);

        //get the menu from the sqlite db
        String[]menu = db.getMenu(schoolName,0);

        //set text for all textviews
        monday.setText(menu[2]);
        tuesday.setText(menu[3]);
        wednesday.setText(menu[4]);
        thursday.setText(menu[5]);
        friday.setText(menu[6]);
    }
}
