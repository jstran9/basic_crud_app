package tran.example.basicwebapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This run time exception can help our web app send back a certain status code to the user.
 */
@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {
    public BadRequestException() {
    }

    public BadRequestException(Throwable cause) {
        super(cause);
    }
}
