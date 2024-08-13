package kr.mainstream.seolyu.common.exception.file;

import kr.mainstream.seolyu.common.exception.SimpleMessageIllegalArgumentException;

public class FileSizeExceededException extends SimpleMessageIllegalArgumentException {
    public FileSizeExceededException() {
        super("파일 크기가 20MB를 초과합니다.");
    }
}
