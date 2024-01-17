package com.market.board.infrastructure.board;

import com.market.board.domain.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BoardJpaRepository extends JpaRepository<Board, Long> {

    Board save(final Board board);

    Optional<Board> findById(final Long id);

    @Query("SELECT DISTINCT b FROM Board b LEFT JOIN FETCH b.images WHERE b.id = :boardId")
    Optional<Board> findBoardWithImages(@Param("boardId") Long boardId);

    void deleteById(final Long id);
}