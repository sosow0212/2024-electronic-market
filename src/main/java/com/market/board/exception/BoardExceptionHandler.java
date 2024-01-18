package com.market.board.exception;

import com.market.board.exception.exceptions.BoardNotFoundException;
import com.market.board.exception.exceptions.FileUploadFailureException;
import com.market.board.exception.exceptions.UnsupportedImageFormatException;
import com.market.board.exception.exceptions.WriterNotEqualsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BoardExceptionHandler {

    @ExceptionHandler(BoardNotFoundException.class)
    public ResponseEntity<String> handleBoardNotFoundException(final BoardNotFoundException e) {
        return getResponse(HttpStatus.NOT_FOUND, e);
    }

    @ExceptionHandler(WriterNotEqualsException.class)
    public ResponseEntity<String> handleWriterNotEqualsException(final WriterNotEqualsException e) {
        return getResponse(HttpStatus.UNAUTHORIZED, e);
    }

    @ExceptionHandler(UnsupportedImageFormatException.class)
    public ResponseEntity<String> handleUnsupportedImageFormatException(final UnsupportedImageFormatException e) {
        return getResponse(HttpStatus.BAD_REQUEST, e);
    }

    @ExceptionHandler(FileUploadFailureException.class)
    public ResponseEntity<String> handleFileUploadFailureException(final FileUploadFailureException e) {
        return getResponse(HttpStatus.INTERNAL_SERVER_ERROR, e);
    }

    private ResponseEntity<String> getResponse(final HttpStatus httpStatus, final RuntimeException e) {
        return ResponseEntity.status(httpStatus)
                .body(e.getMessage());
    }
}
