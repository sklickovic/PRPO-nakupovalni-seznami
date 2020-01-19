package si.fri.prpo.nakupovalniseznami.dto;

import si.fri.prpo.nakupovalniseznami.entitete.Kategorija;
import si.fri.prpo.nakupovalniseznami.entitete.WishList;

public class IzdelkiDTO {
    private Integer idIzdelka;
    private String cena;
    private String nazivIzdelka;
    private Integer zalogaIzdelka;
    private WishList list;
    private Kategorija kategorije;

    public Integer getIdIzdelka() {
        return idIzdelka;
    }

    public void setIdIzdelka(Integer idIzdelka) {
        this.idIzdelka = idIzdelka;
    }

    public String getCena() {
        return cena;
    }

    public void setCena(String cena) {
        this.cena = cena;
    }

    public String getNazivIzdelka() {
        return nazivIzdelka;
    }

    public void setNazivIzdelka(String nazivIzdelka) {
        this.nazivIzdelka = nazivIzdelka;
    }

    public Integer getZalogaIzdelka() {
        return zalogaIzdelka;
    }

    public void setZalogaIzdelka(Integer zalogaIzdelka) {
        this.zalogaIzdelka = zalogaIzdelka;
    }

    public WishList getList() {
        return list;
    }

    public void setList(WishList list) {
        this.list = list;
    }

    public Kategorija getKategorije() {
        return kategorije;
    }

    public void setKategorije(Kategorija kategorije) {
        this.kategorije = kategorije;
    }
}
