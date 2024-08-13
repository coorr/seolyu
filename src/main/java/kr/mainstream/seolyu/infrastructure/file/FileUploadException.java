package kr.mainstream.seolyu.infrastructure.file;


import kr.mainstream.seolyu.common.exception.SimpleMessageIllegalStateException;

public class FileUploadException extends SimpleMessageIllegalStateException {
    public FileUploadException() {
        super("파일 업로드 실패했습니다.");
    }
}
