package com.example.chatapp.controller;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/file")
public class FileProxyController {

    @GetMapping("/proxy")
    public ResponseEntity<?> proxyFile(@RequestParam("url") String fileUrl) {
        try {
            // 🔧 Encode the URL safely (avoid space or + errors)
            String safeUrl = URLEncoder.encode(fileUrl, StandardCharsets.UTF_8)
                    .replaceAll("%3A", ":")
                    .replaceAll("%2F", "/");

            URL url = new URL(safeUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);

            int status = connection.getResponseCode();
            if (status >= 400) {
                return ResponseEntity.status(status).body("Remote server returned " + status);
            }

            InputStream inputStream = connection.getInputStream();
            String contentType = connection.getContentType();

            if (contentType == null) contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;

            String fileName = safeUrl.substring(safeUrl.lastIndexOf('/') + 1);

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + fileName + "\"")
                    .body(new InputStreamResource(inputStream));

        } catch (Exception e) {
            e.printStackTrace(); // Log it in console
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error fetching file: " + e.getMessage());
        }
    }
}
