/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.tads.as.Controllers;

import br.edu.ifnmg.tads.as.DomainModel.Funcionario;
import br.edu.ifnmg.tads.as.DomainModel.IFuncionarioRepositorio;
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
@Named(value = "funcionarioConverter")
@SessionScoped
public class FuncionarioConverter implements Serializable, Converter {

    /**
     * Creates a new instance of ExpedienteConverter
     */
    @EJB
    IFuncionarioRepositorio dao;

    public FuncionarioConverter() {
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
            Funcionario f = (Funcionario) value;
            return f.getId().toString();
        }
    }

}
