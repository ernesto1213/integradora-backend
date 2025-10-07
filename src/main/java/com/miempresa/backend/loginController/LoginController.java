package com.miempresa.backend.loginController;

import com.miempresa.backend.model.User;
import com.miempresa.backend.service.LoginService;
import com.miempresa.backend.service.MascotaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.miempresa.backend.service.MascotaService;
import java.util.*;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private LoginService loginService;
    @Autowired 
    private MascotaService mascotaService;
    @PostMapping("/register")
    public Map<String, String> register(@RequestBody User user) {
        String message = loginService.register(user);
        String status = message.contains("éxito") ? "ok" : "error";
        return Map.of("status", status, "message", message);
    }

@PostMapping("/login")
public Map<String, Object> login(@RequestBody Map<String, String> request) {
    String email = request.get("email");
    String password = request.get("password");
    Map<String, Object> result = loginService.login(email, password);

    // Tomamos directamente el status que nos envió el servicio
    String status = (String) result.get("status");
    String message = (String) result.get("message");
    Object id = result.get("id");
    String token = status.equals("ok") ? "123456" : "";

    Map<String, Object> response = new HashMap<>();
    response.put("status", status);
    response.put("message", message);
    response.put("token", token);
    if (id != null) {
        response.put("id", id);
    }

    return response;
}


    @GetMapping("/consulta/{id}")
    public ResponseEntity<User> consulta(@PathVariable Long id) {
        Optional<User> user = loginService.rango(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
@PostMapping("/ordenar")
public List<MascotaService.Mascota> ordenarMascotas(@RequestBody List<MascotaService.Mascota> lista) {
    return mascotaService.bubbleSort(lista);
}

}


