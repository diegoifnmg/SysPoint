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
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Maike Jordan
 */
@Named(value = "funcionarioController")
@SessionScoped
public class FuncionarioController implements Serializable {

    /**
     * Creates a new instance of FuncionarioController
     */
    Email email;
    Telefone telefone;
    Endereco endereco;
    Expediente expediente;
    Funcionario entidade, filtro;
    List<Funcionario> listagem;

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

    public void salvar() {
        if (dao.Salvar(entidade)) {
            exibirMensagem("Salvo com sucesso!");
            entidade = new Funcionario();
        } else {
            exibirMensagem("Falha!");
        }
    }

    public void filtrar() {
        listagem = dao.Buscar(filtro);
    }

    public String novo() {
        entidade = new Funcionario();
        return "editarFuncionario.xhtml";
    }

    public String excluir() {
        if (dao.Apagar(entidade)) {
            listagem = null;
            return "listagemFuncionario.xhtml";
        } else {
            return "";
        }
    }

    public String abrir() {
        return "editarFuncionario.xhtml";
    }

    public String cancelar() {
        return "listagemFuncionario.xhtml";
    }

    public Funcionario getEntidade() {
        return entidade;
    }

    public void setEntidade(Funcionario entidade) {
        this.entidade = entidade;
    }

    public Funcionario getFiltro() {
        return filtro;
    }

    public void setFiltro(Funcionario filtro) {
        this.filtro = filtro;
    }

    public List<Funcionario> getListagem() {
        if (listagem == null) {
            listagem = dao.Buscar(null);
        }
        return listagem;
    }

    public void setListagem(List<Funcionario> listagem) {
        this.listagem = listagem;
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
