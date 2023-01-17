package cz.decko.procvicovani;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static final String SOUBOR = "vstup.txt";

    public static void main(String[] args) throws ZvysPocetProdeju {

        List<Zakaznik> zakaznik1 = new ArrayList<>();

        zakaznik1.add(new Zakaznik("František Karel", 25));
        zakaznik1.add(new Zakaznik("Karel Franta", 30));

        int lastIndex = zakaznik1.size() -1;
        zakaznik1.remove(lastIndex);

        for (Zakaznik zakaznik : zakaznik1) System.out.println(zakaznik.getJmeno() + ":" + zakaznik.getPocetProdeju());


        List<String> zakaznik2 = new ArrayList<>();

        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(SOUBOR)))
        ){
            while (scanner.hasNextLine()){
                String prectenyRadek = scanner.nextLine();
                String[] line = prectenyRadek.split("#");
                zakaznik2.addAll(Arrays.asList(line));
            }
        } catch (FileNotFoundException e){
            throw new RuntimeException(e);
        }

        zakaznik2.forEach(System.out::println);

        List<Zakaznik> zakaznik3 = new ArrayList<>();

            zakaznik3.add(new Zakaznik("Karel Dvořák", 20));

            writeToFile(zakaznik3, "zaznamy", ":");

    }

    private static void writeToFile(List<Zakaznik> zakaznik3, String soubor, String oddelovac) throws ZvysPocetProdeju {

        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(soubor)))
        ){
            for (Zakaznik zakaznik : zakaznik3){
                writer.println(zakaznik.getJmeno() + oddelovac + zakaznik.getPocetProdeju());
            }
        } catch (IOException e){
            throw new ZvysPocetProdeju("Chyba při zápisu do souboru " + soubor + ": " + e.getLocalizedMessage());
        }

    }


}
