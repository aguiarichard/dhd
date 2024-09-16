package com.dhd.dominio;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "SEC_MATRICULA", schema = "GVDASA")
public class Matricula {

    @Id
    @Column(name = "CODIGOMATRICULA")
    private String codigoMatricula;

    @Id
    @Column(name = "CODIGOALUNO")
    private String codigoAluno;
}
