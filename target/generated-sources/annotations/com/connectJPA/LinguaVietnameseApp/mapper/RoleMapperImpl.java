package com.connectJPA.LinguaVietnameseApp.mapper;

import com.connectJPA.LinguaVietnameseApp.dto.RoleDTO;
import com.connectJPA.LinguaVietnameseApp.entity.Role;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-13T21:50:42+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class RoleMapperImpl implements RoleMapper {

    @Override
    public RoleDTO toDto(Role entity) {
        if ( entity == null ) {
            return null;
        }

        RoleDTO.RoleDTOBuilder roleDTO = RoleDTO.builder();

        if ( entity.getRoleId() != null ) {
            roleDTO.roleId( entity.getRoleId().toString() );
        }
        roleDTO.createdAt( entity.getCreatedAt() );
        roleDTO.updatedAt( entity.getUpdatedAt() );

        return roleDTO.build();
    }

    @Override
    public Role toEntity(RoleDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Role.RoleBuilder<?, ?> role = Role.builder();

        role.createdAt( dto.getCreatedAt() );
        role.updatedAt( dto.getUpdatedAt() );
        if ( dto.getRoleId() != null ) {
            role.roleId( UUID.fromString( dto.getRoleId() ) );
        }

        return role.build();
    }
}
