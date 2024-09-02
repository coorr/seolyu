package kr.mainstream.seolyu;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
public class KeepAliveController {
    @GetMapping("/health")
    public String ping() {
        return "ok";
    }
}
