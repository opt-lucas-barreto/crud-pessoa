package com.kumulus.crudpessoa.bean;

import com.kumulus.crudpessoa.business.PessoaBusiness;
import com.kumulus.crudpessoa.dto.EnderecoDTO;
import com.kumulus.crudpessoa.dto.PessoaDTO;
import com.kumulus.crudpessoa.utils.Mensagens;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.ejb.EJBException;
import jakarta.faces.FacesException;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Named
@ViewScoped
public class PessoaBean implements Serializable {

    @EJB
    private PessoaBusiness pessoaBusiness;

    @Getter @Setter
    private List<PessoaDTO> pessoas;

    @Getter @Setter
    private PessoaDTO pessoaSelecionada;

    @PostConstruct
    public void init() {
        pessoaSelecionada = new PessoaDTO();
        pessoas = pessoaBusiness.buscarTodos();
    }


    public void editar() {
        pessoaBusiness.editar(pessoaSelecionada);
        // Atualize sua lista de pessoas, se necessário
        pessoas = pessoaBusiness.buscarTodos();
    }

    public void excluir() {
        try {
            if(pessoaSelecionada == null || pessoaSelecionada.getId() == null) {
                throw new FacesException("Selecione uma pessoa");
            }
            pessoaBusiness.excluir(pessoaSelecionada);
            pessoas = pessoaBusiness.buscarTodos();
        }catch(Exception e){
            Mensagens.criarMensagem(FacesMessage.SEVERITY_ERROR, "Erro ao excluir pessoa", e.getMessage());
        }
    }

    public void novo() {
        pessoaSelecionada = new PessoaDTO();
        pessoaSelecionada.setEnderecos(List.of(new EnderecoDTO()));
    }

    public void adicionar() {
        if(validarPessoaDTO(pessoaSelecionada)) {
            pessoaBusiness.salvar(pessoaSelecionada);
            pessoas = pessoaBusiness.buscarTodos();
       }
    }

    private Boolean validarPessoaDTO(PessoaDTO pessoaDTO) {
        boolean erro = false;
        if (pessoaDTO.getNome() == null || pessoaDTO.getNome().isEmpty() || pessoaDTO.getNome().length() > 20) {
            Mensagens.criarMensagem(FacesMessage.SEVERITY_ERROR, "Erro ao salvar pessoa","Nome inválido: O nome não pode ser vazio e deve ter no máximo 20 caracteres.");
            erro = true;
        }

        if (pessoaDTO.getDataNascimento() == null || pessoaDTO.getDataNascimento().after(java.sql.Date.valueOf(LocalDate.now()))) {
            Mensagens.criarMensagem(FacesMessage.SEVERITY_ERROR, "Erro ao salvar pessoa","Data de Nascimento inválida: A data de nascimento não pode ser nula e deve ser anterior à data atual.");
            erro = true;
        }

        if (pessoaDTO.getSexo() == null || (!pessoaDTO.getSexo().equals("M") && !pessoaDTO.getSexo().equals("F"))) {
            Mensagens.criarMensagem(FacesMessage.SEVERITY_ERROR, "Erro ao salvar pessoa","Sexo inválido: O sexo deve ser 'M' para masculino ou 'F' para feminino.");
            erro = true;
        }
        return !erro;
    }

}