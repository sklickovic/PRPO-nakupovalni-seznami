package si.fri.prpo.nakupovalniseznami.dto;

import si.fri.prpo.nakupovalniseznami.entitete.Izdelki;

import java.util.List;

public class KategorijaDTO {
    private Integer idKategorije;
    private String nazivKategorije;
    private List<Izdelki> izdelkiList;

    public Integer getIdKategorije() {
        return idKategorije;
    }

    public void setIdKategorije(Integer idKategorije) {
        this.idKategorije = idKategorije;
    }

    public String getNazivKategorije() {
        return nazivKategorije;
    }

    public void setNazivKategorije(String nazivKategorije) {
        this.nazivKategorije = nazivKategorije;
    }

    public List<Izdelki> getIzdelkiList() {
        return izdelkiList;
    }

    public void setIzdelkiList(List<Izdelki> izdelkiList) {
        this.izdelkiList = izdelkiList;
    }
}
