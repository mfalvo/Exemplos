package com.example.applogin;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.applogin.Api.AlunoApi;
import com.example.applogin.Api.RetrofitClient;
import com.example.applogin.models.Aluno;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        resultTextView = findViewById(R.id.resultTextView);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString().trim();
                String senha = passwordEditText.getText().toString().trim();
                if (email.isEmpty() || senha.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Por favor, preencha o email e senha!", Toast.LENGTH_SHORT).show();
                    return;
                }

                AlunoApi alunoApi = RetrofitClient.getRetrofitInstance().create(AlunoApi.class);
                //Call<Aluno> call = alunoApi.getAlunoByEmail(email);
                Call<Aluno> call = alunoApi.getAlunoByEmailAndPassword(email,senha);
                call.enqueue(new Callback<Aluno>() {
                    @Override
                    public void onResponse(Call<Aluno> call, Response<Aluno> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            Aluno aluno = response.body();
                            resultTextView.setText("Nome: " + aluno.getNome());
                        } else {
                            resultTextView.setText("Email não encontrado");
                        }
                    }

                    @Override
                    public void onFailure(Call<Aluno> call, Throwable t) {
                        resultTextView.setText("Erro: " + t.getMessage());
                    }
                });
            }
        });
    }
}