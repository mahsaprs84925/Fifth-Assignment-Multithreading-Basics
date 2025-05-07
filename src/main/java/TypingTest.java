import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TypingTest {

    private static String lastInput = "";
    private static Scanner scanner = new Scanner(System.in);

    private static int correctCount = 0;
    private static int incorrectCount = 0;
    private static List<Long> duration = new ArrayList<>();
    private static boolean pause = false;

    public static class InputRunnable implements Runnable {

        //TODO: Implement a thread to get user input without blocking the main thread
        @Override
        public void run() {
            while (!pause)
            {
                lastInput = scanner.nextLine();
            }
        }
    }


    public static void testWord(String wordToTest) {
        try {
            System.out.println(wordToTest);
            lastInput = "";
            // TODO


            long startTime = System.currentTimeMillis();
            long timeout = wordToTest.length() * 1000;

            while(System.currentTimeMillis() - startTime < timeout)
            {
                if(!lastInput.isEmpty()) break;
                Thread.sleep(10);
            }
            long elapsedTime = System.currentTimeMillis() - startTime;
            duration.add(elapsedTime);

            System.out.println();
            System.out.println("You typed: " + lastInput);
            if (lastInput.equals(wordToTest)) {
                System.out.println("Correct");
                correctCount++;
            } else {
                System.out.println("Incorrect");
                incorrectCount++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void typingTest(List<String> inputList) throws InterruptedException {

        for (int i = 0; i < inputList.size(); i++) {
            String wordToTest = inputList.get(i);
            testWord(wordToTest);
            Thread.sleep(2000); // Pause briefly before showing the next word
        }

        // TODO: Display a summary of test results
        long totalTime = duration.stream().mapToLong(Long::longValue).sum();
        double averageTime = duration.isEmpty() ? 0 : totalTime / (double) duration.size();
        System.out.println("\n--- Summary ---");
        System.out.println("Correct: " + correctCount);
        System.out.println("Incorrect: " + incorrectCount);
        System.out.printf("Total Time: %.2f seconds\n", totalTime / 1000.0);
        System.out.printf("Average Time per Word: %.2f seconds\n", averageTime / 1000.0);
    }

    public static void main(String[] args) throws InterruptedException {
        List<String> words = new ArrayList<>();
        try (InputStream inputStream = TypingTest.class.getClassLoader().getResourceAsStream("Words.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream)))
        {
            words = reader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("reading error : " + e.getMessage());
            return;
        }
        catch (Exception e)
        {
            System.out.println("error : " + e.getMessage());
            return;
        }
        // TODO: Replace the hardcoded word list with words read from the given file in the resources folder (Words.txt)
        Thread inputThread = new Thread(new InputRunnable());
        inputThread.start();
        typingTest(words);
        pause = true;

        System.out.println("Press enter to exit.");
        scanner.nextLine();
    }
}