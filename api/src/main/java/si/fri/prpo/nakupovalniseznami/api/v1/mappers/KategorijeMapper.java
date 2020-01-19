package si.fri.prpo.nakupovalniseznami.api.v1.mappers;

import org.mapstruct.Mapper;
import si.fri.prpo.nakupovalniseznami.dto.KategorijaDTO;
import si.fri.prpo.nakupovalniseznami.entitete.Kategorija;

import java.util.List;

@Mapper(componentModel = "cdi",
        uses = KategorijeMapper.class)
public interface KategorijeMapper {
    Kategorija mapToKategorija (KategorijaDTO dto);
    KategorijaDTO mapToKategorijaDTO (Kategorija entiteta);

    List<Kategorija> mapToKategorijaList (List<KategorijaDTO> dtoList);
    List<KategorijaDTO> mapToKategorijaDTOList (List<Kategorija> enitetaList);
}
