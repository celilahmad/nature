package app.service;

import app.model.Post;
import app.repo.PostRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepo postRepo;

    public PostService(PostRepo postRepo) {
        this.postRepo = postRepo;
    }

    public Page<Post> listAll(int pageNumber){
        Sort sort = Sort.by("date").descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, 6, sort);
        return postRepo
                .findAll(pageable);


    }

    public List<Post> relatedPosts(String category){
        return postRepo
                .findAllByCategoryName(category)
                .stream()
                .limit(6)
                .collect(Collectors.toList());
    }

    public Page<Post> listAllByCategory(int pageNumber, String name){
        Sort sort = Sort.by("date").descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, 6, sort);
        return
                postRepo.findAllByCategoryName(name, pageable);


    }

    public List<Post> searchResult(String keyword){

        return postRepo
                .findAll()
                .stream()
                .filter(x -> x.getTitle().contains(keyword))
                .collect(Collectors.toList());

    }

    public Post postDetail(String path) {
        return postRepo.findByPath(path);

    }
}
