package com.connectJPA.demo.controller;

import com.connectJPA.demo.dto.request.PermissionRequest;
import com.connectJPA.demo.dto.request.RoleRequest;
import com.connectJPA.demo.dto.response.ApiResponse;
import com.connectJPA.demo.dto.response.PermissionResponse;
import com.connectJPA.demo.dto.response.RoleResponse;
import com.connectJPA.demo.service.PermissionService;
import com.connectJPA.demo.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/roles")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class RoleController {
    RoleService roleService;

    @PostMapping
    ApiResponse<RoleResponse> create(@RequestBody RoleRequest request){
        return ApiResponse.<RoleResponse>builder()
                .result(roleService.create(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<RoleResponse>> getAll(){
        return ApiResponse.<List<RoleResponse>>builder()
                .result(roleService.getAll())
                .build();
    }

//   for Test
    @DeleteMapping("/{permission}")
    ApiResponse<Void> delete(@PathVariable String role){
        roleService.delete(role);
        return ApiResponse.<Void>builder().build();
    }

}
