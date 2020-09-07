package app.repo;

import app.model.Category;
import app.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostRepo extends JpaRepository<Post, Integer> {


    Page<Post> findAllByCategoryName(String name ,  Pageable pageable);

    Post findByPath(String path);
}
