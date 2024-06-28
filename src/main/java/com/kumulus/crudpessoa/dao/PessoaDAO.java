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
        return em.createQuery("SELECT p FROM Pessoa p", Pessoa.class).getResultList();
    }

    public void salvar(Pessoa pessoa) {
    }

    public void excluir(Pessoa pessoa) {
        em.createQuery("DELETE FROM Pessoa p WHERE p.id = :id")
                .setParameter("id", pessoa.getId())
                .executeUpdate();
    }
}
