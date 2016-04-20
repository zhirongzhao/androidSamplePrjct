package com.sample.androidsampleprjct.module;

import android.util.Log;

import com.sample.androidsampleprjct.dao.lottery.SSQExtVODao;
import com.sample.androidsampleprjct.module.http.BaiduAPIService;
import com.sample.androidsampleprjct.util.BaseApplication;
import com.sample.androidsampleprjct.vo.SSQ;
import com.sample.androidsampleprjct.vo.SSQExtVO;
import com.sample.androidsampleprjct.vo.remote.LotteryRemoteVO;

import java.util.Date;
import java.util.List;

import de.greenrobot.dao.query.WhereCondition;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by samsung on 2016/4/11.
 */
public class SSQExtService {
    private  static final String TAG = "SSQExtService_LOG";
    SSQExtVODao dao;

    public SSQExtService() {
        dao = BaseApplication.getDaoSession().getSSQExtVODao();
    }

    public long addSSQQueue(SSQExtVO data){
       return  dao.insert(data);
    }
    public List<SSQExtVO> findSSQByExpt(String expt){
        return dao.queryBuilder().where(SSQExtVODao.Properties.Expect.eq(expt)).list();
    }
    public List<SSQExtVO> findEarlyList(int num){
        return dao.queryBuilder().orderDesc(SSQExtVODao.Properties.Expect).limit(num).list();
    }
    public String getMaxExpect(){
        List<SSQExtVO> maxExpects = dao.queryBuilder().where(new WhereCondition.StringCondition("EXPECT IN (SELECT MAX(EXPECT) FROM LT_SSQ_REMOTE)")).list();
        return Integer.parseInt(maxExpects.get(0).getExpect())+1+"";
    }
    public void initExtSSQ(){
        SSQExtService service = new SSQExtService();
        BaiduAPIService baiduAPIService = new BaiduAPIService();
        Observable<LotteryRemoteVO> observable =  baiduAPIService.lotteryquery("ssq","10");
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(lotteryRemoteVO -> {
                    Log.d(TAG, "http: "+lotteryRemoteVO.getRetMsg());
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
    }
}
