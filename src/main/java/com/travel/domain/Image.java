package com.travel.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class Image {

    @Id
    @Column(name = "idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idx;

    @Column(name = "contentType")
    private String contentType;

    @Column(name = "fileName")
    private String fileName;

    @Column(name = "filePath")
    private String filePath;

    @Column(name = "registerDate")
    private Date registerDate;

    @Column(name = "saveFileName")
    private String saveFileName;

    @Column(name = "size")
    private int size;

    @Builder
    public Image(int idx, String contentType, String fileName, String filePath, Date registerDate, String saveFileName, int size) {
        this.idx = idx;
        this.contentType = contentType;
        this.fileName = fileName;
        this.filePath = filePath;
        this.registerDate = registerDate;
        this.saveFileName = saveFileName;
        this.size = size;
    }
}
