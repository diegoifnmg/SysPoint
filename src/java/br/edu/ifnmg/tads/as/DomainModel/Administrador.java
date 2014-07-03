/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.tads.as.DomainModel;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 *
 * @author Maike
 */
@Entity
public class Administrador extends Pessoa implements  Serializable {
    
    @Column(name = "ativo")
    private boolean ativo;

    public Administrador() {
        
        this.ativo = true;
    }

    public Administrador(boolean ativo) {
        this.ativo = ativo;
    }
    
    
    

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + (this.ativo ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Administrador other = (Administrador) obj;
        if (this.ativo != other.ativo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getNome().toString();
    }
    
   
}
