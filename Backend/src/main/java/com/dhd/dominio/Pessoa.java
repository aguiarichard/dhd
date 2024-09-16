package com.dhd.dominio;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "PAD_PESSOA", schema = "GVDASA")
public class Pessoa {

    @Id
    @Column(name = "CODIGOPESSOA")
    private String codigoPessoa;

    @Column(name = "NOME")
    private String nome;

    public String getCodigoPessoa() {
        return codigoPessoa;
    }

    public String getNome() {
        return nome;
    }
}
