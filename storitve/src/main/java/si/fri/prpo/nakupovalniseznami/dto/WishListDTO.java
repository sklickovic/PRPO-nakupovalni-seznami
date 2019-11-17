package si.fri.prpo.nakupovalniseznami.dto;

import si.fri.prpo.nakupovalniseznami.entitete.Izdelki;

import java.util.List;

public class WishListDTO {

    public int uporabnikId;

    public int id;
    List<Izdelki> seznamIzdelkov;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Izdelki> getIzdelki() {
        return seznamIzdelkov;
    }

    public void setIzdelki(List<Izdelki> seznam) {
        this.seznamIzdelkov = seznam;
    }

    public int getUporabnikId() {
        return uporabnikId;
    }

    public void setUporabnikId(int id) {
        this.uporabnikId = id;
    }
}
