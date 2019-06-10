package vn.blu.tvviem.loansys.exceptions;

// Các tài nguyên hình ko tìm thấy
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
