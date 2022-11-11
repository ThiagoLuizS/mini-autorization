package br.com.miniautorization.models.mapper;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapStructMapper<T, View, Form> {

    View entityToView(T t);

    T formToEntity(Form form);

}
