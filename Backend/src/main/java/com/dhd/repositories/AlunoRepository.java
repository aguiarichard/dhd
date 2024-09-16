package com.dhd.repositories;

import com.dhd.dominio.Aluno;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, String> {

    @Query("SELECT CONCAT(p.nome, ' - ', a.numeroAluno) " +
            "FROM Aluno a " +
            "JOIN Pessoa p ON p.codigoPessoa = a.codigoPessoa " +
            "ORDER BY p.nome")
    Page<String> findNomeNumeroAluno(Pageable pageable);


    @Query("SELECT CONCAT(p.nome, ' - ', a.numeroAluno) " +
            "FROM Aluno a " +
            "JOIN Pessoa p ON p.codigoPessoa = a.codigoPessoa " +
            "WHERE UPPER(p.nome) LIKE UPPER(CONCAT('%', :nome, '%')) " +
            "ORDER BY p.nome")
    Page<String> findNomeNumeroAlunoByNome(@Param("nome") String nome, Pageable pageable);

    @Transactional
    @Modifying
    @Query("UPDATE HistoricoEscolar his SET his.situacaoHistorico = :situacao " +
            "WHERE his.codigoHistoricoEscolar IN (" +
            "   SELECT his.codigoHistoricoEscolar " +
            "   FROM HistoricoEscolar his " +
            "   INNER JOIN Matricula m ON m.codigoMatricula = his.codigoMatricula " +
            "   INNER JOIN Aluno a ON a.codigoAluno = m.codigoAluno" +
            "   INNER JOIN Pessoa p ON p.codigoPessoa = a.codigoPessoa" +
            "   WHERE UPPER(p.nome) LIKE UPPER(:nome)" +
            ")")
    int atualizarSituacaoHistorico(@Param("nome") String nome, @Param("situacao") int situacao);

    @Transactional
    @Modifying
    @Query("UPDATE DiplomaDigital dig " +
            "SET dig.situacaoDiploma = :situacao " +
            "WHERE dig.codigoDiplomaDigital IN (" +
            "   SELECT dig.codigoDiplomaDigital " +
            "   FROM DiplomaDigital his " +
            "   INNER JOIN Matricula m ON m.codigoMatricula = dig.codigoMatricula " +
            "   INNER JOIN Aluno a ON a.codigoAluno = m.codigoAluno " +
            "   INNER JOIN Pessoa p ON p.codigoPessoa = a.codigoPessoa " +
            "   WHERE UPPER(p.nome) LIKE UPPER(:nome)" +
            ")")
    int atualizarSituacaoDiploma(@Param("nome") String nome, @Param("situacao") int situacao);
}
