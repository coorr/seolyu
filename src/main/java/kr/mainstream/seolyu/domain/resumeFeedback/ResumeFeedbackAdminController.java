package kr.mainstream.seolyu.domain.resumeFeedback;

import jakarta.validation.Valid;
import kr.mainstream.seolyu.common.exception.ValidationIllegalArgumentException;
import kr.mainstream.seolyu.domain.resumeFeedback.dto.ResumeFeedbackPostReqDto;
import kr.mainstream.seolyu.login.LoginInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/resume-feedbacks")
public class ResumeFeedbackAdminController {
    private final ResumeFeedbackAdminService resumeFeedbackAdminService;


    @PostMapping
    public ResponseEntity<Void> post(@Valid @RequestBody ResumeFeedbackPostReqDto dto,
                                     BindingResult bindingResult,
                                     @RequestAttribute LoginInfo loginInfo) {
        if (bindingResult.hasErrors()) {
            throw new ValidationIllegalArgumentException(bindingResult);
        }
        resumeFeedbackAdminService.post(dto, loginInfo.getMemberId());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
