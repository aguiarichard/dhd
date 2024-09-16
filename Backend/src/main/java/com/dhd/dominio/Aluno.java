package com.dhd.dominio;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "SEC_ALUNO", schema = "GVDASA")
public class Aluno {

    @Id
    @Column(name = "CODIGOPESSOA")
    private String codigoPessoa;

    @Column(name = "NUMEROALUNO")
    private String numeroAluno;

    @Column(name = "CODIGOALUNO")
    private String codigoAluno;

    public String getCodigoPessoa() {
        return codigoPessoa;
    }

    public String getNumeroAluno() {
        return numeroAluno;
    }
}
