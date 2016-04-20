package com.sample.androidsampleprjct.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.github.aakira.expandablelayout.Utils;
import com.sample.androidsampleprjct.R;
import com.sample.androidsampleprjct.module.BullService;
import com.sample.androidsampleprjct.module.SSQExtService;
import com.sample.androidsampleprjct.ui.dummy.ItemModel;
import com.sample.androidsampleprjct.ui.dummy.RecyclerViewRecyclerAdapter;
import com.sample.androidsampleprjct.util.DemoPagerAdapter;
import com.sample.androidsampleprjct.util.DividerItemDecoration;
import com.sample.androidsampleprjct.util.Util;
import com.sample.androidsampleprjct.vo.SSQ;
import com.sample.androidsampleprjct.vo.SSQExtVO;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.relex.circleindicator.CircleIndicator;


public class BullActivity extends AppCompatActivity {
    private static final String TAG = "BullActivity:::";
    @Bind(R.id.btn_8)
    Button btn8;
    @Bind(R.id.btn_9)
    Button btn9;
    @Bind(R.id.btn_10)
    Button btn10;
    @Bind(R.id.btn_11)
    Button btn11;
    @Bind(R.id.btn_12)
    Button btn12;
    @Bind(R.id.btn_13)
    Button btn13;
    @Bind(R.id.btn_14)
    Button btn14;
    @Bind(R.id.btn_15)
    Button btn15;
    @Bind(R.id.btn_16)
    Button btn16;
    @Bind(R.id.btn_17)
    Button btn17;
    @Bind(R.id.btn_18)
    Button btn18;
    @Bind(R.id.btn_19)
    Button btn19;
    @Bind(R.id.btn_20)
    Button btn20;
    @Bind(R.id.btn_21)
    Button btn21;
    @Bind(R.id.btn_22)
    Button btn22;
    @Bind(R.id.btn_23)
    Button btn23;
    @Bind(R.id.btn_24)
    Button btn24;
    @Bind(R.id.btn_25)
    Button btn25;
    @Bind(R.id.btn_26)
    Button btn26;
    @Bind(R.id.btn_27)
    Button btn27;
    @Bind(R.id.btn_28)
    Button btn28;
    @Bind(R.id.btn_29)
    Button btn29;
    @Bind(R.id.btn_30)
    Button btn30;
    @Bind(R.id.btn_31)
    Button btn31;
    @Bind(R.id.btn_32)
    Button btn32;
    @Bind(R.id.btn_33)
    Button btn33;
    @Bind(R.id.but_blu_1)
    Button butBlu1;
    @Bind(R.id.but_blu_2)
    Button butBlu2;
    @Bind(R.id.but_blu_3)
    Button butBlu3;
    @Bind(R.id.but_blu_4)
    Button butBlu4;
    @Bind(R.id.but_blu_5)
    Button butBlu5;
    @Bind(R.id.but_blu_6)
    Button butBlu6;
    @Bind(R.id.but_blu_7)
    Button butBlu7;
    @Bind(R.id.but_blu_8)
    Button butBlu8;
    @Bind(R.id.but_blu_9)
    Button butBlu9;
    @Bind(R.id.but_blu_10)
    Button butBlu10;
    @Bind(R.id.but_blu_11)
    Button butBlu11;
    @Bind(R.id.but_blu_12)
    Button butBlu12;
    @Bind(R.id.but_blu_13)
    Button butBlu13;
    @Bind(R.id.but_blu_14)
    Button butBlu14;
    @Bind(R.id.but_blu_15)
    Button butBlu15;
    @Bind(R.id.but_blu_16)
    Button butBlu16;
    @Bind(R.id.button22)
    Button button22;

    @Bind(R.id.btn_1)
    Button btn1;
    @Bind(R.id.btn_2)
    Button btn2;
    @Bind(R.id.btn_3)
    Button btn3;
    @Bind(R.id.btn_4)
    Button btn4;
    @Bind(R.id.btn_5)
    Button btn5;
    @Bind(R.id.btn_6)
    Button btn6;
    @Bind(R.id.btn_7)
    Button btn7;
    @Bind(R.id.viewpager)
    ViewPager viewpager;
    @Bind(R.id.indicator)
    CircleIndicator indicator;
    @Bind(R.id.btn_search_ssq)
    BootstrapButton btnSearchSsq;
    @Bind(R.id.but_blu_17)
    Button butBlu17;
    @Bind(R.id.but_blu_18)
    Button butBlu18;
    @Bind(R.id.but_blu_19)
    Button butBlu19;
    @Bind(R.id.but_blu_20)
    Button butBlu20;

    private Map<Integer, Integer> choiseMap = new TreeMap<>();
    private Map<Integer, Integer> bluChoiseMap = new HashMap<>();
    BullService service;
    SSQExtService ssqExtService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bull);
        ButterKnife.bind(this);
        service = new BullService();
        ssqExtService = new SSQExtService();
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.openedList);
        recyclerView.addItemDecoration(new DividerItemDecoration(this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ssqExtService.initExtSSQ();
        List<SSQExtVO> resultList = ssqExtService.findEarlyList(5);
        final List<ItemModel> data = new ArrayList<>();
        SSQExtVO firstResult = resultList.get(0);
        String descripDetail = "";
        for (int i = 1; i <resultList.size() ; i++) {
            SSQExtVO result = resultList.get(i);
            descripDetail += result.getExpect()+getString(R.string.str_qi)+result.getOpenCode()+" \n ";
        }
        data.add(new ItemModel(
                firstResult.getExpect()+getString(R.string.str_qi)+firstResult.getOpenCode(),
                R.color.material_red_500,
                R.color.material_red_300,
                Utils.createInterpolator(Utils.ACCELERATE_DECELERATE_INTERPOLATOR),descripDetail));
        recyclerView.setAdapter(new RecyclerViewRecyclerAdapter(data));

    }

    @Override
    protected void onResume() {
        super.onResume();
        viewpager.setAdapter(new DemoPagerAdapter());
        indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(viewpager);
    }

    public SSQ makSsq(Map<Integer, Integer> choiseMap, Map<Integer, Integer> bluChoiseMap, SSQ ssq) {
        int i = 0;
        for (Integer integer : choiseMap.values()) {
            i++;
            switch (i) {
                case 1:
                    ssq.setRed1(integer);
                    break;
                case 2:
                    ssq.setRed2(integer);
                    break;
                case 3:
                    ssq.setRed3(integer);
                    break;
                case 4:
                    ssq.setRed4(integer);
                    break;
                case 5:
                    ssq.setRed5(integer);
                    break;
                case 6:
                    ssq.setRed6(integer);
                    break;
                case 7:
                    ssq.setRed7(integer);
                    break;
                case 8:
                    ssq.setRed8(integer);
                    break;

            }
        }

        for (Integer integer : bluChoiseMap.values()) {
            ssq.setBlu(integer);
        }

        return ssq;
    }
    public boolean checkSave(){
        if(choiseMap.size()!=6){
            Toast.makeText(this, R.string.str_choiseSixRed, Toast.LENGTH_SHORT).show();
            return false;
        }else if (bluChoiseMap.size()!=1){
            Toast.makeText(this, R.string.str_choiseOenBul, Toast.LENGTH_SHORT).show();
            return false ;
        }

        return true;
    }
    public void onSaveClick(View view) {
        if(checkSave()) {
            SSQ ssq = new SSQ();
            ssq = makSsq(choiseMap, bluChoiseMap, ssq);
            Log.d(TAG, "onSaveClick: " + ssq);
            Log.d(TAG, "onSaveClick: " + ssqExtService.getMaxExpect());
            ssq.setExpect(ssqExtService.getMaxExpect());
            ssq.setRegDate(Util.getNow());
            ssq.setIsWin(getString(R.string.str_ssqNotOpen));
            long result = service.saveSSQ(ssq);
            if (result > 0) {
                Toast.makeText(this, R.string.saveSuccess, Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void onDeleteClick(View view){
        service.removeAll();
    }

    public void onRedClick(View view) {
        Log.d(TAG, "onClick: " + view.getBackground());
        Button but = (Button) view;
        if (choiseMap.get(view.getId()) == null) {

            choiseMap.put(view.getId(), Integer.parseInt(but.getText().toString()));
            but.setBackgroundResource(R.drawable.bg_special_disease_circle);
            but.setTextColor(getResources().getColor(R.color.white));
        } else {
            choiseMap.remove(view.getId());
            Log.d(TAG, "onClick: " + choiseMap.toString());
            but.setBackgroundResource(R.drawable.bg_special_disease_ring);
            but.setTextColor(getResources().getColor(R.color.red));
        }
    }

    public void onBluClick(View view) {
        Log.d(TAG, "onClick: " + view.getBackground());
        Button but = (Button) view;
        if (bluChoiseMap.get(view.getId()) == null) {
            bluChoiseMap.put(view.getId(), Integer.parseInt(but.getText().toString()));
            but.setBackgroundResource(R.drawable.bg_special_disease_circle_blue);
            but.setTextColor(getResources().getColor(R.color.white));
        } else {
            bluChoiseMap.remove(view.getId());
            Log.d(TAG, "onClick: " + bluChoiseMap.toString());
            but.setBackgroundResource(R.drawable.bg_special_disease_ring_blue);
            but.setTextColor(getResources().getColor(R.color.colorPrimary));
        }
    }

    @OnClick(R.id.btn_search_ssq)
    public void onClick() {
        Log.d(TAG, "onClick: xxxxx");
        startActivity(new Intent(this,BullListActivity.class));
    }
}
