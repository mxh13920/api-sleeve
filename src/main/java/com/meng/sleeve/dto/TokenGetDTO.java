package com.meng.sleeve.dto;

import com.meng.sleeve.core.enumeration.LoginType;
import com.meng.sleeve.dto.validators.TokenPassword;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class TokenGetDTO {

    @NotBlank(message = "账号不能为空")
//    account小程序传过的code吗
    private  String account;

    @TokenPassword(message = "{token.password}")
    private String password;

    private LoginType type;

}
