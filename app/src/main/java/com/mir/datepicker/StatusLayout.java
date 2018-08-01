package com.mir.datepicker;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.IntDef;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @data 2018-08-01
 * @desc
 */

public class StatusLayout extends RelativeLayout {

    public static final int STATUS_LOADING = 1;
    public static final int STATUS_LOAD_FAIL = 2;
    public static final int STATUS_HIDE = 3;
    private int mStatus = STATUS_LOADING;

    @IntDef({STATUS_LOADING, STATUS_LOAD_FAIL, STATUS_HIDE})
    @Retention(RetentionPolicy.SOURCE)
    @interface Status{}

    private Context mContext;
    private FrameLayout mFlFailContainer;
    private LinearLayout mLlLoading;
    private TextView mTvFailMsg;

    public StatusLayout(Context context) {
        this(context, null);
    }

    public StatusLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StatusLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.layout_status, this, true);
        mFlFailContainer = findViewById(R.id.fl_fail_container);
        mLlLoading = findViewById(R.id.ll_loading);
        mTvFailMsg = findViewById(R.id.tv_fail_msg);
    }

    /**
     * 设置多状态布局状态
     * @param status @param status {@link Status}
     */
    public void setStatus(@Status int status){
        this.mStatus = status;
        switchStatusLayout();
    }

    public void setStatus(@Status int status, OnClickListener onClickListener){
        this.mStatus = status;
        switchStatusLayout();
        mTvFailMsg.setOnClickListener(onClickListener);
    }

    private void switchStatusLayout() {
        switch (mStatus){
            case STATUS_LOADING:
                showLoading();
                break;
            case STATUS_LOAD_FAIL:
                showLoadFail();
                break;
            case STATUS_HIDE:
                hint();
                break;
        }
    }

    private void showLoadFail() {
        // 判断网络是否可用
        boolean isAvailable = networkIsAvailable(getContext());
        if (isAvailable) {
            mTvFailMsg.setText("加载失败, 点击重试");
            mTvFailMsg.setCompoundDrawablesWithIntrinsicBounds(null
                    , ContextCompat.getDrawable(mContext, R.mipmap.ic_load_fail), null, null);
        }else{
            mTvFailMsg.setText("网络异常, 点击重试");
            mTvFailMsg.setCompoundDrawablesWithIntrinsicBounds(null
                    , ContextCompat.getDrawable(mContext, R.mipmap.ic_not_network), null, null);
        }
        show();
        mLlLoading.setVisibility(GONE);
        mFlFailContainer.setVisibility(VISIBLE);
    }

    private void showLoading() {
        show();
        mFlFailContainer.setVisibility(GONE);
        mLlLoading.setVisibility(VISIBLE);
    }

    private void show(){
        setVisibility(VISIBLE);
    }

    private void hint(){
        setVisibility(GONE);
    }

    /**
     * 检测网络是否可用
     */
    public boolean networkIsAvailable(Context context) {
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (cm != null) {
                NetworkInfo info = cm.getActiveNetworkInfo();
                if (info != null && info.isConnected()) {
                    if (info.getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
