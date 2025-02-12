package spring.bbusinsa.global.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.logging.LogLevel;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorType {

    DEFAULT_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.E500, "An unexpected error has occurred.", LogLevel.ERROR),

    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, ErrorCode.E404, "Member not found.", LogLevel.ERROR),
    MEMBER_GENDER_IS_INVALID(HttpStatus.BAD_REQUEST, ErrorCode.E400, "Member Gender Is Not Available.", LogLevel.ERROR),

    PRODUCT_CATEGORY_IS_INVALID(HttpStatus.BAD_REQUEST, ErrorCode.E400, "Product Category Is Not Available.", LogLevel.ERROR),
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, ErrorCode.E404, "Product not found.", LogLevel.ERROR),
    MARKET_NOT_FOUND(HttpStatus.NOT_FOUND, ErrorCode.E404, "Market not found.", LogLevel.ERROR),
    ;

    private final HttpStatus status;

    private final ErrorCode code;

    private final String message;

    private final LogLevel logLevel;

}
