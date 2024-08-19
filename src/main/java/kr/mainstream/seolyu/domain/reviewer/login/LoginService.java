package kr.mainstream.seolyu.domain.reviewer.login;

import kr.mainstream.seolyu.domain.reviewer.Reviewer;
import kr.mainstream.seolyu.domain.reviewer.ReviewerService;
import kr.mainstream.seolyu.domain.reviewer.exception.ReviewerNotFoundException;
import kr.mainstream.seolyu.login.LoginInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import static kr.mainstream.seolyu.infrastructure.redis.RedisEnv.LOGIN_INFO_KEY;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final ReviewerService reviewerService;

    public Reviewer getReviewer(String email, String password) {
        Reviewer reviewer = reviewerService.getReviewerByCredentials(email, password);
        reviewer.verifyWithdraw();
        return reviewer;
    }

    @Cacheable(value = LOGIN_INFO_KEY, key = "#reviewerId")
    public LoginInfo getLoginInfo(Long reviewerId, String clientId) {
        Reviewer reviewer = reviewerService.getReviewerByCredentials(reviewerId);
        if (ObjectUtils.isEmpty(reviewer)) {
            throw new ReviewerNotFoundException();
        }
        return new LoginInfo(reviewer.getId(), clientId, reviewer.getEmail(), reviewer.getName(), reviewer.getRole(), reviewer.getJoinStatus());
    }
}
