package com.sample.androidsampleprjct.util.http;

import com.sample.androidsampleprjct.vo.remote.LotteryRemoteVO;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by samsung on 2016/4/7.
 */
public interface BaiduAPI {
    @Headers("apikey:0d0eada0634d008b561731e2c9ded4d2")
    @GET("/apistore/lottery/lotteryquery")
    Observable<LotteryRemoteVO>lotteryquery(@Query("lotterycode") String owner, @Query("recordcnt") String repo);


    @Headers("apikey:0d0eada0634d008b561731e2c9ded4d2")
    @GET("/apistore/lottery/lotteryquery")
    Call<LotteryRemoteVO> lotteryquerySync(@Query("lotterycode") String owner, @Query("recordcnt") String repo);


}
