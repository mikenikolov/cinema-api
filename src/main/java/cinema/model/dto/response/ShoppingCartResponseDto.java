package cinema.model.dto.response;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ShoppingCartResponseDto {
    private Long userId;
    private List<Long> ticketIds;
}
