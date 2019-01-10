package phonebrandsystem.sxd.hsh.phonebrandsystem;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    /**
     * 手机型号:
     */
    private EditText mEdName;
    /**
     * 处理器(CPU)
     */
    private EditText mEdCpu;
    /**
     * 电池容量
     */
    private EditText mEdBattery;
    /**
     * 确定修改
     */
    private AppCompatButton mBtOk;
    private Context context = UpdateActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        initView();
        getSupportActionBar().setTitle("修改信息");


        Intent intent = getIntent();
        final Phone phone = (Phone) intent.getSerializableExtra("message");

        mEdName.setText(phone.getName());
        mEdCpu.setText(phone.getCpu());
        mEdBattery.setText(phone.getBattery());

        mBtOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mEdName.getText().toString();
                String cpu = mEdCpu.getText().toString();
                String battery = mEdBattery.getText().toString();

                DBAdapter dbAdapter = new DBAdapter(context);
                Phone newPhone = new Phone(name, cpu, battery);
                boolean update = dbAdapter.update(phone, newPhone);
                if (update) {
                    Toast.makeText(context, "修改成功!", Toast.LENGTH_SHORT).show();
                    setResult(666);
                    finish();
                } else {
                    Toast.makeText(context, "修改失败", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void initView() {
        mEdName = (EditText) findViewById(R.id.edName);
        mEdCpu = (EditText) findViewById(R.id.edCpu);
        mEdBattery = (EditText) findViewById(R.id.edBattery);
        mBtOk = (AppCompatButton) findViewById(R.id.btOk);
    }
}
