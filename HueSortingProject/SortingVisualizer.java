import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.example.AlgorithmList;

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
    int ticks = 0;
    int testy = 0;

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
        String sortingAlgorithms[] = { "Bubble Sort", "Selection Sort", "Insertion Sort", "Merge Sort", "Quick Sort",
                "Heap Sort" };
        comboBox = new JComboBox(sortingAlgorithms);
        comboBox.addActionListener(e -> {
            generateData();
            repaint();
            String selectedNumber = (String) comboBox.getSelectedItem();
            System.out.println("Selected sort: " + selectedNumber);
            algorithmName = selectedNumber;
            switch (selectedNumber) {
    case "Bubble Sort":
        sortStartTime = System.currentTimeMillis();
        int[] dataClone = data.clone();
        AlgorithmList.bubbleSort(dataClone);
        sortEndTime = System.currentTimeMillis();
        sortTime = (int) (sortEndTime - sortStartTime);
        sortTimeLabel.setText("Sort Time: " + Integer.toString(sortTime) + " ms");
        animationStartTime = System.currentTimeMillis();
        bubbleSort(data);
        break;
    case "Selection Sort":
        sortStartTime = System.currentTimeMillis();
        dataClone = data.clone();
        AlgorithmList.selectionSort(dataClone);
        sortEndTime = System.currentTimeMillis();
        sortTime = (int) (sortEndTime - sortStartTime);
        sortTimeLabel.setText("Sort Time: " + Integer.toString(sortTime) + " ms");
        animationStartTime = System.currentTimeMillis();
        selectionSort(data);
        break;
    case "Insertion Sort":
        sortStartTime = System.currentTimeMillis();
        dataClone = data.clone();
        AlgorithmList.insertionSort(dataClone);
        sortEndTime = System.currentTimeMillis();
        sortTime = (int) (sortEndTime - sortStartTime);
        sortTimeLabel.setText("Sort Time: " + Integer.toString(sortTime) + " ms");
        animationStartTime = System.currentTimeMillis();
        insertionSort(data);
        break;
    // case "Merge Sort":
    //     sortStartTime = System.currentTimeMillis();
    //     dataClone = data.clone();
    //     AlgorithmList.mergeSort(dataClone, 0, dataClone.length - 1);
    //     sortEndTime = System.currentTimeMillis();
    //     sortTime = (int) (sortEndTime - sortStartTime);
    //     sortTimeLabel.setText("Sort Time: " + Integer.toString(sortTime) + " ms");
    //     animationStartTime = System.currentTimeMillis();
    //     mergeSort(data, 0, data.length - 1);
    //     break;
    case "Quick Sort":
        sortStartTime = System.currentTimeMillis();
        dataClone = data.clone();
        AlgorithmList.quickSort(dataClone, 0, dataClone.length - 1);
        sortEndTime = System.currentTimeMillis();
        sortTime = (int) (sortEndTime - sortStartTime);
        sortTimeLabel.setText("Sort Time: " + Integer.toString(sortTime) + " ms");
        animationStartTime = System.currentTimeMillis();
        quickSort(data, 0, data.length - 1);
        break;
    // case "Heap Sort":
    //     sortStartTime = System.currentTimeMillis();
    //     dataClone = data.clone();
    //     AlgorithmList.heapSort(dataClone);
    //     sortEndTime = System.currentTimeMillis();
    //     sortTime = (int) (sortEndTime - sortStartTime);
    //     sortTimeLabel.setText("Sort Time: " + Integer.toString(sortTime) + " ms");
    //     animationStartTime = System.currentTimeMillis();
    //     heapSort(data);
    //     break;
    // Add more cases for additional sorting algorithms
    default:
        break;
}
        });
        comboBox.setBounds(0, 60, 200, 50);
        add(comboBox);

        generateData();
    }

    private void generateData() {
        // Generate random data for the visualization
        for (int i = 0; i < NUM_BARS; i++) {
            data[i] = rand.nextInt(MAX_BAR_HEIGHT - MIN_BAR_HEIGHT) + MIN_BAR_HEIGHT;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        

        // Draw the bars for the visualization
        for (int i = 0; i < NUM_BARS; i++) {
            System.out.println(ticks);
            ticks++;
            int x = i * BAR_WIDTH;
            int y = HEIGHT - data[i];
            g.setColor(Color.BLACK);
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
    
    new Thread(() -> {
        for (int i = 1; i < data.length; i++) {
            int key = data[i];
            int j = i - 1;
            while (j >= 0 && data[j] > key) {
                data[j + 1] = data[j];
                j--;
                currentIndex = j + 1;
                sortedIndex = j + 2;
                repaint();
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
    }).start();
}
    private void selectionSort(int[] arr) {
    new Thread(() -> {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
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
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
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
                Thread.sleep(50); // Add a delay to slow down the sorting animation
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
        Thread.sleep(50); // Add a delay to slow down the sorting animation
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    return i + 1;
}
    private void bubbleSort(int[] arr) {
        repaint();
        
        new Thread(() -> {
            for (int i = 0; i < data.length - 1; i++) {
                for (int j = 0; j < data.length - i - 1; j++) {
                    currentIndex = j;
                    if (data[j] > data[j + 1]) {
                        int temp = data[j];
                        data[j] = data[j + 1];
                        data[j + 1] = temp;
                        System.out.println(j);
                        repaint();
                        //make thread sleep for 10 milliseconds
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

    public static void main(String[] args) {
        JFrame frame = new JFrame("Sorting Visualizer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new SortingVisualizer());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}