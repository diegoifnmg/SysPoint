/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.tads.as.DomainModel;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 *
 * @author Maike
 */
@Entity
public class Funcionario extends Pessoa implements Serializable {

    @Column(name = "expedientenome")
    private String Expedientenome;

    @Column(name = "expedienteinicio")
    private String Expedienteinicio;

    @Column(name = "expedientefim")
    private String Expedientefim;

    public Funcionario() {
        this.Expedientenome = "";
        this.Expedienteinicio = "";
        this.Expedientefim = "";
    }

    public Funcionario(String Expedientenome, String Expedienteinicio, String Expedientefim) {
        this.Expedientenome = Expedientenome;
        this.Expedienteinicio = Expedienteinicio;
        this.Expedientefim = Expedientefim;
    }

    public String getExpedientenome() {
        return Expedientenome;
    }

    public void setExpedientenome(String Expedientenome) {
        this.Expedientenome = Expedientenome;
    }

    public String getExpedienteinicio() {
        return Expedienteinicio;
    }

    public void setExpedienteinicio(String Expedienteinicio) {
        this.Expedienteinicio = Expedienteinicio;
    }

    public String getExpedientefim() {
        return Expedientefim;
    }

    public void setExpedientefim(String Expedientefim) {
        this.Expedientefim = Expedientefim;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.Expedientenome);
        hash = 53 * hash + Objects.hashCode(this.Expedienteinicio);
        hash = 53 * hash + Objects.hashCode(this.Expedientefim);
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
        final Funcionario other = (Funcionario) obj;
        if (!Objects.equals(this.Expedientenome, other.Expedientenome)) {
            return false;
        }
        if (!Objects.equals(this.Expedienteinicio, other.Expedienteinicio)) {
            return false;
        }
        if (!Objects.equals(this.Expedientefim, other.Expedientefim)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getNome().toString();
    }
    
}
