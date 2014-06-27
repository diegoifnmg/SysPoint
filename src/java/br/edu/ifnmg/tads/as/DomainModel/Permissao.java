/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.tads.as.DomainModel;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Maike Jordan
 */
@Entity
public class Permissao implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long permissaoid;

    public Long getPermissaoid() {
        return permissaoid;
    }

    public void setPermissaoid(Long permissaoid) {
        this.permissaoid = permissaoid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (permissaoid != null ? permissaoid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Permissao)) {
            return false;
        }
        Permissao other = (Permissao) object;
        if ((this.permissaoid == null && other.permissaoid != null) || (this.permissaoid != null && !this.permissaoid.equals(other.permissaoid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.edu.ifnmg.tads.as.DomainModel.Permissao[ id=" + permissaoid + " ]";
    }
    
}
