/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edwinbaquiax.library.services;

import com.edwinbaquiax.library.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class LoginService {
   
    @Autowired
    private UserRepository userRepository;
    
    public boolean todoCorrecto(String username,String password){
        
        return userRepository.existsByUsernameAndPassword(username, password);
    }
}
