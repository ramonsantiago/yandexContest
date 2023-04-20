package ru.alfant;

import java.util.Scanner;

public class A {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int first = scanner.nextInt();
        int previous = first;
        int last = first;
        for (int i = 1; i < n; i++){
            last = scanner.nextInt();
            if(last < previous) {
                System.out.println(-1);
                System.exit(0);
            }
            previous = last;
        }
        System.out.println(last - first);
    }
}
