package cinema.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ShoppingCartResponseDto {
    @JsonProperty("user_id")
    private Long userId;
    @JsonProperty("ticket_ids")
    private List<Long> ticketIds;
}
