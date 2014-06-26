/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.tads.as.Controllers;

import br.edu.ifnmg.tads.as.DomainModel.Email;
import br.edu.ifnmg.tads.as.DomainModel.Endereco;
import br.edu.ifnmg.tads.as.DomainModel.Expediente;
import br.edu.ifnmg.tads.as.DomainModel.Funcionario;
import br.edu.ifnmg.tads.as.DomainModel.Telefone;
import br.edu.ifnmg.tads.as.Infraestrutura.FuncionarioDAO;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Maike Jordan
 */
@Named(value = "funcionarioController")
@SessionScoped
public class FuncionarioController
        extends ControllerGenerico<Funcionario> implements Serializable {

    /**
     * Creates a new instance of FuncionarioController
     */
    Email email;
    Telefone telefone;
    Endereco endereco;
    Expediente expediente;

    public FuncionarioController() {
        entidade = new Funcionario();
        filtro = new Funcionario();
        email = new Email();
        telefone = new Telefone();
        endereco = new Endereco();
        expediente = new Expediente();
    }
    @EJB
    FuncionarioDAO dao;

    @Override
    public void salvar() {
        if (dao.Salvar(entidade)) {
            exibirMensagem("Salvo com sucesso!");
            entidade = new Funcionario();
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
        entidade = new Funcionario();
        return "editarFuncionario.xhtml";
    }

    @Override
    public String excluir() {
        if (dao.Apagar(entidade)) {
            listagem = null;
            return "listagemFuncionario.xhtml";
        } else {
            return "";
        }
    }

    @Override
    public String abrir() {
        return "editarFuncionario.xhtml";
    }

    @Override
    public String cancelar() {
        return "listagemFuncionario.xhtml";
    }
    
    
    public FuncionarioDAO getDao() {
        return dao;
    }

    public void setDao(FuncionarioDAO dao) {
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
