package com.kumulus.crudpessoa.business;

import com.kumulus.crudpessoa.dao.PessoaDAO;
import com.kumulus.crudpessoa.dto.PessoaDTO;
import com.kumulus.crudpessoa.exception.BusinessException;
import com.kumulus.crudpessoa.model.Pessoa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class PessoaBusinessTest {

    @Mock
    private PessoaDAO pessoaDAO;

    @Mock
    private EnderecoBusiness enderecoBusiness;

    @InjectMocks
    private PessoaBusiness pessoaBusiness;
    
    private Pessoa pessoa;
    
    private List<Pessoa> pessoas;

    @BeforeEach
    public void setUp() {

        MockitoAnnotations.openMocks(this);

        pessoa = new Pessoa();
        pessoa.setId(1);
        pessoa.setNome("Nome da Pessoa");
        LocalDate localDate = LocalDate.of(1990, 1, 1);
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        pessoa.setDataNascimento(date);
        pessoa.setSexo("Masculino");
        pessoa.setEnderecos(new ArrayList<>());

        pessoas = new ArrayList<>();
        pessoas.add(pessoa);
    }

    @Test
    public void deveSalvarPessoaComSucesso() {
        pessoaBusiness.salvar(pessoa.toPessoaDTO());
        verify(pessoaDAO, times(1)).salvar(any(Pessoa.class));
    }
    @Test
    public void deveLancarExcecaoAoSalvarPessoaComErro() throws BusinessException {
        doThrow(new RuntimeException("Erro ao salvar pessoa")).when(pessoaDAO).salvar(pessoa);
        assertThrows(BusinessException.class, () -> pessoaBusiness.salvar(pessoa.toPessoaDTO()));
    }

    @Test
    public void deveEditarPessoaComSucesso() throws BusinessException {
        pessoaBusiness.editar(pessoa.toPessoaDTO());
        verify(pessoaDAO, times(1)).editar(any(Pessoa.class));
    }

    @Test
    public void deveBuscarTodasPessoasComSucesso() {
        when(pessoaDAO.buscarTodos()).thenReturn(pessoas);
        List<PessoaDTO> pessoasDTO = pessoaBusiness.buscarTodos();
        assertEquals(pessoas.size(), pessoasDTO.size());
    }

    @Test
    public void deveExcluirPessoaComSucesso() {
        pessoaBusiness.excluir(pessoa.toPessoaDTO());
        verify(enderecoBusiness, times(1)).excluirPorPessoaId(pessoa.getId());
    }

    @Test
    public void deveBuscarPessoaComSucesso() {
        when(pessoaDAO.buscarPessoa(pessoa.toPessoaDTO())).thenReturn(pessoa);
        PessoaDTO pessoaDTO = pessoaBusiness.buscarPessoa(pessoa.toPessoaDTO());
        assertEquals(pessoa.toPessoaDTO(), pessoaDTO);
    }
}
