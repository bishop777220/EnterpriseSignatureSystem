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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author mrbis
 */
@Entity
@Table(name = "atest2")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Atest2.findAll", query = "SELECT a FROM Atest2 a"),
    @NamedQuery(name = "Atest2.findById", query = "SELECT a FROM Atest2 a WHERE a.id = :id"),
    @NamedQuery(name = "Atest2.findByTitle", query = "SELECT a FROM Atest2 a WHERE a.title = :title")})
public class Atest2 implements Serializable {

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
    
    
    @JoinColumn(name = "atest1link", referencedColumnName = "id")
    @ManyToOne
    private Atest1 atest1link;

    public Atest2() {
    }

    public Atest2(Integer id) {
        this.id = id;
    }

    public Atest2(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Atest1 getAtest1link() {
        return atest1link;
    }

    public void setAtest1link(Atest1 atest1link) {
        this.atest1link = atest1link;
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
        if (!(object instanceof Atest2)) {
            return false;
        }
        Atest2 other = (Atest2) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tsystem.enterprisesignaturesystem.Atest2[ id=" + id + " ]";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
}
