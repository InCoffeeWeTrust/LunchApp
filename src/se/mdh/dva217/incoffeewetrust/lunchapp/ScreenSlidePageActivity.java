package se.mdh.dva217.incoffeewetrust.lunchapp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.*;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import se.mdh.dva217.incoffeewetrust.db.DBAdapter;


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);

        //Binds header buttons
        imagebutton_search = (SquareImageView) findViewById(R.id.imageButton_search);
        imagebutton_favorites = (SquareImageView) findViewById(R.id.imageButton_favorites);
        imagebutton_settings = (SquareImageView) findViewById(R.id.imageButton_settings);


        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getFragmentManager());
        mPager.setAdapter(mPagerAdapter);


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
                    return search;
            }
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}