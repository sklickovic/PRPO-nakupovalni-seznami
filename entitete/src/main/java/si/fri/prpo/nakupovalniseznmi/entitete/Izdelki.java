package si.fri.prpo.nakupovalniseznmi.entitete;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "izdelki")
@NamedQueries(value =
        {
                @NamedQuery(name = "Izdelki.getAll", query = "SELECT i FROM Izdelki i")
        })
public class Izdelki {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idIzdelka;

    private String cena;
    private String nazivIzdelka;
    private Integer zalogaIzdelka;


    //povezave
    @ManyToMany(mappedBy = "izdelkiList")
    private List<WishList> wishLists;

    @ManyToOne
    @JoinColumn(name="kategorijaId")
    private Kategorija kategorijes;


    //setters and getters
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
}
