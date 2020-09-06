package app.controller;

import app.model.Post;
import app.service.CategoryService;
import app.service.CommentService;
import app.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PostDetailController {

    private final CategoryService categoryService;
    private final PostService postService;
    private final CommentService commentService;

    public PostDetailController(CategoryService categoryService, PostService postService, CommentService commentService) {
        this.categoryService = categoryService;
        this.postService = postService;
        this.commentService = commentService;
    }

    @GetMapping("{category}/{path}/")
    public String getDetail(@PathVariable("category") String category,
                            @PathVariable("path") String path,
                            Model model){
        Post post = postService.postDetail(path);
        model.addAttribute("post", post);
        return "post-detail";
    }
}
