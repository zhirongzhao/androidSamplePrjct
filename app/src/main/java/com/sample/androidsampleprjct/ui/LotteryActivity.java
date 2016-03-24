package com.sample.androidsampleprjct.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.sample.androidsampleprjct.R;
import com.sample.androidsampleprjct.service.LotteryService;
import com.sample.androidsampleprjct.vo.Lottery;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LotteryActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    static ArrayList<String> datalist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottery);

        datalist.add(getString(R.string.str_lotteryDatafucai));
        datalist.add(getString(R.string.str_lottaryTicai));
        datalist.add(getString(R.string.str_lottaryZucai));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lottery, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment implements View.OnClickListener {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        TextView tvLotteryCategory;
        Spinner spinnerLotteryCategory;
        TextView textView3;
        EditText spinner2;
        Button button;
        Button butSave;
        ListView listView;
        LotteryService service = new LotteryService();
        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView;

            switch (getArguments().getInt(ARG_SECTION_NUMBER)) {
                case 1:
                    rootView = inflater.inflate(R.layout.fragment_lottery, container, false);
                    initMyLottery(rootView);
                    break;
                case 2:
                    rootView = inflater.inflate(R.layout.fragment_lottery_recommend, container, false);
                    break;
                default:
                    rootView = inflater.inflate(R.layout.fragment_lottery, container, false);
            }

            ButterKnife.bind(this, rootView);
            return rootView;
        }

        private void initMyLottery(View rootView) {
            spinnerLotteryCategory = (Spinner) rootView.findViewById(R.id.spinner_lotteryCategory);

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, datalist);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerLotteryCategory.setAdapter(adapter);

            butSave = (Button) rootView.findViewById(R.id.butSave);
            butSave.setOnClickListener(this);

            spinner2 = (EditText) rootView.findViewById(R.id.spinner2);

            listView = (ListView) rootView.findViewById(R.id.listView);
        }

        private void eOnSaveClick() {
            Lottery lottery = new Lottery();
            lottery.setLotteryCode("fdsafdd");
            lottery.setCandidateCode(spinner2.getText().toString());
            lottery.setExpect(spinnerLotteryCategory.getSelectedItemPosition()+"");
            service.saveLottery(lottery);

        }
        private void eSelectAll(){
            List<Lottery> lotteryList = service.selectAll();
            ArrayAdapter arrayAdapter = new ArrayAdapter<Lottery>(
                    getActivity()
                    ,android.R.layout.simple_list_item_1
                    ,lotteryList);
            listView.setAdapter(arrayAdapter);
        }
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.butSave:
                    eOnSaveClick();
                    eSelectAll();
                    break;
            }
        }

        @Override
        public void onDestroyView() {
            super.onDestroyView();
            ButterKnife.unbind(this);
        }
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
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
//            Fragment fragment ;
//            switch (position){
//                case 0:
//                    fragment = ItemFragment.newInstance(position);
//                    break;
//                case 1:
//                    fragment =  ItemFragment.newInstance(position);
//                    break;
//                default:
//                    fragment =  ItemFragment.newInstance(position);
//            }
//            return fragment;
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "GreenDao+Grid";
                case 1:
                    return "";
//                case 2:
//                    return "推荐选号";
            }
            return null;
        }
    }
}
