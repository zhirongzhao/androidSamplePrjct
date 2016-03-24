package com.sample.androidsampleprjct.vo.remote;

import java.util.Date;
import java.util.List;

/**
 * Created by samsung on 2016/3/23.
 */
public class LotteryRemoteVO {
    /**
     * errNum : 0
     * retMsg : success
     * retData : {"recordCnt":"1","lotteryCode":"dlt","data":[{"expect":"2015063","openCode":"01,04,06,34,35+02,04","openTime":"2015-06-03 20:38:00","openTimeStamp":"1433335080000"}]}
     */
    private int errNum;
    private String retMsg;
    /**
     * recordCnt : 1
     * lotteryCode : dlt
     * data : [{"expect":"2015063","openCode":"01,04,06,34,35+02,04","openTime":"2015-06-03 20:38:00","openTimeStamp":"1433335080000"}]
     */

    private RetDataBean retData;

    public int getErrNum() {
        return errNum;
    }

    public void setErrNum(int errNum) {
        this.errNum = errNum;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public RetDataBean getRetData() {
        return retData;
    }

    public void setRetData(RetDataBean retData) {
        this.retData = retData;
    }

    public static class RetDataBean {
        private String recordCnt;
        private String lotteryCode;
        /**
         * expect : 2015063
         * openCode : 01,04,06,34,35+02,04
         * openTime : 2015-06-03 20:38:00
         * openTimeStamp : 1433335080000
         */

        private List<DataBean> data;

        public String getRecordCnt() {
            return recordCnt;
        }

        public void setRecordCnt(String recordCnt) {
            this.recordCnt = recordCnt;
        }

        public String getLotteryCode() {
            return lotteryCode;
        }

        public void setLotteryCode(String lotteryCode) {
            this.lotteryCode = lotteryCode;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            private String expect;
            private String openCode;
            private String openTime;
            private String openTimeStamp;

            public String getExpect() {
                return expect;
            }

            public void setExpect(String expect) {
                this.expect = expect;
            }

            public String getOpenCode() {
                return openCode;
            }

            public void setOpenCode(String openCode) {
                this.openCode = openCode;
            }

            public String getOpenTime() {
                return openTime;
            }

            public void setOpenTime(String openTime) {
                this.openTime = openTime;
            }

            public String getOpenTimeStamp() {
                return openTimeStamp;
            }

            public void setOpenTimeStamp(String openTimeStamp) {
                this.openTimeStamp = openTimeStamp;
            }
        }
    }
}
