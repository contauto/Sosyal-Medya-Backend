package com.example.learningSpring.sos.Dtos;

import com.example.learningSpring.sos.AppropriateWord;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class SosSubmitDto {
    @Size(min = 1, max = 1000)
    @AppropriateWord
    private String content;
    private long attachmentId;
}
