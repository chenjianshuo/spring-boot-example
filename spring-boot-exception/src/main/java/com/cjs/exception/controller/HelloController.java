package com.cjs.exception.controller;

import com.cjs.exception.exception.MyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by chenjianshuo on 2018/8/27 17:16.
 */
@Controller
public class HelloController {

    @RequestMapping("/hello")
    public String hello() throws Exception {
        throw new Exception("发生错误");
    }

    @RequestMapping("/json")
    public String json() throws MyException {
        throw new MyException("发生错误2");
    }

    @RequestMapping("/")
    public String index(ModelMap map) {
        map.addAttribute("host", "chenjianshuo");
        return "index";
    }

}