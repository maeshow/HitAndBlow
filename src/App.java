import java.util.Random;
import java.util.Scanner;

public class App {
    private static final Scanner STDIN = new Scanner(System.in);
    private static final Random RANDOM = new Random();

    private static final int DIGITS = 5;
    private static final int RANDOM_RANGE = 10;
    private static final int RESULT_PATTERN = 2;
    private static final int MIN_NUMBER = (int) Math.pow(10, DIGITS - 1);
    private static final int MAX_NUMBER = (int) Math.pow(10, DIGITS);
    private static final int HIT_INDEX = 0;
    private static final int BLOW_INDEX = 1;
    private static final int DUMMY_ANSWER = -1;
    private static final int RESPONES_COUNT_INIT = 1;
    private static final int HINT_FREQUENCY = 3;

    public static void main(String[] args) {
        startGame();
        endGame();
    }

    private static void startGame() {
        startPlayerRespones(createNumberbyRandom(DIGITS, RANDOM_RANGE));
    }

    private static void endGame() {
        STDIN.close();
    }

    private static int[] createNumberbyRandom(int digits, int randomRange) {
        int[] answers = new int[digits];
        for (int i = 0; i < answers.length; i++) {
            initAnswer(i, answers);
            int randomNumber = 0;
            while (isDuplicate(answers, randomNumber)) {
                randomNumber = RANDOM.nextInt(randomRange);
            }
            answers[i] = randomNumber;
        }
        return answers;
    }

    private static void initAnswer(int count, int[] answers) {
        if (0 < count) {
            answers[count] = DUMMY_ANSWER;
        }
    }

    private static boolean isDuplicate(int[] answers, int randomNumber) {
        for (int i = 0; i < answers.length; i++) {
            if (answers[i] == randomNumber) {
                return true;
            }
        }
        return false;
    }

    private static void startPlayerRespones(int[] answers) {
        int count = RESPONES_COUNT_INIT;
        int[] result = new int[RESULT_PATTERN];
        while (!isCorrectAnswer(result)) {
            int[] input = getPlayerInput(getPlayerStdin());
            result = getHitAndBlowCount(input, answers, result);
            showResult(result, count);
            if (isDivisible(count, HINT_FREQUENCY)) {
                showHint(answers, getHintIndex(count));
            }
            count++;
        }
    }

    private static boolean isDivisible(int a, int b) {
        return a % b == 0;
    }

    private static int getHintIndex(int count) {
        return (count / HINT_FREQUENCY) - 1;
    }

    private static boolean isCorrectAnswer(int[] result) {
        return result[HIT_INDEX] == DIGITS;
    }

    private static int[] getHitAndBlowCount(int[] input, int[] answers, int[] result) {
        int hitCount = 0;
        int blowCount = 0;
        for (int i = 0; i < DIGITS; i++) {
            if (isHit(input, answers, i)) {
                hitCount++;
                continue;
            }
            if (isBlow(input, answers, i)) {
                blowCount++;
            }
        }
        result[HIT_INDEX] = hitCount;
        result[BLOW_INDEX] = blowCount;

        return result;
    }

    private static boolean isBlow(int[] input, int[] answers, int index) {
        for (int answer : answers) {
            if (input[index] == answer) {
                return true;
            }
        }
        return false;
    }

    private static boolean isHit(int[] input, int[] answers, int index) {
        return input[index] == answers[index];
    }

    private static int getPlayerStdin() {
        showFormattedMessage(Messages.WAITING_INPUT, DIGITS);
        String input = STDIN.next();
        int inputNumber = 0;
        try {
            inputNumber = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            showWithNewLine(Messages.ENTER_NUMBER_WARN);
            showNewLine();
            return getPlayerStdin();
        }

        if (isCorrectRange(inputNumber)) {
            return inputNumber;
        }
        showWithNewLine(Messages.INVALID_NUMBER_WARN);
        showNewLine();
        return getPlayerStdin();
    }

    private static int[] getPlayerInput(int stdin) {
        int[] input = new int[DIGITS];
        for (int i = 0; i < DIGITS; i++) {
            input[(input.length - 1) - i] = stdin % 10;
            stdin /= 10;
        }
        return input;
    }

    private static boolean isCorrectRange(int inputNumber) {
        return MIN_NUMBER <= inputNumber && inputNumber < MAX_NUMBER;
    }

    private static void showResult(int[] result, int count) {
        if (isCorrectAnswer(result)) {
            showFormattedMessage(Messages.CORRECT, count);
            return;
        }
        showFormattedMessage(Messages.RESULT, result[HIT_INDEX], result[BLOW_INDEX]);
    }

    private static void showHint(int[] answers, int index) {
        showFormattedMessage(Messages.HINT, answers[index]);
    }

    private static void showWithNewLine(String message) {
        System.out.println(message);
    }

    private static void showNewLine() {
        System.out.println();
    }

    private static void showFormattedMessage(String message, int a) {
        System.out.format(message, a);
    }

    private static void showFormattedMessage(String message, int a, int b) {
        System.out.format(message, a, b);
    }
}
