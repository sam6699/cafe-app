package cafe.sam.uz.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cafe.sam.uz.myapplication.R;

/**
 * Created by Sam on 30.01.2019.
 */

public class Login  extends AppCompatActivity {
private boolean isValidate = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drafr_login);
        registerOnce();
        final EditText user = (EditText) findViewById(R.id.loginField);
        final EditText password = (EditText) findViewById(R.id.passwordField);
        Button signin = (Button) findViewById(R.id.sign);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateUser(user)){
                    if (validatePassword(password))
                        isValidate = true;
                }

                next(isValidate);

            }
        });

    }

    private void next(boolean start) {
            if (start){
                Intent intent = new Intent(this,cafe.sam.uz.myapplication.Main.class);
                startActivity(intent);
            }


    }

    void registerOnce(){
        SharedPreferences sp = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("login","admin");
        editor.putString("password","12345");
        //editor.putInt("tables",10);
        editor.commit();



    }
    boolean validateUser(EditText ed){

        SharedPreferences sp = getPreferences(MODE_PRIVATE);
        String login = sp.getString("login","");
        if(!login.equals(ed.getText().toString())){
            ed.setError("login hato kiritilgan");
            return false;
        }else {
            return true;
        }

    }


    boolean validatePassword(EditText ed){
        SharedPreferences sp = getPreferences(MODE_PRIVATE);
        String password = sp.getString("password","");
        if(!password.equals(ed.getText().toString())){
            ed.setError("parol hato kiritilgan");
            return false;
        }else {
            return true;
        }

    }

}
