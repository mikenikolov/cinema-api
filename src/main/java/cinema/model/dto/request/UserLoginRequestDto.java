package cinema.model.dto.request;

import cinema.annotation.ValidEmail;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginRequestDto {
    @NotBlank(message = "Login can't be null or blank!")
    @ValidEmail(message = "Wrong email format")
    private String email;
    @NotBlank(message = "Password can't be null or blank!")
    @Size(min = 8, max = 40, message = "Password must be at least 8 characters long")
    private String password;
}
