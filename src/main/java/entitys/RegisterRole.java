/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entitys;

import com.tsystem.tplatform.entityes.TPItem;
import com.tsystem.tplatform.security.TPUser;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;

/**
 *
 * @author mrbis
 */
@Entity
@Table(name = "registerrole")
public class RegisterRole extends TPItem {

    @ManyToOne    
    @JoinColumn(name = "register", referencedColumnName = "id")
    private Register register;
    
    @ManyToOne    
    @JoinColumn(name = "role", referencedColumnName = "id")
    private Role role;
    
    @ManyToOne    
    @JoinColumn(name = "tpuser", referencedColumnName = "id")
    private TPUser tpuser;
    
}
