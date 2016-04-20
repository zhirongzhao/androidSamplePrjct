package com.sample.androidsampleprjct.module;

import android.util.Log;

import com.sample.androidsampleprjct.dao.lottery.SSQDao;
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
 * Created by samsung on 2016/4/5.
 */
public class BullService {

    private  static final String TAG = "BullService_LOG";
    SSQDao dao;

    public BullService() {
        dao = BaseApplication.getDaoSession().getSSQDao();
    }

    public  long saveSSQ(SSQ ssq){
        return  dao.insert(ssq);
    }
    public List<SSQ> getSSSQ(){
        return dao.loadAll();
    }
    public void removeSSQByID(long id){
        dao.deleteByKey(id);
    }
    public void removeAll(){
        dao.deleteAll();
    }

    public List<SSQ> findSSQByIsWin(String isWin){
       return dao.queryBuilder().where(SSQDao.Properties.IsWin.eq(isWin)).list();
    }
    public void updateISWin(SSQ ssq){
        dao.update(ssq);
    }




}
