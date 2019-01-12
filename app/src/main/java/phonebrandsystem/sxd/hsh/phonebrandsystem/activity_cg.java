package phonebrandsystem.sxd.hsh.phonebrandsystem;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class activity_cg extends AppCompatActivity {
    private Button bt_main_jr,bt2;
    private TextView tv_main_yhm;
    private TextView tv_main_mm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitycg_main);
        Intent intent=getIntent();
        String name=intent.getStringExtra("name");
        String password=intent.getStringExtra("password");
        TextView tv_main_yhm = (TextView) findViewById(R.id.tv_main_yhm);
        TextView tv_main_mm = (TextView) findViewById(R.id.tv_main_mm);
         bt_main_jr=findViewById(R.id.bt_main_jr);
         bt2=findViewById(R.id.tv_main_fh);
        if (name !=null&&password!=null) {
            tv_main_yhm.setText("用户名：" + name);
            tv_main_mm.setText("密码：" + password);
            bt_main_jr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                }
            });
        }
            bt2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent();
                    intent.setClass(getApplicationContext(),activity_zc.class);
                    startActivity(intent);
                }
            });


    }
}
