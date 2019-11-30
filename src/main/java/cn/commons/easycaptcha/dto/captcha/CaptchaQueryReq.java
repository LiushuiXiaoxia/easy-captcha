package cn.commons.easycaptcha.dto.captcha;

import lombok.Data;

@Data
public class CaptchaQueryReq {

    private String userId;

    private String channel;

    private String captchaId;
}