package com.codeplay.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class Html5PathsController {
    //TODO:세션에서 토큰 저장해야함.
    @RequestMapping(value = "/{path:[^\\.]*}")
    public String forward() {
        log.info("forward 처리");
        return "forward:/index.html";
    }
}
