package com.meng.sleeve.core.hack;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

public class AutoPrefixUriMapping extends RequestMappingHandlerMapping {

    @Value("${sleeve.perfix}")
    private String doPath;

    @Override
    protected RequestMappingInfo getMappingForMethod(Method method, Class<?> handlerType) {
        RequestMappingInfo mappingForMethod = super.getMappingForMethod(method, handlerType);
        if (mappingForMethod != null) {
            String perfix = this.getPerfix(handlerType);
//            设置前缀
            RequestMappingInfo combine = RequestMappingInfo.paths(perfix).build().combine(mappingForMethod);
            return combine;
        }
        return mappingForMethod;
    }

//    获取报名前缀及格式化
    public String getPerfix(Class<?> handlerType) {
        String name = handlerType.getPackage().getName();
        String path=name.replaceAll(this.doPath,"");
        return path.replace(".","/");
    }
}
