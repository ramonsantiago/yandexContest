package ru.alfant;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

    static class Element {
        int value;
        int index;

        public Element(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();

        // sort
        Element[] elements = new Element[n];
        for (int i = 0; i < n; i++) {
            elements[i] = new Element(scanner.nextInt(), i);
        }
        Arrays.sort(elements, Comparator.comparingInt(e -> e.value));

        // find min distance
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            int left = i - 1;
            int right = i + 1;
            int distance = 0;
            for (int j = 0; j < k; j++) {
                if (left >= 0 && (right == n || Math.abs(elements[i].value - elements[left].value) <= Math.abs(elements[right].value - elements[i].value))) {
                    distance += Math.abs(elements[i].value - elements[left].value);
                    left--;
                } else { //if (right < n && (left < 0 || Math.abs(elements[right].value - elements[i].value) < Math.abs(elements[i].value - elements[left].value))) {
                    distance += Math.abs(elements[right].value - elements[i].value);
                    right++;
                }
            }
            res[elements[i].index] = distance;
        }

        // print res
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(res[i]).append(" ");
        }
        System.out.println(sb.toString().trim());
    }
}
