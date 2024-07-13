package com.devnot.ApiFrontEnd.page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PageController {

    @Autowired
    private PageRepository pageRepository;

    @GetMapping("/page/{id}")
    public String getPage(@PathVariable Long id, Model model) {
        Page page = pageRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid page Id:" + id));
        model.addAttribute("title", page.getTitle());
        model.addAttribute("content", page.getContent());
        return "page";
    }

    @PostMapping("/page/")
    public void postPage(@RequestBody ContentDTO data) {
        Page page = new Page();
        page.setTitle(data.title());
        page.setContent(data.content());
        pageRepository.save(page);
    }
}
