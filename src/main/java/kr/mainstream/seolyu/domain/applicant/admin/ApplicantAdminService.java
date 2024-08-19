package kr.mainstream.seolyu.domain.applicant.admin;

import kr.mainstream.seolyu.domain.applicant.ApplicantQueryRepository;
import kr.mainstream.seolyu.domain.applicant.dto.ApplicantSearchReqDto;
import kr.mainstream.seolyu.domain.applicant.dto.ApplicantSearchResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicantAdminService {
    private final ApplicantQueryRepository applicantQueryRepository;

    public Page<ApplicantSearchResDto> getList(ApplicantSearchReqDto dto) {
        return applicantQueryRepository.findAllSearchConditions(dto);
    }
}
