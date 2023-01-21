package com.example.learningSpring.sos;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Entity
public class Sos {
    @Id
    @GeneratedValue
    private long id;
    @Size(min = 1, max = 1000)
    @Column(length = 1000)
    private String content;
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
}
