package se.mdh.dva217.incoffeewetrust;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.*;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.Arrays;


public class ScreenSlidePageActivity extends FragmentActivity {
    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 3;




    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;

    private SquareImageView imagebutton_settings;
    private SquareImageView imagebutton_favorites;
    private SquareImageView imagebutton_search;
    private TableRow favoritesR;
    private TableRow searchR;
    private TableRow settingsR;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);

        Databasehelper db = new Databasehelper(this);

        db.addSchool("Bajsskolan");

        String[] s = db.getSchools();

        Toast.makeText(this, Arrays.toString(s),Toast.LENGTH_LONG);




        //Binds header buttons
        imagebutton_search = (SquareImageView) findViewById(R.id.imageButton_search);
        imagebutton_favorites = (SquareImageView) findViewById(R.id.imageButton_favorites);
        imagebutton_favorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mPager.setCurrentItem(0);
            }
        });
        imagebutton_search = (SquareImageView) findViewById(R.id.imageButton_search);
        imagebutton_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mPager.setCurrentItem(1);
            }
        });
        imagebutton_settings = (SquareImageView) findViewById(R.id.imageButton_settings);
        imagebutton_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mPager.setCurrentItem(2);
            }
        });

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {

            }

            @Override
            public void onPageSelected(int i) {

                settingsR =(TableRow)findViewById(R.id.settings_row);
                favoritesR = (TableRow)findViewById(R.id.favorites_row);
                searchR = (TableRow)findViewById(R.id.search_row);
                switch (i){
                    case 0:
                    {

                        settingsR.setBackgroundResource(R.color.header);
                        favoritesR.setBackgroundResource(R.color.header_active);
                        searchR.setBackgroundResource(R.color.header);
                        break;
                    }
                    case 1:
                    {
                        settingsR.setBackgroundResource(R.color.header);
                        favoritesR.setBackgroundResource(R.color.header);
                        searchR.setBackgroundResource(R.color.header_active);
                        break;
                    }
                    case 2:
                    {
                        settingsR.setBackgroundResource(R.color.header_active);
                        favoritesR.setBackgroundResource(R.color.header);
                        searchR.setBackgroundResource(R.color.header);
                        break;
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });


    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

        private final SearchFragment search = new SearchFragment();
        private final SettingsFragmentActivity settings = new SettingsFragmentActivity();
        private final FavoritesFragmentActivity favorites = new FavoritesFragmentActivity();

        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position)
            {
                case 0:
                    return favorites;
                case 1:
                    return search;
                case 2:
                    return settings;

                default:
                    return favorites;
            }
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}