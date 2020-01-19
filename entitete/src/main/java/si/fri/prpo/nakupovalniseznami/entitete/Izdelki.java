package si.fri.prpo.nakupovalniseznami.entitete;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;

@Entity
@Table(name = "izdelki")
@NamedQueries(value =
        {
                @NamedQuery(name = "Izdelki.getAll", query = "SELECT i FROM Izdelki i"),
                @NamedQuery(name = "Izdelki.getIzdelkiWithBiggerSupply", query = "SELECT i FROM Izdelki i WHERE i.zalogaIzdelka >= 20"),
                @NamedQuery(name = "Izdelki.getCheapest", query = "SELECT MIN(i.cena) FROM Izdelki i"),
                @NamedQuery(name = "Izdelki.getMostExpensivePrice", query = "SELECT MAX(i.cena) FROM Izdelki i")
        })
public class Izdelki {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idIzdelka;
    private String cena;
    private String nazivIzdelka;
    private Integer zalogaIzdelka;

    //povezave
    @JsonbTransient
    @ManyToOne
    @JoinColumn(name = "idWishListe")
    private WishList list;

    @JsonbTransient
    @ManyToOne
    @JoinColumn(name = "idKategorije")
    private Kategorija kategorije;

    //setters and getters
    @Id
    @Column(name = "idIzdelka")
    public Integer getIdIzdelka() {
        return idIzdelka;
    }

    public void setIdIzdelka(Integer idIzdelka) {
        this.idIzdelka = idIzdelka;
    }

    @Basic
    @Column(name = "cena")
    public String getCena() {
        return cena;
    }

    public void setCena(String cena) {
        this.cena = cena;
    }

    @Basic
    @Column(name = "nazivIzdelka")
    public String getNazivIzdelka() {
        return nazivIzdelka;
    }

    public void setNazivIzdelka(String nazivIzdelka) {
        this.nazivIzdelka = nazivIzdelka;
    }

    @Basic
    @Column(name = "zalogaIzdelka")
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
