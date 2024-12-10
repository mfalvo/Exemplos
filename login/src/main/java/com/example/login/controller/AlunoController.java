package com.example.login.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.login.AlunoRepository.AlunoRepository;
import com.example.login.model.Aluno;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    @GetMapping
    public Iterable<Aluno> getAllAlunos() {
        return alunoRepository.findAll();
    }

    @GetMapping("/{email}")
    public Optional<Aluno> getAlunoByEmail(@PathVariable String email) {
        return alunoRepository.findById(email);
    }
    
    @GetMapping("/{email}/{senha}")
    public Aluno getAlunoByEmailAndPassword(@PathVariable String email, @PathVariable String senha ) {
        return alunoRepository.findByEmailAndPassword(email, senha);
    }
    

    @PostMapping
    public Aluno createAluno(@RequestBody Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    @PutMapping("/{email}")
    public Aluno updateAluno(@PathVariable String email, @RequestBody Aluno aluno) {
        aluno.setEmail(email);
        return alunoRepository.save(aluno);
    }

    @DeleteMapping("/{email}")
    public void deleteAluno(@PathVariable String email) {
        alunoRepository.deleteById(email);
    }
}