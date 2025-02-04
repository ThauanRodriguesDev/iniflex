package services;

import entity.Funcionario;
import repository.RepositorioFuncionario;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Scanner;

public class ServicoFuncionario {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static RepositorioFuncionario repositorio;

    public ServicoFuncionario(RepositorioFuncionario repositorio) {
        ServicoFuncionario.repositorio = repositorio;
    }

    public static void construirMenu(int operacao){
        switch (operacao){
            case 0:
                System.out.println("Sistema Finalizado.");
                break;
            case 1:
                criarFuncionario();
                break;

            default:
                System.out.println("Opção Invalida insira novamente.");
        }
    }

    private static void imprimirTodosFuncionarios(){
        for(Funcionario repos: repositorio.getFuncionarios()){
            System.out.println("------------------------------------");
            System.out.println("Nome do Funcionário: " + repos.getNome());
            System.out.println("Data de nascimento " + repos.getDataNascimento()
                    .format(DateTimeFormatter.ISO_LOCAL_DATE));
            System.out.println("Função: " + repos.getFuncao());
            System.out.println("Salário: " + repos.getSalário());
        }
    }

    private static void excluirFuncionario(){
        System.out.println("Digite o nome do funcionario que você quer excluir");
        String nome = SCANNER.nextLine();

        System.out.println("Digite o Cargo do funcionário a ser excluido.");
        String funcao = SCANNER.nextLine();

        long contadorNomes = repositorio.getFuncionarios().stream()
                        .filter(x -> Objects.equals(x.getNome(), nome)).count();

        if(contadorNomes < 1){
            System.out.println("Nome não existente");
            return;
        }

        repositorio.getFuncionarios().removeIf(x -> Objects.equals(x.getNome(), nome));
        System.out.println("Funcionário removido com sucesso.");
    }

    private static void criarFuncionario(){
        Funcionario funcionario = new Funcionario();

        System.out.println("Digite o Nome do Funcionario");
        funcionario.setNome(SCANNER.nextLine());

        System.out.println("Digite o Ano do Nascimento");
        int ano = SCANNER.nextInt();
        System.out.println("Digite o mês de nascimento");
        int mes = SCANNER.nextInt();
        while(mes < 1 || mes > 12){
            System.out.println("Mes invalido digite novamente");
            mes = SCANNER.nextInt();
        }
        System.out.println("Digite seu dia de nascimento");
        int dia = SCANNER.nextInt();
        while(!confereDia(mes, dia, ano)){
            System.out.println("Digite um dia de nascimento válido");
            dia = SCANNER.nextInt();
        }
        funcionario.setDataNascimento(LocalDate.of(ano, mes, dia));

        System.out.println("Digite o Salário do Funcionario");
        funcionario.setSalário(SCANNER.nextBigDecimal());

        System.out.println("Digite a função destinada ao Funcionário.");
        funcionario.setFuncao(SCANNER.nextLine());

        repositorio.getFuncionarios().add(funcionario);
        System.out.println("Funcionário criado com sucesso.");
    }

    private static boolean confereDia(int mes, int dia, int ano){
        boolean verificador;
        switch (mes){
            case 1, 3, 5, 7, 8, 10, 11, 12:
             verificador = dia > 0 && dia <= 31;
            break;
            case 2:
                if(ano % 4 == 0 && ano % 100 != 0){
                    verificador = dia > 0 && dia <= 29;
                    break;
                }
                verificador = dia > 0 && dia <= 28;
                break;
            case 4, 6, 9:
                verificador = dia > 0 && dia <= 30;
                break;
            default:
                verificador = false;
                break;
        }
        return verificador;
    }
}
