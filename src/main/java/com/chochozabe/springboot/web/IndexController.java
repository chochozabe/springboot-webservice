package com.chochozabe.springboot.web;

import com.chochozabe.springboot.config.auth.LoginUser;
import com.chochozabe.springboot.config.auth.dto.SessionUser;
import com.chochozabe.springboot.dto.PostsResponseDTO;
import com.chochozabe.springboot.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());

        if (user != null) {
            System.out.println("==================================");
            System.out.println(user.toString());
            model.addAttribute("loginName", user.getName());
        }

        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDTO dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }


}
