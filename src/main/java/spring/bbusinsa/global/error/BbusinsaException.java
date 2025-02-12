package spring.bbusinsa.global.error;

import lombok.Getter;

@Getter
public class BbusinsaException extends RuntimeException {

    private final ErrorType errorType;

    private final Object data;

    public BbusinsaException(ErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
        this.data = null;
    }

    public BbusinsaException(ErrorType errorType, Object data) {
        super(errorType.getMessage());
        this.errorType = errorType;
        this.data = data;
    }

}
