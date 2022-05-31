package com.travel.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class Board {

    @Id
    @Column(name = "idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idx;

    @Column(name = "userId")
    private String userId;

    @Column(name = "title")
    private String title;

    @Column(name = "time")
    private Date time;

    @Column(name = "content")
    private String content;

    @Column(name = "view")
    private int view;

    @Column(name = "recommend")
    private int recommend;

    @Column(name = "category")
    private String category;

    @Builder
    public Board(int idx, String userId, String title, Date time, String content, int view, int recommend, String category) {
        this.idx = idx;
        this.userId = userId;
        this.title = title;
        this.time = time;
        this.content = content;
        this.view = view;
        this.recommend = recommend;
        this.category = category;
    }
}
