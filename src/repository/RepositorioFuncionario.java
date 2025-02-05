package repository;

import entity.Funcionario;

import java.util.LinkedList;
import java.util.Map;

public class RepositorioFuncionario {
    private LinkedList<Funcionario> funcionarios;
    private Map<String, Funcionario> funcoesFuncionarios;

    public LinkedList<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public Map<String, Funcionario> getFuncoesFuncionarios() {
        return funcoesFuncionarios;
    }
}
