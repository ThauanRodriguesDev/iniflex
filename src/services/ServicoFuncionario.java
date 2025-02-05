package services;

import entity.Funcionario;
import repository.RepositorioFuncionario;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalField;
import java.util.Comparator;
import java.util.List;
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
            case 2:
                excluirFuncionario();
                break;
            case 3:
                System.out.println("Como você quer imprimir a lista de funcionários ?");
                System.out.println("1- Imprimir Todos");
                System.out.println("2- Imprimir Todos por função exercida");
                System.out.println("3- Imprimir Funcionários por ordem alfabética");
                System.out.println("4- Imprimir com Base no Aniversário");
                System.out.println("5- Imprimir Salário do Funcionário");
                System.out.println("6- Imprimir Quantidade de Salários Minimos dos Funcionários");
                System.out.println("7- Imprimir Funcionário de Maior Idade");
                System.out.println("Qualquer outro numero- Voltar ao Menu inicial");
                int indice = SCANNER.nextInt();
                switch (indice) {
                    case 1:
                        imprimirTodosFuncionarios();
                        break;
                    case 2:
                        imprimirFuncionariosPorFuncao();
                        break;
                    case 3:
                        imprimirTodosFuncionariosPorOrdemAlfabetica();
                        break;
                    case 4:
                        imprimirComBaseNoMes();
                        break;
                    case 5:
                        imprimirSalarioFuncionario();
                        break;
                    case 6:
                        imprimirQuantidadeSalarioMinimoFuncionario();
                        break;
                    case 7:
                        imprimirMaiorSalarioEIdade();
                        break;
                    default:
                        break;
                }
                break;
            default:
                System.out.println("Opção Invalida insira novamente.");
        }
    }

    private static void imprimirSalarioFuncionario(){
        System.out.println("Impressão Do Funcionário com seu respectivo salário");
        repositorio.getFuncionarios().forEach(x
                -> System.out.println("---------------------\n" + "Funcionario: " + x.getNome() + " Salário: R$" + x.getSalario()));
    }

    private static void imprimirQuantidadeSalarioMinimoFuncionario(){
        System.out.println("Impressão Do Funcionário com sua quantidade de salário mínimo");
        repositorio.getFuncionarios().forEach(x
                -> System.out.println("---------------------\n" + "Funcionario: " + x.getNome()
                + " Quantidade de Salário mínimo: " + (x.getSalario().divide(BigDecimal.valueOf(1212)))));
    }

    private static void imprimirMaiorSalarioEIdade(){
        Funcionario funcionario = repositorio.getFuncionarios().stream()
                .max(Comparator.comparing(x -> x.getDataNascimento().getLong((TemporalField) DateFormat.Field.MILLISECOND)))
                .get();

        int ano = LocalDate.now().getYear() - funcionario.getDataNascimento().getYear();

        if (LocalDate.now().getDayOfYear() < funcionario.getDataNascimento().getDayOfYear()) {
            ano--;
        }
        System.out.println("O Funcionário com maior idade é: " + funcionario.getNome() + " e sua idade é: " + ano);
    }

    private static void imprimirComBaseNoMes(){
        System.out.println("Digite os Dois Meses para filtrar");
        int mes1= SCANNER.nextInt();
        int mes2 = SCANNER.nextInt();

        System.out.println("Funcionário Mês " + mes1);
        repositorio.getFuncionarios().stream().filter(x -> x.getDataNascimento().getMonthValue() == mes1)
                .forEach(x -> System.out.println("---------------------------\n" + "Nome do Funcionario " + x.getNome() + " Cargo: " + x.getFuncao()));

        System.out.println("Funcionário Mês " + mes2);
        repositorio.getFuncionarios().stream().filter(x -> x.getDataNascimento().getMonthValue() == mes2)
                .forEach(x -> System.out.println("--------------------------\n" + "Nome do Funcionario " + x.getNome() + " Cargo: " + x.getFuncao()));
    }

    private static void imprimirTodosFuncionarios(){
        for(Funcionario repos: repositorio.getFuncionarios()){
            System.out.println("------------------------------------");
            System.out.println("Nome do Funcionário: " + repos.getNome());
            System.out.println("Data de nascimento " + repos.getDataNascimento()
                    .format(DateTimeFormatter.ISO_LOCAL_DATE));
            System.out.println("Função: " + repos.getFuncao());
            System.out.println("Salário: " + repos.getSalario());
        }
    }
    private static void imprimirTodosFuncionariosPorOrdemAlfabetica(){
        List<Funcionario> listaOrdenada = repositorio.getFuncionarios().stream().sorted(Comparator.comparing(x -> x.getNome())).toList();
        for(Funcionario repos: listaOrdenada){
            System.out.println("------------------------------------");
            System.out.println("Nome do Funcionário: " + repos.getNome());
            System.out.println("Data de nascimento " + repos.getDataNascimento()
                    .format(DateTimeFormatter.ISO_LOCAL_DATE));
            System.out.println("Função: " + repos.getFuncao());
            System.out.println("Salário: " + repos.getSalario());
        }
    }

    private static void imprimirFuncionariosPorFuncao(){
        repositorio.getFuncoesFuncionarios().forEach((x, v) ->
                System.out.println("------------------------\n" + "Funcionario " + v.getNome() + " respectiva função: " + x));
    }


    private static void excluirFuncionario(){
        int contadorFalhas = 0;

        System.out.println("Digite o nome do funcionario que você quer excluir");
        String nome = SCANNER.nextLine();


        long contadorNomes = repositorio.getFuncionarios().stream()
                        .filter(x -> Objects.equals(x.getNome().toLowerCase(), nome.toLowerCase())).count();

        if(contadorNomes < 1){
            System.out.println("Nome não existente");
            return;
        }

        for(Funcionario func: repositorio.getFuncionarios()){
            if(nome.equalsIgnoreCase(func.getNome())){
                repositorio.getFuncoesFuncionarios().remove(func.getFuncao(), func);
                repositorio.getFuncionarios().remove(func);
                contadorFalhas++;
            }
        }

        if (contadorFalhas < 1){
            System.out.println("Falha ao remover funcionario");
            return;
        }
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
        funcionario.setSalario(SCANNER.nextBigDecimal());

        System.out.println("Digite a função destinada ao Funcionário.");
        funcionario.setFuncao(SCANNER.nextLine());

        repositorio.getFuncionarios().add(funcionario);
        repositorio.getFuncoesFuncionarios().put(funcionario.getFuncao().toLowerCase(), funcionario);
        System.out.println("Funcionário criado com sucesso.");
    }

    private static boolean confereDia(int mes, int dia, int ano){
        boolean verificador;
        switch (mes){
            case 1, 3, 5, 7, 8, 10, 12:
             verificador = dia > 0 && dia <= 31;
            break;
            case 2:
                if(ano % 4 == 0 && ano % 100 != 0){
                    verificador = dia > 0 && dia <= 29;
                    break;
                }
                verificador = dia > 0 && dia <= 28;
                break;
            case 4, 6, 9, 11:
                verificador = dia > 0 && dia <= 30;
                break;
            default:
                verificador = false;
                break;
        }
        return verificador;
    }
}
