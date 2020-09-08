package app.controller;

import app.model.Category;
import app.model.Post;
import app.service.CategoryService;
import app.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchController {

    private final PostService postService;
    private final CategoryService categoryService;

    public SearchController(PostService postService, CategoryService categoryService) {
        this.postService = postService;
        this.categoryService = categoryService;
    }

    @GetMapping("/search")
    public String getSearchResult(@RequestParam("search") String word,
                                  Model model){

        List<Post> result = postService.searchResult(word);
        List<Category> categories = categoryService.allCategory();
        model.addAttribute("searchResult", result);
        model.addAttribute("categories", categories);
        return "search-result";

    }
}
