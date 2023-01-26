package com.example.learningSpring.sos.Dtos;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class SosSubmitDto {
    @Size(min = 1, max = 1000)
    private String content;
    private long attachmentId;
}
