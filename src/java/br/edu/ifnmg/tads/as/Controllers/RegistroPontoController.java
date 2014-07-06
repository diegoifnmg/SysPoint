/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.tads.as.Controllers;

import br.edu.ifnmg.tads.as.DomainModel.IRegistroPontoRepositorio;
import br.edu.ifnmg.tads.as.DomainModel.RegistroPonto;
import java.io.Serializable;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


    
    
/**
 *
 * @author Diego
 */
@Named(value = "registroPontoController")
@SessionScoped
public class RegistroPontoController implements Serializable {

    RegistroPonto entidade;
    RegistroPonto filtro;
    List<RegistroPonto> listagem;
    @EJB
    IRegistroPontoRepositorio dao;

    /**
     * Creates a new instance of RegistroAtividadeConverter
     */
    public RegistroPontoController() {
        entidade = new RegistroPonto();
        filtro = new RegistroPonto();
    }

    public void salvar() {
        if (dao.Salvar(entidade)) {
            exibirMensagem("Ponto salvo com sucesso!");
            entidade = new RegistroPonto();
        } else {
            exibirMensagem("Falha!");
        }
    }

    public String criar() {
        listagem = null;
        entidade = new RegistroPonto();
        return "registrarPonto.xhtml";
    }

    public void filtrar() {
        listagem = dao.Buscar(filtro);
    }

    public String novo() {
        entidade = new RegistroPonto();
        return "registrarPonto.xhtml";
    }

    public String abrir() {
        return "registrarPonto.xhtml";
    }

    public void exibirMensagem(String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(msg));
    }
    
    private String getTime() { 
         
        String hora = new SimpleDateFormat("HH:mm").format(new Date());  
        
        return hora;
    }

    public RegistroPonto getEntidade() {
        return entidade;
    }

    public void setEntidade(RegistroPonto entidade) {
        this.entidade = entidade;
    }

    public RegistroPonto getFiltro() {
        return filtro;
    }

    public void setFiltro(RegistroPonto filtro) {
        this.filtro = filtro;
    }

    public List<RegistroPonto> getListagem() {
        return listagem;
    }

    public void setListagem(List<RegistroPonto> listagem) {
        this.listagem = listagem;
    }

    public IRegistroPontoRepositorio getDao() {
        return dao;
    }

    public void setDao(IRegistroPontoRepositorio dao) {
        this.dao = dao;
    }

}
