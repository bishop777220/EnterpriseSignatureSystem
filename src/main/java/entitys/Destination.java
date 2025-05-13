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
import jakarta.persistence.Table;

/**
 *
 * @author mrbis
 */
@Entity
@Table(name = "destination")
public class Destination extends TPItem{

    // Путь к каталогу назначения
    @Column(name = "path", length = 255)
    private String path;
    
    // Описние реестра
    @Column(name = "description", length = 4000)
    private String description;
    
    @ManyToOne    
    @JoinColumn(name = "register", referencedColumnName = "id")
    private Register register;

    public Destination() {
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Register getRegister() {
        return register;
    }

    public void setRegister(Register register) {
        this.register = register;
    }
    
    
}
