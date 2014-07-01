/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.tads.as.Controllers;

import br.edu.ifnmg.tads.as.DomainModel.Departamento;
import br.edu.ifnmg.tads.as.DomainModel.IDepartamentoRepositorio;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

/**
 *
 * @author Maike Jordan
 */
@Named(value = "departamentoConverter")
@SessionScoped
public class DepartamentoConverter implements Serializable, Converter {

    /**
     * Creates a new instance of ExpedienteConverter
     */
    @EJB
    IDepartamentoRepositorio dao;

    public DepartamentoConverter() {
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.trim().equals("")) {
            return null;
        } else {
            Long id = Long.parseLong(value);
            return dao.Abrir(id);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || value.toString().equals("")) {
            return "";
        } else {
            Departamento d = (Departamento) value;
            return d.getDepartamentoid().toString();
        }
    }

}
