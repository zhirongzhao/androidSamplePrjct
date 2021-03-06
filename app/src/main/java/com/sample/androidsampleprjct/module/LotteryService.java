package com.sample.androidsampleprjct.module;

import android.util.Log;

import com.sample.androidsampleprjct.dao.lottery.LotteryDao;
import com.sample.androidsampleprjct.util.BaseApplication;
import com.sample.androidsampleprjct.vo.Lottery;

import java.util.List;

/**
 * Created by samsung on 2016/3/23.
 */
public class LotteryService {
    LotteryDao dao;
    public LotteryService() {
        dao = BaseApplication.getDaoSession().getLotteryDao();
    }

    public long saveLottery(Lottery lottery){
        return dao.insert(lottery);
    }

    public List<Lottery> selectAll(){
        String idColumn = LotteryDao.Properties.Id.columnName;
        String orderBy = idColumn + " COLLATE LOCALIZED ASC";

        return  dao.loadAll();
    }

    public void deleteByID(long id){
        Log.d("LotteryServicexxxxxx", "log"+id);
        dao.deleteByKey(id);

    }
    public void clearData(){
        dao.deleteAll();
    }
}
