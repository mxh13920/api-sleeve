package com.meng.sleeve.core.configuration;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Map;

@Getter
@Setter
@ConfigurationProperties(prefix = "mxh")
@Component
@PropertySource(value = "classpath:config/Exception-code.properties")
@ToString
public class ExceptionConfiguration {
    Map<Integer,String> codes;

    public String getMessage(int code){
        String message = codes.get(code);
        return message;
    }
}
