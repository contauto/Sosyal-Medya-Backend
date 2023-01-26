package com.example.learningSpring.sos;

import com.example.learningSpring.file.FileAttachment;
import com.example.learningSpring.user.User;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Sos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 1000)
    private String content;
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @ManyToOne
    private User user;
    @OneToOne(mappedBy = "sos")
    private FileAttachment fileAttachment;

}
