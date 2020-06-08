package com.meng.sleeve.vo;

import com.meng.sleeve.model.Activity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class ActivityPureVo {
    private Long id;
    private String title;
    private String entranceImg;
    private Boolean online;
    private String remark;
    private String startTime;
    private String endTime;

    public ActivityPureVo(Activity activity){
        BeanUtils.copyProperties(activity,this);
    }

    public ActivityPureVo(Object object){
        BeanUtils.copyProperties(object,this);
    }
}
