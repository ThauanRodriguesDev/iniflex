import services.ServicoFuncionario;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        private static final Scanner SCANNER = new Scanner(System.in);
        private int indice = 1;
        while(indice != 0){
            produzirMenu();
            indice = Integer.parseInt(SCANNER.nextLine());
            ServicoFuncionario.construirMenu(indice);

        }
    }
    private static void produzirMenu(){
        System.out.println("Digite o Numero da Operacão");
        System.out.println();
    }
}