package com.example.viagens;

import com.example.viagens.model.Usuario;
import com.example.viagens.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ViagensApplication {

    public static void main(String[] args) {
        SpringApplication.run(ViagensApplication.class, args);
    }

    @Bean
    public CommandLineRunner initUsers(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        return args -> {

            final String rawPassword = "123456";

            String encodedPassword = passwordEncoder.encode(rawPassword);

            if (usuarioRepository.findByEmail("cliente.generico@viagens.com").isEmpty()) {
                Usuario cliente = new Usuario(
                        "Cliente Avaliador",
                        "cliente.generico@viagens.com",
                        encodedPassword,
                        "CLIENTE"
                );
                usuarioRepository.save(cliente);
                System.out.println(">>> Usuário CLIENTE criado: cliente.generico@viagens.com / 123456");
            }

            if (usuarioRepository.findByEmail("admin@viagens.com").isEmpty()) {
                Usuario admin = new Usuario(
                        "Administrador Master",
                        "admin@viagens.com",
                        encodedPassword,
                        "ADMIN"
                );
                usuarioRepository.save(admin);
                System.out.println(">>> Usuário ADMIN criado: admin@viagens.com / 123456");
            }
        };
    }
}