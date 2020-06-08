package com.meng.sleeve.bo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PageCount {
    private Integer page;
    private Integer count;
}
