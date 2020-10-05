package ir.maktab;

import java.util.Scanner;

public final class Scan {

    private static Scan referenceToSingleInputObject = null;

    private Scanner scannerKeyboard;

    private Scan() {
        scannerKeyboard = new Scanner(System.in);
    }

    public static Scan getInstance() {
        if (referenceToSingleInputObject == null) referenceToSingleInputObject = new Scan();
        return referenceToSingleInputObject;
    }

    public String getString(String sPrompt) {
        System.out.print(sPrompt);
        return scannerKeyboard.nextLine();
    }

    public String getString() {
        return scannerKeyboard.nextLine();
    }

    public void clearBuffer(){
        scannerKeyboard.nextLine();
    }

    private String getText(String s) {
        System.out.print(s);
        StringBuilder text = new StringBuilder();
        while (true) {
            String temp = getString();
            if (temp.equals("ESC")) {
                break;
            }
            text.append(temp).append("\n");
        }
        return text.toString();
    }
}