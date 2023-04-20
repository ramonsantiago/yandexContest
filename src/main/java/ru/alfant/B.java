package ru.alfant;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class B {
    public static void main(String[] args) {
        String fileName = "/home/skat/input.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            int n = Integer.parseInt(reader.readLine());
            String[][] cabin = new String[n][2];
            for (int i = 0; i < n; i++) {
                String[] line = reader.readLine().split("_");
                cabin[i][0] = line[0];
                cabin[i][1] = line[1];
            }
            int m = Integer.parseInt(reader.readLine());
            for (int i = 0; i < m; i++) {
                String[] line = reader.readLine().split("\\s");
                int p = Integer.parseInt(line[0]);
                int side = line[1].equals("left") ? 0 : 1;
                String literals = side == 0 ? "ABC" : "DEF";
                String dir = ((line[1].equals("left") && line[2].equals("window")) || (line[1].equals("right") && line[2].equals("aisle"))) ? "left" : "right";
                boolean find = false;
                int row = -1;
                for (int r = 0; r < n; r++) {
                    find = false;
                    row = -1;
                    // find row
                    if (dir.equals("left")) {
                        if (cabin[r][side].startsWith(".".repeat(p))) {
                            cabin[r][side] = p == 3 ? "XXX" : "X".repeat(p) + cabin[r][side].substring(p);
                            find = true;
                            row = r;
                            System.out.print("Passengers can take seats:");
                            for (int l = 0; l < p; l++) {
                                System.out.print(" " + (r + 1) + literals.charAt(l));
                            }
                            System.out.print("\n");
                            break;
                        }
                    } else {
                        if (cabin[r][side].endsWith(".".repeat(p))) {
                            cabin[r][side] = p == 3 ? "XXX" : cabin[r][side].substring(0, 3 - p) + "X".repeat(p);
                            find = true;
                            row = r;
                            System.out.print("Passengers can take seats:");
                            for (int l = 3 - p; l < 3; l++) {
                                System.out.print(" " + (r + 1) + literals.charAt(l));
                            }
                            System.out.print("\n");
                            break;
                        }
                    }
                }
                if (find) {
                    printCabin(cabin);
                    cabin[row][side] = cabin[row][side].replaceAll("X", "#");
                } else System.out.println("Cannot fulfill passengers requirements");
                // check flag, print, clean flag
            }

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    private static void printCabin(String[][] cabin) {
        for (int i = 0; i < cabin.length; i++) {
            System.out.println(cabin[i][0] + "_" + cabin[i][1]);
        }
    }
}