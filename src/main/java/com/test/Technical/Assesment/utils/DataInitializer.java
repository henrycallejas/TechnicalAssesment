package com.test.Technical.Assesment.utils;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.test.Technical.Assesment.model.Client;
import com.test.Technical.Assesment.repository.ClientRepository;
import com.test.Technical.Assesment.repository.ProductRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ClientRepository clientRepository;

    public DataInitializer(ClientRepository clientRepository, ProductRepository productRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Client client = new Client();
        client.setAddress("Soyapango, San Salvador");
        client.setEmail("prueba@gmail.com");
        client.setName("Henry");
        client.setLastName("Callejas");
        client.setRole("ADMIN");
        client.setUsername("admin");
        client.setPassword("$2a$12$tM3cexnIigdvtfvmIZsiROQpcYorXX1ru4PsqFnZILgFaN7M0gLfy");
        this.clientRepository.save(client);
    }

}
