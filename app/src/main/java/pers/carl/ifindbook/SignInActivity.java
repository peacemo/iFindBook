package pers.carl.ifindbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.concurrent.FutureTask;

import pers.carl.ifindbook.handler.ResponseBody;
import pers.carl.ifindbook.handler.SignHandler;

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
        String account = edtAccount.getText().toString();
        String pwd = edtPwd.getText().toString();
        FutureTask<String> signInTask = new FutureTask<>(new SignHandler(account, pwd, Constants.SIGN_IN));
        Thread thread = new Thread(signInTask);
        thread.start();
        try{
            //get获取线程返回值，通过ObjectMapper反序列化为ResponseBody
            ResponseBody body = mapper.readValue(signInTask.get(),ResponseBody.class);
            //根据返回码确定提示信息
            Toast.makeText(getApplicationContext(),body.getResponseCode() == 200 ? "登录成功" : "登录失败",Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}