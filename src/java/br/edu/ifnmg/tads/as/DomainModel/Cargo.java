/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.tads.as.DomainModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 *
 * @author Maike Jordan
 */
@Entity
public class Cargo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cargoid; 
    
    @Column(name = "nome", length = 255)
    private String nome;
    
    @Column(name = "salario")
    private Double salario;
    
    @Column(name = "ativo")
    private boolean ativo;
    
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Departamento> departamentos;

    public Cargo() {
        departamentos = new ArrayList<>();
        this.ativo = true;
    }

    public Cargo(Long cargoid, String nome, Double salario) {
        this.cargoid = cargoid;
        this.nome = nome;
        this.salario = salario;
        this.departamentos = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public List<Departamento> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(List<Departamento> departamentos) {
        this.departamentos = departamentos;
    }

    public Long getCargoid() {
        return cargoid;
    }

    public void setCargoid(Long cargoid) {
        this.cargoid = cargoid;
    }

    public void add(Departamento e) {
        if (!departamentos.contains(e)) {
            departamentos.add(e);
        }
    }

    public void remove(Departamento e) {
        if (departamentos.contains(e)) {
            departamentos.remove(e);
        }
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
        hash = 97 * hash + Objects.hashCode(this.cargoid);
        hash = 97 * hash + Objects.hashCode(this.nome);
        hash = 97 * hash + Objects.hashCode(this.salario);
        hash = 97 * hash + (this.ativo ? 1 : 0);
        hash = 97 * hash + Objects.hashCode(this.departamentos);
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
        final Cargo other = (Cargo) obj;
        if (!Objects.equals(this.cargoid, other.cargoid)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.salario, other.salario)) {
            return false;
        }
        if (this.ativo != other.ativo) {
            return false;
        }
        if (!Objects.equals(this.departamentos, other.departamentos)) {
            return false;
        }
        return true;
    }


    @Override
    public String toString() {
        return nome;
    }
}
