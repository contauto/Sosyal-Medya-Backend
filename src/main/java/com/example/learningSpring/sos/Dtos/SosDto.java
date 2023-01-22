package com.example.learningSpring.sos.Dtos;

import com.example.learningSpring.sos.Sos;
import com.example.learningSpring.user.Dtos.UserDto;
import lombok.Data;

@Data
public class SosDto {
    private long id;
    private String content;
    private long timestamp;
    private UserDto userDto;

    public SosDto(Sos sos) {
        this.setId(sos.getId());
        this.setContent(sos.getContent());
        this.setTimestamp(sos.getTimestamp().getTime());
        this.setUserDto(new UserDto(sos.getUser()));
    }
}
