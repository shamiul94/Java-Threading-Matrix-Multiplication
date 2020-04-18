package OfflineAssignment;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 *
 * @author Shamiul Hasan Rumman-BUET CSE-1505038
 */
public class Main {

    static int[][] Ans = new int[1000][1000];

    public static void main(String[] args) {
        Queue<WorkItem> q;
        q = new LinkedList<>();

        int rowA, rowB;
        int colmA, colmB;
        int n;
        int[][] A, B, TransB;
        A = new int[1000][1000];
        B = new int[1000][1000];
        TransB = new int[1000][1000];

        Scanner conin = new Scanner(System.in);
        System.out.println("Enter Row Number of Matrix A: ");
        rowA = conin.nextInt();
        System.out.println("Enter Column Number of Matrix A: ");
        colmA = conin.nextInt();
        System.out.println("Enter Row Number of Matrix B: ");
        rowB = conin.nextInt();
        System.out.println("Enter Column Number of Matrix B: ");
        colmB = conin.nextInt();

        int i, j, k;

        System.out.println("Enter Elements of Matrix A: ");
        for (i = 0; i < rowA; i++) {
            for (j = 0; j < colmA; j++) {
                A[i][j] = conin.nextInt();
            }
        }
        System.out.println("Enter Elements of Matrix B: ");
        for (i = 0; i < rowB; i++) {
            for (j = 0; j < colmB; j++) {
                B[i][j] = conin.nextInt();
            }
        }

        System.out.println("How Many Threads Do You Want to Create?: ");
        n = conin.nextInt();

        for (i = 0; i < rowB; i++) {
            for (j = 0; j < colmB; j++) {
                TransB[j][i] = B[i][j];
            }
        }

        for (i = 0; i < rowA; i++) {
            for (j = 0; j < colmB; j++) {
                WorkItem Item = new WorkItem(i, j, A[i], TransB[j]);
                q.add(Item);
            }
        }
        int EachThread = (rowA * colmB) / n;

        WorkerThread[] t = new WorkerThread[n];

        for (i = 0; i < n; i++) {
            t[i] = new WorkerThread(q, EachThread, colmA);
        } 

        try {
            for (i = 0; i < n; i++) {
                t[i].T.join();
            }
        } catch (InterruptedException ex) {
            System.out.println("Interrupted.");
        }

        for (i = 0; i < rowA; i++) {
            for (j = 0; j < colmB; j++) {
                System.out.print(Ans[i][j] + " ");
            }
            System.out.println("");
        }

    }
}
