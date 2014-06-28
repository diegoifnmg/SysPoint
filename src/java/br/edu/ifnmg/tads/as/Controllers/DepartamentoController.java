/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.tads.as.Controllers;

import br.edu.ifnmg.tads.as.DomainModel.Departamento;
import br.edu.ifnmg.tads.as.DomainModel.IDepartamentoRepositorio;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Maike Jordan
 */
@Named(value = "departamentoController")
@SessionScoped
public class DepartamentoController implements Serializable {

    
    Departamento entidade;
    Departamento filtro;
    List<Departamento> listagem;
    
    @EJB
    IDepartamentoRepositorio dao;

    /**
     * Creates a new instance of DepartamentoController
     */
    public DepartamentoController() {
        entidade = new Departamento();
        filtro = new Departamento();
    }

    public void exibirMensagem(String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(msg));
    }

    public void salvar() {
        if (dao.Salvar(entidade)) {
            exibirMensagem("Salvo com sucesso!");
            entidade = new Departamento();
        } else {
            exibirMensagem("Falha!");
        }
    }

    public String criar() {
        listagem = null;
        entidade = new Departamento();
        return "editarDepartamento.xhtml";
    }

    public void filtrar() {
        listagem = dao.Buscar(filtro);
    }

    public String novo() {
        entidade = new Departamento();
        return "editarDepartamento.xhtml";
    }

    public String excluir() {
        if (dao.Apagar(entidade)) {
            listagem = null;
            return "listagemDepartamento.xhtml";
        } else {
            return "";
        }
    }

    public String abrir() {
        return "editarDepartamento.xhtml";
    }

    public String cancelar() {
        return "listagemDepartamento.xhtml";
    }

    public String editar() {
        return "editarDepartamento.xhtml";
    }

    public IDepartamentoRepositorio getDao() {
        return dao;
    }

    public void setDao(IDepartamentoRepositorio dao) {
        this.dao = dao;
    }

    public Departamento getEntidade() {
        return entidade;
    }

    public void setEntidade(Departamento entidade) {
        this.entidade = entidade;
    }

    public Departamento getFiltro() {
        return filtro;
    }

    public void setFiltro(Departamento filtro) {
        this.filtro = filtro;
    }

    public List<Departamento> getListagem() {
        return listagem;
    }

    public void setListagem(List<Departamento> listagem) {
        this.listagem = listagem;
    }
}
