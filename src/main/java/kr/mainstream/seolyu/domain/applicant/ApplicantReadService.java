package kr.mainstream.seolyu.domain.applicant;

import kr.mainstream.seolyu.domain.applicant.exception.ApplicantNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ApplicantReadService {
    private final ApplicantRepository applicantRepository;

    public Applicant getApplicant(Long id) {
        return applicantRepository.findById(id)
                .orElseThrow(ApplicantNotFoundException::new);
    }

    public void checkExists(Long id) {
        boolean isExists = applicantRepository.existsById(id);
        if (!isExists) {
            throw new ApplicantNotFoundException();
        }
    }
}
