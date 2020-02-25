package com.company;

import java.util.Random;
import java.util.Scanner;


public class GuessGame2 {
    public static void main(String[] args){
        guessNumber2();
    }

    public static void  guessNumber2 () {
        Scanner scan = new Scanner(System.in);
        System.out.println("Podaj liczbę 1-1000:");
        int numberToGuess = getIntValue(scan);

        Random rand = new Random();
        int i=0;
        int max=1000;
        int min=0;
        int numberGuessed = rand.nextInt((max - min) + 1) + min;

        do{
            if(numberGuessed>numberToGuess) {
                System.out.println("Zgadywana liczba to: "+numberGuessed+ " z przedziału: "+min+ " - "+max+". Szukana liczba jest większa.");
                max = numberGuessed;
            }
            else if(numberGuessed<numberToGuess) {
                min = numberGuessed;
                System.out.println("Zgadywana liczba to: "+numberGuessed+ " z przedziału: "+min+ " - "+max+". Szukana liczba jest mniejsza.");
            }

            numberGuessed = rand.nextInt((max - min) + 1) + min;
           i++;
        }while(numberGuessed!=numberToGuess&&i<=10);

        if(numberGuessed==numberToGuess) System.out.println("Twoja liczba to: "+numberGuessed);
        else System.out.println("Liczba nie została odgadnięta.");

    }

    static int getIntValue(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            scanner.nextLine();
            System.out.println("To nie jest liczba");
        }

        int a = scanner.nextInt();

        while(a<1||a>1000) {
            System.out.println("Podaj liczbę z zakresu 1-1000:");
            a = scanner.nextInt();
        }

        return a;
    }
}
