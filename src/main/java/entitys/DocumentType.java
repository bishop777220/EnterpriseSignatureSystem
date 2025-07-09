/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entitys;

import com.tsystem.tplatform.entityes.TPItem;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author mrbis
 */
@Entity
@Table(name = "documentType")
public class DocumentType extends TPItem {

    @Column(name = "folderName", length = 50)
    private String folderName;

    @Column(name = "description", length = 4000)
    private String description;

    // Указывает что директория вида догумента находиться до директории дня по умолчанию false
    @Column(name = "beforeTheDate")
    private Boolean beforeTheDate = false;

    // Указывает что в отображении шаблонов видов документа будут учавствовать вложенные директории по умолчанию false
    @Column(name = "recursive")
    private Boolean recursive = false;

    @ManyToOne
    @JoinColumn(name = "register", referencedColumnName = "id")
    private Register register;

    // @OneToMany(mappedBy = "register", cascade = CascadeType.ALL, orphanRemoval = true)
    @OneToMany(mappedBy = "documentType")
    public List<FileTemplate> fileTemplateList;

    public DocumentType() {
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getBeforeTheDate() {
        return beforeTheDate;
    }

    public void setBeforeTheDate(Boolean beforeTheDate) {
        this.beforeTheDate = beforeTheDate;
    }

    public Boolean getRecursive() {
        if(recursive && !beforeTheDate){
            return true;
        } else {
            return false;
        }
    }

    public void setRecursive(Boolean recursive) {
        this.recursive = recursive;
    }

    public Register getRegister() {
        return register;
    }

    public void setRegister(Register register) {
        this.register = register;
    }

    public List<FileTemplate> getFileTemplateList() {
        return fileTemplateList;
    }

    public void setFileTemplateList(List<FileTemplate> fileTemplateList) {
        this.fileTemplateList = fileTemplateList;
    }

    

    public Set<String> getFullSourcePath(LocalDate date) {
        String dayTimeDirectory = this.register.getDateFolderMask();
        if (date != null && this.register.getDateFolderMask() != null) {
            DateTimeFormatter sdf = DateTimeFormatter.ofPattern(this.register.getDateFolderMask());
            //dayTimeDirectory = sdf.format(date);
            dayTimeDirectory = date.format(sdf);
        }
        Set<String> fullSourcePathList = new HashSet<>();
        for (Source source : this.register.getSourceSet()) {
            String fullSourcePath = source.getPath();
            if (this.register.getIsDayTimeDocuments()) {
                if (this.beforeTheDate) {
                    fullSourcePath += "\\" + this.getTitle();
                }
                fullSourcePath += "\\" + dayTimeDirectory;
                if (!this.beforeTheDate) {
                    fullSourcePath += "\\" + this.getTitle();
                }
            } else {
                fullSourcePath += "\\" + this.getTitle();
            }

            fullSourcePathList.add(fullSourcePath);
        }
        return fullSourcePathList;
    }
}
