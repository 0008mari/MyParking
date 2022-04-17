package project.myparking.domain;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Review {

    @Id @GeneratedValue
    @Column(name = "review_id")
    private Long id;

    @ManyToOne
    private Member member;

    @ManyToOne
    private Parkinglot parkinglot;

    @ManyToMany
    private List<Keyword> keywordList = new ArrayList<>();

    private String contents;

    private Date createdDate;

}


