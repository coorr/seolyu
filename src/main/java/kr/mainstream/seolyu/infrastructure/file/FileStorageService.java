package kr.mainstream.seolyu.infrastructure.file;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    FileMetadata upload(MultipartFile file);
}
