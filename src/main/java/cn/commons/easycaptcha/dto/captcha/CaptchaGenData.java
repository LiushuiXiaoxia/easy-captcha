package cn.commons.easycaptcha.dto.captcha;

import lombok.Data;

@Data
public class CaptchaGenData {

    private String captchaId;

    private String imageBase64Header;

    private String imageBase64;

    // private String hash;
}