package cinema.service.mapper;

import cinema.model.ShoppingCart;
import cinema.model.Ticket;
import cinema.model.dto.response.ShoppingCartResponseDto;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartMapper implements
        ResponseDtoMapper<ShoppingCartResponseDto, ShoppingCart> {

    @Override
    public ShoppingCartResponseDto mapToDto(ShoppingCart shoppingCart) {
        ShoppingCartResponseDto responseDto = new ShoppingCartResponseDto();
        responseDto.setUserId(shoppingCart.getUser().getId());
        responseDto.setTicketIds(shoppingCart.getTickets()
                .stream()
                .map(Ticket::getId)
                .collect(Collectors.toList()));
        return responseDto;
    }
}
