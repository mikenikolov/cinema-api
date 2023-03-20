package cinema.model.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderResponseDto {
    private Long id;
    @JsonProperty("ticket_ids")
    private List<Long> ticketIds;
    @JsonProperty("user_id")
    private Long userId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonProperty("order_time")
    private LocalDateTime orderTime;
}
