package kr.mainstream.seolyu.common.exception.file;

import kr.mainstream.seolyu.common.exception.SimpleMessageIllegalArgumentException;

import java.text.MessageFormat;

public class NotSupportedFileTypeException extends SimpleMessageIllegalArgumentException {
    public NotSupportedFileTypeException(String... supportedFileTypes) {
        super(formatMessage(supportedFileTypes));
    }

    public NotSupportedFileTypeException() {
        super("지원되지 않는 파일 형식입니다.");
    }

    private static String formatMessage(String[] supportedFileTypes) {
        String supportedTypes = String.join(", ", supportedFileTypes);
        return MessageFormat.format("지원되지 않는 파일 형식입니다. (지원 형식: {0})", supportedTypes);
    }
}
