package com.nowcoder.community;

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
 * Title: MailTests
 * ProjectName: community
 * Function:  TODO
 * author     Yiming Zhao
 * Date:      2019-07-04 23:21
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class MailTests {

    @Autowired
    private MailClient mailClient;

    @Autowired
    private TemplateEngine templateEngine;

    @Test
    public void testTextMail() {
        mailClient.sendMail("zym0719@foxmail.com", "Test", "Welcome.");
    }

    @Test
    public void testHtmlMail() {
        Context context = new Context();
        context.setVariable("username", "Crow");
        String content = templateEngine.process("/mail/demo", context);
        System.out.println(content);

        mailClient.sendMail("zym0719@foxmail.com", "HtmlMailTest", content);
    }

}
