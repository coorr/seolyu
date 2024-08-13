package kr.mainstream.seolyu.infrastructure.email.template;

public class RequestCompleteEmailTemplate extends EmailTemplate {

    public RequestCompleteEmailTemplate(String subject, String content) {
        super(subject, content);
    }

    public static EmailTemplate create(String name) {
        String subject = "\uD83C\uDF1F 서류 검토 신청 완료 안내";

        String content = String.format(
                "안녕하세요, %s님.\n\n" +
                        "먼저, %s님의 서류 검토 요청이 성공적으로 접수되었음을 알려드립니다.\n\n" +
                        "저희 팀은 제출해주신 서류를 면밀히 검토할 예정이며, 검토 결과는 7일 이내로 안내해드릴 예정입니다.\n\n" +
                        "기타 문의사항이 있으시면 언제든지 메일로 연락 주시기 바랍니다.\n\n" +
                        "감사합니다.\n\n",
                name, name
        );

        return new RequestCompleteEmailTemplate(subject, content);
    }
}
