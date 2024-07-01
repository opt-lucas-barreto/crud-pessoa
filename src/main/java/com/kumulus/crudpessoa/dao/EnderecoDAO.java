package com.kumulus.crudpessoa.dao;

import com.kumulus.crudpessoa.dto.EnderecoDTO;
import com.kumulus.crudpessoa.model.Endereco;
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

    public Endereco buscarEndereco(EnderecoDTO enderecoSelecionado) {
        TypedQuery<Endereco> query = em.createQuery("SELECT e FROM Endereco e WHERE e.cep = :cep AND e.logradouro = :logradouro AND e.bairro = :bairro AND e.cidade = :cidade AND e.estado = :uf AND e.idPessoa = :pessoaId", Endereco.class);
        query.setParameter("cep", enderecoSelecionado.getCep().replaceAll("[^0-9]", ""));
        query.setParameter("logradouro", enderecoSelecionado.getLogradouro());
        query.setParameter("bairro", enderecoSelecionado.getBairro());
        query.setParameter("cidade", enderecoSelecionado.getCidade());
        query.setParameter("uf", enderecoSelecionado.getEstado());
        query.setParameter("pessoaId", enderecoSelecionado.getIdPessoa());
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }

    }
}
