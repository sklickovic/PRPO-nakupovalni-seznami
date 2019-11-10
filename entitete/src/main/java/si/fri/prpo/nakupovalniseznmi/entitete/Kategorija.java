package si.fri.prpo.nakupovalniseznmi.entitete;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "kategorije")
@NamedQueries(value =
        {
                @NamedQuery(name = "Kategorija.getAll", query = "SELECT k FROM Kategorija k")
        })
public class Kategorija {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idKategorije;

    private String nazivKategorije;


    //povezave
    @OneToMany (mappedBy = "kategorijes")
    private List<Izdelki> izdelkis;


    //setters and getters
    public Integer getIdKategorije() {
        return idKategorije;
    }

    public void setIdKategorije(Integer idKategorije) {
        this.idKategorije = idKategorije;
    }

    public String getNazivKategorije() {
        return nazivKategorije;
    }

    public void setNazivKategorije(String nazivKategorije) {
        this.nazivKategorije = nazivKategorije;
    }
}
