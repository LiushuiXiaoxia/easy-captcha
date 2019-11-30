package cn.commons.easycaptcha.controller;

import cn.commons.easycaptcha.dto.BaseResp;
import cn.commons.easycaptcha.dto.captcha.CaptchaCheckReq;
import cn.commons.easycaptcha.dto.captcha.CaptchaGenData;
import cn.commons.easycaptcha.dto.captcha.CaptchaGenReq;
import cn.commons.easycaptcha.dto.captcha.CaptchaQueryReq;
import cn.commons.easycaptcha.service.ICaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/captcha")
public class CaptchaController {

    @Autowired
    ICaptchaService captchaService;

    @RequestMapping(path = "/gen", method = RequestMethod.POST)
    public BaseResp<CaptchaGenData> gen(@RequestBody CaptchaGenReq req) {
        CaptchaGenData data = captchaService.gen(req);
        return BaseResp.success(data);
    }

    @RequestMapping(path = "/check", method = RequestMethod.POST)
    public BaseResp<Boolean> check(@RequestBody CaptchaCheckReq req) {
        boolean check = captchaService.check(req);
        return BaseResp.success(check);
    }

    @RequestMapping(path = "/query", method = RequestMethod.POST)
    public BaseResp<String> query(@RequestBody CaptchaQueryReq req) {
        String text = captchaService.query(req);
        return BaseResp.success(text);
    }
}