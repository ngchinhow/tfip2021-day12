package com.tfip2021.module2;

import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = { "/random" })
public class RandomResource {
    private static final int RANGE_MIN = 1;
    private static final int RANGE_MAX = 31;

    @GetMapping(produces = { "text/html" })
    public String getRandom(@ModelAttribute RandomNumber rn, Model model) {
        model.addAttribute("num", rn.getNum());
        model.addAttribute("gen", generateRandomNumbers(rn.getNum()));
        return "result";
    }

    public Integer[] generateRandomNumbers(int length) {
        Set<Integer> generatedNum = new TreeSet<Integer> ();
        while (generatedNum.size() < length) {
            generatedNum.add(ThreadLocalRandom.current().nextInt(
                RANGE_MIN, RANGE_MAX
            ));
        }
        return generatedNum.toArray(new Integer[length]);
    }
}
