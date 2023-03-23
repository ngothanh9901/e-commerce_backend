package com.example.mediamarkbe.service.impl;

import com.example.mediamarkbe.dto.RolePayload;
import com.example.mediamarkbe.dto.UserPayload;
import com.example.mediamarkbe.model.Role;
import com.example.mediamarkbe.model.User;
import com.example.mediamarkbe.respository.RoleRepository;
import com.example.mediamarkbe.respository.UserRepository;
import com.example.mediamarkbe.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    @Override
    public User saveUser(UserPayload payload) {
        log.info("Saving new user {} to the database",payload.getUsername());

        List<Role> role = roleRepository.findAllById(payload.getRoleId());

        User user = UserPayload.convertToModel(payload);

        user.setRoles(role);

        if(StringUtils.isNotBlank(payload.getPassword())){
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(payload.getPassword());
            user.setPassword(encodedPassword);
        }

        return userRepository.save(user);
    }
    @Override
    public Role saveRole(RolePayload rolePayload) {
        log.info("Saving new role {} to the database",rolePayload.getName());
        Role role = RolePayload.convertToModel(rolePayload);
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to user {}",roleName, username);

        User user = userRepository.findByUsernameIgnoreCase(username).get();
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public User getUser(String username) {
        log.info("Fetching user {}",username);
        return userRepository.findByUsernameIgnoreCase(username).get();
    }

}
