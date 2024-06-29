package com.kumulus.crudpessoa.dao;

import com.kumulus.crudpessoa.model.Pessoa;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.io.Serializable;
import java.util.List;

@Stateless
public class PessoaDAO implements Serializable {

    @PersistenceContext
    private EntityManager em;

    public List<Pessoa> buscarTodos() {
        return em.createQuery("SELECT DISTINCT p FROM Pessoa p JOIN FETCH p.enderecos", Pessoa.class).getResultList();
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
}
