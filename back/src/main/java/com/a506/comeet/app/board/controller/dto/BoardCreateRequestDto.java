package com.a506.comeet.app.board.controller.dto;

import com.a506.comeet.common.enums.BoardType;
import com.a506.comeet.common.enums.FreeBoardCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardCreateRequestDto {

    private final Integer likeCount = 0;
    private final Boolean isValid = true;
    private String writerId;
    private String title;
    private String content;
    private BoardType type;
    private FreeBoardCategory category;
    private Long roomId;
}
