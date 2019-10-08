package io.github.yuanjg.core.captcha.image;

import com.google.code.kaptcha.Producer;
import io.github.yuanjg.core.captcha.CaptchaGenerate;
import io.github.yuanjg.core.captcha.entity.ImageCaptchaVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;

/**
 * 图片验证码生成实现类
 * @author yuanjg
 * @create 2019-10-06 9:37
 */


@Service("imageCaptchaGenerate")
public class ImageCaptchaGenerate implements CaptchaGenerate {

    @Autowired
    private Producer producer;//config bean中配置

    @Override
    public ImageCaptchaVo generate() {
        String code = producer.createText();
        BufferedImage bufferedImage = producer.createImage(code);
        return new ImageCaptchaVo(bufferedImage, code, 60 * 5);//5分钟过期
    }
}

