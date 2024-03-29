package com.market.community.infrastructure.comment;

import com.market.community.domain.comment.Comment;
import com.market.community.domain.comment.CommentRepository;
import com.market.community.domain.comment.dto.CommentSimpleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class CommentRepositoryImpl implements CommentRepository {

    private final CommentJpaRepository commentJpaRepository;
    private final CommentJdbcRepository commentJdbcRepository;
    private final CommentQueryRepository commentQueryRepository;

    @Override
    public Comment save(final Comment comment) {
        return commentJpaRepository.save(comment);
    }

    @Override
    public Optional<Comment> findById(final Long id) {
        return commentJpaRepository.findById(id);
    }

    public List<CommentSimpleResponse> findAllCommentsByBoardId(final Long boardId, final Long commentId, final int pageSize) {
        return commentQueryRepository.findAllWithPaging(boardId, commentId, pageSize);
    }

    @Override
    public void deleteById(final Long id) {
        commentJpaRepository.deleteById(id);
    }

    @Override
    public void deleteAllByBoardId(final Long boardId) {
        commentJdbcRepository.deleteAllCommentsByBoardId(boardId);
    }
}
