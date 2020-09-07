package app.repo;

import app.model.Category;
import app.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

    Page<Post> findAllByName(String name, Pageable pageable);

}
