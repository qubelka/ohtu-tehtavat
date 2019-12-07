package ohtu.kivipaperisakset;

import java.util.Scanner;

public class KonsoliIO implements IO {
    private Scanner scanner;

    public KonsoliIO() {
        scanner = new Scanner(System.in);
    }

    @Override
    public void print(String m) {
        System.out.print(m);
    }

    @Override
    public void println(String m) {
        System.out.println(m);
    }

    @Override
    public String read() {
        return scanner.nextLine();
    }

}
