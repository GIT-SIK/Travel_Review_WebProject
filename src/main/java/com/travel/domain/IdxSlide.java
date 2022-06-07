package com.travel.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table (name="idx_slide")
public class IdxSlide {
    @Id
    @Column(name = "slide_idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int slideIdx;

    @Column(name = "slide_link")
    private String slideLink;

    @Column(name = "slide_title")
    private String slideTitle;

    @Column(name = "slide_content")
    private String slideContent;

    @Column(name = "slide_position")
    private String slidePosition;



    @Builder
    public IdxSlide(int slideIdx , String slideLink, String slideTitle, String slideContent, String slidePosition) {
        this.slideIdx = slideIdx;
        this.slideLink = slideLink;
        this.slideTitle = slideTitle;
        this.slideContent = slideContent;
        this.slidePosition = slidePosition;
    }
}
