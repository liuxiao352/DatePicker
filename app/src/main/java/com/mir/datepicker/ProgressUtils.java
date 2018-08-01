package com.mir.datepicker;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * @data 2018-08-01
 * @desc
 */

public class ProgressUtils {

    private static AlertDialog dialog;

    public static void show(Context context){
        show(context, null, false);
    }

    public static void show(Context context, String msg, boolean cancelable){
        TextView tvMsg;
        View view = LayoutInflater.from(context).inflate(R.layout.layout_loading_progress, null);
        tvMsg = view.findViewById(R.id.tv_msg);
        dialog = new AlertDialog.Builder(context)
                .setView(view)
                .setCancelable(cancelable)
                .create();
        dialog.show();
        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        layoutParams.width = dp2px(context, 300f);
        dialog.getWindow().setAttributes(layoutParams);
        tvMsg.setText(msg == null ? "正在加载中..." : msg);
   }

   public static void dissmiss(){
       if (dialog != null && dialog.isShowing()) {
           dialog.dismiss();
       }
   }

    private static int dp2px(Context context,float dpValue){
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dpValue * scale + 0.5f);
    }
}
