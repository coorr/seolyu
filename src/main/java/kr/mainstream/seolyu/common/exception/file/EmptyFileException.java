package kr.mainstream.seolyu.common.exception.file;

import kr.mainstream.seolyu.common.exception.SimpleMessageIllegalArgumentException;

public class EmptyFileException extends SimpleMessageIllegalArgumentException {
    public EmptyFileException() {
        super("common.text.file.error.empty"); // 파일을 선택해주세요.
    }
}
