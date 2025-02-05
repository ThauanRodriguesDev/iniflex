package repository;

import entity.Funcionario;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class RepositorioFuncionario {
    private final LinkedList<Funcionario> funcionarios = new LinkedList<>();
    private final Map<String, Funcionario> funcoesFuncionarios = new HashMap<>();

    public LinkedList<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public Map<String, Funcionario> getFuncoesFuncionarios() {
        return funcoesFuncionarios;
    }
}
