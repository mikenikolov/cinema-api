package cinema.model.dto.request;

import cinema.annotation.FieldsValueMatch;
import cinema.annotation.ValidEmail;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.Size;
import lombok.Getter;

@Getter
@FieldsValueMatch(
        field = "password",
        fieldMatch = "repeatPassword",
        message = "Passwords do not match!"
)
public class UserRequestDto {
    @ValidEmail(message = "Wrong email format")
    private String email;
    @Size(min = 8, max = 40, message = "Password must be at least 8 characters long")
    private String password;
    @JsonProperty("repeat_password")
    private String repeatPassword;
}
