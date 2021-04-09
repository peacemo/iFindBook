package pers.carl.ifindbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.concurrent.FutureTask;

import pers.carl.ifindbook.handler.ResponseBody;
import pers.carl.ifindbook.handler.SignHandler;
import pers.carl.ifindbook.pojo.User;
import pers.carl.ifindbook.utils.DBUtils;

public class SignInActivity extends AppCompatActivity {

    private EditText edtAccount;
    private EditText edtPwd;
    private Button btnSignIn;
    private Button btnSignUp;
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        getWindow().getDecorView().setSystemUiVisibility(
//                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        );

        initView();
        setOnClickListener();
    }

    private void setOnClickListener() {
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        edtAccount = findViewById(R.id.edtAccount);
        edtPwd = findViewById(R.id.edtPwd);
        btnSignIn = findViewById(R.id.btnSignIn);
        btnSignUp = findViewById(R.id.btnSignUp);
    }

    public void signIn(View view){

        User userTemp = new User();
        User resUser = new User();

        String account = edtAccount.getText().toString();
        String pwd = edtPwd.getText().toString();
        FutureTask<String> signInTask = new FutureTask<>(new SignHandler(account, pwd, Constants.SIGN_IN));
        Thread thread = new Thread(signInTask);
        thread.start();
        try{
            //get获取线程返回值，通过ObjectMapper反序列化为User
            userTemp = mapper.readValue(signInTask.get(), User.class);
            resUser = mapper.convertValue(userTemp, new TypeReference<User>(){});
            DBUtils.getInstance().setUser(resUser);
            if (DBUtils.getUser().getId() != 0) {
                Log.e("SignAct", DBUtils.getUser().toString());
                Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                //加载书籍
                DBUtils.getInstance().initData();
                Intent intent = new Intent();
                intent.putExtra("userName", DBUtils.getUser().getNickname());
                setResult(0, intent);
                finish();
            } else {
                Toast.makeText(this, "登录失败", Toast.LENGTH_SHORT).show();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}