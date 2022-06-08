package com.travel.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
@ToString
@Builder
public class Reply {
    @Id
    @Column(name = "rid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rid;

    @Column(name = "r_bid")
    private Integer rBid;

    @Column(name = "r_rid")
    private Integer rRid;

    @Column(name = "r_author")
    private String rAuthor;

    @Column(name = "r_content")
    private String rContent;

    @Column(name = "r_date")
    private String rDate;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "r_bid", referencedColumnName="idx", insertable = false, updatable = false)
    private Board board;

    @Builder
    public Reply(Integer rid, Integer rBid, Integer rRid, String rAuthor, String rContent,
        String rDate, Board board) {
        this.rid = rid;
        this.rBid = rBid;
        this.rRid = rRid;
        this.rAuthor = rAuthor;
        this.rContent = rContent;
        this.rDate = rDate;
        this.board = board;
    }
}
