package kr.mainstream.seolyu.infrastructure.file;

import kr.mainstream.seolyu.common.exception.file.EmptyFileException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class LocalFileStorageService implements FileStorageService{
    public static final String FILE_DIRECTORY = "/Users/gimjinseong/Downloads/resume-pdf/";

    @Override
    public FileMetadata upload(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new EmptyFileException();
        }
        String fileName = generateFileName(file.getOriginalFilename());
        Path filePath = Paths.get(FILE_DIRECTORY + fileName);

        try {
            Files.copy(file.getInputStream(), filePath);
        } catch (IOException e) {
            log.error("file upload failed message : {} " , e.getMessage());
            throw new FileUploadException();
        }
        return new FileMetadata(file, filePath.toString());
    }

    private String generateFileName(String originalFileName) {
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        return UUID.randomUUID().toString() + extension;
    }
}
