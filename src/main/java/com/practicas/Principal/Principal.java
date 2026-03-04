package com.practicas.Principal;

import com.practicas.service.ConsumoApi;

import java.util.Scanner;

public class Principal {
    private Scanner teclado=new Scanner(System.in);
    private ConsumoApi consumoApi = new ConsumoApi();
    private final String URL_BASE= "https://gutendex.com/books";

    public void muestraMenu() {
        var opcion= -1;
        while (opcion !=0){
            var menu = """
                    1 - Buscar autor
                    2 - Buscar libro
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();
        }
    }
}
