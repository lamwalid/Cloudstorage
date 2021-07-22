package com.example.cloudstorage.cloudstorage.services;

import com.example.cloudstorage.cloudstorage.mapper.CredentialMapper;
import com.example.cloudstorage.cloudstorage.model.Credential;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialService {

    private CredentialMapper credentialMapper;
    private EncryptionService encryptionService;

    public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
    }

    public String getEncodedKey(){
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        return Base64.getEncoder().encodeToString(key);
    }

    public void createOrUpdateCredential(Credential credential){


        String encodedKey = getEncodedKey();
        String encryptedPassword = encryptionService.encryptValue(credential.getPassword(), encodedKey);
        credential.setPassword(encryptedPassword);
        credential.setKey(encodedKey);
        String decryptedPassword = encryptionService.decryptValue(encryptedPassword, encodedKey);
        if(credential.getCredentialId() == null)
            credentialMapper.insertCredential(credential);
        else
            credentialMapper.updateCredential(credential);


    }


    public List<Credential> getAllCredential(Integer userId){
        return credentialMapper.getAllCredentials(userId);

    }


    public void deleteCredential(Integer credentialId, Integer userId) {
        credentialMapper.deleteCredential(credentialId, userId);
    }



}
