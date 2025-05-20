/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import entitys.FileTemplate;
import entitys.FileToBeSigned;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author mrbis
 */
public class DayDocumentTypes {
    
    private LocalDate date;
    
    private List<FileTemplate> fileTemplateList;

    public DayDocumentTypes() {
         this.fileTemplateList = new ArrayList<>();
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<FileTemplate> getFileTemplateList() {
        return fileTemplateList;
    }

    public void setFileTemplateList(List<FileTemplate> fileTemplateList) {
        this.fileTemplateList = fileTemplateList;
    }

    
    
}
