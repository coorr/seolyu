package kr.mainstream.seolyu.domain.applicant;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ApplicantService {
    private final ApplicantRepository applicantRepository;

    @Transactional
    public void save(Applicant applicant) {
        applicantRepository.save(applicant);
    }
}
