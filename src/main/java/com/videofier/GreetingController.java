package com.videofier;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {

    private static List<String> videos = new ArrayList<>();
    private static List<String> wittyReturnMessage = new ArrayList<>();
    private static Random rand = new Random();

    static {
        videos.add("-n_5RdXD5go");
        videos.add("n6jp2eGOTf8");
        videos.add("O7D-1RG-VRk");

        wittyReturnMessage.add("Spin the dice.");
        wittyReturnMessage.add("Home, home again.");
        wittyReturnMessage.add("Deja Vu?");
        wittyReturnMessage.add("Retry.");
        wittyReturnMessage.add("Refreshing!");
        wittyReturnMessage.add("Just can't get enough..");
    }

    @RequestMapping("/")
    public String greeting(@RequestParam(value = "video", required = false, defaultValue = "") String video, Model model) {
        if (video == null || video.trim().isEmpty()) {
            int index = rand.nextInt(videos.size());
            video = videos.get(index);
            return "redirect:/?video=" + video;
        }
        if (!videos.contains(video)) {
            videos.add(video);
        }
        int index = rand.nextInt(wittyReturnMessage.size());

        model.addAttribute("wittyMessage", wittyReturnMessage.get(index));
        model.addAttribute("video", video);
        return "greeting";

    }

}
