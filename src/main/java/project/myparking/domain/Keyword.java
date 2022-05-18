package project.myparking.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Keyword {
    @Id @Column//(name = "KID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String spacious;
    private String difficulty;
    private String pricefairness;
    private String gentleness;
    private String revisitYN;

    @OneToMany(mappedBy = "keyword")
    List<Review> reviewList = new ArrayList<>();

    @Builder
    public Keyword(String spacious, String difficulty,
                   String pricefairness, String gentleness,
                   String revisitYN)
    {
        this.spacious = spacious;
        this.difficulty = difficulty;
        this.pricefairness = pricefairness;
        this.gentleness = gentleness;
        this.revisitYN = revisitYN;
    }


}
