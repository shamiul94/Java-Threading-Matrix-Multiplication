package OfflineAssignment;

import java.util.Queue;

public class WorkerThread implements Runnable {

    int Each, colmA;
    Queue<WorkItem> q;
    Thread T;

    public WorkerThread(Queue<WorkItem> q, int Each, int colA) {
        this.q = q;
        this.Each = Each;
        T = new Thread(this);
        colmA = colA;
        T.start();
    }

    @Override
    public void run() {
        int time = 0;
        while (!q.isEmpty() && time < Each) {
            synchronized (q) {
                //System.out.println(""+T);
                int i, sum = 0;
                WorkItem Item = q.remove();
                for (i = 0; i <= colmA; i++) {
                    sum += (Item.arrA[i] * Item.arrB[i]);
                }
                Main.Ans[Item.i][Item.j] = sum;
                time++;
            }
        }
    }

}
