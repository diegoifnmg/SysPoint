/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.tads.as.Controllers;

import br.edu.ifnmg.tads.as.DomainModel.Funcionario;
import br.edu.ifnmg.tads.as.DomainModel.IFuncionarioRepositorio;
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
@Named(value = "autenticacaoFuncionarioController")
@SessionScoped
public class AutenticacaoFuncionarioController implements Serializable {

    @EJB
    IFuncionarioRepositorio dao;
    Funcionario login;
    Funcionario funcionario;

    /**
     * Creates a new instance of AutenticacaoFuncionarioController
     */
    public AutenticacaoFuncionarioController() {
        login = new Funcionario();
    }
    
    public void exibirMensagem(String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(msg));
    }

    public String validar() {

        try {
            funcionario = dao.Login(login);

            if (funcionario == null) {
                exibirMensagem("Login ou senha não Correspondem");
                return "index0.xhtml";
            } else {
                HttpSession session;

                FacesContext ctx = FacesContext.getCurrentInstance();
                session = (HttpSession) ctx.getExternalContext().getSession(false);
                session.setAttribute("funcionarioAutenticado", funcionario);
                exibirMensagem("Funcionario Logado !");
                return "registrarPonto.xhtml";
            }

        } catch (Exception ex) {
            //ex.printStackTrace();
            exibirMensagem("Funcionario Ou Senha Inválidos");
            return "index0.xhtml";
        }
    }
    
    public String logout() {
        HttpSession session;

        FacesContext ctx = FacesContext.getCurrentInstance();
        session = (HttpSession) ctx.getExternalContext().getSession(false);
        session.setAttribute("funcionarioAutenticado", null);

       // AppendLog("Logout");
        Enumeration<String> vals = session.getAttributeNames();

        while (vals.hasMoreElements()) {
            session.removeAttribute(vals.nextElement());
        }
        
        return "index0.xhtml";

    }
    
    public Funcionario getLogin() {
        return login;
    }

    public void setLogin(Funcionario login) {
        this.login = login;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
}

