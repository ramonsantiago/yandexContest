package ru.alfant;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class C {

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
            int[] arr = new int[n];
            for (int i = 0; i < n; i++){
                arr[i] = scanner.nextInt();
            }

            //sort
            Element[] elements = new Element[n];
            for (int i = 0; i < arr.length; i++) {
                elements[i] = new Element(arr[i], i);
            }
            Arrays.sort(elements, Comparator.comparingInt(e -> e.value));

            //find min distance
            int[] res = new int[n];
            for (int i = 0; i < n; i++){
                int positivIndex = i;
                int negativeIndex = i;
                int distance = 0;
                for (int j = 0; j < k; j++){
                    if ((positivIndex + 1) < n){
                        int positivValue = Math.abs(elements[positivIndex + 1].value - elements[i].value);
                        if ((negativeIndex - 1) >= 0){
                            int negativeValue = Math.abs(elements[negativeIndex - 1].value - elements[i].value);
                            if (negativeValue < positivValue){
                                negativeIndex--;
                                distance += negativeValue;
                            }else{
                                positivIndex++;
                                distance += positivValue;
                            }
                        }else{
                            positivIndex++;
                            distance += positivValue;
                        }
                    }else{
                        negativeIndex--;
                        distance += Math.abs(elements[negativeIndex].value - elements[i].value);
                    }
                }
                res[elements[i].index] = distance;
            }

            // print res
            System.out.print(res[0]);
            for (int i = 1; i < n; i++){
                System.out.print(" " + res[i]);
            }
        }
    }

