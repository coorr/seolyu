package kr.mainstream.seolyu.domain.reviewer.login;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import kr.mainstream.seolyu.common.exception.ValidationIllegalArgumentException;
import kr.mainstream.seolyu.domain.reviewer.Reviewer;
import kr.mainstream.seolyu.domain.reviewer.login.dto.LoginGetResDto;
import kr.mainstream.seolyu.domain.reviewer.login.dto.LoginPostReqDto;
import kr.mainstream.seolyu.domain.reviewer.login.validator.LoginPostReqDtoValidator;
import kr.mainstream.seolyu.login.GatewayService;
import kr.mainstream.seolyu.login.LoginInfo;
import kr.mainstream.seolyu.login.RequestHeader;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;
    private final GatewayService gatewayService;
    private final LoginPostReqDtoValidator loginPostReqDtoValidator;

    @PostMapping("/login")
    public ResponseEntity<LoginGetResDto> postLogin(
            @RequestBody @Valid LoginPostReqDto postReqDto,
            BindingResult bindingResult,
            @RequestAttribute RequestHeader requestHeader,
            HttpServletRequest request,
            HttpServletResponse response) {

        loginPostReqDtoValidator.validate(postReqDto, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new ValidationIllegalArgumentException(bindingResult);
        }

        Reviewer reviewer = loginService.getReviewer(postReqDto.getEmail(), postReqDto.getPassword());
        String clientId = requestHeader.isWebCall() ? gatewayService.getClientId(request) : requestHeader.getDeviceId();
        LoginInfo loginInfo = new LoginInfo(reviewer, clientId);
        gatewayService.login(loginInfo, requestHeader.isWebCall(), response);

        return ResponseEntity.ok(new LoginGetResDto(reviewer));
    }
}
