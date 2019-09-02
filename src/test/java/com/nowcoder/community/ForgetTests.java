package com.nowcoder.community;

import com.nowcoder.community.util.CommunityUtil;
import com.nowcoder.community.util.MailClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * Title: ForgetTests
 * ProjectName: community
 * Function:  TODO
 * author     Yiming Zhao
 * Date:      2019-07-08 22:51
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class ForgetTests {

    @Autowired
    private MailClient mailClient;

    @Autowired
    private TemplateEngine templateEngine;

    @Test
    public void testHtmlMail() {

        Context context = new Context();
        context.setVariable("email", "zym0719@foxmail.com");
        String captchaCode = CommunityUtil.generateUUID().substring(0, 6);
        context.setVariable("captchaCode", captchaCode);
        String content = templateEngine.process("/mail/forget", context);
        System.out.println(content);
        mailClient.sendMail("zym0719@foxmail.com", "忘记密码 验证码", content);
    }
}
