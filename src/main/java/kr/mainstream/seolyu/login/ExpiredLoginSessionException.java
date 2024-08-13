package kr.mainstream.seolyu.login;


import lombok.Getter;

@Getter
public class ExpiredLoginSessionException extends IllegalStateException {
    public ExpiredLoginSessionException() {
        super("세션이 끊어졌습니다. 다시 로그인 해 주세요.");
    }
}
