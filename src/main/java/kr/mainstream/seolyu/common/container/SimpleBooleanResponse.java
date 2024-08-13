package kr.mainstream.seolyu.common.container;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
@ApiModel(value = "응답: 공통 Boolean 응답")
public class SimpleBooleanResponse {

    @ApiModelProperty(value = "결과", allowableValues = "true, false")
    private final Boolean result;

    public SimpleBooleanResponse(Boolean result) {
        this.result = result;
    }
}
