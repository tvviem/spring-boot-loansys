package vn.blu.tvviem.loansys.exceptions.types;

// @ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
    /*public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }*/
}