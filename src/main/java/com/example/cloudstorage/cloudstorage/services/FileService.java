package com.example.cloudstorage.cloudstorage.services;
import com.example.cloudstorage.cloudstorage.mapper.FileMapper;
import com.example.cloudstorage.cloudstorage.model.File;


import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


@Service
public class FileService {

    private final FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public void addFile(File file) {
        fileMapper.insertFile(file);
    }

    public List<File> getFiles(int userId) {
        return fileMapper.getAllFiles(userId);
    }

    public byte[] getFileData(Integer fileId, int userId) {
        return fileMapper.getFile(fileId, userId).getFileData();
    }

    public void deleteFile(Integer fileId, int userId) {
        fileMapper.deleteFile(fileId, userId);
    }

    public boolean fileExists(String filename, int userId) {
        return Arrays.asList(fileMapper.getAllFiles(userId)).contains(filename);
    }

    public File viewFile(Integer fileId, Integer userId){
        return fileMapper.getFile(fileId, userId);
    }


}
