package com.travel.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@Table (name="tb_index")
public class TbIndex {
    @Id
    @Column(name = "index_key")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int indexKey;

    @Column(name = "best_three_view")
    private boolean bestThreeView;

    @Column(name = "slide_link_view")
    private boolean slideLinkView;

    @Column(name = "festival_view")
    private boolean festivalView;

    @Column(name = "festival_date")
    private Date festivalDate;


    @Builder
    public TbIndex(boolean bestThreeView, boolean slideLinkView, boolean festivalView, java.util.Date festivalDate) {
        this.bestThreeView = bestThreeView;
        this.slideLinkView = slideLinkView;
        this.festivalView = festivalView;
        this.festivalDate = festivalDate;
    }
}
