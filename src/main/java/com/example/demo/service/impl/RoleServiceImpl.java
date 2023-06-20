package com.example.demo.service.impl;

import com.example.demo.model.Role;
import com.example.demo.model.RoleName;
import com.example.demo.repository.IRoleRepository;
import com.example.demo.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    IRoleRepository roleRepository;

    @Override
    public Optional<Role> findByName(RoleName name) {
        return roleRepository.findByName(name);
    }

    @Override
    public Iterable<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public void setDefaultRoleName() {
        Iterable<Role> roles = findAll();
        long roleSize = roles.spliterator().getExactSizeIfKnown();
        if (roleSize == 0) {
            List<Role> roleList = new ArrayList<>();
            roleList.add(new Role(1L, RoleName.USER));
            roleList.add(new Role(2L, RoleName.PM));
            roleList.add(new Role(3L, RoleName.ADMIN));
            roleRepository.saveAll(roleList);
        }
    }
}
