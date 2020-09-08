package app.controller;

import app.model.Post;
import app.service.CategoryService;
import app.service.CommentService;
import app.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

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
        List<Post> getRelatedPosts = postService.relatedPosts(category);
        model.addAttribute("post", post);
        model.addAttribute("relatedPosts", getRelatedPosts);
        return "post-detail";
    }
}
