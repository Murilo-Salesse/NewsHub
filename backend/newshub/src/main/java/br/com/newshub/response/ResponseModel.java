package br.com.newshub.response;


public record ResponseModel<T>(T data, String message) {

    public static <T> ResponseModel<T> of(T data, String message) {
        return new ResponseModel<>(data, message);
    }

    public static <T> ResponseModel<T> ok(T data) {
        return new ResponseModel<>(data, "Success");
    }
}

