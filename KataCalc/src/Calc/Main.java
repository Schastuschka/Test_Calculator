package Calc;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static String calc(String input) throws Exception {
        String [] primer = input.split(" ");
        String otvet;
        if (primer.length != 3) {throw new Exception("Ввести только два числа  не больше 10 и знак действия");}
        try {
            otvet = Integer.toString(arabCalc(primer));
        }
        catch (NumberFormatException e){
            otvet = romeCalc(primer);}
        return " Ответ: " + otvet;

    }

    public static String romeCalc(String [] primer) throws Exception {
        int x = romeToArab(primer[0]);
        int y = romeToArab(primer[2]);
        String znak = primer[1];
        if (x == -1 || y == -1) {
            throw new Exception("Другая система счисления или неправильно введены данные");
        }
        int otvet = switch (znak) {
            case "+" -> x + y;
            case "-" -> x - y;
            case "*" -> x * y;
            case "/" -> x / y;
            default -> throw new Exception("Такое действие не предусмотрено");
        };
        if (otvet <= 0) {
            throw new Exception("В римской системе нет нуля и  отрицательных чисел");
        }
        return arabToRome(otvet);
    }
    public static int arabCalc(String [] primer) throws Exception {
        int m = Integer.parseInt(primer[0]);
        int n = Integer.parseInt(primer[2]);
        String znak = primer[1];

        if (m > 10 || n > 10) {
            throw new Exception("Число не должно быть больше 10 или X");
        }
        int otvet = switch (znak) {

            case "+" -> m + n;
            case "-" -> m - n;
            case "*" -> m * n;
            case "/" -> m / n;
            default -> throw new Exception(" Такое действие не предусмотрено");
        };
        return otvet;
    }

    public static int romeToArab(String romNumber){
        int rN = -1;
        String [] romNumb = {"I","II","III","IV","V","VI","VII","VIII","IX","X"};
        for(int i = 0;i<10;i++){
            if(Objects.equals(romNumber,romNumb[i])){
                rN = i + 1;
            }
        }
        return rN;
    }
    public static String arabToRome(int d){
        String [] romNumber = {"I","II","III","IV","V","VI","VII","VIII","IX","X"};
        ArrayList<String> rN = new ArrayList<>();
        StringBuilder otv = new StringBuilder();
        while (d > 0){
            if (d/100 != 0){
                d -= 100;
                rN.add("C");
            }else if
            (d/50 != 0) {
                if (d >= 90) {
                    rN.add("XC");
                    d -= 90;
                } else {
                    rN.add("L");
                    d -= 50;
                }
            }
            else if(d/10 != 0){
                if (d >= 40){
                    rN.add("XL");
                    d -= 40;
                }
                else{
                    rN.add("X");
                    d -= 10;
                }}
            else {
                rN.add(romNumber[d - 1]);
                d -= 10;
            }
        }
        for (String r:rN) {
            otv.append(r);
        }

        return otv.toString();
    }


    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите два числа от 1 до 10( в арабской или римской ");
        System.out.println("системе счисления) и знак действия( + , - , *, /");
        String primer = sc.nextLine();
        System.out.println(calc(primer));
    }
}