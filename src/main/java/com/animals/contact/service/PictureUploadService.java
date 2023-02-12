package com.animals.contact.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public class PictureUploadService {

    public String save(MultipartFile file, Long id, String type) {

        //replace by  ? file.getContentType()
        String[] fileArr = (file.getOriginalFilename()).split("\\.");
        String contentType = file.getContentType();


        if ( fileArr.length > 0 && contentType.contains("image")) {
            String format = "." + fileArr[fileArr.length - 1];
            String fileName = id + format;

            String path = this.getClass().getClassLoader().getResource("").getPath();
            String pathArr[] = path.split("/target/classes/");
            String fullPath = pathArr[0] + "/src/main/resources/static/img/avatar/" + type + "/";

            try {
                file.transferTo(new File(fullPath + fileName));
                return format;
            } catch (Exception e) {
                System.out.println("Erreur de transfer");
            }
        } else {
            System.out.println("ceci n'est pas une image ou le nom est incorrecte.");
        }
        return null;
    }

}
