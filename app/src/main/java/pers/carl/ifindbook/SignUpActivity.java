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

        getWindow().getDecorView().setSystemUiVisibility(
//                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        );

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
            //get??????????????????????????????ObjectMapper???????????????ResponseBody
            ResponseBody body = mapper.readValue(signUpTask.get(),ResponseBody.class);
            //?????????????????????????????????
            Toast.makeText(getApplicationContext(),body.getResponseCode() == 200 ? "????????????" : "????????????",Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}