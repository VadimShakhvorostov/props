package props.exception;

import lombok.Getter;

@Getter
public class ExceptionRespons {

    private final String description;

    public ExceptionRespons(String description) {
        this.description = description;
    }
}
