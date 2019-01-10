        package phonebrandsystem.sxd.hsh.phonebrandsystem;

        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;

        public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

            /**
             * 登录
             */
            private Button btLogin;
            /**
             * 账号
             */
            private EditText edName;
            /**
             * 密码
             */
            private EditText  edPass;

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                SharedPreferences sp = getSharedPreferences("message", MODE_PRIVATE);
                boolean login = sp.getBoolean("login", false);
                if (login) {


                    Intent intent = new Intent(this, MainActivity.class);
                    User user = new User();
                    user.setName(sp.getString("name", ""));
                    intent.putExtra("user", user);
                    startActivity(intent);
                    finish();
                }

                setContentView(R.layout.activity_login);
                initView();


            }
//

            private void initView() {
                btLogin = (Button) findViewById(R.id.btLogin);
                btLogin.setOnClickListener(this);
                edName = (EditText) findViewById(R.id.edName);
                edPass = (EditText) findViewById(R.id.edPass);
            }

            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    default:
                        break;
                    case R.id.btLogin:
                        String name = edName.getText().toString();
                        String pass = edPass.getText().toString();

                        SharedPreferences sp = getSharedPreferences("message", MODE_PRIVATE);
                        sp.edit().putBoolean("login", true).apply();
                        sp.edit().putString("name", name).apply();
                        Intent intent = new Intent(this, MainActivity.class);
                        User user = new User();
                        user.setName(name);
                        user.setPassword(pass);
                        intent.putExtra("user", user);
                        startActivity(intent);
                        finish();
                        break;
                }
            }
        }

