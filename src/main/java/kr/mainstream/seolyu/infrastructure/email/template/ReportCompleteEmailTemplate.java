package kr.mainstream.seolyu.infrastructure.email.template;

public class ReportCompleteEmailTemplate extends EmailTemplate {
    public ReportCompleteEmailTemplate(String subject, String content) {
        super(subject, content);
    }

    public static EmailTemplate create(String name, String reportId) {
        String subject = "\uD83C\uDF1F서류 검토 완료 안내";

        String content = String.format(
                "안녕하세요, %s님.\n\n" +
                        "귀하의 서류 검토가 완료되었습니다. 아래 링크를 클릭하시면 리포트를 확인하실 수 있습니다.\n\n" +
                        "리포트 확인하기: https://seolyu.com/resume-report/%s\n\n" +
                        "리포트에는 귀하의 서류에 대한 자세한 검토 결과와 피드백이 포함되어 있습니다.\n\n" +
                        "추가적인 질문이나 도움이 필요하시면 언제든지 메일로 연락 주시기 바랍니다.\n\n" +
                        "감사합니다.\n\n",
                name, reportId
        );

        return new ReportCompleteEmailTemplate(subject, content);
    }
}
