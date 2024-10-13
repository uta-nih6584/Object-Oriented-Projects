import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Boggle {
    private static List<Board> boards = new ArrayList<>();
    private static List<String> words = new ArrayList<>();
    private static Set<Solution> solutions = new TreeSet<>();
    private static int numberOfBoards = 1;
    private static int boardSize = 50;
    private static int numThreads = 1;
    private static String filename = "words.txt";
    private static int verbosity = 0;
    private static AtomicInteger currentBoardIndex = new AtomicInteger(0); // Shared counter

    public Boggle() {}

    public static void main(String[] var0) {
        try {
            if (var0.length > 0 && var0[0].equals("-h")) {
                System.err.println("usage: java Boggle [#boards] [boardSize] [#threads] [wordsFilename] [verboseLevel(0-3)]\n       defaults:       1         50           1       words.txt            0\n       You may skip any parameters with '-'. To use 4 threads, type: java Boggle - - 4\n       verbosity 0 = # solutions, 1 = threads, 2 = boards & solutions, 3 = details");
                System.exit(0);
            }

            // Parse command line arguments
            parseCommandLineArguments(var0);

            // Generate Boggle boards
            generateBoards();

            // Load words from file
            loadWords();

            // Create thread pool and solve boards
            ExecutorService threadPool = Executors.newFixedThreadPool(numThreads);
            for (int i = 0; i < numThreads; i++) {
                threadPool.execute(() -> solveBoards());
            }

            // Shutdown the thread pool and wait for tasks to finish
            threadPool.shutdown();

            // Output results
            outputResults();

        } catch (Exception e) {
            System.err.println("Unexpected exception (panic): Contact support");
            e.printStackTrace();
            System.exit(-99);
        }
    }

    private static void parseCommandLineArguments(String[] args) {
        try {
            if (args.length > 0 && !args[0].equals("-")) {
                numberOfBoards = Integer.parseInt(args[0]);
            }

            if (args.length > 1 && !args[1].equals("-")) {
                boardSize = Integer.parseInt(args[1]);
            }

            if (args.length > 2 && !args[2].equals("-")) {
                numThreads = Integer.parseInt(args[2]);
            }

            if (args.length > 3 && !args[3].equals("-")) {
                filename = args[3];
            }

            if (args.length > 4 && !args[4].equals("-")) {
                verbosity = Integer.parseInt(args[4]);
            }
        } catch (Exception e) {
            System.err.println("Invalid command line arguments: " + e);
            System.exit(-2);
        }
    }

    private static void generateBoards() {
        for (int i = 0; i < numberOfBoards; i++) {
            boards.add(new Board(boardSize));
            log("\nBoard " + i + "\n\n" + boards.get(i) + "\n\n", 2);
        }
    }

    private static void loadWords() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                words.add(line);
            }
        } catch (IOException e) {
            System.err.println("Unable to read words from file " + filename + ": " + e);
            System.exit(-1);
        }
    }

    private static void solveBoards() {
        while (true) {
            int boardIndex = currentBoardIndex.getAndIncrement(); // Get next board index
            if (boardIndex >= boards.size()) {
                break; // Exit when no more boards are available
            }
            Board board = boards.get(boardIndex);
            Solver solver = new Solver(board, boardIndex, verbosity);

            for (String word : words) {
                Solution solution = solver.solve(word);
                if (solution != null) {
                    solutions.add(solution);
                }
            }
        }
    }

    private static void outputResults() {
        for (Solution solution : solutions) {
            log(solution.toString(), 2);
        }
        System.out.println("\nFound " + solutions.size() + " solutions");
        System.out.printf("Hash is 0x%08X\n", Objects.hash(new Object[]{solutions}));
    }

    private static void log(String message, int level) {
        if (verbosity == level) {
            System.out.println(message);
        }
    }
}
