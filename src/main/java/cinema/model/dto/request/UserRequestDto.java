package cinema.model.dto.request;

import cinema.annotation.FieldsValueMatch;
import cinema.annotation.ValidEmail;
import javax.validation.constraints.Size;
import lombok.Getter;

@Getter
@FieldsValueMatch(
        field = "password",
        fieldMatch = "repeatPassword",
        message = "Passwords do not match!"
)
public class UserRequestDto {
    @ValidEmail
    private String email;
    @Size(min = 8, max = 40)
    private String password;
    private String repeatPassword;
}
