/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entitys;

import com.tsystem.tplatform.entityes.TPItem;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * Реестр докментов
 *
 * @author mrbis
 */
@Entity
@Table(name = "register")
public class Register extends TPItem {

    // Полное наименование реестра
    @Column(name = "fullName", length = 255)
    private String fullName;

    // Описние реестра
    @Column(name = "description", length = 4000)
    private String description;
    
    // Реестр являетья списком документов дня
    @Column(name = "isDayTimeDocuments")
    private Boolean isDayTimeDocuments = true;

    // Маска директории дня - даты в формате dd.MM.yyyy
    @Column(name = "dateFolderMask", length = 50)
    private String dateFolderMask;

    // Указывает что отправка будет осуществляться каждого видв документа отдельно по умолчанию false
    @Column(name = "sendOneByOne")
    private Boolean sendOneByOne = false;

    @OneToMany(mappedBy = "register")
    public Set<Source> sourceSet;

    @OneToMany(mappedBy = "register")
    public Set<Destination> destinationSet;

    // @OneToMany(mappedBy = "register", cascade = CascadeType.ALL, orphanRemoval = true)
    @OneToMany(mappedBy = "register")
    public Set<DocumentType> documentTypeSet;
    
    @OneToMany(mappedBy = "register")
    public Set<RegisterRole> registerRoleSet;

    public Register() {
        this.sourceSet = new HashSet<>();
        this.destinationSet = new HashSet<>(); 
        this.documentTypeSet = new HashSet<>();
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsDayTimeDocuments() {
        return isDayTimeDocuments;
    }

    public void setIsDayTimeDocuments(Boolean isDayTimeDocuments) {
        this.isDayTimeDocuments = isDayTimeDocuments;
    }

    public String getDateFolderMask() {
        return dateFolderMask;
    }

    public void setDateFolderMask(String dateFolderMask) {
        this.dateFolderMask = dateFolderMask;
    }

    public Boolean getSendOneByOne() {
        return sendOneByOne;
    }

    public void setSendOneByOne(Boolean sendOneByOne) {
        this.sendOneByOne = sendOneByOne;
    }

    public Set<Source> getSourceSet() {
        return sourceSet;
    }

    public void setSourceSet(Set<Source> sourceSet) {
        this.sourceSet = sourceSet;
    }

    public Set<Destination> getDestinationSet() {
        return destinationSet;
    }

    public void setDestinationSet(Set<Destination> destinationSet) {
        this.destinationSet = destinationSet;
    }

    public Set<DocumentType> getDocumentTypeSet() {
        return documentTypeSet;
    }

    public void setDocumentTypeSet(Set<DocumentType> documentTypeSet) {
        this.documentTypeSet = documentTypeSet;
    }

}
