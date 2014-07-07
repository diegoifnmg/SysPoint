/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.tads.as.Controllers;

import br.edu.ifnmg.tads.as.DomainModel.Funcionario;
import br.edu.ifnmg.tads.as.DomainModel.IRegistroAtividadeRepositorio;
import br.edu.ifnmg.tads.as.DomainModel.RegistroAtividade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Maike Jordan
 */
@Named(value = "registroAtividadeController")
@SessionScoped
public class RegistroAtividadeController implements Serializable {

    RegistroAtividade entidade;
    RegistroAtividade filtro;
    List<RegistroAtividade> listagem;
    @EJB
    IRegistroAtividadeRepositorio dao;

    /**
     * Creates a new instance of RegistroAtividadeConverter
     */
    public RegistroAtividadeController() {
        entidade = new RegistroAtividade();
        filtro = new RegistroAtividade();
    }

    public void salvar() {
        if (dao.Salvar(entidade)) {
            exibirMensagem("Atividade salva com sucesso!");
            entidade = new RegistroAtividade();
        } else {
            exibirMensagem("Falha!");
        }
    }

    public String criar() {
        listagem = null;
        entidade = new RegistroAtividade();
        return "registrarPonto.xhtml";
    }

    public void filtrar() {
        listagem = dao.Buscar(filtro);
    }

    public String novo() {
        entidade = new RegistroAtividade();
        return "registrarPonto.xhtml";
    }

    public String abrir() {
        return "registrarPonto.xhtml";
    }

    public void exibirMensagem(String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(msg));
    }
    
    public Funcionario pegarDaSessao() {
        HttpSession session;

        FacesContext ctx = FacesContext.getCurrentInstance();
        session = (HttpSession) ctx.getExternalContext().getSession(false);

        return (Funcionario) session.getAttribute("funcionarioAutenticado");

    }
    
    private String getDateTime() { 
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 
        Date date = new Date(); 
        return dateFormat.format(date);
    }

    public RegistroAtividade getEntidade() {
        return entidade;
    }

    public void setEntidade(RegistroAtividade entidade) {
        this.entidade = entidade;
    }

    public RegistroAtividade getFiltro() {
        return filtro;
    }

    public void setFiltro(RegistroAtividade filtro) {
        this.filtro = filtro;
    }

    public List<RegistroAtividade> getListagem() {
        return listagem;
    }

    public void setListagem(List<RegistroAtividade> listagem) {
        this.listagem = listagem;
    }

    public IRegistroAtividadeRepositorio getDao() {
        return dao;
    }

    public void setDao(IRegistroAtividadeRepositorio dao) {
        this.dao = dao;
    }
    
}
