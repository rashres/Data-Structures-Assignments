// Rahul Shrestha
// Project 1 – Data Structures (Recursion)

import java.util.*;

public class Project1 {

    private static char currentLabel = 'a';
    private static final int[][] directions = {
        {-1, 0}, // north
        {1, 0},  // south
        {0, -1}, // west
        {0, 1}   // east
    };

    private static int numRows, numCols;
    private static Map<Integer, Integer> componentSizes = new TreeMap<>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Read number of images
        int numImages = Integer.parseInt(scanner.nextLine().trim());

        ArrayList<char[][]> allImages = new ArrayList<>();

        for (int img = 0; img < numImages; img++) {

            // Read dimensions, skipping blank lines safely
            String line;
            do {
                line = scanner.nextLine().trim();
            } while (line.isEmpty());

            String[] dims = line.split("\\s+");
            numRows = Integer.parseInt(dims[0]);
            numCols = Integer.parseInt(dims[1]);

            char[][] image = new char[numRows][numCols];

            // Read image rows
            for (int r = 0; r < numRows; r++) {
                line = scanner.nextLine();
                for (int c = 0; c < numCols; c++) {
                    image[r][c] = line.charAt(c);
                }
            }

            allImages.add(image);
        }

        scanner.close();

        System.out.println();

        // Process each image
        for (char[][] image : allImages) {

            numRows = image.length;
            numCols = image[0].length;

            labelConnectedAreas(image);
            printResults(image);

            System.out.println();

            currentLabel = 'a';
            componentSizes.clear();
        }
    }

    // Label all connected components
    private static void labelConnectedAreas(char[][] image) {
        for (int r = 0; r < numRows; r++) {
            for (int c = 0; c < numCols; c++) {
                if (image[r][c] == '*') {
                    int size = exploreConnectedArea(image, r, c);
                    componentSizes.put(size,
                        componentSizes.getOrDefault(size, 0) + 1);
                    currentLabel++;
                }
            }
        }
    }

    // Recursive flood fill
    private static int exploreConnectedArea(char[][] image, int r, int c) {

        image[r][c] = currentLabel;
        int size = 1;

        for (int[] d : directions) {
            int nr = r + d[0];
            int nc = c + d[1];

            if (isValid(nr, nc) && image[nr][nc] == '*') {
                size += exploreConnectedArea(image, nr, nc);
            }
        }

        return size;
    }

    private static boolean isValid(int r, int c) {
        return r >= 0 && r < numRows && c >= 0 && c < numCols;
    }

    // Print image and component table
    private static void printResults(char[][] image) {

        for (int r = 0; r < numRows; r++) {
            for (int c = 0; c < numCols; c++) {
                System.out.print(image[r][c]);
            }
            System.out.println();
        }

        for (Map.Entry<Integer, Integer> entry : componentSizes.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
