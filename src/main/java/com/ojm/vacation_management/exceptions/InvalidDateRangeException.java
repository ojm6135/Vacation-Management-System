package com.ojm.vacation_management.exceptions;


/**
 * 유효하지 않은 날짜 범위일 때 발생.
 * <p>시작 날짜가 종료 날짜 이후인 경우에 발생.</p>
 */
public class InvalidDateRangeException extends RuntimeException {
    public InvalidDateRangeException(String message) {
        super(message);
    }
}
