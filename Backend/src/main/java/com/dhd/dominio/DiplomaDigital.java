package com.dhd.dominio;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "SEC_DIPLOMADIGITAL", schema = "GVDASA")
public class DiplomaDigital {

    @Id
    @Column(name = "CODIGOMATRICULA")
    private String codigoMatricula;

    @Column(name = "E_SITUACAODIPLOMA")
    private int situacaoDiploma;

    @Column(name = "CODIGODIPLOMADIGITAL")
    private int codigoDiplomaDigital;
}
