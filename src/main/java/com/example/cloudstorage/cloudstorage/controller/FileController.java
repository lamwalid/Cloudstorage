package com.example.cloudstorage.cloudstorage.controller;

import com.example.cloudstorage.cloudstorage.model.File;
import com.example.cloudstorage.cloudstorage.services.FileService;
import com.example.cloudstorage.cloudstorage.services.UserService;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class FileController {

    private FileService fileService;
    private UserService userService;

    public FileController(FileService fileService, UserService userService) {
        this.fileService = fileService;
        this.userService = userService;
    }


    @PostMapping("/files")
    public String saveCredential(HttpServletResponse response, Authentication authentication, Model model, @RequestParam("fileUpload") MultipartFile multipartFile) throws IOException {
        Integer userId = userService.getUser(authentication.getName()).getUserId();

        if(multipartFile.getOriginalFilename().equals("") || multipartFile.getSize()==0){
            model.addAttribute("error","Incorrect type of file uploaded!");
        }else if(fileService.fileExists(multipartFile.getOriginalFilename(), userId)){
            model.addAttribute("error", "A file with such name already exists");
        }else{
            fileService.addFile(new File(
                    null,
                    multipartFile.getOriginalFilename(),
                    multipartFile.getContentType(),
                    multipartFile.getSize(),
                    userId,
                    multipartFile.getBytes()
            ));
            model.addAttribute("success", "File uploaded successfully");
        }

        return "result";
    }

    @GetMapping("/view_file")
    public ResponseEntity<ByteArrayResource> getFile(Authentication authentication, Integer fileId) {
        Integer userId = userService.getUser(authentication.getName()).getUserId();
        File file = fileService.viewFile(fileId, userId);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +  file.getFileName() + "\"" ).body(new ByteArrayResource(file.getFileData()));
    }

    @GetMapping( "/delete_file")
    public String deleteFile(Authentication authentication, @RequestParam Integer fileId, Model model){
        Integer userId = userService.getUser(authentication.getName()).getUserId();

        try{
            fileService.deleteFile(fileId, userId);
            model.addAttribute("success", "File deleted successfully");
        }catch(Exception e){
            model.addAttribute("error", "An error has occurred, please try again ");
            e.printStackTrace();
        }
        model.addAttribute("redirectTab", "nav-credential-tab");

        return "result";
    }
}
