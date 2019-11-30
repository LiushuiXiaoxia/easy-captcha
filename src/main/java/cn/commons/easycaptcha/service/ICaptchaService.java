package cn.commons.easycaptcha.service;

import cn.commons.easycaptcha.dto.captcha.CaptchaCheckReq;
import cn.commons.easycaptcha.dto.captcha.CaptchaGenData;
import cn.commons.easycaptcha.dto.captcha.CaptchaGenReq;
import cn.commons.easycaptcha.dto.captcha.CaptchaQueryReq;

public interface ICaptchaService {

    CaptchaGenData gen(CaptchaGenReq req);

    boolean check(CaptchaCheckReq dto);

    String query(CaptchaQueryReq dto);
}