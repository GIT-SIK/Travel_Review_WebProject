package com.travel.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class Reply {

    @Id
    @Column(name = "idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idx;

    @Column(name = "boardIdx")
    private int boardIdx;

    @Column(name = "userId")
    private String userId;

    @Column(name = "parentIdx")
    private int parentIdx;

    @Column(name = "time")
    private Date time;

    @Column(name = "content")
    private String content;

    @Builder
    public Reply(int idx, int boardIdx, String userId, int parentIdx, Date time, String content) {
        this.idx = idx;
        this.boardIdx = boardIdx;
        this.userId = userId;
        this.parentIdx = parentIdx;
        this.time = time;
        this.content = content;
    }
}
