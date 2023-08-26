package nus.sg.edu.iss.csf_musicupload.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import nus.sg.edu.iss.csf_musicupload.repo.s3Repo;

@RestController
@RequestMapping
public class UploadMusicController {

    @Autowired
    private AmazonS3 s3;

    @Autowired
    private s3Repo s3repo;
    
    @PostMapping(path="/uploadmusic", consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadFiles(@RequestPart MultipartFile[] files) {

    try {

        JsonArrayBuilder jsonBuilder = Json.createArrayBuilder();


        for(MultipartFile file : files) {
           String fileName = s3repo.uploadFile(file);
           jsonBuilder.add(fileName);
        }

        JsonObject resp = Json.createObjectBuilder()
                                .add("uploadedFiles", jsonBuilder.build())
                                .build();

        return ResponseEntity.status(200).body(resp.toString());
    } catch (Exception ex) {
        
        JsonObject resp = Json.createObjectBuilder()
                            .add("error", ex.getMessage())
                            .build();

        return ResponseEntity.status(HttpStatus.SC_NOT_MODIFIED).body(resp.toString());
    }
}

    // @GetMapping() 
    // public ResponseEntity<String> {


    // }
 
}
