/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.tads.as.Controllers;

import br.edu.ifnmg.tads.as.DomainModel.Cargo;
import br.edu.ifnmg.tads.as.DomainModel.Email;
import br.edu.ifnmg.tads.as.DomainModel.Endereco;
import br.edu.ifnmg.tads.as.DomainModel.Funcionario;
import br.edu.ifnmg.tads.as.DomainModel.IFuncionarioRepositorio;
import br.edu.ifnmg.tads.as.DomainModel.Telefone;
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
@Named(value = "funcionarioController")
@SessionScoped
public class FuncionarioController implements Serializable {

    Email email;
    Telefone telefone;
    Endereco endereco;
    Funcionario entidade;
    Funcionario filtro;
    List<Funcionario> listagem;
    Cargo cargo;
    
    @EJB
    IFuncionarioRepositorio dao;

    /**
     * Creates a new instance of FuncionarioController
     */
    public FuncionarioController() {
        entidade = new Funcionario();
        filtro = new Funcionario();
        email = new Email();
        telefone = new Telefone();
        endereco = new Endereco();
        cargo = new Cargo();
    }

    public void salvar() {
        if (dao.Salvar(entidade)) {
            exibirMensagem("Salvo com sucesso!");
            entidade = new Funcionario();
        } else {
            exibirMensagem("Falha!");
        }
    }

    public String criar() {
        listagem = null;
        entidade = new Funcionario();
        return "editarFuncionario.xhtml";
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

    public String editar() {
        return "editarFuncionario.xhtml";
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
        return listagem;
    }

    public void setListagem(List<Funcionario> listagem) {
        this.listagem = listagem;
    }

    public IFuncionarioRepositorio getDao() {
        return dao;
    }

    public void setDao(IFuncionarioRepositorio dao) {
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

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public void exibirMensagem(String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(msg));
    }
}
