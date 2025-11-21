package com.example.chatapp.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {

    private final Cloudinary cloudinary;

    public CloudinaryService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public String uploadFile(MultipartFile file) throws IOException {
        Map uploadResult = cloudinary.uploader().upload(
                file.getBytes(),
                ObjectUtils.asMap(
                        "folder", "chat-media",          // store all chat files here
                        "resource_type", "auto",         // handle any type (image, pdf, etc.)
                        "type", "upload",                // ✅ forces public delivery
                        "access_mode", "public",         // ✅ ensures no 401
                        "overwrite", true,
                        "use_filename", true,
                        "unique_filename", true
                )
        );

        // return the public Cloudinary URL
        return uploadResult.get("secure_url").toString();
    }
}
