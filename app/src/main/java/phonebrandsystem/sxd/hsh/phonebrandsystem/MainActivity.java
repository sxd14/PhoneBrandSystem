package phonebrandsystem.sxd.hsh.phonebrandsystem;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mLv;

    private IBaseAdapter iAdapter;
    private List<Phone> dataList;
    private Context context = MainActivity.this;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra("user");
        if (user != null) {
            String name = user.getName();
            if (name != null) {
                Toast.makeText(context, "欢迎" + name, Toast.LENGTH_SHORT).show();
            }
        }


        initView();
        initData();

        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(context, AddActivity.class), 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1) {
            if (resultCode == 666) {
                iAdapter.refresh();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void initData() {

        DBAdapter dbAdapter = new DBAdapter(context);
        dataList = dbAdapter.query();
        iAdapter = new IBaseAdapter(this, dataList);
        mLv.setAdapter(iAdapter);
    }

    private void initView() {
        mLv = (ListView) findViewById(R.id.lv);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.help:
                dialog = new AlertDialog.Builder(context)
                        .setTitle("说明")
                        .setMessage("单机每条列表可以删除或者修改")
                        .show();
                break;
            case R.id.zhuxiao:
                SharedPreferences sp = getSharedPreferences("message", MODE_PRIVATE);
                sp.edit().putBoolean("login", false).apply();
                startActivity(new Intent(context, LoginActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
