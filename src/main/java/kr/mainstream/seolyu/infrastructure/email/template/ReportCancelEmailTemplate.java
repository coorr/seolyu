package kr.mainstream.seolyu.infrastructure.email.template;

public class ReportCancelEmailTemplate extends EmailTemplate {
    public ReportCancelEmailTemplate(String subject, String content) {
        super(subject, content);
    }

    public static EmailTemplate create(String name) {
        String subject = "⚠️ 서류 검토 취소 안내";

        String content = String.format(
                "안녕하세요, %s님.\n\n" +
                        "귀하의 서류 검토가 취소되었음을 안내드립니다. 검토 과정에서 불가피한 사유로 인해 검토를 완료할 수 없었습니다.\n\n" +
                        "취소 사유에 대해 궁금하시거나 추가적인 질문이 있으시면, 언제든지 저희에게 메일로 연락 주시기 바랍니다.\n\n" +
                        "다시 한 번 불편을 드려 죄송합니다. 귀하의 이해와 양해 부탁드립니다.\n\n" +
                        "감사합니다.\n\n",
                name
        );

        return new ReportCancelEmailTemplate(subject, content);
    }
}
