package app.repo;

import app.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PostRepo extends JpaRepository<Post, Integer> {


    Page<Post> findAllByCategoryName(String name ,  Pageable pageable);

    List<Post> findAllByCategoryName(String name);

    Post findByPath(String path);
}
