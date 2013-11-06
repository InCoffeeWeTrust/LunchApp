package se.mdh.dva217.incoffeewetrust;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

/**
 * Created with IntelliJ IDEA.
 * User: d3eniz
 * Date: 11/5/13
 * Time: 6:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class SingleMenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.singlemenu);

        TextView school = (TextView) findViewById(R.id.singleMenuSchoolname);
        school.setText(getIntent().getExtras().getString("schoolName"));

        setLunchText(R.id.singleMenuMondayLunch, 0);
        setLunchText(R.id.singleMenuTuesdayLunch, 1);
        setLunchText(R.id.singleMenuWednesdayLunch, 2);
        setLunchText(R.id.singleMenuThursdayLunch, 3);
        setLunchText(R.id.singleMenuFridayLunch, 4);
    }

    private void setLunchText(int id, int index) {


        TextView textView = (TextView) findViewById(id);
        textView.setText(getIntent().getExtras().getStringArray("menu")[index]);
    }
}