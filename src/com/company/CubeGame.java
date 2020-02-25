package com.company;
import java.util.Scanner;
import java.util.Random;


public class CubeGame {

    public static void main(String[] args){
        System.out.println("Jaki rzut wykonać? Podaj dane wg wzoru: [ilość rzutów; jeżeli 1, nic nie wpisuj] D[rodzaj kostki] + [liczba punktów do dodania; jeżeli 0, nic nie wpisuj].");
        String cubeThrow = getThrow();
        System.out.println("Rzut kostką/-ami dał wynik: " + getResult(cubeThrow));

    }

    public static int getResult(String cubeThrow){
        int result = 0;
        Random rand = new Random();
        String[] throwParts = cubeThrow.split("D");

        if (cubeThrow.matches("D(3|4|6|8|10|12|20|100)")) result = rand.nextInt(Integer.parseInt(throwParts[1]))+1;

        else if (cubeThrow.matches("(\\d+)D(3|4|6|8|10|12|20|100)")){
            for (int i = 0 ; i<Integer.parseInt(throwParts[0]); i++) {
                result += rand.nextInt(Integer.parseInt(throwParts[1])) + 1;
            }
        }

        else if (cubeThrow.matches("D(3|4|6|8|10|12|20|100)(\\+\\d+)")){
            result += rand.nextInt(Integer.parseInt(throwParts[1].split("\\+")[0])) + 1 + Integer.parseInt(throwParts[0].split("\\+")[1]);
        }

        else if (cubeThrow.matches("(\\d+)D(3|4|6|8|10|12|20|100)(\\+\\d+)")) {
            for (int i = 0; i < Integer.parseInt(cubeThrow.split("D")[0]); i++) {
                result += rand.nextInt(Integer.parseInt(throwParts[1].split("\\+")[0]));
            }
            result += Integer.parseInt(throwParts[1].split("\\+")[1]);
        }
        
        return result;
    }

    public static String getThrow(){
        Scanner scan = new Scanner(System.in);
        String cubeThrow;

        while (true) {
            cubeThrow = scan.nextLine();
            if (cubeThrow.matches("(\\d+)?D(3|4|6|8|10|12|20|100)(\\+\\d+)?")) break;
            else System.out.println("Podaj poprawne dane!");
        }

        scan.close();
        return cubeThrow;
    }

}
