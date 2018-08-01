package com.mir.datepicker;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private FrameLayout mFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFrameLayout = findViewById(R.id.llt_content);
        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogUtils.showDialog(MainActivity.this, "温馨提示", "确定要清除内存吗?", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, "确定", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogUtils.showEditDialog(MainActivity.this, "昵称", "请输入昵称", new DialogUtils.OnEdtDialogClickListener() {
                    @Override
                    public void onEditDialog(String content) {
                        Toast.makeText(MainActivity.this, content, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        findViewById(R.id.btn3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String[] sexs = {"男", "女"};
                DialogUtils.showItemDialog(MainActivity.this, "性别", sexs, new DialogUtils.OnItemDialogClickListener() {
                    @Override
                    public void onItemDialog(int i) {
                        Toast.makeText(MainActivity.this, "选择了 : " + sexs[i], Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        findViewById(R.id.btn4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressUtils.show(MainActivity.this);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ProgressUtils.dissmiss();
                    }
                }, 2000);
            }
        });

        findViewById(R.id.btn5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //mFrameLayout 要展示多状态的布局返回
                StatusUtils.create(mFrameLayout).showLoading(); //设置加载中状态
            }
        });

        findViewById(R.id.btn6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StatusUtils.create(mFrameLayout).fail(new View.OnClickListener() { //设置加载失败状态
                    @Override
                    public void onClick(View view) {
                        StatusUtils.create(mFrameLayout).showLoading();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                StatusUtils.create(mFrameLayout).hint();
                            }
                        }, 2000);
                    }
                });
            }
        });

        findViewById(R.id.btn7).setOnClickListener(new View.OnClickListener() { //隐藏多状态布局, 显示主布局
            @Override
            public void onClick(View view) {
                StatusUtils.create(mFrameLayout).hint();
            }
        });


    }
}
