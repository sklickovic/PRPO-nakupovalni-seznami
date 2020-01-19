package si.fri.prpo.nakupovalniseznami.api.v1.mappers;

import org.mapstruct.Mapper;
import si.fri.prpo.nakupovalniseznami.dto.IzdelkiDTO;
import si.fri.prpo.nakupovalniseznami.entitete.Izdelki;

import java.util.List;

@Mapper(componentModel = "cdi",
        uses = IzdelkiMapper.class)
public interface IzdelkiMapper {
    Izdelki mapToIzdelki (IzdelkiDTO dto);
    IzdelkiDTO mapToIzdelkiDTO (Izdelki entiteta);

    List<Izdelki> mapToIzdelkiList (List<IzdelkiDTO> dtoList);
    List<IzdelkiDTO> mapToIzdelkiDTOList (List<Izdelki> enitetaList);
}
