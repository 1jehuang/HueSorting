package com.example;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.lang.Integer;

public class SortingVisualizer extends JPanel {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int BAR_WIDTH = 5;
    private static final int NUM_BARS = WIDTH / BAR_WIDTH;
    private static final int MAX_BAR_HEIGHT = HEIGHT - 50;
    private static final int MIN_BAR_HEIGHT = 10;
    private static final int[] data = new int[NUM_BARS];
    private static final Random rand = new Random();
    private static final int DELAY = 10;
    private Timer timer;
    private JLabel algorithmLabel;
    private String algorithmName = "None";
    private int sortTime;
    private int animationTime;
    private JLabel sortTimeLabel;
    private JLabel animationTimeLabel;
    private long sortStartTime;
    private long sortEndTime;
    private long animationStartTime;
    private long animationEndTime;
    private JComboBox<Integer> comboBox;
    int currentIndex = -1;
    int sortedIndex = -1;
    boolean isSorting = false;
    private boolean stopThreadBubble = false;
    private boolean stopThreadSelection = false;
    private boolean stopThreadInsertion = false;
    private boolean stopThreadMerge = false;
    private boolean stopThreadQuick = false;
    private boolean stopThreadHeap = false;
    private boolean stopThreadRadix = false;

    private boolean stopThreadShell;
    private boolean stopThreadCounting;
    private boolean stopThreadBucket;
    private boolean stopThreadCocktail;
    private boolean stopThreadComb;


    public SortingVisualizer() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.WHITE);

        algorithmLabel = new JLabel("Sorting Algorithm: ");
        sortTimeLabel = new JLabel("Sort Time: ");
        animationTimeLabel = new JLabel("Animation Time: 0 ms");

        setLayout(null);
        algorithmLabel.setBounds(0, 0, 200, 50);
        add(algorithmLabel);
        sortTimeLabel.setBounds(0, 10, 200, 50);
        add(sortTimeLabel);
        animationTimeLabel.setBounds(0, 20, 200, 50);
        add(animationTimeLabel);
        String sortingAlgorithms[] = {
                // "Test Bubble Sort",
                "Bubble Sort", "Selection Sort", "Insertion Sort",
                "Merge Sort", "Quick Sort",
                "Heap Sort", "Radix Sort", "Shell Sort",
                // "Counting Sort", "Bucket Sort",
                "Cocktail Shaker Sort", "Comb Sort" };
        comboBox = new JComboBox(sortingAlgorithms);
        comboBox.addActionListener(e -> {
            generateData();
            repaint();
            String selectedNumber = (String) comboBox.getSelectedItem();
            System.out.println("Selected sort: " + selectedNumber);
            algorithmName = selectedNumber;

            switch (selectedNumber) {
                // case "Test Bubble Sort":
                // var currentsort = new BubbleSort(data);
                // currentsort.sort();
                // break;
                case "Bubble Sort":

                    stopAllSortingThreads();// Stop the sorting thread before starting a new

                    stopThreadBubble = false;
                    sortStartTime = System.currentTimeMillis();
                    AlgorithmList.bubbleSort(generateMoreData());
                    sortEndTime = System.currentTimeMillis();
                    sortTime = (int) (sortEndTime - sortStartTime);
                    sortTimeLabel.setText("Sort Time: " + Integer.toString(sortTime) + " ms");
                    animationStartTime = System.currentTimeMillis();
                    bubbleSort(data);
                    break;
                case "Selection Sort":
                    stopAllSortingThreads();// Stop the sorting thread before starting a new sorting algorithm

                    stopThreadSelection = false;
                    sortStartTime = System.currentTimeMillis();
                    AlgorithmList.selectionSort(generateMoreData());
                    sortEndTime = System.currentTimeMillis();
                    sortTime = (int) (sortEndTime - sortStartTime);
                    sortTimeLabel.setText("Sort Time: " + Integer.toString(sortTime) + " ms");
                    animationStartTime = System.currentTimeMillis();
                    selectionSort(data);
                    break;
                case "Insertion Sort":

                    stopAllSortingThreads();// Stop the sorting thread before starting a new sorting algorithm
                    stopThreadInsertion = false;
                    sortStartTime = System.currentTimeMillis();
                    AlgorithmList.insertionSort(generateMoreData());
                    sortEndTime = System.currentTimeMillis();
                    sortTime = (int) (sortEndTime - sortStartTime);
                    sortTimeLabel.setText("Sort Time: " + Integer.toString(sortTime) + " ms");
                    animationStartTime = System.currentTimeMillis();
                    insertionSort(data);
                    break;
                case "Merge Sort":
                    stopAllSortingThreads();
                    stopThreadMerge = false;
                    sortStartTime = System.currentTimeMillis();
                    AlgorithmList.mergeSort(generateMoreData());
                    sortEndTime = System.currentTimeMillis();
                    sortTime = (int) (sortEndTime - sortStartTime);
                    sortTimeLabel.setText("Sort Time: " + Integer.toString(sortTime) + " ms");
                    animationStartTime = System.currentTimeMillis();
                    mergeSort(data);
                    break;
                case "Quick Sort":
                    stopAllSortingThreads();// Stop the sorting thread before starting a new sorting algorithm
                    stopThreadQuick = false;
                    sortStartTime = System.currentTimeMillis();
                    AlgorithmList.quickSort(generateMoreData());
                    sortEndTime = System.currentTimeMillis();
                    sortTime = (int) (sortEndTime - sortStartTime);
                    sortTimeLabel.setText("Sort Time: " + Integer.toString(sortTime) + " ms");
                    animationStartTime = System.currentTimeMillis();
                    quickSort(data, 0, data.length - 1);
                    break;
                case "Heap Sort":
                    stopAllSortingThreads();
                    stopThreadHeap = false;
                    sortStartTime = System.currentTimeMillis();
                    AlgorithmList.heapSort(generateMoreData());
                    sortEndTime = System.currentTimeMillis();
                    sortTime = (int) (sortEndTime - sortStartTime);
                    sortTimeLabel.setText("Sort Time: " + Integer.toString(sortTime) + " ms");
                    animationStartTime = System.currentTimeMillis();
                    heapSort(data);
                    break;
                case "Radix Sort":
                    stopAllSortingThreads();
                    stopThreadRadix = false;
                    sortStartTime = System.currentTimeMillis();
                    //AlgorithmList.radixSort(generateMoreData());
                    sortEndTime = System.currentTimeMillis();
                    sortTime = (int) (sortEndTime - sortStartTime);
                    sortTimeLabel.setText("Sort Time: " + Integer.toString(sortTime) + " ms");
                    animationStartTime = System.currentTimeMillis();
                    radixSort(data);
                    break;
                case "Shell Sort":
                    stopAllSortingThreads();
                    stopThreadShell = false;
                    sortStartTime = System.currentTimeMillis();
                    AlgorithmList.shellSort(generateMoreData());
                    sortEndTime = System.currentTimeMillis();
                    sortTime = (int) (sortEndTime - sortStartTime);
                    sortTimeLabel.setText("Sort Time: " + Integer.toString(sortTime) + " ms");
                    animationStartTime = System.currentTimeMillis();
                    shellSort(data);
                    break;
                // case "Counting Sort":
                //     stopAllSortingThreads();
                //     stopThreadCounting = false;
                //     sortStartTime = System.currentTimeMillis();
                    
                //     sortEndTime = System.currentTimeMillis();
                //     sortTime = (int) (sortEndTime - sortStartTime);
                //     sortTimeLabel.setText("Sort Time: " + Integer.toString(sortTime) + " ms");
                //     animationStartTime = System.currentTimeMillis();
                //     countingSort(data);
                //     break;
                // case "Bucket Sort":
                //     stopAllSortingThreads();
                //     stopThreadBucket = false;
                //     sortStartTime = System.currentTimeMillis();
                //     dataClone = data.clone();
                //     // AlgorithmList.bucketSort(dataClone);
                //     sortEndTime = System.currentTimeMillis();
                //     sortTime = (int) (sortEndTime - sortStartTime);
                //     sortTimeLabel.setText("Sort Time: " + Integer.toString(sortTime) + " ms");
                //     animationStartTime = System.currentTimeMillis();
                //     bucketSort(data);
                //     break;
                case "Cocktail Shaker Sort":
                    stopAllSortingThreads();
                    stopThreadCocktail = false;
                    sortStartTime = System.currentTimeMillis();
                    AlgorithmList.cocktailShakerSort(generateMoreData());
                    sortEndTime = System.currentTimeMillis();
                    sortTime = (int) (sortEndTime - sortStartTime);
                    sortTimeLabel.setText("Sort Time: " + Integer.toString(sortTime) + " ms");
                    animationStartTime = System.currentTimeMillis();
                    cocktailShakerSort(data);
                    break;
                case "Comb Sort":
                    stopAllSortingThreads();
                    stopThreadComb = false;
                    sortStartTime = System.currentTimeMillis();
                    AlgorithmList.combSort(generateMoreData());
                    sortEndTime = System.currentTimeMillis();
                    sortTime = (int) (sortEndTime - sortStartTime);
                    sortTimeLabel.setText("Sort Time: " + Integer.toString(sortTime) + " ms");
                    animationStartTime = System.currentTimeMillis();
                    combSort(data);
                    break;
                default:
                    break;
            }
        });
        comboBox.setBounds(0, 60, 200, 50);
        add(comboBox);

        generateData();
    }

    private void stopAllSortingThreads() {
        stopThreadBubble = true;
        stopThreadSelection = true;
        stopThreadInsertion = true;
        stopThreadMerge = true;
        stopThreadQuick = true;
        stopThreadHeap = true;
        stopThreadRadix = true;
        stopThreadShell = true;
        stopThreadCounting = true;
        stopThreadBucket = true;
        stopThreadCocktail = true;
        stopThreadComb = true;
    }

    private void generateData() {
        // Generate random data for the visualization
        for (int i = 0; i < NUM_BARS; i++) {
            data[i] = rand.nextInt(MAX_BAR_HEIGHT - MIN_BAR_HEIGHT) + MIN_BAR_HEIGHT;
        }
    }
    public static int[] generateMoreData() {
    int size = 10000;
    int[] arr = new int[size];
    Random rand = new Random();
    for (int i = 0; i < size; i++) {
        arr[i] = rand.nextInt();
    }
    return arr;
}
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the bars for the visualization
        for (int i = 0; i < NUM_BARS; i++) {
            int x = i * BAR_WIDTH;
            int y = HEIGHT - data[i];
            g.setColor(Color.BLACK);
            if (i == currentIndex) {
                g.setColor(Color.RED);
            }
            // else if(i == sortedIndex){
            //     g.setColor(Color.BLUE);}
            else{
                g.setColor(Color.BLACK);
            }
            g.fillRect(x, y, BAR_WIDTH, data[i]);

        }

        algorithmLabel.setText("Sorting Algorithm: " + algorithmName);
        animationEndTime = System.currentTimeMillis();
        animationTime = (int) (animationEndTime - animationStartTime);
        sortTimeLabel.setText("Sort Time: " + Integer.toString(sortTime) + " ms");
        animationTimeLabel.setText("Animation Time: " + Integer.toString(animationTime) + " ms");
    }

    private void insertionSort(int[] arr) {
        repaint();

        Thread thing = new Thread(() -> {
            stopAllSortingThreads();
            stopThreadInsertion = false;
            for (int i = 1; i < data.length; i++) {
                int key = data[i];
                int j = i - 1;
                while (j >= 0 && data[j] > key) {
                    data[j + 1] = data[j];
                    j--;
                    currentIndex = j + 1;
                    sortedIndex = j + 2;
                    repaint();
                    if (stopThreadInsertion) {
                        return;
                    }
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                data[j + 1] = key;
                currentIndex = -1;
                sortedIndex = i;
                repaint();
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            currentIndex = -1;
            sortedIndex = -1;
            isSorting = false;
        });

        thing.start();

    }

    private void selectionSort(int[] arr) {

        new Thread(() -> {

            stopAllSortingThreads();
            stopThreadSelection = false;
            for (int i = 0; i < arr.length - 1; i++) {
                int minIndex = i;
                for (int j = i + 1; j < arr.length; j++) {
                    if (stopThreadSelection) {
                        return;
                    }
                    if (arr[j] < arr[minIndex]) {
                        minIndex = j;
                    }
                }
                int temp = arr[minIndex];
                arr[minIndex] = arr[i];
                arr[i] = temp;
                currentIndex = i;
                sortedIndex = minIndex;
                repaint();
                try {
                    Thread.sleep(50); // Add a delay to slow down the sorting animation
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            currentIndex = -1;
            sortedIndex = -1;
            isSorting = false;
        }).start();
    }

    private void quickSort(int[] arr, int low, int high) {
        new Thread(() -> {

            stopAllSortingThreads();
            stopThreadQuick = false;
            if (low < high) {
                int pivotIndex = partition(arr, low, high);
                if (stopThreadQuick) {
                    return;
                }
                quickSort(arr, low, pivotIndex - 1);
                quickSort(arr, pivotIndex + 1, high);
            }
            currentIndex = -1;
            sortedIndex = -1;
            isSorting = false;
        }).start();
    }

    private int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {

            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                currentIndex = i;
                sortedIndex = j;
                repaint();
                try {
                    Thread.sleep(10); // Add a delay to slow down the sorting animation
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        currentIndex = i + 1;
        sortedIndex = high;
        repaint();
        try {
            Thread.sleep(1); // Add a delay to slow down the sorting animation
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return i + 1;
    }

    private void mergeSort(int[] arr) {
        repaint();

        new Thread(() -> {
            stopAllSortingThreads();
            stopThreadMerge = false;
            mergeSortHelper(arr, 0, arr.length - 1);
            repaint();
        }).start();
    }

    private void mergeSortHelper(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSortHelper(arr, left, mid);
            mergeSortHelper(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            currentIndex = j;
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
            sortedIndex = i - 1;
            repaint();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (stopThreadMerge) {
                return;
            }
        }

        while (i <= mid) {
            temp[k++] = arr[i++];
            sortedIndex = i - 1;
            repaint();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (stopThreadMerge) {
                return;
            }
        }

        while (j <= right) {
            temp[k++] = arr[j++];
            sortedIndex = j - 1;
            repaint();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (stopThreadMerge) {
                return;
            }
        }

        for (i = left; i <= right; i++) {
            arr[i] = temp[i - left];
            currentIndex = i;
            sortedIndex = i;
            repaint();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (stopThreadMerge) {
                return;
            }
        }
    }

    private void heapSort(int[] arr) {
        repaint();

        new Thread(() -> {
            stopAllSortingThreads();
            stopThreadHeap = false;
            int n = arr.length;

            for (int i = n / 2 - 1; i >= 0; i--) {
                heapify(arr, n, i);
            }

            for (int i = n - 1; i >= 0; i--) {
                swap(arr, 0, i);
                currentIndex = i;
                repaint();
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (stopThreadHeap) {
                    return;
                }
                heapify(arr, i, 0);
            }

            currentIndex = -1;
            sortedIndex = -1;
            repaint();
        }).start();
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private void heapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }

        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }

        if (largest != i) {
            swap(arr, i, largest);
            currentIndex = largest;
            repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (stopThreadHeap) {
                return;
            }
            heapify(arr, n, largest);
        }
    }

    public void radixSort(int[] arr) {
        new Thread(() -> {
            stopAllSortingThreads();
            stopThreadRadix = false;
            int max = getMax(arr);
            for (int exp = 1; max / exp > 0; exp *= 10) {
                countSort(arr, exp);
                repaint();
            }
            repaint();
        }).start();

    }

    private int getMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    private void countSort(int[] arr, int exp) {
        int[] output = new int[arr.length];
        int[] count = new int[10];

        for (int i = 0; i < arr.length; i++) {
            count[(arr[i] / exp) % 10]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = output[i];
            currentIndex = i;
            repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (stopThreadRadix) {
                return;
            }
        }
    }

    private void shellSort(int[] arr) {
        repaint();

        new Thread(() -> {

            int n = arr.length;
            for (int gap = n / 2; gap > 0; gap /= 2) {
                for (int i = gap; i < n; i++) {
                    currentIndex = i;
                    int temp = arr[i];
                    int j;
                    for (j = i; j >= gap && arr[j - gap] > temp; j -= gap) {
                        arr[j] = arr[j - gap];
                        if (stopThreadShell) {
                            return;
                        }
                        repaint();
                        try {
                            Thread.sleep(5);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    arr[j] = temp;
                    if (stopThreadShell) {
                        return;
                    }
                    repaint();
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
            repaint();
        }).start();
    }

    private void bucketSort(int[] arr) {
        repaint();

        new Thread(() -> {

            int n = arr.length;
            int maxVal = getMax(arr);

            ArrayList<Integer>[] bucket = new ArrayList[maxVal + 1];
            for (int i = 0; i < bucket.length; i++) {
                bucket[i] = new ArrayList<>();
            }
            for (int i = 0; i < bucket.length; i++) {
                bucket[i] = new ArrayList<>();
            }

            for (int i = 0; i < n; i++) {
                int bucketIndex = (int) ((arr[i] / (float) maxVal) * maxVal);
                bucket[bucketIndex].add(arr[i]);
                currentIndex = i;
                repaint();
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            int index = 0;
            for (int i = 0; i < bucket.length; i++) {
                Collections.sort(bucket[i]);
                for (int j = 0; j < bucket[i].size(); j++) {
                    arr[index++] = bucket[i].get(j);
                    currentIndex = index;
                    repaint();
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            repaint();
        }).start();
    }

    private void countingSort(int[] arr) {
        repaint();

        new Thread(() -> {

            int n = arr.length;
            int maxVal = getMax(arr);

            int[] count = new int[maxVal + 1];
            for (int i = 0; i < n; i++) {
                count[arr[i]]++;
                currentIndex = i;
                repaint();
                sleep();
            }

            int index = 0;
            for (int i = 0; i < count.length; i++) {
                for (int j = 0; j < count[i]; j++) {
                    arr[index++] = i;
                    currentIndex = index;
                    repaint();
                    sleep();
                }
            }
            repaint();
        }).start();
    }

    private void cocktailShakerSort(int[] arr) {
        repaint();

        new Thread(() -> {

            int n = arr.length;
            int left = 0;
            int right = n - 1;

            while (left < right) {
                for (int i = left; i < right; i++) {
                    currentIndex = i;
                    if (arr[i] > arr[i + 1]) {
                        swap(arr, i, i + 1);
                    }
                    if (stopThreadCocktail) {
                        return;
                    }
                    repaint();
                    sleep();
                }
                right--;

                for (int i = right; i > left; i--) {
                    currentIndex = i;
                    if (arr[i] < arr[i - 1]) {
                        swap(arr, i, i - 1);
                    }
                    if (stopThreadCocktail) {
                        return;
                    }
                    repaint();
                    sleep();
                }
                left++;
            }
            repaint();
        }).start();
    }

    private void combSort(int[] arr) {
        repaint();

        new Thread(() -> {

            int n = arr.length;
            int gap = n;
            boolean swapped = true;

            while (gap > 1 || swapped) {
                gap = getNextGap(gap);
                swapped = false;

                for (int i = 0; i < n - gap; i++) {
                    currentIndex = i;
                    int j = i + gap;
                    if (arr[i] > arr[j]) {
                        swap(arr, i, j);
                        swapped = true;
                    }
                    if (stopThreadComb) {
                        return;
                    }
                    repaint();
                    sleep();
                }
            }
            repaint();
        }).start();
    }

    private int getNextGap(int gap) {
        gap = (gap * 10) / 13;
        if (gap < 1) {
            return 1;
        }
        return gap;
    }

    private void bubbleSort(int[] arr) {
        repaint();

        new Thread(() -> {

            stopAllSortingThreads();
            stopThreadBubble = false;
            for (int i = 0; i < data.length - 1; i++) {
                for (int j = 0; j < data.length - i - 1; j++) {
                    currentIndex = j;
                    if (data[j] > data[j + 1]) {
                        int temp = data[j];
                        data[j] = data[j + 1];
                        data[j + 1] = temp;
                        repaint();
                        if (stopThreadBubble) {
                            return;
                        }
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
                sortedIndex = data.length - i - 1;
            }
            currentIndex = -1;
            sortedIndex = -1;
            isSorting = false;
        }).start();
    }

    public void sleep() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Sorting Visualizer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new SortingVisualizer());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}