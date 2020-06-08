package com.meng.sleeve.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meng.sleeve.exception.http.NotFoundException;
import com.meng.sleeve.exception.http.ParameterException;
import com.meng.sleeve.model.User;
import com.meng.sleeve.repository.UserRepository;
import com.meng.sleeve.utils.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class WxAuthenticationService {

    @Value("${wx.appid}")
    private String appid;
    @Value("${wx.appsecret}")
    private String appsecret;
    @Value("${wx.code2Session}")
    private String code2Session;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper mapper;

    public String code2session(String code) {
//        拼接字符串
        String url = MessageFormat.format(this.code2Session, this.appid, this.appsecret, code);
//        发送请求(获取openid)
        RestTemplate restTemplate = new RestTemplate();
        String sassionText = restTemplate.getForObject(url, String.class);
//        反序列化
        Map<String, Object> sassion = new HashMap<>();
        try {
           sassion=mapper.readValue(sassionText,Map.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return this.register(sassion);
    }

//    查看数据库是否存在openid 返回jwt令牌
    private String register(Map<String, Object> sassion) {
        String openid = (String) sassion.get("openid");
        if (openid == null) {
            throw new ParameterException(20004);
        }
//        如果数据库存在openid
        Optional<User> userOpenid = userRepository.findByOpenid(openid);
        if (userOpenid.isPresent()) {
//            返回jwt令牌
            return JwtToken.makeToken(userOpenid.get().getId());
        }
//        不存在openi时
        User user = User.builder()
                .openid(openid)
                .build();
//        存入数据库
        User save = userRepository.save(user);
        Long id = save.getId();
//            返回jwt令牌
        return JwtToken.makeToken(id);
    }
}
