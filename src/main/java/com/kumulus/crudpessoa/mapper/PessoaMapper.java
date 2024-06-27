package com.kumulus.crudpessoa.mapper;

import com.kumulus.crudpessoa.dto.PessoaDTO;
import com.kumulus.crudpessoa.model.Pessoa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PessoaMapper {

    PessoaMapper INSTANCE = Mappers.getMapper(PessoaMapper.class);

    PessoaDTO pessoaToPessoaDTO(Pessoa pessoa);

    Pessoa pessoaDTOToPessoa(PessoaDTO pessoaDTO);

    List<PessoaDTO> pessoaDTOToPessoaList(List<Pessoa> pessoas);
}
