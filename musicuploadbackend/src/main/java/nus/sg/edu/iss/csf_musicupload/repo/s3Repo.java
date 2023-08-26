package nus.sg.edu.iss.csf_musicupload.repo;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GetObjectAclRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

@Repository
public class s3Repo {

    @Autowired
    private AmazonS3 s3;
    
    public String uploadFile(MultipartFile file) {


        ObjectMetadata metaData = new ObjectMetadata();
             metaData.setContentType(file.getContentType());
             metaData.setContentLength(file.getSize());
             metaData.addUserMetadata("name", file.getOriginalFilename());
             System.out.println(file.getOriginalFilename());

             try  {
            System.out.println("trying to put files");
             PutObjectRequest putReq = new PutObjectRequest("musicupload", file.getOriginalFilename(), file.getInputStream(), 
             metaData);
             putReq = putReq.withCannedAcl(CannedAccessControlList.PublicRead);
             try {
               //put req here fails
                 s3.putObject(putReq);
                System.out.println("Upload successful");
             } catch(Exception ex)  {
                ex.printStackTrace();
             }

             } catch(IOException ex) {
                ex.printStackTrace();
             }

             
             
             
              
             return file.getOriginalFilename();
    }

    // public String getFiles (String fileName) {

    //     try {
    //         GetObjectRequest getReq = new GetObjectRequest("musicupload",);
    //         S3Object result = s3.getObject(getReq);
    //         ObjectMetadata metadata = result.getObjectMetadata();
    //         Map<String, String> userData = result.getObjectMetadata();

    //         try(S3ObjectInputStream is = result.getObjectContent()) {
    //             byte[] buffer = is.getAllBytes();
    //         }
    //     }
    // }
}
