package project.myparking.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Keyword {
    @Id @Column(name = "KID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String word;

    @OneToMany(mappedBy = "keyword")
    List<Review> reviewList = new ArrayList<>();
}
