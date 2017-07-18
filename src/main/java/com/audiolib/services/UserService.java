/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.audiolib.services;

import com.audiolib.model.dao.DAOFactory;
import com.audiolib.model.entites.User;
import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Service for User Auth
 *
 * @author Victor Novikov
 */
@Service
public class UserService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException {
        User user = DAOFactory.getDAOUser().getUserByName(string);
        if (user == null) {
            throw new UsernameNotFoundException("User with name " + string + " not found");
        }
        return user;
    }

    /**
     * Find User By Id
     *
     * @param id
     * @return
     */
    public Optional<User> findById(Integer id) {
        User user = DAOFactory.getDAOUser().getUserByID(id);
        return Optional.ofNullable(user);
    }
}
