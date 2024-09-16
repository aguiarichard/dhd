package com.dhd.dominio;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "SEC_HISTORICOESCOLAR", schema = "GVDASA")
public class HistoricoEscolar {
    @Id
    @Column(name = "CODIGOMATRICULA")
    private String codigoMatricula;

    @Column(name = "E_SITUACAOHISTORICO")
    private int situacaoHistorico;

    @Column(name = "CODIGOHISTORICOESCOLAR")
    private int codigoHistoricoEscolar;

    public String getCodigoMatricula() {
        return codigoMatricula;
    }

    public int getSituacaoHistorico() {
        return situacaoHistorico;
    }

    public int getCodigoHistoricoEscolar() {
        return codigoHistoricoEscolar;
    }
}
