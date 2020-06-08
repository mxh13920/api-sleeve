package com.meng.sleeve.api.v1;

import com.meng.sleeve.dto.TokenDTO;
import com.meng.sleeve.dto.TokenGetDTO;
import com.meng.sleeve.exception.http.ForbiddenException;
import com.meng.sleeve.service.WxAuthenticationService;
import com.meng.sleeve.utils.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "token")
public class TokenController {

    @Autowired
    private WxAuthenticationService wxAuthenticationService;

    @PostMapping("")
    public Map<String, String> getToken(@RequestBody @Validated TokenGetDTO tokenGetDTO) {
        Map<String,String> map=new HashMap<>();
        String token = null;
        switch (tokenGetDTO.getType()){
            case USER_WX:
                 token = wxAuthenticationService.code2session(tokenGetDTO.getAccount());
                break;
            case USER_Email:
                break;
            default:
                throw new ForbiddenException(10003);
        }
        map.put("token",token);
        return map;
    }

    @PostMapping("/verify")
    public Map<String,Boolean> verify(@RequestBody TokenDTO tokenDTO){
        Map<String,Boolean> map=new HashMap<>();
        Boolean valid = JwtToken.verifyToken(tokenDTO.getToken());
        map.put("verify",valid);
        return map;
    }

}
