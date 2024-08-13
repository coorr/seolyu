package kr.mainstream.seolyu.common.page;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
public class PageReqDto {
    private static final Integer PAGE_DEFAULT_SIZE = 20;

    @ApiModelProperty(value = "페이지 번호", example = "0", position = 100)
    private Integer page;

    @ApiModelProperty(value = "페이지 갯수", example = "20", position = 101)
    private Integer size;

    public Pageable generatePage() {
        return this.generatePage(PAGE_DEFAULT_SIZE);
    }

    public Pageable generatePage(Integer pageSize) {
        int page = validPage(this.page) ? this.page : 0;
        int size = validSize(this.size) ? this.size : pageSize;

        return PageRequest.of(page, size);
    }

    private boolean validPage(Integer page) {
        return page != null;
    }

    private boolean validSize(Integer size) {
        return !(size == null || size < 1);
    }
}


