package com.sample.androidsampleprjct.ui;

import android.content.Context;
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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.AwesomeTextView;
import com.beardedhen.androidbootstrap.BootstrapButton;
import com.sample.androidsampleprjct.R;
import com.sample.androidsampleprjct.module.LotteryService;
import com.sample.androidsampleprjct.vo.Lottery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        datalist.add(getString(R.string.code1));
        datalist.add(getString(R.string.code2));
        datalist.add(getString(R.string.code3));
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
        BootstrapButton btnDelete;
        BootstrapButton btnCustomBut;
        List<Map<String, Object>> items;

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
                    initLotteryRecommend(rootView);
                    break;
                default:
                    rootView = inflater.inflate(R.layout.fragment_lottery, container, false);
            }

            return rootView;
        }

        private void initLotteryRecommend(View rootView) {


        }




















        private void initMyLottery(View rootView) {



            spinnerLotteryCategory = (Spinner) rootView.findViewById(R.id.spinner_lotteryCategory);

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, datalist);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerLotteryCategory.setAdapter(adapter);

            butSave = (Button) rootView.findViewById(R.id.butSave);
            butSave.setOnClickListener(this);

            btnCustomBut = (BootstrapButton) rootView.findViewById(R.id.btnCustomBut);
            btnCustomBut.setOnClickListener(v -> {
                Log.d("PlaceholderFragment", "aaaaaaaaaaaa");
                Toast.makeText(getContext(), "https://github.com/Bearded-Hen/Android-Bootstrap", Toast.LENGTH_SHORT).show();
            });
            btnCustomBut.setOnClickListener(this);

            spinner2 = (EditText) rootView.findViewById(R.id.spinner2);

            listView = (ListView) rootView.findViewById(R.id.listView);
            button = (Button) rootView.findViewById(R.id.button);
            button.setOnClickListener(v->{
                service.clearData();
                eSelectAll();
            });
            eSelectAll();
        }

        private void eOnSaveClick() {
            Lottery lottery = new Lottery();
            lottery.setLotteryCode("fdsafdd");
            lottery.setCandidateCode(spinner2.getText().toString());
            lottery.setExpect(spinnerLotteryCategory.getSelectedItemPosition() + "");
            long result =  service.saveLottery(lottery);
            if (result>0){
                eSelectAll();
            }

        }

        private void eSelectAll() {
            List<Lottery> lotteryList = service.selectAll();
//            ArrayAdapter arrayAdapter = new ArrayAdapter<Lottery>(
//                    getActivity()
//                    ,android.R.layout.simple_list_item_1
//                    ,lotteryList);
//            listView.setAdapter(arrayAdapter);

            items = new ArrayList<>();
            for (Lottery lottery : lotteryList) {
                Map<String, Object> map = new HashMap<>();
                map.put("id", lottery.getId());
                map.put("expct", lottery.getExpect());
                map.put("code", lottery.getLotteryCode());
                map.put("candiNumber", lottery.getCandidateCode());
                map.put("win", lottery.getIsWin());
                items.add(map);
            }

//            SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(), items, R.layout.fragment_lottery_list_column
//                    , new String[]{"id", "expct", "code", "candiNumber", "win"}
//                    , new int[]{R.id.txtId,R.id.txtExpect,R.id.txtCode,R.id.txtCadiNum,R.id.txtWin}
//            );
//            listView.setAdapter(simpleAdapter);
            LotteryAdapter lotteryAdapter =  new LotteryAdapter(getContext());
            listView.setAdapter(lotteryAdapter);
        }


        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.butSave:
                    eOnSaveClick();

                    break;
                case R.id.btnCustomBut:
                    Toast.makeText(getContext(), "https://github.com/Bearded-Hen/Android-Bootstrap", Toast.LENGTH_SHORT).show();
                    break;

            }
        }

        @Override
        public void onDestroyView() {
            super.onDestroyView();
            ButterKnife.unbind(this);
        }
        class ViewHodler{
            public AwesomeTextView txtID;
            public AwesomeTextView txtExpect;
            public AwesomeTextView txtCadiNum;
            public AwesomeTextView txtCode;
            public AwesomeTextView txtWin;
            public BootstrapButton btnDelete;

        }
        class  LotteryAdapter extends BaseAdapter{
            private LayoutInflater mInflater;

            public LotteryAdapter(Context context){
                this.mInflater = LayoutInflater.from(context);
            }
            @Override
            public int getCount() {
                return items.size();
            }

            @Override
            public Object getItem(int position) {
                return items.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                ViewHodler holder = null;
                if(convertView == null){
                    convertView = mInflater.inflate(R.layout.fragment_lottery_list_column,null);
                    holder = new ViewHodler();
                    holder.txtID = (com.beardedhen.androidbootstrap.AwesomeTextView) convertView.findViewById(R.id.txtId);
                    holder.btnDelete = (com.beardedhen.androidbootstrap.BootstrapButton) convertView.findViewById(R.id.btnDelete);
                    holder.txtCadiNum = (com.beardedhen.androidbootstrap.AwesomeTextView) convertView.findViewById(R.id.txtCadiNum);
                    holder.txtExpect = (com.beardedhen.androidbootstrap.AwesomeTextView) convertView.findViewById(R.id.txtExpect);
                    holder.txtWin = (com.beardedhen.androidbootstrap.AwesomeTextView) convertView.findViewById(R.id.txtWin);
                    holder.txtCode = (com.beardedhen.androidbootstrap.AwesomeTextView) convertView.findViewById(R.id.txtCode);
                    convertView.setTag(holder);
                }else{
                    holder = (ViewHodler) convertView.getTag();
                }

                holder.txtID.setText(items.get(position).get("id")+"");
                holder.txtExpect.setText((String) items.get(position).get("expct"));
                holder.txtCadiNum.setText((String) items.get(position).get("candiNumber"));
                holder.txtWin.setText((String) items.get(position).get("win"));
                holder.txtCode.setText((String) items.get(position).get("code"));

                holder.btnDelete.setOnClickListener(
                        v->{
                            service.deleteByID((Long) items.get(position).get("id"));
                            eSelectAll();
                        }
                );

                return convertView;
            }
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
                    return "GreenDao+ListView";
                case 1:
                    return "OrmLite+RecyclerView";

//                case 2:
//                    return "推荐选号";
            }
            return null;
        }
    }



}
