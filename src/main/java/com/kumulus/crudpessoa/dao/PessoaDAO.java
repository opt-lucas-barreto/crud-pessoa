package com.kumulus.crudpessoa.dao;

import com.kumulus.crudpessoa.model.Pessoa;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class PessoaDAO {

    @PersistenceContext
    private EntityManager em;

    public List<Pessoa> buscarTodos() {
        return em.createQuery("SELECT p FROM Pessoa p", Pessoa.class).getResultList();
    }

    public void salvar(Pessoa pessoa) {
    }
}
