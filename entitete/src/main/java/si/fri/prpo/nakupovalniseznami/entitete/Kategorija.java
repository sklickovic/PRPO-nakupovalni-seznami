package si.fri.prpo.nakupovalniseznami.entitete;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "kategorije")
@NamedQueries(value =
        {
                @NamedQuery(name = "Kategorija.getAll", query = "SELECT k FROM Kategorija k"),
                @NamedQuery(name = "Kategorija.getByName", query = "SELECT k FROM Kategorija k WHERE k.nazivKategorije = :naziv"),
                @NamedQuery(name = "Kategorija.getFirst", query = "SELECT k FROM Kategorija k WHERE k.idKategorije = 1")
        })
public class Kategorija {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idKategorije;
    private String nazivKategorije;


    //povezave
    @OneToMany(mappedBy = "kategorije")
    private List<Izdelki> izdelkiList;

    //setters and getters
    @Id
    @Column(name = "idKategorije")
    public Integer getIdKategorije() {
        return idKategorije;
    }

    public void setIdKategorije(Integer idKategorije) {
        this.idKategorije = idKategorije;
    }

    @Basic
    @Column(name = "nazivKategorije")
    public String getNazivKategorije() {
        return nazivKategorije;
    }

    public void setNazivKategorije(String nazivKategorije) {
        this.nazivKategorije = nazivKategorije;
    }

    public List<Izdelki> getIzdelkiList() {
        return izdelkiList;
    }

    public void setIzdelkiList(List<Izdelki> izdelkiList) {
        this.izdelkiList = izdelkiList;
    }
}
