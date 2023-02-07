package HueSortingProject;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import javax.swing.Timer;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.DimensionUIResource;
import java.awt.event.*;
import java.util.*;

public class HueSorter extends JPanel implements ActionListener {

    public final int TOTAL_PIXELS = 70000;
    int[] hueList = new int[TOTAL_PIXELS];
    int countingTime = 0;
    boolean timerStarted = false;
    String sortType = "sortType: incrementing sort";
    double animationRunTime = 0;
    double realRunTime = 0;
    Timer timer;
    double startTime;
    double offset;
    int redness = 0;
    int realtimesortcounter;
    int algtype = 1;// number which indicates which algorithm to use
    int percentSorted = 0; // not used yet

    public static void main(String[] args) {
        JFrame frame = new JFrame("Color Sorter");
        frame.add(new HueSorter());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLayout(null);
        float[] hsb = ColorUIResource.RGBtoHSB(255, 0, 0, null);
        System.out.println(hsb[0] + " " + hsb[1] + " " + hsb[2]);

    }

    public void reset() {
        makeRandom();
        countingTime = 0;
        timerStarted = false;
        sortType = "sortType: ";

        animationRunTime = 0;
        realRunTime = 0;
        redness = 0;
        realtimesortcounter = 0;
        switch (algtype) {
            case 1:
                sortType += "incrementing";
                break;
            case 2:
                sortType += "merge";
                break;
            case 3:
                sortType += "quick";
                break;
            case 4:
                sortType += "bubble";
                break;
            case 5:
                sortType += "shell";
                break;
            case 6:
                sortType += "counting";
                break;
        }
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        animationRunTime = (int) getTime() - (int) startTime;
        switch (algtype) {
            case 1:

                incrementingSort();
                break;

            case 2:
                // do merge sort
                break;

            case 3:
                quickSort();
                break;
        }

    }

    public void changeSortTypeLeft() {
        algtype--;

        reset();
    }

    public void changeSortTypeRight() {
        algtype++;

        reset();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (timerStarted == false) {
            makeRandom();
            timer.start();
            timerStarted = true;
        }
        draw(g);
        displayNumbers(g);

    }

    public void displayNumbers(Graphics g) { // displays the numbers and times
        g.drawString(sortType, 100, 100);
        String animation = "animationRunTime: ";
        String runtime = "realRunTime: ";
        String ticks = "ticks: ";
        g.drawString(animation + String.valueOf(animationRunTime) + " ms", 100, 120);
        g.drawString(ticks + String.valueOf(countingTime) + " ms", 100, 110);
        g.drawString(runtime + String.valueOf(realRunTime), 100, 130);
    }

    public void mergeSort() {

    }

    public void setOffset() {// method that sets the offset variable to the negative runtime, and is used in
                             // getTime() method to add the current time to obtain the real time

        offset = 0 - System.currentTimeMillis();

    }

    public double getTime() {
        return System.currentTimeMillis() + offset;
    }

    public void quickSort() {
        if (countingTime == 0) {
            int[] copyList = Arrays.copyOf(hueList, hueList.length);

            setOffset();
            realTimeQuickSort(copyList, 0, 7000);
            realRunTime = getTime();
            System.out.println(realRunTime);
            setOffset();

        }
        countingTime++;

        if (countingTime == 1) {

            animationQuickSort(hueList, 0, 7000);
        }

        // animationRunTime = (int) getTime();
        // quickSortRecursiveCall(copyList, 0, copyList.length-1);

    }

    public void realTimeQuickSort(int arr[], int low, int high) {
        if (low < high) {
            /*
             * pi is partitioning index, arr[pi] is
             * now at right place
             */
            int pi = partition(arr, low, high);

            // Recursively sort elements before
            // partition and after partition
            realTimeQuickSort(arr, low, pi - 1);
            realTimeQuickSort(arr, pi + 1, high);
        }
    }

    public void animationQuickSort(int arr[], int low, int high) {

        animationRunTime = getTime();

        if (low < high) {
            /*
             * pi is partitioning index, arr[pi] is
             * now at right place
             */
            int pi = animationPartition(arr, low, high);

            // Recursively sort elements before
            // partition and after partition
            animationQuickSort(arr, low, pi - 1);
            animationQuickSort(arr, pi + 1, high);
        }

    }

    int animationPartition(int arr[], int low, int high) {
        int pivot = arr[high];
        int i = (low - 1); // index of smaller element
        for (int j = low; j < high; j++) {
            // If current element is smaller than or
            // equal to pivot
            if (arr[j] <= pivot) {
                i++;

                // swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                repaint();
            }
        }
        return i + 1;
    }

    int partition(int arr[], int low, int high) {
        int pivot = arr[high];
        int i = (low - 1); // index of smaller element
        for (int j = low; j < high; j++) {
            // If current element is smaller than or
            // equal to pivot
            if (arr[j] <= pivot) {
                i++;

                // swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        return i + 1;
    }

    //

    public void incrementingSort() {
        if (countingTime == 0) {
            int[] copyList = Arrays.copyOf(hueList, hueList.length);
            setOffset();
            realtimeIncrementingSort(copyList);
            realRunTime = getTime();

        }
        if (countingTime < TOTAL_PIXELS) {
            for (int i = 0; i < TOTAL_PIXELS; i++) {
                if (hueList[i] == redness) {
                    int tempint = hueList[countingTime];
                    hueList[countingTime] = hueList[i];
                    hueList[i] = tempint;
                    countingTime++;
                }

            }
            repaint();
        }
        redness++;
    }

    public void realtimeIncrementingSort(int[] copyList) {

        for (int i = 0; i < copyList.length; i++) {
            if (copyList[i] == redness) {
                int tempint = copyList[countingTime];
                copyList[countingTime] = copyList[i];
                copyList[i] = tempint;
                realtimesortcounter++;

            }

        }
    }

    public int index = 0;

    public HueSorter() {
        this.setPreferredSize(new DimensionUIResource(700, 700));
        JButton rightButton = new JButton(">");
        JButton leftButton = new JButton("<");
        this.setLayout(null);
        this.add(leftButton);
        this.add(rightButton);
        rightButton.setBounds(550, 325, 50, 50);
        leftButton.setBounds(100, 325, 50, 50);
        rightButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                changeSortTypeRight();
            }
        });
        leftButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                changeSortTypeLeft();
            }
        });
        timer = new Timer(1, this);
    }

    public void makeRandom() {

        for (int i = 0; i < TOTAL_PIXELS; i++) {
            int a = (int) (Math.random() * 361);
            hueList[i] = a;
        }
    }

    public void draw(Graphics g) {

        int newLine = 0;
        for (int i = 0; i < TOTAL_PIXELS; i++) {
            ColorUIResource hsbColor = new ColorUIResource(
                    ColorUIResource.getHSBColor((float) hueList[i] / 360, (float) 1, (float) 1));
            g.setColor(hsbColor);

            if (i % 100 == 0) {
                newLine++;
            }
            g.drawRect(700 / 2 - 50 + i % 100, newLine, 1, 1);
        }

    }

}
