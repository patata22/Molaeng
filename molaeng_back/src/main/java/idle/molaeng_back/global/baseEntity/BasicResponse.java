package idle.molaeng_back.global.baseEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BasicResponse<T> {

    private HttpStatus statusCode;
    private String message;
    private T data;

    public BasicResponse(HttpStatus statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
