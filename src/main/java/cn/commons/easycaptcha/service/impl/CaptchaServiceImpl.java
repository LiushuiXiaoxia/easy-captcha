package cn.commons.easycaptcha.service.impl;

import cn.commons.easycaptcha.dto.captcha.CaptchaCheckReq;
import cn.commons.easycaptcha.dto.captcha.CaptchaGenData;
import cn.commons.easycaptcha.dto.captcha.CaptchaGenReq;
import cn.commons.easycaptcha.dto.captcha.CaptchaQueryReq;
import cn.commons.easycaptcha.service.ICaptchaService;
import cn.commons.easycaptcha.util.Base64Util;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

@Service
public class CaptchaServiceImpl implements ICaptchaService {

    @Autowired
    CaptchaCache cache;

    @Autowired
    DefaultKaptcha defaultKaptcha;

    @Override
    public CaptchaGenData gen(CaptchaGenReq genReq) {
        String captchaId = UUID.randomUUID().toString();
        String text = defaultKaptcha.createText();
        BufferedImage image = defaultKaptcha.createImage(text);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "jpg", outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        CaptchaGenData dto = new CaptchaGenData();
        dto.setCaptchaId(captchaId);
        dto.setImageBase64Header("data:image/jpeg;base64,");
        dto.setImageBase64(Base64Util.base64(outputStream.toByteArray()));
        // dto.setHash(Md5Util.hash(captchaId + text));

        String key = cacheKey(genReq.getChannel(), genReq.getUserId(), captchaId);
        cache.put(key, text);
        return dto;
    }

    @Override
    public boolean check(CaptchaCheckReq dto) {
        String key = cacheKey(dto.getChannel(), dto.getUserId(), dto.getCaptchaId());
        String s = cache.get(key);
        String text = dto.getCaptchaText();
        if (!Strings.isNullOrEmpty(text)) {
            if (text.equalsIgnoreCase(s)) {
                cache.remove(key);
                return true;
            }
        }
        return false;
    }

    @Override
    public String query(CaptchaQueryReq dto) {
        return cache.get(cacheKey(dto.getChannel(), dto.getUserId(), dto.getCaptchaId()));
    }

    private static String cacheKey(String channel, String userId, String captchaId) {
        return String.format("captcha_%s_%s_%s", channel, userId, captchaId);
    }
}