package com.kumulus.crudpessoa.dao;

import com.kumulus.crudpessoa.model.Endereco;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.io.Serializable;
import java.util.List;

@Stateless
public class EnderecoDAO implements Serializable {
    @PersistenceContext
    private EntityManager em;

    public void salvar(Endereco endereco) {
        em.persist(endereco);
    }

    public void editar(Endereco endereco) {
        em.merge(endereco);
    }

    public void excluir(Endereco endereco) {
        em.remove(em.contains(endereco) ? endereco : em.merge(endereco));
    }

    public void excluirPorPessoaId(Integer pessoaId) {
        Endereco end = buscarPorId(pessoaId);
        if (end != null) {
            excluir(end);
        }else {
            System.out.println("Endereço não encontrado");
        }
    }

    public Endereco buscarPorId(Integer id) {
        return em.find(Endereco.class, id);
    }

    public List<Endereco> buscarPorPessoaId(Integer pessoaId) {
        return em.createQuery("SELECT e FROM Endereco e WHERE e.idPessoa = :pessoaId", Endereco.class)
                .setParameter("pessoaId", pessoaId)
                .getResultList();
    }

    public List<Endereco> buscarTodos() {
        return em.createQuery("SELECT e FROM Endereco e", Endereco.class).getResultList();
    }
}
