package si.fri.prpo.nakupovalniseznami.entitete;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "wishList")
@NamedQueries(value =
        {
                @NamedQuery(name = "WishList.getAll", query = "SELECT w FROM WishList w"),
                @NamedQuery(name = "WishList.getSeznamUporabnikov", query = "SELECT u FROM WishList u WHERE u.user = :uporabnik"),
                @NamedQuery(name = "WishList.getById", query = "SELECT u FROM WishList u WHERE u.idWishListe = :id")
        })
public class WishList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idWishListe;


    //povezave
    @OneToMany(mappedBy = "list")
    private List<Izdelki> izdelkiList;

    @JsonbTransient
    @ManyToOne
    @JoinColumn (name="id")
    private Uporabnik user;


    //setters and getters
    @Id
    @Column(name = "idWishListe")
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