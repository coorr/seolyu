package kr.mainstream.seolyu.domain.applicant.admin;

import kr.mainstream.seolyu.common.exception.ValidationIllegalArgumentException;
import kr.mainstream.seolyu.domain.applicant.dto.ApplicantGetReqDto;
import kr.mainstream.seolyu.domain.applicant.dto.ApplicantSearchReqDto;
import kr.mainstream.seolyu.domain.applicant.dto.ApplicantSearchResDto;
import kr.mainstream.seolyu.domain.applicant.validator.ApplicantGetReqDtoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/applicants")
public class ApplicantAdminController {
    private final ApplicantAdminService applicantAdminService;
    private final ApplicantGetReqDtoValidator applicantGetReqDtoValidator;

    @GetMapping
    public ResponseEntity<Page<ApplicantSearchResDto>> getList(ApplicantGetReqDto dto, BindingResult bindingResult) {
        applicantGetReqDtoValidator.validate(dto, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new ValidationIllegalArgumentException(bindingResult);
        }
        ApplicantSearchReqDto reqDto = new ApplicantSearchReqDto(dto);
        return ResponseEntity.ok(applicantAdminService.getList(reqDto));
    }
}
