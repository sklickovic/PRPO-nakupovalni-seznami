package si.fri.prpo.nakupovalniseznami.api.v1.mappers;

import org.mapstruct.Mapper;
import si.fri.prpo.nakupovalniseznami.dto.WishListDTO;
import si.fri.prpo.nakupovalniseznami.entitete.WishList;

import java.util.List;

@Mapper(componentModel = "cdi",
        uses = WishListMapper.class)
public interface WishListMapper {
    WishList mapToWishList (WishListDTO dto);
    WishListDTO mapToWishListDTO (WishList entiteta);

    List<WishList> mapToWishListList (List<WishListDTO> dtoList);
    List<WishListDTO> mapToWishListDTOList (List<WishList> enitetaList);
}
