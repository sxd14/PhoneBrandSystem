package phonebrandsystem.sxd.hsh.phonebrandsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class activity_zc extends AppCompatActivity {
    private Button tb_main_zc;
    private EditText ab_main_qs;
    private EditText ab_main_qsr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityzc_main);
        ab_main_qs=(EditText) findViewById(R.id.ab_main_qs);
        ab_main_qsr=(EditText) findViewById(R.id.ab_main_qsr);
        Button bt_main_ff = (Button) findViewById(R.id.bt_main_ff);
        bt_main_ff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account=ab_main_qs.getText().toString().trim();
                String password=ab_main_qsr.getText().toString().trim();
                passDate();
                Toast.makeText(getApplicationContext(),"注册成功",Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void passDate(){
        Intent intent=new Intent(this,activity_cg.class);
        intent.putExtra("name",ab_main_qs.getText().toString().trim());
        intent.putExtra("password",ab_main_qsr.getText().toString().trim());
        startActivity(intent);
    }
}
