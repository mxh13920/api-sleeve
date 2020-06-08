package com.meng.sleeve.dto;

import com.meng.sleeve.dto.validators.PasswordEqual;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

@Getter
@Builder
@Setter
@ToString
@PasswordEqual(message = "两次密码不同")
public class PersonDTO {
    @Length(min = 2)
    private String name;
    private Integer age;

//    @Valid
//    private SchoolDTO schoolDTO;
    private String password1;
    private String password2;
}
