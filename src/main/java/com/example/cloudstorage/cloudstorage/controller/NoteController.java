package com.example.cloudstorage.cloudstorage.controller;


import com.example.cloudstorage.cloudstorage.model.Note;
import com.example.cloudstorage.cloudstorage.services.NoteService;
import com.example.cloudstorage.cloudstorage.services.UserService;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class NoteController {

    private UserService userService;
    private NoteService noteService;

    public NoteController(UserService userService, NoteService noteService) {
        this.userService = userService;
        this.noteService = noteService;
    }

    @PostMapping("/notes")
    public String saveNote(Authentication authentication, @ModelAttribute Note note, Model model){

        note.setUserId(userService.getUser(authentication.getName()).getUserId());

        try {
            if(note.getNoteId() != null)
                noteService.editNote(note);
            else
                noteService.addNote(note);

            model.addAttribute("success", "Credential saved successfully");
        } catch(Exception e){
            model.addAttribute("error", "An error has occurred, please try again ");
            e.printStackTrace();
        }

        return "result";
    }


    @GetMapping( "/delete_note")
    public String deleteNote(Authentication authentication, @RequestParam Integer noteId, Model model){
        Integer userId = userService.getUser(authentication.getName()).getUserId();

        try{
            noteService.deleteNote(noteId, userId);
            model.addAttribute("success", "Note deleted successfully");
        }catch(Exception e){
            model.addAttribute("error", "An error has occurred, please try again ");
            e.printStackTrace();
        }
        model.addAttribute("redirectTab", "nav-credential-tab");
        return "result";
    }

}
