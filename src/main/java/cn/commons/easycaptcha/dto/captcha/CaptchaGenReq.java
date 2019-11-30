package cn.commons.easycaptcha.dto.captcha;

import lombok.Data;

@Data
public class CaptchaGenReq {

    private String userId;

    private String channel;
}