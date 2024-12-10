package com.example.applogin.Api;

import com.example.applogin.models.Aluno;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AlunoApi {
    @GET("alunos/{email}")
    Call<Aluno> getAlunoByEmail(@Path("email") String email);

    @GET("alunos/{email}/{senha}")
    Call<Aluno> getAlunoByEmailAndPassword(@Path("email") String email, @Path("senha") String senha);

}