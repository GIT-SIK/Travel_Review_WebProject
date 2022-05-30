package com.travel.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class Festival {

    @Id
    @Column(name = "idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idx;

    @Column(name = "name")
    private String name;

    @Column(name = "location")
    private String location;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "content")
    private String content;

    @Column(name = "tel")
    private String tel;

    @Column(name = "homepage")
    private String homepage;

    @Column(name = "road_address")
    private String roadAddress;

    @Column(name = "lot_num_address")
    private String lotNumAddress;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;

    @Builder
    public Festival(int idx, String name, String location, Date startDate, Date endDate, String content, String tel, String homepage, String roadAddress, String lotNumAddress, String latitude, String longitude) {
        this.idx = idx;
        this.name = name;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.content = content;
        this.tel = tel;
        this.homepage = homepage;
        this.roadAddress = roadAddress;
        this.lotNumAddress = lotNumAddress;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
