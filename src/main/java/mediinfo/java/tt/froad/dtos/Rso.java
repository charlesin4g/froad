package mediinfo.java.tt.froad.dtos;

import lombok.Data;

@Data
public class Rso<T> {
    private boolean isSuccess = true;

    private String message;

    private T data;

    public Rso(boolean isSuccess, String message) {
        this.isSuccess = isSuccess;
        this.message = message;
    }

    public Rso(String message) {
        this.message = message;
        this.data = null;
    }

    public Rso(T data) {
        this.data = data;
    }

    public Rso(String message, T data) {
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess() {
        return isSuccess;
    }
}
