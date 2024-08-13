package kr.mainstream.seolyu.domain.member.my;

import kr.mainstream.seolyu.login.LoginInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member/my")
@RequiredArgsConstructor
public class MemberMyController {

    @GetMapping
    public ResponseEntity<MemberMySummaryGetResDto> get(@RequestAttribute LoginInfo loginInfo) {
        return ResponseEntity.ok(new MemberMySummaryGetResDto(loginInfo));
    }
}
