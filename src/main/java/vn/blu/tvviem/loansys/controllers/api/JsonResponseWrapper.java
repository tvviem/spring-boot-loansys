package vn.blu.tvviem.loansys.controllers.api;

import lombok.Data;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice(basePackages = "vn.blu.tvviem.loansys.controllers.api")
public class JsonResponseWrapper implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof Iterable && !(body instanceof Page)) {
            return new Wrapper((Iterable<Object>) body);
        }
        return body;
    }

    @Data // just the lombok annotation which provides getter and setter
    private class Wrapper<T> {
        private final Iterable<T> arrObjects;

        Wrapper(Iterable<T> arrObjects) {
            this.arrObjects = arrObjects;
        }
    }
}
