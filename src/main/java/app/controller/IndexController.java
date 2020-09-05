package app.controller;

import app.model.Category;
import app.model.Post;
import app.service.CategoryService;
import app.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class IndexController {

    private final PostService postService;
    private final CategoryService categoryService;

    public IndexController(PostService postService, CategoryService categoryService) {
        this.postService = postService;
        this.categoryService = categoryService;
    }

    @RequestMapping("/")
    public String home(Model model) {

        return listByPage(model, 1);

    }

    @GetMapping("/page/{pageNumber}")
    public String listByPage(Model model,
                             @PathVariable("pageNumber") int currentPage){
        Page<Post> page = postService.listAll(currentPage);
        long totalItem = page.getTotalElements();
        int totalPages = page.getTotalPages();
        List<Integer> listTotalPages = IntStream.rangeClosed(1, page.getTotalPages()).boxed().collect(Collectors.toList());
        List<Post> all = page.getContent();
        List<Category> categories = categoryService.allCategory();

        model.addAttribute("categories", categories);
        model.addAttribute("posts", all);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalItem", totalItem);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("listTotalPages", listTotalPages);

        return "tech-index";


    }


}
