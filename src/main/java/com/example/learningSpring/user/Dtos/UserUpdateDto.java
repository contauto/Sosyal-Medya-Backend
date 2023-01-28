package com.example.learningSpring.user.Dtos;

import com.example.learningSpring.shared.FileType;
import com.example.learningSpring.sos.AppropriateWord;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserUpdateDto {
    @NotNull
    @Size(min = 4, max = 64)
    @AppropriateWord
    private String name;
    @FileType(types = {"jpg", "jpeg", "webp", "png"})
    private String image;
}
