package com.dhd.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
public class AlunoPessoaController {


    @Autowired
    private AlunoPessoaService alunoPessoaService;

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @GetMapping("/alunos")
    public Page<String> getNomeNumeroAlunoByNome(@RequestParam(value = "nome", required = false) String nome,
                                                 @RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);

        if (nome != null && !nome.isEmpty()) {
            System.out.println(alunoPessoaService.getNomeNumeroAlunoByNome(nome, pageable));
            return alunoPessoaService.getNomeNumeroAlunoByNome(nome, pageable);
        }

        System.out.println(alunoPessoaService.getNomeNumeroAluno(pageable));
        return alunoPessoaService.getNomeNumeroAluno(pageable);
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @PostMapping("/atualizar-historico")
    public void atualizarSituacaoHistorico(@RequestParam String nome, @RequestParam int situacao) {
        System.out.println(nome);
        alunoPessoaService.atualizarHistorico(nome, situacao);
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @PostMapping("/atualizar-diploma")
    public void atualizarSituacaoDiploma(@RequestParam String nome, @RequestParam int situacao) {
        System.out.println(nome);
        alunoPessoaService.atualizarDiploma(nome, situacao);
    }
}
