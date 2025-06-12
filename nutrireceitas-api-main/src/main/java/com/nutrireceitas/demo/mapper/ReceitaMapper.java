package com.nutrireceitas.demo.mapper;

import com.nutrireceitas.demo.dto.ReceitaDto;
import com.nutrireceitas.demo.model.Receita;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ReceitaMapper {
    ReceitaMapper INSTANCE = Mappers.getMapper(ReceitaMapper.class);

    @Mapping(source = "usuario.id", target = "usuarioId")
    ReceitaDto receitaToReceitaDTO(Receita receita);
    @Mapping(source = "usuarioId", target = "usuario.id")
    Receita receitaDTOToReceita(ReceitaDto receitaDto);
}
