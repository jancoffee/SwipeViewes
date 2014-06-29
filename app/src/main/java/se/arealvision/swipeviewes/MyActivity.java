package se.arealvision.swipeviewes;

import java.util.Locale;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v13.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;


public class MyActivity extends Activity {

    private static final int NR_OF_PAGES = 3;
    private static final String LOGTAG = "SwipeViews";
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v13.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LOGTAG,"onCreate");
        setContentView(R.layout.activity_my);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d(LOGTAG, "onPageScrolled position:"+position+ " positionOffset:"+positionOffset+ " positionOffsetPixels:"+positionOffsetPixels);
                //Will be called even if we don't change the Page!
            }

            @Override
            public void onPageSelected(int position) {
                Log.d(LOGTAG, "onPageSelected position:"+position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.d(LOGTAG, "onPageScrollStateChanged state:"+state);
                //Will be called even if we don't change the Page

                switch(state){
                    case ViewPager.SCROLL_STATE_IDLE: Log.d(LOGTAG,"SCROLL_STATE_IDLE"); break;
                    case ViewPager.SCROLL_STATE_DRAGGING: Log.d(LOGTAG,"SCROLL_STATE_DRAGGING"); break;
                    case ViewPager.SCROLL_STATE_SETTLING: Log.d(LOGTAG,"SCROLL_STATE_SETTLING"); break;
                    default:
                        Log.e(LOGTAG, "onPageScrollStateChanged, unknown state:" + state);
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOGTAG,"onPause");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Log.d(LOGTAG,"getItem pos:"+position);
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show nr total pages.
            return NR_OF_PAGES;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Log.d(LOGTAG,"getPageTitle pos:"+position);
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section3).toUpperCase(l);
            }
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        private TextView label;

        public static PlaceholderFragment newInstance(int sectionNumber) {
            Log.d(LOGTAG,"newInstance sectionNumber:"+sectionNumber);
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
            Log.d(LOGTAG,"PlaceholderFragment");
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
            Log.d(LOGTAG,"onCreateView");
            int id = getArguments().getInt(ARG_SECTION_NUMBER,-1);

            View rootView = inflater.inflate(R.layout.fragment_my, container, false);
            label = (TextView)rootView.findViewById(R.id.section_label);
            label.setText(String.valueOf(id));
            return rootView;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Log.d(LOGTAG,"PlaceholderFragment onCreate "+savedInstanceState);

        }
    }

}
