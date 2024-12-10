package com.example.login.AlunoRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.login.model.Aluno;

public interface AlunoRepository extends CrudRepository<Aluno, String>  {

	@Query("SELECT u FROM Aluno u WHERE u.email = :email AND u.senha = :password") 
    Aluno findByEmailAndPassword(@Param("email") String email, @Param("password") String password);  
}
