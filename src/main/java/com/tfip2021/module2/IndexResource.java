package com.tfip2021.module2;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = { "/", "/index.html" })
public class IndexResource {
    @GetMapping(produces = { "text/html "})
    public String indexForm(Model model) {
        model.addAttribute("currTime", (new Date()).toString());
        model.addAttribute("rn", new RandomNumber());
        return "index";
    }
}
