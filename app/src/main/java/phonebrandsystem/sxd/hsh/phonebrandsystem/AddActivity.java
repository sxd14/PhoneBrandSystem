package phonebrandsystem.sxd.hsh.phonebrandsystem;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 添加
     */
    private AppCompatButton mBtAdd;
    private Context context = AddActivity.this;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initView();
        getSupportActionBar().setTitle("添加手机");
        getSupportActionBar().setIcon(R.mipmap.back_android_light2);
        mBtAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mEdName.getText().toString();
                String cpu = mEdCpu.getText().toString();
                String battery = mEdBattery.getText().toString();

                DBAdapter dbAdapter = new DBAdapter(context);
                Phone phone = new Phone(name,cpu,battery);
                boolean insert = dbAdapter.insert(phone);
                if (insert){
                    Toast.makeText(context, "添加成功!", Toast.LENGTH_SHORT).show();
                    setResult(666);
                    finish();
                }else {
                    Toast.makeText(context, "添加失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView() {
        mBtAdd = (AppCompatButton) findViewById(R.id.btAdd);
        mEdName = (EditText) findViewById(R.id.edName);
        mEdCpu = (EditText) findViewById(R.id.edCpu);
        mEdBattery = (EditText) findViewById(R.id.edBattery);
      //  mBtAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btAdd:
                break;
        }
    }
}
