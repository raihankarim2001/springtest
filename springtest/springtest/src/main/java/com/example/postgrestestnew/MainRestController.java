package com.example.postgrestestnew;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainRestController
{
    @Autowired
    CredentialRepository credentialRepository;
    @GetMapping("/save")
    public String save(){
        Credential credential = new Credential();
        credential.setUsername("sushant");
        credential.setPassword("Dhoni@16");
        credentialRepository.save(credential);
        return "new credential added";
    }
}
