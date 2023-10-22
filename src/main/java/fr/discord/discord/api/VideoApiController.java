package fr.discord.discord.api;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@RestController
@RequestMapping("/api/video")
public class VideoApiController {

    @PostMapping("/upload-video")
    public ResponseEntity<String> uploadVideo(@RequestParam("video") MultipartFile videoFile) {
        // Define a directory where you want to save the uploaded videos
        String uploadDirectory = "src\\main\\resources\\static";

        // Create the directory if it doesn't exist
        Path uploadPath = Path.of(uploadDirectory);
        if (!Files.exists(uploadPath)) {
            try {
                Files.createDirectories(uploadPath);
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Failed to create upload directory.");
            }
        }

        // Save the uploaded video to the directory
        try {
            Path videoPath = uploadPath.resolve(videoFile.getOriginalFilename());
            Files.copy(videoFile.getInputStream(), videoPath, StandardCopyOption.REPLACE_EXISTING);
            return ResponseEntity.ok("Video uploaded successfully.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload video.");
        }
    }

@GetMapping("/download-video")
    public ResponseEntity<ByteArrayResource> downloadVideo(@RequestParam("video") String videoName) {
        String uploadDirectory = "src/main/resources/static";
        Path uploadPath = Path.of(uploadDirectory);

        Path videoPath = uploadPath.resolve(videoName);

        try {
            byte[] videoBytes = Files.readAllBytes(videoPath);
            ByteArrayResource resource = new ByteArrayResource(videoBytes);

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + videoName);
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(videoBytes.length)
                    .body(resource);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ByteArrayResource(("Failed to download video: " + e.getMessage()).getBytes()));
        }
    }

}
