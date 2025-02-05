import services.ServicoFuncionario;

import java.util.Scanner;
// A dificuldade que foi nomear tudo em portugues ksksks
public class Main {

    public static void main(String[] args) {
        final Scanner SCANNER = new Scanner(System.in);
        int indice = 1;
        while(indice != 0){
            produzirMenu();
            indice = Integer.parseInt(SCANNER.nextLine());
            ServicoFuncionario.construirMenu(indice);
        }
    }
    private static void produzirMenu(){
        System.out.println("----------------------------");
        System.out.println("Digite o Numero da Operacão");
        System.out.println("1- Criar Funcionário");
        System.out.println("2- Excluir Funcionário");
        System.out.println("3- Ver Funcionários");
        System.out.println("4- Aumentar Salários por porcentagem");
        System.out.println("0- Finalizar");
    }
}