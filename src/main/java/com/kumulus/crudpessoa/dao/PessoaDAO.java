package com.kumulus.crudpessoa.dao;

import com.kumulus.crudpessoa.dto.PessoaDTO;
import com.kumulus.crudpessoa.model.Pessoa;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.io.Serializable;
import java.util.List;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class PessoaDAO implements Serializable {

    @PersistenceContext
    private EntityManager em;

    public List<Pessoa> buscarTodos() {
        return em.createQuery("SELECT DISTINCT p FROM Pessoa p LEFT JOIN FETCH p.enderecos", Pessoa.class).getResultList();
    }

    public void salvar(Pessoa pessoa) {

        em.persist(pessoa);
    }

    public void excluir(Pessoa pessoa) {
        em.remove(em.contains(pessoa) ? pessoa : em.merge(pessoa));
    }

    public void editar(Pessoa pessoa) {
        em.merge(pessoa);
    }

    public Pessoa buscarPessoa(PessoaDTO pessoaSelecionada) {
        TypedQuery<Pessoa> query = em.createQuery("SELECT p FROM Pessoa p WHERE p.nome = :nome AND p.dataNascimento = :dataNascimento AND p.sexo = :sexo", Pessoa.class);
        query.setParameter("nome", pessoaSelecionada.getNome());
        query.setParameter("dataNascimento", pessoaSelecionada.getDataNascimento());
        query.setParameter("sexo", pessoaSelecionada.getSexo());
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
