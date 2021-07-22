package com.example.cloudstorage.cloudstorage.controller;

import com.example.cloudstorage.cloudstorage.model.Credential;
import com.example.cloudstorage.cloudstorage.model.File;
import com.example.cloudstorage.cloudstorage.model.Note;
import com.example.cloudstorage.cloudstorage.services.*;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {


    private UserService userService;
    private NoteService noteService;
    private FileService fileService;
    private CredentialService credentialService;
    private EncryptionService encryptionService;



    public HomeController(UserService userService, NoteService noteService, FileService fileService, CredentialService credentialService) {
        this.userService = userService;
        this.noteService = noteService;
        this.fileService = fileService;
        this.credentialService = credentialService;
    }

    @GetMapping
    public String getHomePage(Authentication authentication, Model model, Note note, Credential credential, File file, EncryptionService encryptionService){
        int userId = userService.getUser(authentication.getName()).getUserId();
        model.addAttribute("notes", noteService.getAllNotes(userId));
        model.addAttribute("files", fileService.getFiles(userId));
        model.addAttribute("credentials", credentialService.getAllCredential(userId));
        model.addAttribute("encryptionService", encryptionService);
        return "home";
    }

}
