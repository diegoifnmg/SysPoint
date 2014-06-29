/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.tads.as.Controllers;

import br.edu.ifnmg.tads.as.DomainModel.Cargo;
import br.edu.ifnmg.tads.as.DomainModel.Departamento;
import br.edu.ifnmg.tads.as.DomainModel.ICargoRepositorio;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Diego
 */
@Named(value = "cargoController")
@SessionScoped
public class CargoController implements Serializable {

    /**
     * Creates a new instance of CargoController
     */
    
    ICargoRepositorio dao;
    List<Departamento> departamentos;
    Cargo entidade;
    Cargo filtro;
    List<Cargo> listagem;
    
    public CargoController() {
        
        entidade = new Cargo();
        filtro = new Cargo(); 
    }
    
    public void salvar() {
        if (dao.Salvar(entidade)) {
            exibirMensagem("Salvo com sucesso!");
            entidade = new Cargo();
        } else {
            exibirMensagem("Falha!");
        }
    }

    public String criar(){
        listagem = null;
        entidade = new Cargo();
        return "editarFuncionario.xhtml";
    }
    
    public void filtrar() {
        listagem = dao.Buscar(filtro);
    }

    public String novo() {
        entidade = new Cargo();
        return "editarCargo.xhtml";
    }

    public String excluir() {
        if (dao.Apagar(entidade)) {
            listagem = null;
            return "listagemCargo.xhtml";
        } else {
            return "";
        }
    }

    public String abrir() {
        return "editarCargo.xhtml";
    }

    public String cancelar() {
        return "listagemCargo.xhtml";
    }
    
    public String editar(){
       return "editarCargo.xhtml";
    }

    public Cargo getEntidade() {
        return entidade;
    }

    public void setEntidade(Cargo entidade) {
        this.entidade = entidade;
    }

    public Cargo getFiltro() {
        return filtro;
    }

    public void setFiltro(Cargo filtro) {
        this.filtro = filtro;
    }

    public ICargoRepositorio getDao() {
        return dao;
    }

    public void setDao(ICargoRepositorio dao) {
        this.dao = dao;
    }

    public List<Departamento> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(List<Departamento> departamentos) {
        this.departamentos = departamentos;
    }

    public List<Cargo> getListagem() {
        return listagem;
    }

    public void setListagem(List<Cargo> listagem) {
        this.listagem = listagem;
    }
    
    public void exibirMensagem(String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(msg));
    }
    
    
    
}
