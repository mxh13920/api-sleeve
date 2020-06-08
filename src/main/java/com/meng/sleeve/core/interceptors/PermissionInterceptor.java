package com.meng.sleeve.core.interceptors;

import com.auth0.jwt.interfaces.Claim;
import com.meng.sleeve.core.annotations.ScopeLevel;
import com.meng.sleeve.exception.http.ForbiddenException;
import com.meng.sleeve.exception.http.UnAuthenticatedException;
import com.meng.sleeve.utils.JwtToken;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;

public class PermissionInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //        获取scopeLevel注解
        Optional<ScopeLevel> scopeLevel = getScopeLevel(handler);
//        如果没有scopelevel注解直接通过
        if (!scopeLevel.isPresent()) {
            return true;
        }
//        获取token  ---Authorization约定
        // 令牌 ：Authorization:bearer <token>
        String bearerToken = request.getHeader("Authorization");
//        如果为null直接返回false
        if (StringUtils.isEmpty(bearerToken)) {
            throw new UnAuthenticatedException(10004);
        }
//        Token 是否具以 bearer 开头
        // 令牌 ：Authorization:bearer <token>
        if (!bearerToken.startsWith("Bearer")) {
            throw new UnAuthenticatedException(10004);
        }
//        以为bearer 和token有一个空格  Authorization:bearer <token>
        String token = bearerToken.split(" ")[1];
        Optional<Map<String, Claim>> claim = JwtToken.getClaim(token);
//        判断是否过期或合法
        Map<String, Claim> stringClaimMap = claim.orElseThrow(() -> new UnAuthenticatedException(10004));

        boolean validat = this.hasPermission(scopeLevel.get(), claim.get());
        return validat;
    }

    //    判断权限是否足够
    private boolean hasPermission(ScopeLevel scopeLevel, Map<String, Claim> map) {
        int level = scopeLevel.value();
        Integer scope = map.get("scope").asInt();
        if (level > scope) {
            throw new ForbiddenException(10005);
        }
        return true;
    }


    //    获取注解
    private Optional<ScopeLevel> getScopeLevel(Object handler) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            ScopeLevel annotation = handlerMethod.getMethod().getAnnotation(ScopeLevel.class);
            if (annotation == null) {
            return Optional.empty();
            }
            return Optional.of(annotation);
        }
        return Optional.empty();

    }
}
