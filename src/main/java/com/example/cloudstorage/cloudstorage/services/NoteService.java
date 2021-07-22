package com.example.cloudstorage.cloudstorage.services;

import com.example.cloudstorage.cloudstorage.mapper.NoteMapper;
import com.example.cloudstorage.cloudstorage.model.Note;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoteService {

    private NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public void addNote(Note note){
        noteMapper.insertNote(note);
    }

    public void editNote(Note note) {
        noteMapper.updateNote(note);
    }


    public List<Note> getAllNotes(Integer userId){
        return new ArrayList<>(this.noteMapper.getAllNotes(userId));
    }

    public void deleteNote(Integer noteId, Integer userId){
        noteMapper.deleteNote(noteId, userId);
    }
}
