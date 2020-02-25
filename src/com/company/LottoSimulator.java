package com.company;

import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

public class LottoSimulator {
    public static void main(String[] args){
        lotto();
    }


    public static void  lotto (){
        int[] random = new int[6];
        int[] guess = new int[random.length];
        Random rand = new Random();

       Scanner scan = new Scanner(System.in);

        for(int i=0; i<random.length; i++) {
            System.out.println("Podaj " + i +" liczbę:");
            guess[i]=getIntValue(scan);
            random[i]=rand.nextInt(49)+1;

            if(i>0){

                for(int j = 0; j<i; j++) {

                    while (random[j] == random[i]) {
                        random[j] = getIntValue(scan);
                    }

                    while (guess[j] == guess[i]) {
                        System.out.println("Liczba została uzyta. Podaj inną " + i + " liczbę:");
                        guess[i] = getIntValue(scan);
                    }
                }
            }
        }

        Arrays.sort(random);
        System.out.println("Wylosowane liczby to:" + Arrays.toString(random));

        Arrays.sort(guess);
        System.out.println("Wybrane liczby to: " + Arrays.toString(guess));

        int counter=0;

        for(int i = 0 ; i<guess.length; i++){
            for(int j=0; j<random.length; j++){
            if(guess[i]==random[j]) {
                counter++;
            }
            }
        }

        if(counter>=3){
            System.out.println("Trafiłeś conajmniej trójkę!");
        }

        else{
            System.out.println("Nic nie wygrałeś");
        }
    }

    static int getIntValue(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            scanner.nextLine();
            System.out.println("To nie jest liczba");
        }

        int a = scanner.nextInt();

        while(a<1||a>49) {
            System.out.println("Podaj liczbę z zakresu 1-49:");
            a = scanner.nextInt();
        }

        return a;
    }
}
