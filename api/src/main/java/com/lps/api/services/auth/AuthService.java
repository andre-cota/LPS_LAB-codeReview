package com.lps.api.services.auth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lps.api.models.Student;
import com.lps.api.models.User;
import com.lps.api.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AuthService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("Usuário não encontrado com o email: " +
                    email);
        }

        List<GrantedAuthority> authorities = new ArrayList<>();

        if (user instanceof Student)
            authorities.add(new SimpleGrantedAuthority("isStudent"));
        else if (user instanceof User)
            authorities.add(new SimpleGrantedAuthority("isAgencia"));
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                authorities);

    }
}