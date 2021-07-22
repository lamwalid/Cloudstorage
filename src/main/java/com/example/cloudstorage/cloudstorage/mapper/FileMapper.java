package com.example.cloudstorage.cloudstorage.mapper;


import com.example.cloudstorage.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {
    @Select("SELECT * FROM FILES WHERE userid = #{userId}")
    List<File> getAllFiles(Integer userId);

    @Insert("INSERT INTO FILES (filename, contenttype, filesize, userid, filedata) VALUES(#{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insertFile(File file);

    @Select("SELECT * FROM FILES WHERE fileid = #{fileId} AND userid = #{userId}")
    File getFile(Integer fileId, Integer userId);


    @Delete("DELETE FROM FILES WHERE fileid = #{fileId} AND userid = #{userId}")
    void deleteFile(Integer fileId, int userId);


}
