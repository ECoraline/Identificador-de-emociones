package Principal;

import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {

        System.out.println("\t\t********Bienvenido al asistente emocional de UTC********\n");
        Scanner sc = new Scanner(System.in);
        int continuar;
        do {
            System.out.println("A continuacion dime como te " +
                    "sientes para ayudarte a identificar tus emociones, puedes escribir cosas que te han pasado o tus sentimientos: ");
            var texto = sc.nextLine();
            ConsultaAPI consultaAPI = new ConsultaAPI();
            System.out.println(consultaAPI.Consultar(texto));
            System.out.println("\n\t\t*****Para salir ingrese el numero 0 (Cero), para continuar ingresa 1 (Uno)*****");
            continuar = sc.nextInt();
            sc.nextLine();
            if (continuar == 0) {
                System.out.println("\t\t*****Gracias por usar el asistente emocional de UTC*****");
                break;
            }
        }while (true) ;
    }
}