package cn.commons.easycaptcha.dto.captcha;

import lombok.Data;

@Data
public class CaptchaCheckReq {

    private String userId;

    private String channel;

    private String captchaId;

    private String captchaText;
}