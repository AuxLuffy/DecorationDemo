package com.example.administrator.decorationdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setFullDisp();
//        requestWindowFeature(Window.FEATURE_NO_TITLE);//这个必须在setContentView之前调用
        setContentView(R.layout.activity_main);
        hideActionBar();

        tv = (TextView) findViewById(R.id.tv);
        btn = (Button) findViewById(R.id.btn);
        tv.setOnClickListener(this);
        btn.setOnClickListener(this);
    }

    /**
     * 设置全屏
     */
    private void setFullDisp() {
        if (/*Build.VERSION.SDK_INT < 16*/true) {
            //方法一：使用WindowManager设置
            //设置了WindowManager的flag，将一直保持该flag的效果，除非手动重置了flag。
//            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);

            //方法二：使用activity可见的view设置
            //一旦UI Flag被清除（比如跳转到另外的activity），你需要重新设置UI flag来隐藏system bar。
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;//api16以后
            decorView.setSystemUiVisibility(uiOptions);
            // Remember that you should never show the action bar if the
            // status bar is hidden, so hide that too if necessary.
            hideActionBar();

        }
    }

    /**
     * 隐藏ActionBar
     * 不必须在setContentView之前调用
     */
    private void hideActionBar() {
        //方法1：会保留tab标签，也就是会保留actionbar的位置
//        ActionBar actionBar = getSupportActionBar();//高版本可以换成 ActionBar actionBar = getActionBar();  
//        actionBar.setDisplayShowTitleEnabled(false);
//        actionBar.setDisplayShowHomeEnabled(false);
        //方法2：不会保留tab标签 
        getSupportActionBar().hide();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv:
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                break;
            case R.id.btn:
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                break;
        }
    }
}
