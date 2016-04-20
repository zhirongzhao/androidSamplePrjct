package com.sample.androidsampleprjct.service;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.sample.androidsampleprjct.R;
import com.sample.androidsampleprjct.module.BullService;
import com.sample.androidsampleprjct.module.SSQExtService;
import com.sample.androidsampleprjct.module.http.BaiduAPIService;
import com.sample.androidsampleprjct.ui.BullActivity;
import com.sample.androidsampleprjct.ui.BullListActivity;
import com.sample.androidsampleprjct.util.Util;
import com.sample.androidsampleprjct.vo.SSQ;
import com.sample.androidsampleprjct.vo.SSQExtVO;
import com.sample.androidsampleprjct.vo.remote.LotteryRemoteVO;

import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class SSQService extends IntentService {

    private  static final String TAG = "SSQService_LOG";
    private static final String ACTION_FETCH_SSQ_NET = "com.sample.androidsampleprjct.service.action.SSQService";
    private static final String ACTION_BAZ = "com.sample.androidsampleprjct.service.action.BAZ";

    // TODO: Rename parameters
    private static final String EXTRA_PARAM1 = "com.sample.androidsampleprjct.service.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "com.sample.androidsampleprjct.service.extra.PARAM2";

    public SSQService() {
        super("SSQService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionSSQ(Context context, String param1, String param2) {
        Intent intent = new Intent(context, SSQService.class);
        intent.setAction(ACTION_FETCH_SSQ_NET);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionBaz(Context context, String param1, String param2) {
        Intent intent = new Intent(context, SSQService.class);
        intent.setAction(ACTION_BAZ);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_FETCH_SSQ_NET.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionSSQ(param1, param2);
            } else if (ACTION_BAZ.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionBaz(param1, param2);
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionSSQ(String param1, String param2) {
        Log.d(TAG, "in handleActionSSQ: param1"+param1+" param2:"+param2);

        Timer timer = new Timer();
        SSQExtService service = new SSQExtService();
        BullService bullService = new BullService();
        timer.schedule(new TimerTask() {


            @Override
            public void run()  {
                Log.d(TAG, "onNavigationItemSelected: ininin");
                BaiduAPIService baiduAPIService = new BaiduAPIService();
                Observable<LotteryRemoteVO> observable =  baiduAPIService.lotteryquery("ssq","10");
                observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                        .subscribe(lotteryRemoteVO -> {
                            Log.d(TAG, "onNavigationItemSelected: "+lotteryRemoteVO.getRetMsg());
                            List<LotteryRemoteVO.RetDataBean.DataBean> datas = lotteryRemoteVO.getRetData().getData();
                            for (LotteryRemoteVO.RetDataBean.DataBean dataBean : datas) {
                                List<SSQExtVO> ssqExtVOs = service.findSSQByExpt(dataBean.getExpect());
                                if(ssqExtVOs.size()==0){
                                    service.addSSQQueue(new SSQExtVO(null,
                                            dataBean.getExpect()
                                            ,dataBean.getOpenCode()
                                            ,dataBean.getOpenTime()
                                            ,dataBean.getOpenTimeStamp()
                                              ,"N",new Date()));
                                    Log.d(TAG, "run: sync 1 Record");

                                }
                            }
                        });

                List<SSQ> cadidateSSQList = bullService.findSSQByIsWin(getString(R.string.str_ssqNotOpen));
                Log.d(TAG, "onNavigationItemSelected: isWin cadidate"+cadidateSSQList.size());
                for (SSQ ssq : cadidateSSQList) {
                    List<SSQExtVO> ssqExtVOs = service.findSSQByExpt(ssq.getExpect());

                    if(ssqExtVOs.size()>0)
                        if (Util.compareSSQ(ssq, ssqExtVOs.get(0).getOpenCode())) {
                            Log.d(TAG, "onNavigationItemSelected: " + ssq.toString() + "  --- " + ssqExtVOs.get(0).getOpenCode());
                            ssq.setIsWin(getString(R.string.str_Win));
                            bullService.updateISWin(ssq);

                            NotificationCompat.Builder builder = new NotificationCompat.Builder(SSQService.this);

                            builder.setSmallIcon(R.drawable.ic_announcement_black_18dp);
                            builder.setContentTitle(getString(R.string.str_winNotifiy));
                            builder.setContentText(ssq.getExpect()+getString(R.string.str_winPleaseCheck));

                            Intent intent = new Intent(SSQService.this, BullActivity.class);
                            PendingIntent pendingIntent = PendingIntent.getActivity(SSQService.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                            builder.setContentIntent(pendingIntent);

                            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                            notificationManager.notify(1, builder.build());

                        } else {
                            Log.d(TAG, "onNavigationItemSelected: " + ssq.toString() + "  --- " + ssqExtVOs.get(0).getOpenCode());
                            ssq.setIsWin(getString(R.string.bullIsWin));
                            bullService.updateISWin(ssq);
                        }
                }

            }
        },1000,Long.parseLong(param1));
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
