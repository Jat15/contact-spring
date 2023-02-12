package com.animals.contact.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

import static java.util.UUID.randomUUID;

@Service
public class PictureUploadService {
    public String save(MultipartFile file, String type) {
        String contentType = file.getContentType();
        String[] contentTypeArr = contentType.split("/");

        if ( contentTypeArr.length > 0 /*&& contentType.contains("image")*/) {
            String uuid = randomUUID().toString();
            String format = "." + contentTypeArr[contentTypeArr.length - 1];
            String fileName = uuid + format;

            String path = this.getClass().getClassLoader().getResource("").getPath();
            String pathArr[] = path.split("/target/classes/");
            String fullPath = pathArr[0] + "/src/main/resources/static/img/avatar/" + type + "/";

            try {
                file.transferTo(new File(fullPath + fileName));
                return fileName;
            } catch (Exception e) {
                System.out.println("Erreur de transfer");
            }
        } else {
            System.out.println("ceci n'est pas une image ou le nom est incorrecte.");
        }
        return null;
    }
}
