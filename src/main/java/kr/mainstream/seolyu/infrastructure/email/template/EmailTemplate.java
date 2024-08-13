package kr.mainstream.seolyu.infrastructure.email.template;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class EmailTemplate {
    private final String subject;
    private final String content;
}
