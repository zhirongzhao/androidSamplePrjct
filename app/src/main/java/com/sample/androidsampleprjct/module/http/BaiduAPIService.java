package com.sample.androidsampleprjct.module.http;

import com.sample.androidsampleprjct.util.http.BaiduAPI;
import com.sample.androidsampleprjct.vo.remote.LotteryRemoteVO;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Observable;

/**
 * Created by samsung on 2016/4/7.
 */
public class BaiduAPIService {
    public static final String API_URL = "http://apis.baidu.com";
    public Observable<LotteryRemoteVO> lotteryquery(String lotterycode, String recordcnt){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        BaiduAPI baiduAPI = retrofit.create(BaiduAPI.class);
        return baiduAPI.lotteryquery("ssq","10");
    }

    public Call<LotteryRemoteVO> lotteryquerySync(String lotterycode, String recordcnt){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        BaiduAPI baiduAPI = retrofit.create(BaiduAPI.class);
        return baiduAPI.lotteryquerySync("ssq","10");
    }
}
