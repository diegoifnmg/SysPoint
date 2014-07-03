/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.tads.as.Controllers;

import br.edu.ifnmg.tads.as.DomainModel.Administrador;
import br.edu.ifnmg.tads.as.DomainModel.IAdministradorRepositorio;
import br.edu.ifnmg.tads.as.DomainModel.ILogAcessoRepositorioAdmin;
import br.edu.ifnmg.tads.as.DomainModel.LogAcessoAdmin;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Enumeration;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Maike Jordan
 */
@Named(value = "autenticacaoController")
@SessionScoped
public class AutenticacaoController implements Serializable {

    @EJB
    IAdministradorRepositorio dao;
    
    @EJB
    ILogAcessoRepositorioAdmin daoLogAcesso;
    String login;
    String senha;
    Administrador admin;
    public AutenticacaoController() {
    }
    
    public void exibirMensagem(String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Aviso",msg));
    }
    
    public String validar() {
        try {
            admin = dao.porLogin(login);

            if (admin == null) {
                exibirMensagem("Login ou senha não Correspondem");
                return "login.xhtml";
            } else {
                if (senha.equals(admin.getSenha())) {

                    HttpSession session;

                    FacesContext ctx = FacesContext.getCurrentInstance();
                    session = (HttpSession) ctx.getExternalContext().getSession(false);
                    session.setAttribute("adminAutenticado", admin);
                    
                    //Adicionando logIn entrada no sistema
                    LogAcessoAdmin log = new LogAcessoAdmin();
                    log.setTipo(1);
                    log.setAdmin(admin);
                    
                    daoLogAcesso.Salvar(log);

                    // AppendLog("Login");
                    return "index.xhtml";
                } else {
                    exibirMensagem("Login ou senha não correspondem");
                    return "login.xhtml";
                }
            }
        } catch (Exception ex) {
            //ex.printStackTrace();
            exibirMensagem(ex.getMessage());
            
            return "login.xhtml";
        }
        

    }
    
    public Administrador pegarDaSessao() {
        HttpSession session;

        FacesContext ctx = FacesContext.getCurrentInstance();
        session = (HttpSession) ctx.getExternalContext().getSession(false);

        return (Administrador) session.getAttribute("adminAutenticado");

    }

    public String logout() {
        HttpSession session;

        FacesContext ctx = FacesContext.getCurrentInstance();
        session = (HttpSession) ctx.getExternalContext().getSession(false);
        session.setAttribute("usuarioAutenticado", null);

       // AppendLog("Logout");
        Enumeration<String> vals = session.getAttributeNames();

        while (vals.hasMoreElements()) {
            session.removeAttribute(vals.nextElement());
        }

        //Adicionando logOut saída no sistema
        LogAcessoAdmin log = new LogAcessoAdmin();
        log.setTipo(2);
        log.setAdmin(admin);

        daoLogAcesso.Salvar(log);

        return "login.xhtml";

    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
}
