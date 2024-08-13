package kr.mainstream.seolyu.config;

import org.springframework.beans.factory.annotation.Value;

//@Configuration
public class AwsConfig {

    @Value("${cloud.aws.credentials.accessKey}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secretKey")
    private String secretKey;

    @Value("${cloud.aws.region.static}")
    private String region;


//    @Bean
//    public AmazonS3Client amazonS3Client() {
//        BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
//        return (AmazonS3Client) AmazonS3ClientBuilder.standard()
//                .withRegion(region)
//                .withCredentials(new AWSStaticCredentialsProvider(credentials))
//                .build();
//    }
}
