package entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Funcionario extends Pessoa{
    private BigDecimal salário;
    private String funcao;

    public Funcionario() {
    }

    public Funcionario(String nome, LocalDate dataNascimento, String funcao, BigDecimal salário) {
        super(nome, dataNascimento);
        this.funcao = funcao;
        this.salário = salário;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public BigDecimal getSalário() {
        return salário;
    }

    public void setSalário(BigDecimal salário) {
        this.salário = salário;
    }
}
