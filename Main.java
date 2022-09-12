import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        String input;
        String otvet;
        Scanner s = new Scanner(System.in);
        System.out.println("итак, введите арифметическое выражение из одного действия");
        input = s.nextLine();

        otvet = calc (input); //
        System.out.println(otvet); //System.out.println(otvet+" ответ вернувшийся в Main");
    }

    public static String calc(String input) throws FileNotFoundException {
//        System.out.println(input); // передача состоялась)
        if (input.length()<5) {
            try {
                throw new IOException();
            } catch (IOException e) {
                return "некорректный ввод, т.к. строка не является математической операцией  (21)";
            }
        }

        input = input.trim();
        int inputLenght = input.length();
        char[] charArray = input.toCharArray();    // создаёт массив символов, посимвольная разбивка
        int counterSpace = 0;
        for (int i=0; i<inputLenght; i++) {
            char cA = charArray[i];
            if (cA == " ".codePointAt(0)) {
                counterSpace++;
            }
        }          // пробелы тоже символы
        if (counterSpace > 2) {
            try {
                throw new IOException();
            } catch (IOException e) {
            return "недопустимый ввод, т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (39)";
            }

        }

        String[] inputSplit;
        inputSplit = input.split(" ");
        String iS0 = inputSplit[0];     // input.split состоит из 3х переменных is1 is2 is3
        String iS1 = inputSplit[1];
        String iS2 = inputSplit[2];

        String flag = null;
        int indexIS0 = 0;
        int indexIS2 = 0;
        int res = 0; int z=0;

        String[] arrayActions = new String[]{"+", "-", "*", "/"};
        for ( String act: arrayActions) {
            if (act.equals(iS1)) {z++;}
        }
        if (z==0) {
            try {
                throw new IOException();
            }  catch (IOException e) {
                return "недопустимый ввод, где арифметическое действие? (63)";
            }
        }

        String znak = "+";
        String[] arrayArab = new String[101];
        File fileRim = new File("rim.txt");
        Scanner scannerRim = new Scanner(fileRim);
        String[] arrayRim = new String[101];
        for (int i = 0; i <= 100; ++i) {
            arrayRim[i] = scannerRim.nextLine();
            arrayArab[i] = Integer.toString(i);} // заполняем с преобразованием в строки

        for (int i=0; i<=10; ++i) {
            if (iS0.equals(arrayArab[i])) {
                for (int j = 0; j <= 10; ++j) {
                    if (iS2.equals(arrayArab[j])) {flag = "Arab"; indexIS0=i; indexIS2 = j; break;}}
            } else if (iS0.equals(arrayRim[i])) {
                for (int j = 0; j <= 10; ++j) {
                    if (iS2.equals(arrayRim[j])) {flag = "Rim"; indexIS0=i; indexIS2=j; break;}}
            }
        }

        if (flag == null) { //(0>indexIS0) | (indexIS0>10) | (indexIS2>10) |(indexIS0<0) | (flag == null)
            try {
                throw new IOException();
            } catch (IOException e) {
                return "недопустимый ввод, целые цифры/числа от 0 до 10 включительно либо арабские либо римские (90)";
            }
        }

        switch (iS1) {
            case "+": res = indexIS0 + indexIS2; break;
            case "-": if (indexIS2<=indexIS0) {res = indexIS0 - indexIS2; break;} else res = indexIS2 - indexIS0; znak = "-"; break;
            case "*": res = indexIS0 * indexIS2; break;
            case "/": res = indexIS0 / indexIS2; break;
        }

        if ( ((res == 0) && (flag.equals("Rim"))) || (((znak.equals("-")) && (flag.equals("Rim"))))) {
            try {
                throw new IOException();
            } catch (IOException e) {
                return "недопустимый ввод, результат <1 (105)";
            }
        }
        if (znak.equals("+")) {if (flag.equals("Arab")) {input=arrayArab[res];} else input=arrayRim[res];}
        else if (flag.equals("Arab")) {input="-"+arrayArab[res];} else input="-"+arrayRim[res];

        return input;
    }
}



