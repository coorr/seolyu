package kr.mainstream.seolyu.domain.member.login;

import kr.mainstream.seolyu.domain.member.Member;
import kr.mainstream.seolyu.domain.member.MemberService;
import kr.mainstream.seolyu.domain.member.exception.MemberNotFoundException;
import kr.mainstream.seolyu.login.LoginInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import static kr.mainstream.seolyu.infrastructure.redis.RedisEnv.LOGIN_INFO_KEY;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final MemberService memberService;

    public Member getMember(String email, String password) {
        Member member = memberService.getMemberByCredentials(email, password);
        member.verifyWithdraw();
        return member;
    }

    @Cacheable(value = LOGIN_INFO_KEY, key = "#memberId")
    public LoginInfo getLoginInfo(Long memberId, String clientId) {
        Member member = memberService.getMemberByCredentials(memberId);
        if (ObjectUtils.isEmpty(member)) {
            throw new MemberNotFoundException();
        }
        return new LoginInfo(member.getId(), clientId, member.getEmail(), member.getName(), member.getRole(), member.getJoinStatus());
    }
}
