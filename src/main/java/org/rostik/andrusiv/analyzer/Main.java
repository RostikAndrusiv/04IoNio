package org.rostik.andrusiv.analyzer;

public class Main {
    public static void main(String[] args) {
        int option = Integer.parseInt(args[0]);
        String pathToFile = args[1];

        switch (option){
            case 1:
                DiskAnalyzer.printPathFileWithMostS(pathToFile);
                break;
            case 2:
                DiskAnalyzer.printFilesBySize(pathToFile);
                break;
            case 3:
                DiskAnalyzer.printAvgFilesSize(pathToFile);
                break;
            case 4:
                DiskAnalyzer.printDividedByFirstLetters(pathToFile);
                break;
            default:
                System.out.println("wrong input");
        }

        DiskAnalyzer.printPathFileWithMostS(null);
    }
}
