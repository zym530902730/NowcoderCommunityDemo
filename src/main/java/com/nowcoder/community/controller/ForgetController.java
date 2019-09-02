package com.nowcoder.community.controller;

import com.nowcoder.community.service.UserService;
import com.nowcoder.community.util.CommunityUtil;
import com.nowcoder.community.util.MailClient;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Title: ForgetController
 * ProjectName: community
 * Function:  TODO
 * author     Yiming Zhao
 * Date:      2019-07-08 21:49
 */
@Controller
public class ForgetController {

    @Autowired
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private MailClient mailClient;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @RequestMapping(path = "/forget", method = RequestMethod.GET)
    public String getForgetPage() {
        return "site/forget";
    }



    // 获取验证码
    @RequestMapping(path = "/forget/code", method = RequestMethod.GET)
    @ResponseBody
    public String getForgetCode(String email, HttpSession session) {
        if (StringUtils.isBlank(email)) {
            return CommunityUtil.getJSONString(1, "邮箱不能为空！");
        }

        // 发送邮件
        Context context = new Context();
        context.setVariable("email", email);
        String code = CommunityUtil.generateUUID().substring(0, 4);
        context.setVariable("verifyCode", code);
        String content = templateEngine.process("/mail/forget", context);
        mailClient.sendMail(email, "找回密码", content);

        // 保存验证码
        session.setAttribute("verifyCode", code);

        return CommunityUtil.getJSONString(0);
    }

    // 重置密码
    @RequestMapping(path = "/forget/password", method = RequestMethod.POST)
    public String resetPassword(String email, String verifyCode, String password, Model model, HttpSession session) {
        String code = (String) session.getAttribute("verifyCode");
        System.out.println(code);

        System.out.println(verifyCode);

        if (StringUtils.isBlank(verifyCode) || StringUtils.isBlank(code) || !code.equalsIgnoreCase(verifyCode)) {
            model.addAttribute("codeMsg", "验证码错误!");
            return "/site/forget";
        }

        Map<String, Object> map = userService.resetPassword(email, password);
        if (map.containsKey("user")) {
            return "redirect:/login";
        } else {
            model.addAttribute("emailMsg", map.get("emailMsg"));
            model.addAttribute("passwordMsg", map.get("passwordMsg"));
            return "/site/forget";
        }
    }

//    @RequestMapping(path = "/forget", method = RequestMethod.POST)
//    public void getCaptchaCode(HttpServletResponse response, HttpSession session) {
//        // 生成验证码
//        String captchaCode = kaptchaProducer.createText();
//        BufferedImage image = kaptchaProducer.createImage(text);
//
//        // 将验证码存入session
//        session.setAttribute("captchaCode", captchaCode);
//
//        // 将图片输出给浏览器
//        response.setContentType("image/png");
//        try {
//            OutputStream os = response.getOutputStream();
//            ImageIO.write(image, "png", os);
//        } catch (IOException e) {
//            logger.error("响应验证码失败" + e.getMessage());
//        }
//    }

//    @RequestMapping(path = "/sendVerifycode/{email}", method = RequestMethod.GET)
//    public String sendVerifycode(String email, HttpSession session, Model model) {
//        Map<String, Object> map = userService.sendVerifycode(email);
//        if (map == null || map.isEmpty()) {
//            String captchaCode = map.get("captchaCode").toString();
//            // 将验证码存入session
//            session.setAttribute("captchaCode", captchaCode);
//            return "/site/forget";
//        } else {
//            model.addAttribute("emailMsg", map.get("emailMsg"));
//            return "/site/forget";
//        }
//    }
//
//    @RequestMapping(path = "/forget", method = RequestMethod.POST)
//    public String forget(String email, String password, Model model, HttpSession session) {
//        // 检查验证码
//        String captchaCode = (String) session.getAttribute("captchaCode");
//        Map<String, Object> map  = userService.forget(email, captchaCode, password);
//        if (map == null || map.isEmpty()) {
//            return "redirect:/index";
//        } else {
//            model.addAttribute("emailMsg", map.get("emailMsg"));
//            model.addAttribute("passwordMsg",map.get("passwordMsg"));
//            model.addAttribute("captchaCodeMsg",map.get("captchaCodeMsg"));
//            return "/forget";
//        }
//
//
//    }



}
