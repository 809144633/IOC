package com.zj.ioc;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

@BindContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {
    @BindViews(R.id.btn1)
    private Button btn1;

    @BindViews(R.id.btn2)
    private Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        btn1.setText("第一个");
        btn2.setText("第二个");
    }


    @BindClick({R.id.btn1, R.id.btn2})
    public void onClick(View view) {
        Toast.makeText(this, view.getId() + "", Toast.LENGTH_SHORT).show();
    }

}
