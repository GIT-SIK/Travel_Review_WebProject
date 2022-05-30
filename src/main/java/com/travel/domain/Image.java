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

    @Column(name = "content_type")
    private String contentType;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "register_date")
    private Date registerDate;

    @Column(name = "save_file_name")
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
