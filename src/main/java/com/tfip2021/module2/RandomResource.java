package com.tfip2021.module2;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import java.util.Arrays;

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
        
        int[] generatedNum = generateRandomNumbers(rn.getNum());
        System.out.println(generatedNum.toString());
        model.addAttribute("num", rn.getNum());
        model.addAttribute("gen", generatedNum);
        return "result";
    }

    public int[] generateRandomNumbers(int length) {
        Random rnd = ThreadLocalRandom.current();
        int[] choices = IntStream.range(RANGE_MIN, RANGE_MAX).toArray();
        for (int i = choices.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = choices[index];
            choices[index] = choices[i];
            choices[i] = a;
        }
        return Arrays.copyOfRange(choices, 0, length);
    }
}
