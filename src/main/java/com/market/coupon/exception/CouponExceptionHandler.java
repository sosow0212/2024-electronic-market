package com.market.coupon.exception;

import com.market.coupon.exception.exceptions.ContainsNotExistedCouponException;
import com.market.coupon.exception.exceptions.CouponAmountRangeInvalidException;
import com.market.coupon.exception.exceptions.CouponNotFoundException;
import com.market.coupon.exception.exceptions.MemberCouponSizeNotEqualsException;
import com.market.coupon.exception.exceptions.UsingAloneCouponContainsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CouponExceptionHandler {

    @ExceptionHandler(CouponAmountRangeInvalidException.class)
    public ResponseEntity<String> handleCouponAmountRangeInvalidException(final CouponAmountRangeInvalidException exception) {
        return getResponse(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CouponNotFoundException.class)
    public ResponseEntity<String> handleCouponNotFoundException(final CouponNotFoundException exception) {
        return getResponse(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MemberCouponSizeNotEqualsException.class)
    public ResponseEntity<String> handleMemberCouponSizeNotEqualsException(final MemberCouponSizeNotEqualsException exception) {
        return getResponse(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsingAloneCouponContainsException.class)
    public ResponseEntity<String> handleUsingAloneCouponContainsException(final UsingAloneCouponContainsException exception) {
        return getResponse(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ContainsNotExistedCouponException.class)
    public ResponseEntity<String> handleContainsNotExistedCouponException(final ContainsNotExistedCouponException exception) {
        return getResponse(exception, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<String> getResponse(final RuntimeException e, final HttpStatus httpStatus) {
        return ResponseEntity.status(httpStatus)
                .body(e.getMessage());
    }
}
