package com.sample.androidsampleprjct.ui;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.widget.TextView;
import android.widget.Toast;

import com.sample.androidsampleprjct.R;
import com.sample.androidsampleprjct.module.BullService;
import com.sample.androidsampleprjct.vo.SSQ;
import com.tubb.smrv.SwipeMenuLayout;
import com.tubb.smrv.SwipeMenuRecyclerView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BullListActivity extends AppCompatActivity {
    static final String TAG = "BullListActivity";

    @Bind(R.id.SSQListView)
    SwipeMenuRecyclerView ssqListView;
    @Bind(R.id.SSQSwipeRefreshLayout)
    SwipeRefreshLayout ssqSwipeRefreshLayout;

    private AppAdapter mAdapter;
    List<SSQ> ssqDataList;
    private Context mContext;

    BullService service ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bull_list);
        ButterKnife.bind(this);

        mContext = this;
        ssqSwipeRefreshLayout.setOnRefreshListener(()->{

            ssqSwipeRefreshLayout.setRefreshing(false);
        });
        ssqListView.addItemDecoration(new VerticalSpaceItemDecoration(3));
        ssqListView.setLayoutManager(new LinearLayoutManager(this));
        ssqListView.setOpenInterpolator(new BounceInterpolator());
        ssqListView.setCloseInterpolator(new BounceInterpolator());
        service = new BullService();
        ssqDataList = service.getSSSQ();
        mAdapter = new AppAdapter(this, ssqDataList);
        ssqListView.setAdapter(mAdapter);

    }
    class AppAdapter extends RecyclerView.Adapter {

        private static final int VIEW_TYPE_ENABLE = 0;
        private static final int VIEW_TYPE_DISABLE = 1;
        List<SSQ> ssqDataList;
        public AppAdapter(Context context, List<SSQ> ssqDataList) {
            this.ssqDataList = ssqDataList;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(mContext).inflate(R.layout.activity_bull_list_item_simple_nest, parent, false);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            final SSQ ssq = ssqDataList.get(position);
            final MyViewHolder myViewHolder = (MyViewHolder)holder;
            View itemView = myViewHolder.itemView;

            Log.d(TAG, "onBindViewHolder: ssq.getBlu"+ssq.getBlu());

            myViewHolder.ssqBlu.setText(ssq.getBlu()+"");
            myViewHolder.ssqCadidate.setText(ssq.getExpect()+"");
            myViewHolder.ssqIswin.setText(ssq.getIsWin()+"");
            String redText = ssq.getRed1()+","+ssq.getRed2()+","+ssq.getRed3()+","+ssq.getRed4()+","+ssq.getRed5()+","+ssq.getRed6()+",";
            myViewHolder.ssqRed.setText(redText);
            myViewHolder.ssqTime.setText(ssq.getRegDate()+"");
            myViewHolder.btDelete.setOnClickListener((v)->{
                service.removeSSQByID(ssq.getId());
                ssqDataList.remove(myViewHolder.getAdapterPosition());
                notifyItemRemoved(myViewHolder.getAdapterPosition());

            });

            myViewHolder.swipeMenuLayout.setSwipeEnable(true);
            myViewHolder.swipeMenuLayout.setOpenInterpolator(ssqListView.getOpenInterpolator());
            myViewHolder.swipeMenuLayout.setCloseInterpolator(ssqListView.getCloseInterpolator());
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public int getItemCount() {
            return ssqDataList.size();
        }

        @Override
        public int getItemViewType(int position) {
            SSQ ssq = ssqDataList.get(position);
            return VIEW_TYPE_ENABLE;
        }

        public boolean swipeEnableByViewType(int viewType) {
            if(viewType == VIEW_TYPE_ENABLE) return true;
            else if(viewType == VIEW_TYPE_DISABLE) return false;
            else return true; // default
        }
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView ssqRed;
        TextView ssqBlu;
        TextView ssqCadidate;
        TextView ssqIswin;
        TextView ssqTime;
        View btOpen;
        View btDelete;
        SwipeMenuLayout swipeMenuLayout;
        public MyViewHolder(View itemView) {
            super(itemView);
            ssqRed = (TextView) itemView.findViewById(R.id.ssqRedNumber);
            ssqBlu = (TextView) itemView.findViewById(R.id.ssqBluNumber);
            ssqCadidate = (TextView) itemView.findViewById(R.id.ssqCondidate);
            ssqIswin = (TextView) itemView.findViewById(R.id.ssqIsWin);
            ssqTime = (TextView) itemView.findViewById(R.id.ssqTime);
            btDelete = itemView.findViewById(R.id.btDelete);
            swipeMenuLayout = (SwipeMenuLayout) itemView.findViewById(R.id.sml);
        }
    }
}

class VerticalSpaceItemDecoration extends RecyclerView.ItemDecoration{

    private final int mVerticalSpaceHeight;

    public VerticalSpaceItemDecoration(int mVerticalSpaceHeight) {
        this.mVerticalSpaceHeight = mVerticalSpaceHeight;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        outRect.bottom = mVerticalSpaceHeight;
    }

}
