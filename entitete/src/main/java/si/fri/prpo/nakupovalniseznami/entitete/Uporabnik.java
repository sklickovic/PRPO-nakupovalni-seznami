package si.fri.prpo.nakupovalniseznami.entitete;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "uporabnik")
@NamedQueries(value =
        {
                @NamedQuery(name = "Uporabnik.getAll", query = "SELECT u FROM Uporabnik u"),
                @NamedQuery(name = "Uporabnik.getById", query = "SELECT u FROM Uporabnik u WHERE u.id = :id"),
                @NamedQuery(name = "Uporabnik.getByName", query = "SELECT u FROM Uporabnik u WHERE u.ime = :ime"),
                @NamedQuery(name = "Uporabnik.getByLastname", query = "SELECT u FROM Uporabnik u WHERE u.priimek = :priimek"),
                @NamedQuery(name = "Uporabnik.getByEmail", query = "SELECT u FROM Uporabnik u WHERE u.email = :email"),
                @NamedQuery(name = "Uporabnik.getByNaslov", query = "SELECT u FROM Uporabnik u WHERE u.naslov = :naslov"),
                @NamedQuery(name = "Uporabnik.getByUsername", query = "SELECT u FROM Uporabnik u WHERE u.uporabniskoIme = :uporabniskoIme"),
                @NamedQuery(name = "Uporabnik.getFirstUserId", query = "SELECT MIN(u.id) FROM Uporabnik u")
        })
public class Uporabnik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String ime;
    private String priimek;
    private String email;
    private String naslov;
    private String uporabniskoIme;
    private String password;

    //povezave
    @OneToMany(mappedBy = "user")
    private List<WishList> wishLists;

    //setters and getters
    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ime")
    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    @Basic
    @Column(name = "priimek")
    public String getPriimek() {
        return priimek;
    }

    public void setPriimek(String priimek) {
        this.priimek = priimek;
    }

    @Basic
    @Column(name = "uporabniskoIme")
    public String getUporabniskoIme() {
        return uporabniskoIme;
    }

    public void setUporabniskoIme(String uporabniskoIme) {
        this.uporabniskoIme = uporabniskoIme;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "naslov")
    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<WishList> getWishLists() {
        return wishLists;
    }

    public void setWishLists(List<WishList> wishLists) {
        this.wishLists = wishLists;
    }

}
