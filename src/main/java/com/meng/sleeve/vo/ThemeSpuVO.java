package com.meng.sleeve.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ThemeSpuVO extends ThemePureVO{
    List<SpuSimplifyVO> spulist;
}
