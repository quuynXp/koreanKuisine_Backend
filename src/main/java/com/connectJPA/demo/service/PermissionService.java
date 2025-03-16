package com.connectJPA.demo.service;

import com.connectJPA.demo.dto.request.PermissionRequest;
import com.connectJPA.demo.dto.response.PermissionResponse;
import com.connectJPA.demo.entity.Permission;
import com.connectJPA.demo.mapper.PermissionMapper;
import com.connectJPA.demo.repository.PermissionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionService {
    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;


    public PermissionResponse create(PermissionRequest request){
        Permission permission = permissionMapper.toPermission(request);

        permission = permissionRepository.save(permission);

        return permissionMapper.toPermissionResponse(permission);

    }

    public List<PermissionResponse> getAll(){
        var permission = permissionRepository.findAll();

        return permission.stream().map(permissionMapper::toPermissionResponse).toList();
    }

    public void delete(String permission){
        permissionRepository.deleteById(permission);
    }

}
