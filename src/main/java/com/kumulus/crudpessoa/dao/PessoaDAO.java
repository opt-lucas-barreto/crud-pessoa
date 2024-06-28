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
        // Excluir os endereços associados primeiro
        em.createQuery("DELETE FROM Endereco e WHERE e.pessoa.id = :pessoaId")
                .setParameter("pessoaId", pessoa.getId())
                .executeUpdate();

        // Agora, excluir a pessoa
        em.remove(em.contains(pessoa) ? pessoa : em.merge(pessoa));
    }

    public void editar(Pessoa pessoa) {
        em.merge(pessoa);
    }
}
