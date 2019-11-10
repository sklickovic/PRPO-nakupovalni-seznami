package si.fri.prpo.nakupovalniseznmi.entitete;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "wishList")
@NamedQueries(value =
        {
                @NamedQuery(name = "WishList.getAll", query = "SELECT w FROM WishList w"),
                @NamedQuery(name = "WishList.getSeznamUporabnikov", query = "SELECT u FROM WishList u WHERE u.uporabnik = :uporabnik")
        })
public class WishList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idWishListe;

    private Integer zaloga;


    //povezave
    @ManyToOne
    @JoinColumn(name="uporabnikId")
    private Uporabnik uporabnik;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable (name="wishList_izdelki",
            joinColumns = @JoinColumn (name="wishListId"),
            inverseJoinColumns = @JoinColumn (name="izdelkiId")
    )
    private List<Izdelki> izdelkiList;


    //setters and getters
    public Integer getIdWishListe() {
        return idWishListe;
    }

    public void setIdWishListe(Integer idWishListe) {
        this.idWishListe = idWishListe;
    }

    public Integer getZaloga() {
        return zaloga;
    }

    public void setZaloga(Integer zaloga) {
        this.zaloga = zaloga;
    }

}