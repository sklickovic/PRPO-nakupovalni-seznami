package si.fri.prpo.nakupovalniseznami.entitete;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "uporabnik")
@NamedQueries(value =
        {
                @NamedQuery(name = "Uporabnik.getAll", query = "SELECT u FROM Uporabnik u"),
                @NamedQuery(name = "Uporabnik.getPetras", query = "SELECT u FROM Uporabnik u WHERE u.ime = 'Petra'"),
                @NamedQuery(name = "Uporabnik.getNovaks", query = "SELECT u FROM Uporabnik u WHERE u.priimek = 'Novak'"),
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


    //povezave
    @OneToMany(mappedBy = "uporabnik")
    private List<WishList> wishLists;


    //setters and getters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) { this.id = id; }

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

    public String getUporabniskoIme() {
        return uporabniskoIme;
    }

    public void setUporabniskoIme(String uporabniskoIme) {
        this.uporabniskoIme = uporabniskoIme;
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
}
