/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.tads.as.Controllers;

import br.edu.ifnmg.tads.as.DomainModel.Administrador;
import br.edu.ifnmg.tads.as.DomainModel.Email;
import br.edu.ifnmg.tads.as.DomainModel.Endereco;
import br.edu.ifnmg.tads.as.DomainModel.IAdministradorRepositorio;
import br.edu.ifnmg.tads.as.DomainModel.Telefone;
import java.io.Serializable;
import java.util.List;
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
public class AdministradorController implements Serializable{

    Email email;
    Telefone telefone;
    Endereco endereco;
    Administrador entidade;
    Administrador filtro;
    List<Administrador> listagem;
    
    @EJB
    IAdministradorRepositorio dao;
    
    /**
     * Creates a new instance of FuncionarioController
     */
    public AdministradorController() {
        entidade = new Administrador();
        filtro = new Administrador();
        email = new Email();
        telefone = new Telefone();
        endereco = new Endereco();
    }

    public void salvar() {
        if (dao.Salvar(entidade)) {
            exibirMensagem("Salvo com sucesso!");
            entidade = new Administrador();
        } else {
            exibirMensagem("Falha!");
        }
    }

    public String criar(){
        listagem = null;
        entidade = new Administrador();
        return "editarAdministrador.xhtml";
    }
    
    public void filtrar() {
        listagem = dao.Buscar(filtro);
    }

    public String novo() {
        entidade = new Administrador();
        return "editarAdministrador.xhtml";
    }

    public String excluir() {
        if (dao.Apagar(entidade)) {
            listagem = null;
            return "listagemAdministrador.xhtml";
        } else {
            return "";
        }
    }

    public String abrir() {
        return "editarAdministrador.xhtml";
    }

    public String cancelar() {
        return "listagemAdministrador.xhtml";
    }
    
    public String editar(){
       return "editarAdministrador.xhtml";
    }

    public Administrador getEntidade() {
        return entidade;
    }

    public void setEntidade(Administrador entidade) {
        this.entidade = entidade;
    }

    public Administrador getFiltro() {
        return filtro;
    }

    public void setFiltro(Administrador filtro) {
        this.filtro = filtro;
    }

    public List<Administrador> getListagem() {
        return listagem;
    }

    public void setListagem(List<Administrador> listagem) {
        this.listagem = listagem;
    }

    public IAdministradorRepositorio getDao() {
        return dao;
    }

    public void setDao(IAdministradorRepositorio dao) {
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

    public void exibirMensagem(String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(msg));
    }

}
