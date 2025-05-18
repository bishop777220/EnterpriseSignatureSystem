/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entitys;

import com.tsystem.tplatform.entityes.TPItem;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 *
 * @author mrbis
 */
@Entity
@Table(name = "role")
public class Role extends TPItem {
    
    @Column(name = "code")
    private String code;
    
    @Column(name = "description", length = 4000)
    private String description;

    public Role() {
    }
    
    
}
