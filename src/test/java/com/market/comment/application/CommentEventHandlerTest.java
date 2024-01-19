package com.market.comment.application;

import com.market.board.application.BoardService;
import com.market.board.domain.board.BoardRepository;
import com.market.helper.IntegrationHelper;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static com.market.board.fixture.BoardFixture.게시글_생성_사진있음;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class CommentEventHandlerTest extends IntegrationHelper {

    @MockBean
    private CommentEventHandler commentEventHandler;

    @MockBean
    private BoardRepository boardRepository;

    @Autowired
    private BoardService boardService;


    @Test
    void 게시글_삭제를_한다면_댓글_삭제_리스너가_동작한다() {
        // given
        when(boardRepository.findById(1L)).thenReturn(Optional.of(게시글_생성_사진있음()));

        // when
        boardService.deleteBoardById(1L, 1L);

        // then
        verify(commentEventHandler).deleteAllCommentsByBoardId(any());
    }
}
