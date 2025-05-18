/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entitys;

import com.tsystem.tplatform.entityes.TPItem;
import entitys.FileTemplate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Set;

/**
 *
 * @author mrbis
 */
@Entity
@Table(name = "fileToBeSigned")
public class FileToBeSigned extends TPItem{

    @Column(name = "dayTimeDirectory", length = 20)
    private String dayTimeDirectory;
    
    @Column(name = "fileName", length = 50)
    private String fileName;
    
    @ManyToOne    
    @JoinColumn(name = "fileTemplate", referencedColumnName = "id")
    private FileTemplate fileTemplate;
    
    @OneToMany(mappedBy = "fileTemplate")
    public Set<Signature> signatureSet;

    public FileToBeSigned() {
    }

    public String getDayTimeDirectory() {
        return dayTimeDirectory;
    }

    public void setDayTimeDirectory(String dayTimeDirectory) {
        this.dayTimeDirectory = dayTimeDirectory;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public FileTemplate getFileTemplate() {
        return fileTemplate;
    }

    public void setFileTemplate(FileTemplate fileTemplate) {
        this.fileTemplate = fileTemplate;
    }

    public Set<Signature> getSignatureSet() {
        return signatureSet;
    }

    public void setSignatureSet(Set<Signature> signatureSet) {
        this.signatureSet = signatureSet;
    }
    
    
}
