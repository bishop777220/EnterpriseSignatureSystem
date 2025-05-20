/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entitys;

import com.tsystem.tplatform.entityes.TPItem;
import com.tsystem.tplatform.security.TPUser;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 *
 * @author mrbis
 */
@Entity
@Table(name = "signature")
public class Signature extends TPItem {

    @Column(name = "fileName", length = 50)
    private String fileName;
    
    @ManyToOne    
    @JoinColumn(name = "fileToBeSigned", referencedColumnName = "id")
    private FileToBeSigned fileToBeSigned;
    
    @Column(name = "dnSignatory", length = 50)
    private String dnSignatory;
    
    @ManyToOne    
    @JoinColumn(name = "role", referencedColumnName = "id")
    private Role role;
    
    @ManyToOne    
    @JoinColumn(name = "tpuser", referencedColumnName = "id")
    private TPUser tpuser;
    
    @Column(name = "validationStatus")
    private Boolean validationStatus;
    
    public Signature() {
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public FileToBeSigned getFileToBeSigned() {
        return fileToBeSigned;
    }

    public void setFileToBeSigned(FileToBeSigned fileToBeSigned) {
        this.fileToBeSigned = fileToBeSigned;
    }

    public String getDnSignatory() {
        return dnSignatory;
    }

    public void setDnSignatory(String dnSignatory) {
        this.dnSignatory = dnSignatory;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public TPUser getTpuser() {
        return tpuser;
    }

    public void setTpuser(TPUser tpuser) {
        this.tpuser = tpuser;
    }

    public Boolean getValidationStatus() {
        return validationStatus;
    }

    public void setValidationStatus(Boolean validationStatus) {
        this.validationStatus = validationStatus;
    }

    
}
