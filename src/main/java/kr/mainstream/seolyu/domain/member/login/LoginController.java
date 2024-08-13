package kr.mainstream.seolyu.domain.member.login;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import kr.mainstream.seolyu.common.exception.ValidationIllegalArgumentException;
import kr.mainstream.seolyu.domain.member.Member;
import kr.mainstream.seolyu.domain.member.login.dto.LoginGetResDto;
import kr.mainstream.seolyu.domain.member.login.dto.LoginPostReqDto;
import kr.mainstream.seolyu.domain.member.login.validator.LoginPostReqDtoValidator;
import kr.mainstream.seolyu.login.GatewayService;
import kr.mainstream.seolyu.login.LoginInfo;
import kr.mainstream.seolyu.login.MobileHeader;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;
    private final GatewayService gatewayService;
    private final LoginPostReqDtoValidator loginPostReqDtoValidator;

    @PostMapping("/login")
    public ResponseEntity<LoginGetResDto> postLogin(
            @RequestBody @Valid LoginPostReqDto postReqDto,
            BindingResult bindingResult,
            @RequestAttribute MobileHeader mobileHeader,
            HttpServletRequest request,
            HttpServletResponse response) {

        loginPostReqDtoValidator.validate(postReqDto, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new ValidationIllegalArgumentException(bindingResult);
        }

        Member member = loginService.getMember(postReqDto.getEmail(), postReqDto.getPassword());
        String clientId = mobileHeader.isWebCall() ? gatewayService.getClientId(request) : mobileHeader.getDeviceId();
        LoginInfo loginInfo = new LoginInfo(member, clientId);
        gatewayService.login(loginInfo, mobileHeader.isWebCall(), response);

        return ResponseEntity.ok(new LoginGetResDto(member));
    }
}
