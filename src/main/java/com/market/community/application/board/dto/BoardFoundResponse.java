package com.market.community.application.board.dto;

import java.time.LocalDateTime;

public record BoardFoundResponse(
        Long id,
        String writerNickname,
        String title,
        String content,
        Long likeCount,
        Boolean isMyPost,
        LocalDateTime createdDate
) {
}
