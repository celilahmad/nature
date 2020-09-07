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

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class CategoryController {

    private final CategoryService categoryService;
    private final PostService postService;

    public CategoryController(CategoryService categoryService, PostService postService) {
        this.categoryService = categoryService;
        this.postService = postService;
    }

    @GetMapping("/category/{name}")
    public String getAllPosts(@PathVariable("name") String name,
                              Model model){
        return getCategory(name, 1, model);
    }

    @GetMapping("/category/{name}/page/{pageNumber}")
    public String getCategory(@PathVariable("name") String name,
                              @PathVariable("pageNumber") int currentPage,
                              Model model){
        Page<Post> page = postService.listAllByCategory(currentPage, name.toLowerCase());
        long totalItem = page.getTotalElements();
        int totalPages = page.getTotalPages();
        List<Integer> listTotalPages = IntStream.rangeClosed(1, page.getTotalPages()).boxed().collect(Collectors.toList());
        List<Post> all = page.getContent();
        List<Category> categories = categoryService.allCategory();

        model.addAttribute("paths", name);
        model.addAttribute("categories", categories);
        model.addAttribute("categoryPost", all);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalItem", totalItem);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("listTotalPages", listTotalPages);
        return "post-category";
    }
}
