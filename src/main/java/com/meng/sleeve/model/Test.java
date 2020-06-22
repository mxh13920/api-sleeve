package com.meng.sleeve.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@Scope(value = "pototype",proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Test {
    private String name="1";
}
