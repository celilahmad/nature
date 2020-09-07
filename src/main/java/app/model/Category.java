package app.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Entity
@Table(name = "category")
public class Category {

    @Id
    private int id;

    private String name;

    private String path;

    public Category(String name){
        this.name = name;
    }
}
