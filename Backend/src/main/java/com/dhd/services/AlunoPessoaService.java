package com.dhd.services;

import com.dhd.repositories.AlunoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AlunoPessoaService {

    @Autowired
    private AlunoRepository alunoRepository;


    public Page<String> getNomeNumeroAluno(Pageable pageable) {
        return alunoRepository.findNomeNumeroAluno(pageable);
    }

    public Page<String> getNomeNumeroAlunoByNome(String nome, Pageable pageable) {
        return alunoRepository.findNomeNumeroAlunoByNome(nome, pageable);
    }

    public void atualizarHistorico(String nome, int situacao) {

        int rowsUpdated = alunoRepository.atualizarSituacaoHistorico(nome, situacao);

        System.out.println("Rows updated Historico: " + rowsUpdated);
    }

    public void atualizarDiploma(String nome, int situacao) {

        int rowsUpdated = alunoRepository.atualizarSituacaoDiploma(nome, situacao);

        System.out.println("Rows updated Diploma: " + rowsUpdated);
    }
}