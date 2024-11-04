package kr.mainstream.seolyu.infrastructure.file;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Getter
@AllArgsConstructor
@Setter
public class FilePayload {
    private byte[] content;
    private String originalFilename;
    private String contentType;
    private long size;

    public FilePayload(MultipartFile file) {
        try {
            this.content = file.getBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.originalFilename = file.getOriginalFilename();
        this.contentType = file.getContentType();
        this.size = file.getSize();
    }
}
