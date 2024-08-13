package kr.mainstream.seolyu.domain.member;

import kr.mainstream.seolyu.common.security.EncryptService;
import kr.mainstream.seolyu.domain.member.exception.InvalidCredentialsException;
import kr.mainstream.seolyu.domain.member.exception.MemberNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {
    private final MemberRepository memberRepository;
    private final EncryptService encryptService;

    public Member getMember(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(MemberNotFoundException::new);
    }

    public Member getMemberByCredentials(String email, String password) {
        try {
            password = encryptService.digest(password);
        } catch (Exception e) {
            log.error("비밀번호 단방향 암호화 중 오류 발생 email : {}, password : {}, message : {}", email, password, e);
            throw new InvalidCredentialsException();
        }

        return memberRepository.findByEmailAndPassword(email, password)
                .orElseThrow(MemberNotFoundException::new);
    }

    public Member getMemberByCredentials(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(MemberNotFoundException::new);
    }

}
