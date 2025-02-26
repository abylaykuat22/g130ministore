package com.example.g130ministore.mapper;

import com.example.g130ministore.dto.UserRegisterRequest;
import com.example.g130ministore.entity.User;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(builder = @Builder(disableBuilder = true), unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toEntity(UserRegisterRequest dto);
}
