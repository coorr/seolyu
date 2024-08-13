package kr.mainstream.seolyu.common.container;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "응답: 공통 단순 결과 응답")
public class SimpleResponse {

    @ApiModelProperty(value = "결과")
    private String result;
}
