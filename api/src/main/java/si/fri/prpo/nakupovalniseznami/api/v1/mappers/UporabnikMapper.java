package si.fri.prpo.nakupovalniseznami.api.v1.mappers;

import org.mapstruct.Mapper;
import si.fri.prpo.nakupovalniseznami.dto.UporabnikDTO;
import si.fri.prpo.nakupovalniseznami.entitete.Uporabnik;

import java.util.List;

@Mapper(componentModel = "cdi",
        uses = UporabnikMapper.class)
public interface UporabnikMapper {
    Uporabnik mapToUporabnik (UporabnikDTO dto);
    UporabnikDTO mapToUporabnikDTO (Uporabnik entiteta);

    List<Uporabnik> mapToUporabnikList (List<UporabnikDTO> dtoList);
    List<UporabnikDTO> mapToUporabnikDTOList (List<Uporabnik> enitetaList);
}
