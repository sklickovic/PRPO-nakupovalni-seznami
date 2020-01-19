package si.fri.prpo.nakupovalniseznami.dto;

import si.fri.prpo.nakupovalniseznami.entitete.Izdelki;
import si.fri.prpo.nakupovalniseznami.entitete.Uporabnik;

import java.util.List;

public class WishListDTO {
    private Integer idWishListe;
    private List<Izdelki> izdelkiList;
    private Uporabnik user;

    public Integer getIdWishListe() {
        return idWishListe;
    }

    public void setIdWishListe(Integer idWishListe) {
        this.idWishListe = idWishListe;
    }

    public List<Izdelki> getIzdelkiList() {
        return izdelkiList;
    }

    public void setIzdelkiList(List<Izdelki> izdelkiList) {
        this.izdelkiList = izdelkiList;
    }

    public Uporabnik getUser() {
        return user;
    }

    public void setUser(Uporabnik user) {
        this.user = user;
    }
}
