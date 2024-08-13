package kr.mainstream.seolyu.infrastructure.email;

public class EmailMessageCreateException extends RuntimeException {
    public EmailMessageCreateException() {
        super("이메일 메시지 생성에 실패했습니다.");
    }
}
