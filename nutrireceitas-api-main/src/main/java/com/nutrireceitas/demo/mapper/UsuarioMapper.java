package com.nutrireceitas.demo.mapper;

import com.nutrireceitas.demo.dto.UsuarioDto;
import com.nutrireceitas.demo.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UsuarioMapper {
    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);
    UsuarioDto usuarioToUsuarioDto(Usuario usuario);
    Usuario usuarioDTOToUsuario(UsuarioDto usuarioDto);
}
