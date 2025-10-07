package com.miempresa.backend.service;

import java.util.HashMap;
import java.util.Map;
import com.miempresa.backend.model.User;
import com.miempresa.backend.repository.UserRepository;

import aj.org.objectweb.asm.Opcodes;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class LoginService {
    @Autowired
    private UserRepository userRepository;
    public String register(User user){
        Optional<User> existing = userRepository.findByEmail(user.getEmail());
        if(existing.isPresent()){
            return "Correo ya registrado";
        }
        userRepository.save(user);
        return "Usuario registrado con éxito";
    }

// En loginService
public Map<String, Object> login(String email, String password) {
    Optional<User> existing = userRepository.findByEmail(email);
    Map<String, Object> response = new HashMap<>();
    if(existing.isPresent() && existing.get().getPassword().equals(password)){
        User user = existing.get();
        response.put("message", "Login Exitoso");
        response.put("status", "ok"); // ✅ agregamos status aquí
        response.put("id", user.getId());
        response.put("Nombre", user.getName());
    } else{
        response.put("message", "Correo o contraseña incorrectos");
        response.put("status", "error"); // ✅ agregamos status
    }
    return response;
}


    public Optional<User> rango(Long id){
        Optional<User> dato = userRepository.findById(id);
        return dato;
    }

}