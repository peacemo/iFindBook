package pers.carl.ifindbook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.concurrent.FutureTask;

import pers.carl.ifindbook.handler.ResponseBody;
import pers.carl.ifindbook.handler.SignHandler;

public class SignUpActivity extends AppCompatActivity {

    private EditText edtAccount;
    private EditText edtPwd;
    private EditText edtNickname;
    private EditText edtEmail;
    private Button btnReg;
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initView();
    }

    private void initView() {
        edtAccount = findViewById(R.id.edtSignUpAccount);
        edtPwd = findViewById(R.id.edtSignUpPwd);
        edtNickname = findViewById(R.id.edtNickname);
        edtEmail = findViewById(R.id.edtEmail);
        btnReg = findViewById(R.id.btnRegister);
    }

    public void signUp (View view) {
        String account = edtAccount.getText().toString();
        String nickname = edtNickname.getText().toString();
        String pwd = edtPwd.getText().toString();
        String email = edtEmail.getText().toString();

        SignHandler signHandler = new SignHandler(account, pwd, nickname, email, Constants.SIGN_UP);
        Log.e("Sign up: ", signHandler.toString());

        FutureTask<String> signUpTask = new FutureTask<>(signHandler);
//        FutureTask<String> signUpTask = new FutureTask<>(new SignHandler(account, pwd, nickname, email, Constants.SIGN_UP));
        Thread thread = new Thread(signUpTask);
        thread.start();
        try{
            //get获取线程返回值，通过ObjectMapper反序列化为ResponseBody
            ResponseBody body = mapper.readValue(signUpTask.get(),ResponseBody.class);
            //根据返回码确定提示信息
            Toast.makeText(getApplicationContext(),body.getResponseCode() == 200 ? "注册成功" : "注册失败",Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}