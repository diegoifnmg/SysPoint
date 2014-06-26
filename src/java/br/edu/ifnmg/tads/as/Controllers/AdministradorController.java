/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.tads.as.Controllers;

import br.edu.ifnmg.tads.as.DomainModel.Administrador;
import br.edu.ifnmg.tads.as.DomainModel.Email;
import br.edu.ifnmg.tads.as.DomainModel.Endereco;
import br.edu.ifnmg.tads.as.DomainModel.Expediente;
import br.edu.ifnmg.tads.as.DomainModel.Telefone;
import br.edu.ifnmg.tads.as.Infraestrutura.AdministradorDAO;
import br.edu.ifnmg.tads.as.Infraestrutura.FuncionarioDAO;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Diego
 */
@Named(value = "administradorController")
@SessionScoped
public class AdministradorController extends ControllerGenerico<Administrador> implements Serializable{

    

    Email email;
    Telefone telefone;
    Endereco endereco;
    Expediente expediente;

    public AdministradorController() {
        entidade = new Administrador();
        filtro = new Administrador();
        email = new Email();
        telefone = new Telefone();
        endereco = new Endereco();
        expediente = new Expediente();
    }
    @EJB
    AdministradorDAO dao;

    @Override
    public void salvar() {
        if (dao.Salvar(entidade)) {
            exibirMensagem("Salvo com sucesso!");
            entidade = new Administrador();
        } else {
            exibirMensagem("Falha!");
        }
    }

    @Override
    public void filtrar() {
        listagem = dao.Buscar(filtro);
    }

    @Override
    public String novo() {
        entidade = new Administrador();
        return "editarAdministrador.xhtml";
    }

    @Override
    public String excluir() {
        if (dao.Apagar(entidade)) {
            listagem = null;
            return "listagemAdministrador.xhtml";
        } else {
            return "";
        }
    }

    @Override
    public String abrir() {
        return "editarAdministrador.xhtml";
    }

    @Override
    public String cancelar() {
        return "listagemAdministrador.xhtml";
    }
    
    
    public AdministradorDAO getDao() {
        return dao;
    }

    public void setDao(AdministradorDAO dao) {
        this.dao = dao;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Expediente getExpediente() {
        return expediente;
    }

    public void setExpediente(Expediente expediente) {
        this.expediente = expediente;
    }

    public void exibirMensagem(String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(msg));
    }

    
}
