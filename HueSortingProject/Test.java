package HueSortingProject;

import javax.swing.*;
import java.awt.*;

public class Test extends JPanel {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private int[] data;
    private int currentIndex = -1;
    private int sortedIndex = -1;
    private boolean isSorting = false;

    public Test() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.WHITE);

        generateData();
    }

    private void generateData() {
        data = new int[WIDTH];
        for (int i = 0; i < data.length; i++) {
            data[i] = (int) (Math.random() * HEIGHT);
        }
    }

    public void startSorting() {
        isSorting = true;
        repaint();
        new Thread(() -> {
            for (int i = 0; i < data.length - 1; i++) {
                for (int j = 0; j < data.length - i - 1; j++) {
                    currentIndex = j;
                    if (data[j] > data[j + 1]) {
                        int temp = data[j];
                        data[j] = data[j + 1];
                        data[j + 1] = temp;
                        repaint();
                        //make thread sleep for 10 milliseconds
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < data.length; i++) {
            if (i == currentIndex) {
                g.setColor(Color.RED);
            } else if (i >= sortedIndex) {
                g.setColor(Color.GREEN);
            } else {
                g.setColor(Color.BLACK);
            }
            g.drawLine(i, HEIGHT, i, HEIGHT - data[i]);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Bubble Sort Visualizer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Test visualizer = new Test();
        JButton startButton = new JButton("Start Sorting");
        startButton.addActionListener(e -> {
            if (!visualizer.isSorting) {
                visualizer.generateData();
                visualizer.startSorting();
            }
        });
        frame.add(visualizer, BorderLayout.CENTER);
        frame.add(startButton, BorderLayout.SOUTH);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}