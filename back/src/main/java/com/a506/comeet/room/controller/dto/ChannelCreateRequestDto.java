package com.a506.comeet.room.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;

@Getter
public class ChannelCreateRequestDto {
    private Long roomId;
    @NotBlank
    @Size(min = 2, max = 15)
    private String name;

    public ChannelCreateRequestDto(Long roomId, String name) {
        this.roomId = roomId;
        this.name = name;
    }
}