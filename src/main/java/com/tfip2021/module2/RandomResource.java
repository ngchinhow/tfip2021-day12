package com.tfip2021.module2;

import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ThreadLocalRandom;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = { "/random" })
public class RandomResource {
    // private static final Logger logger = LoggerFactory.getLogger(Workshop12Application.class);
    private static final int RANGE_MIN = 1;
    private static final int RANGE_MAX = 31;

    @GetMapping(produces = { "text/html" })
    public String getRandom(@ModelAttribute RandomNumber rn, Model model) {
        if (rn.getNum() < RANGE_MIN || rn.getNum() >= RANGE_MAX)
            return "error";
        
        Integer[] generatedNum = generateRandomNumbers(rn.getNum());
        System.out.println(generatedNum.toString());
        model.addAttribute("num", rn.getNum());
        model.addAttribute("gen", generatedNum);
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
