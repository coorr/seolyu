package kr.mainstream.seolyu.domain.reviewer;

import kr.mainstream.seolyu.common.security.EncryptService;
import kr.mainstream.seolyu.domain.reviewer.exception.InvalidCredentialsException;
import kr.mainstream.seolyu.domain.reviewer.exception.ReviewerNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewerService {
    private final ReviewerRepository reviewerRepository;
    private final EncryptService encryptService;

    public Reviewer getReviewer(Long id) {
        return reviewerRepository.findById(id)
                .orElseThrow(ReviewerNotFoundException::new);
    }

    public void checkExists(Long id) {
        boolean isExists = reviewerRepository.existsById(id);
        if (!isExists) {
            throw new ReviewerNotFoundException();
        }
    }

    public Reviewer getReviewerByCredentials(String email, String password) {
        try {
            password = encryptService.digest(password);
        } catch (Exception e) {
            log.error("비밀번호 단방향 암호화 중 오류 발생 email : {}, password : {}, message : {}", email, password, e);
            throw new InvalidCredentialsException();
        }
        return reviewerRepository.findByEmailAndPassword(email, password)
                .orElseThrow(ReviewerNotFoundException::new);
    }

    public Reviewer getReviewerByCredentials(Long id) {
        return reviewerRepository.findById(id)
                .orElseThrow(ReviewerNotFoundException::new);
    }

}
