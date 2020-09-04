package app.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private String description;

    private String content;

    private String author;

    private String date;

    @OneToMany
    private List<Image> images;

    @OneToMany
    private List<Category> category;

    @OneToMany
    private List<Comment> comments;
}
