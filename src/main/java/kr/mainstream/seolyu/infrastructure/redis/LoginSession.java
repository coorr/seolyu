package kr.mainstream.seolyu.infrastructure.redis;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginSession {
    private Long reviewerId;
    private String clientId;
}
