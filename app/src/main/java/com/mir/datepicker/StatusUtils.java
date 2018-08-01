package com.mir.datepicker;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * @data 2018-08-01
 * @desc
 */

public class StatusUtils {

    private Activity mActivity;
    private ViewGroup mViewGroup;
    private StatusLayout mStatusLayout;
    private RelativeLayout mRlStatus;

    static StatusUtils mStatusUtils = null;

    public StatusUtils(Activity activity){
        this.mActivity = activity;
    }

    public StatusUtils(ViewGroup viewGroup) {
        mViewGroup = viewGroup;
    }

    public static StatusUtils create(Activity activity){
        mStatusUtils = new StatusUtils(activity);
        return mStatusUtils;
    }

    public static StatusUtils create(ViewGroup viewGroup){
        mStatusUtils = new StatusUtils(viewGroup);
        return mStatusUtils;
    }

    void showLoading(){
        if (mActivity != null) {
            mRlStatus = mActivity.findViewById(R.id.rl_status);
            if (mRlStatus == null) {
                mStatusLayout = new StatusLayout(mActivity);
                mActivity.addContentView(mStatusLayout, new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            }else{
                mStatusLayout = (StatusLayout) mRlStatus.getParent();
            }
            mStatusLayout.setStatus(StatusLayout.STATUS_LOADING);
        } else if (mViewGroup != null) {
            mRlStatus = mViewGroup.findViewById(R.id.rl_status);
            if (mRlStatus == null) {
                mStatusLayout = new StatusLayout(mViewGroup.getContext());
                mViewGroup.addView(mStatusLayout, new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            }else{
                mStatusLayout = (StatusLayout) mRlStatus.getParent();
            }
            mStatusLayout.setStatus(StatusLayout.STATUS_LOADING);
        }
    }

    void hint(){
        if (mActivity != null) {
            mRlStatus = mActivity.findViewById(R.id.rl_status);
            if (mRlStatus == null) {
                mStatusLayout = new StatusLayout(mActivity);
                mActivity.addContentView(mStatusLayout, new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            }else{
                mStatusLayout = (StatusLayout) mRlStatus.getParent();
            }
            mStatusLayout.setStatus(StatusLayout.STATUS_HIDE);
        } else if (mViewGroup != null) {
            mRlStatus = mViewGroup.findViewById(R.id.rl_status);
            if (mRlStatus == null) {
                mStatusLayout = new StatusLayout(mViewGroup.getContext());
                mViewGroup.addView(mStatusLayout, new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            }else{
                mStatusLayout = (StatusLayout) mRlStatus.getParent();
            }
            mStatusLayout.setStatus(StatusLayout.STATUS_HIDE);
        }
    }

    void fail(View.OnClickListener onClickListener){
        if (mActivity != null) {
            mRlStatus = mActivity.findViewById(R.id.rl_status);
            if (mRlStatus == null) {
                mStatusLayout = new StatusLayout(mActivity);
                mActivity.addContentView(mStatusLayout, new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            }else{
                mStatusLayout = (StatusLayout) mRlStatus.getParent();
            }
            mStatusLayout.setStatus(StatusLayout.STATUS_LOAD_FAIL, onClickListener);
        } else if (mViewGroup != null) {
            mRlStatus = mViewGroup.findViewById(R.id.rl_status);
            if (mRlStatus == null) {
                mStatusLayout = new StatusLayout(mViewGroup.getContext());
                mViewGroup.addView(mStatusLayout, new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            }else{
                mStatusLayout = (StatusLayout) mRlStatus.getParent();
            }
            mStatusLayout.setStatus(StatusLayout.STATUS_LOAD_FAIL, onClickListener);
        }
    }

}
