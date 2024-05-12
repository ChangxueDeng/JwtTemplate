package org.example.backend.entity.vo.request;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

/**
 * 注册账户请求
 * @author ChangxueDeng
 * @date 2024/04/02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterVO {
    @Length(min = 2, max = 10)
    private String username;
    @Length(min = 6, max = 16)
    private String password;
    @Email
    private String email;
    @Length(min = 6, max = 6)
    private String code;
}
