/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package newpackage;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author mrbis
 */
@Entity
@Table(name = "atest1")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Atest1.findAll", query = "SELECT a FROM Atest1 a"),
    @NamedQuery(name = "Atest1.findById", query = "SELECT a FROM Atest1 a WHERE a.id = :id"),
    @NamedQuery(name = "Atest1.findByTitle", query = "SELECT a FROM Atest1 a WHERE a.title = :title")})
public class Atest1 implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "title")
    private String title;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @OneToMany(mappedBy = "atest1link")
    private Collection<Atest2> atest2Collection;

    public Atest1() {
    }

    public Atest1(Integer id) {
        this.id = id;
    }

    public Atest1(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    @XmlTransient
    public Collection<Atest2> getAtest2Collection() {
        return atest2Collection;
    }

    public void setAtest2Collection(Collection<Atest2> atest2Collection) {
        this.atest2Collection = atest2Collection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Atest1)) {
            return false;
        }
        Atest1 other = (Atest1) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tsystem.enterprisesignaturesystem.Atest1[ id=" + id + " ]";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
}
