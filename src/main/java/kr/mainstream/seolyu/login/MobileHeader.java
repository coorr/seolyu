package kr.mainstream.seolyu.login;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.util.StringUtils;


@Getter
@ToString
@AllArgsConstructor
public class MobileHeader {
    private String userAgent;
    private String mid;
    private String deviceId;
    private String launchToken;
    private String platform;
    private String os;
    private String appVer;
    private String browser;

    public static MobileHeader valueOf(HttpServletRequest request) {
        String platform = request.getHeader("platform");
        String browser = request.getHeader("browser");

        if (!StringUtils.hasLength(request.getHeader("deviceId"))) {
            platform = "pc";
            browser = request.getHeader("User-Agent");
        }

            return new MobileHeader(request.getHeader("User-Agent"),
                request.getHeader("mid"),
                request.getHeader("deviceId"),
                request.getHeader("launchToken"),
                platform,
                request.getHeader("os"),
                request.getHeader("appVer"),
                browser
        );
    }

    public Long getMemberId() {
        try {
            return Long.parseLong(mid);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean isAppCall() {
        return !"pc".equals(platform);
    }

    public boolean isWebCall() {
        return "pc".equals(platform);
    }
}
