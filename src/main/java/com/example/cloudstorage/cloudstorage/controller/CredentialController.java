package com.example.cloudstorage.cloudstorage.controller;

import com.example.cloudstorage.cloudstorage.model.Credential;
import com.example.cloudstorage.cloudstorage.services.CredentialService;
import com.example.cloudstorage.cloudstorage.services.UserService;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class CredentialController {


    private CredentialService credentialService;
    private UserService userService;

    public CredentialController(CredentialService credentialService, UserService userService) {

        this.credentialService = credentialService;
        this.userService = userService;
    }


    @PostMapping("/credentials")
    public String saveCredential(Authentication authentication, @ModelAttribute Credential credential, Model model){
        credential.setUserId(userService.getUser(authentication.getName()).getUserId());

        try {
            credentialService.createOrUpdateCredential(credential);
            model.addAttribute("success", "Credential saved successfully");
        } catch(Exception e){
            model.addAttribute("error", "An error has occurred, please try again ");
            e.printStackTrace();
        }

        return "result";
    }


   @GetMapping(value = "/delete_credential")
    public String deleteCredential(Authentication authentication, @RequestParam Integer credentialId, Model model){
        Integer userId = userService.getUser(authentication.getName()).getUserId();

        try{
            credentialService.deleteCredential(credentialId, userId);
            model.addAttribute("success", "Credential deleted successfully");
        }catch(Exception e){
            model.addAttribute("error", "An error has occurred, please try again ");
            e.printStackTrace();
        }
        model.addAttribute("redirectTab", "nav-credential-tab");
        return "result";
    }


}
