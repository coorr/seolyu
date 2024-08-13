package kr.mainstream.seolyu.infrastructure.file;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class S3FileStorageService implements FileStorageService {
    @Value("${cloud.aws.s3.bucket:@null}")
    private String bucket;
//    private final AmazonS3 amazonS3;


//    @Override
    public List<FileMetadata> upload(MultipartFile[] files) {
        if (files == null || files.length == 0) {
            return Collections.emptyList();
        }

        List<FileMetadata> fileMetadataList = new ArrayList<>();
//        for (MultipartFile file : files) {
//            FileMetadata metadata = new FileMetadata(file);
//            fileMetadataList.add(metadata);
//
//            Runnable runnable = () -> {
//                ObjectMetadata objectMetadata = new ObjectMetadata();
//                objectMetadata.setContentLength(file.getSize());
//                objectMetadata.setContentType(file.getContentType());
//
//                try (InputStream inputStream = file.getInputStream()) {
//                    /** AWS S3 오픈 시 활성화 */
////                    PutObjectRequest request = new PutObjectRequest(bucket, metadata.getStoredFilename(), inputStream, objectMetadata)
////                            .withCannedAcl(CannedAccessControlList.PublicRead);
////                    amazonS3.putObject(request);
//                } catch (Exception e) {
//                    log.error("file upload failed message : {} " , e.getMessage());
//                    throw new FileUploadException();
//                }
//            };
//            executor.execute(runnable);
//        }
        return fileMetadataList;
    }

    @Override
    public FileMetadata upload(MultipartFile file) {
        return null;
    }
}
