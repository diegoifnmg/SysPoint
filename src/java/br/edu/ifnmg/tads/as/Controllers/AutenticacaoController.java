/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.tads.as.Controllers;

import br.edu.ifnmg.tads.as.DomainModel.Administrador;
import br.edu.ifnmg.tads.as.DomainModel.IAdministradorRepositorio;
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
    Administrador login;
    Administrador admin;

    public AutenticacaoController() {
        login = new Administrador();
    }

    public void exibirMensagem(String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(msg));
    }

    public String validar() {

        try {
            admin = dao.Login(login);

            if (admin == null) {
                exibirMensagem("Login ou senha não Correspondem");
                return "index0.xhtml";
            } else {
                HttpSession session;

                FacesContext ctx = FacesContext.getCurrentInstance();
                session = (HttpSession) ctx.getExternalContext().getSession(false);
                session.setAttribute("adminAutenticado", admin);
                exibirMensagem("Usuário Logado !");
                return "index.xhtml";
            }

        } catch (Exception ex) {
            //ex.printStackTrace();
            exibirMensagem("Usuário Ou Senhas Inválidos");
            return "index0.xhtml";
        }
    }
    
    public String logout() {
        HttpSession session;

        FacesContext ctx = FacesContext.getCurrentInstance();
        session = (HttpSession) ctx.getExternalContext().getSession(false);
        session.setAttribute("adminAutenticado", null);

       // AppendLog("Logout");
        Enumeration<String> vals = session.getAttributeNames();

        while (vals.hasMoreElements()) {
            session.removeAttribute(vals.nextElement());
        }
        
        return "index0.xhtml";

    }
    

    public Administrador getLogin() {
        return login;
    }

    public void setLogin(Administrador login) {
        this.login = login;
    }

    public Administrador getAdmin() {
        return admin;
    }

    public void setAdmin(Administrador admin) {
        this.admin = admin;
    }
}
