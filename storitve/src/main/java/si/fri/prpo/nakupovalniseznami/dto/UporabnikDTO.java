package si.fri.prpo.nakupovalniseznami.dto;

import si.fri.prpo.nakupovalniseznami.entitete.WishList;

import java.util.List;

public class UporabnikDTO {
    private Integer id;
    private String ime;
    private String priimek;
    private String email;
    private String naslov;
    private String uporabniskoIme;
    private String password;
    public List<WishList> wishList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPriimek() {
        return priimek;
    }

    public void setPriimek(String priimek) {
        this.priimek = priimek;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getUporabniskoIme() {
        return uporabniskoIme;
    }

    public void setUporabniskoIme(String uporabniskoIme) {
        this.uporabniskoIme = uporabniskoIme;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<WishList> getWishList() {
        return wishList;
    }

    public void setWishList(List<WishList> wishList) {
        this.wishList = wishList;
    }
}
