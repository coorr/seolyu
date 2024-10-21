package kr.mainstream.seolyu.domain.applicant;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApplicantControllerTest {

    @Test
    void post() {
        String pra = "applicantPostReqDto: "{
            "name": "John Doe",
                    "email": "john.doaasdasd@example.com",
                    "position": "BE",
                    "resumeUrl": "",
                    "requestDetails": "",
                    "eventId": "1"
        }""
        grinder.logger.debug("pra: ${pra}")

        String applicantPostReqDto = """
            {
                "name": "John Doe",
                "email": "john.doaasdasd@example.com",
                "position": "BE",
                "resumeUrl": "",
                "requestDetails": "",
                "eventId": "1"
            }
            """;
        HTTPResponse response = request.POST("https://api.seolyu.com/applicants", pra)
    }
}
