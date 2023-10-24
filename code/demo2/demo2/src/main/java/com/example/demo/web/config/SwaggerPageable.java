package com.example.demo.web.config;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * SwaggerPageable
 * swagger-ui.html에서 Pageable의 속성을 제대로 잡지 못해 RequestParam을 알맞은 속성으로 매핑 후 전달하기 위한 클래스
 */
@Getter
@Setter
@ApiModel
public class SwaggerPageable {
    @ApiModelProperty(value = "페이지 번호(0..N)", example = "1")
    private Integer page;

    @ApiModelProperty(value = "페이지 크기", allowableValues="range[0, 100]", example = "15")
    private Integer size;

    @ApiModelProperty(value = "정렬(사용법: 컬럼명,ASC or DESC)")
    private List<String> sort;
}