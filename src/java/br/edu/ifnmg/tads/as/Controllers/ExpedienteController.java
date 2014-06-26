/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.tads.as.Controllers;

import br.edu.ifnmg.tads.as.DomainModel.Expediente;
import br.edu.ifnmg.tads.as.Infraestrutura.ExpedienteDAO;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Maike Jordan
 */
@Named(value = "expedienteController")
@SessionScoped
public class ExpedienteController implements Serializable {

    /**
     * Creates a new instance of ExpedienteController
     */
    Expediente entidade, filtro;
    List<Expediente> listagem;

    public ExpedienteController() {
        entidade = new Expediente();
        filtro = new Expediente();

    }

    ExpedienteDAO dao;

    public void salvar() {
        if (dao.Salvar(entidade)) {
            exibirMensagem("Salvo com sucesso!");
            entidade = new Expediente();
        } else {
            exibirMensagem("Falha!");
        }
    }

    public void filtrar() {
        listagem = dao.Buscar(filtro);
    }

    public String novo() {
        entidade = new Expediente();
        return "editarExpediente.xhtml";
    }

    public String excluir() {
        if (dao.Apagar(entidade)) {
            listagem = null;
            return "listagemExpediente.xhtml";
        } else {
            return "";
        }
    }

    public String abrir() {
        return "editarExpediente.xhtml";
    }

    public String cancelar() {
        return "listagemExpediente.xhtml";
    }

    public void exibirMensagem(String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(msg));
    }

    public Expediente getEntidade() {
        return entidade;
    }

    public void setEntidade(Expediente entidade) {
        this.entidade = entidade;
    }

    public Expediente getFiltro() {
        return filtro;
    }

    public void setFiltro(Expediente filtro) {
        this.filtro = filtro;
    }

    public List<Expediente> getListagem() {
        return listagem;
    }

    public void setListagem(List<Expediente> listagem) {
        this.listagem = listagem;
    }

    public ExpedienteDAO getDao() {
        return dao;
    }

    public void setDao(ExpedienteDAO dao) {
        this.dao = dao;
    }

     public List<Expediente> listarExpediente(){
        listagem = dao.Buscar(null);
        return listagem;
    }
}
