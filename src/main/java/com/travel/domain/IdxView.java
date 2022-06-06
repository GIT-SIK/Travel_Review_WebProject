package com.travel.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@Table (name="idx_view")
public class IdxView {
    @Id
    @Column(name = "view_idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int viewIdx;

    @Column(name = "best_three_view")
    private boolean bestThreeView;

    @Column(name = "slide_link_view")
    private boolean slideLinkView;

    @Column(name = "festival_view")
    private boolean festivalView;

    @Column(name = "festival_date")
    private Date festivalDate;

    @Column(name = "slide_title")
    private String slideTitle;




    @Builder
    public IdxView(int viewIdx, boolean bestThreeView, boolean slideLinkView, boolean festivalView, java.util.Date festivalDate) {
        this.viewIdx = viewIdx;
        this.bestThreeView = bestThreeView;
        this.slideLinkView = slideLinkView;
        this.festivalView = festivalView;
        this.festivalDate = festivalDate;
    }
}
