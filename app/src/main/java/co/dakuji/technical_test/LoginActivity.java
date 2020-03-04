package co.dakuji.technical_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import co.dakuji.technical_test.interf.Server;
import co.dakuji.technical_test.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity implements Server {

    TextView txtResponse;
    EditText editNick;
    EditText editPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtResponse = findViewById(R.id.textResponse);
        editNick = findViewById(R.id.nickEditText);
        editPass = findViewById(R.id.passEditText);
    }

    @Override
    public Call<User> postAuthentication(String user, String password) {
        return null;
    }

    public void authenticateUser(View target) {
        txtResponse.setText("...");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(this.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Server serverAuth = retrofit.create(Server.class);

        Call<User> call = serverAuth.postAuthentication(editNick.getText().toString(), editPass.getText().toString());
//        Call<User> call = serverAuth.postAuthentication("daniel", "Eitp34");

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                txtResponse.setText(response.code()+"");
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                txtResponse.setText("ERROR: " + t.getMessage());
            }
        });
    }
}
