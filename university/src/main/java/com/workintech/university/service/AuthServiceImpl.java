package com.workintech.university.service;

import com.workintech.university.entity.Role;
import com.workintech.university.entity.User;
import com.workintech.university.exceptions.UserAlreadyRegisteredException;
import com.workintech.university.repository.RoleRepository;
import com.workintech.university.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService{

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public User register(String email, String password) {
        //Check if the user email exists.
        Optional<User> userOptional = userRepository.findByEmail(email);

        if(userOptional.isPresent())
            throw new UserAlreadyRegisteredException("Email already registered");

        //encode password
        String encodedPassword = passwordEncoder.encode(password);

        //find role with USER authority
        Optional<Role> userRole = roleRepository.findAuthority("USER");

        //if role with USER authority doesn't exist, create one
        if(userRole.isEmpty()){
            Role role = new Role();
            role.setAuthority("USER");

            userRole = Optional.of(roleRepository.save(role));
        }

        //create new user
        User user = new User();
        user.setEmail(email);
        user.setPassword(encodedPassword);
        user.setRoles(Set.of(userRole.get()));

        //save new user
        return userRepository.save(user);
    }
}
