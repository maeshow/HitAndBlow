import java.util.Random;
import java.util.Scanner;

public class App {
    private static final Scanner STDIN = new Scanner(System.in);
    private static final Random RANDOM = new Random();

    private static final int DIGITS = 4;
    private static final int MIN_NUMBER = (int) Math.pow(10, DIGITS - 1);
    private static final int MAX_NUMBER = (int) Math.pow(10, DIGITS);
    private static final int BOUND = 10;
    private static final int[] INPUT = new int[DIGITS];
    private static final int[] ANSWER = new int[DIGITS];
    private static final int[] RESULT = new int[2];
    private static final int HIT_INDEX = 0;
    private static final int BLOW_INDEX = 1;
    private static final int LAST_INPUT_INDEX = DIGITS - 1;
    private static final int DUMMY_ANSWER = -1;

    public static void main(String[] args) {
        startGame();
        endGame();
    }

    private static void startGame() {
        createNumberbyRandom();
        startPlayerRespones();
    }

    private static void endGame() {
        STDIN.close();
    }

    private static void createNumberbyRandom() {
        for (int i = 0; i < ANSWER.length; i++) {
            initAnswer(i);
            int randomNumber = 0;
            do {
                randomNumber = RANDOM.nextInt(BOUND);
            } while (isDuplicate(randomNumber));
            ANSWER[i] = randomNumber;
        }
    }

    private static void initAnswer(int count) {
        if (0 < count) {
            ANSWER[count] = DUMMY_ANSWER;
        }
    }

    private static boolean isDuplicate(int randomNumber) {
        for (int i = 0; i < ANSWER.length; i++) {
            if (ANSWER[i] == randomNumber) {
                return true;
            }
        }
        return false;
    }

    private static void startPlayerRespones() {
        int count = 1;
        while (!isCorrectAnswer()) {
            setPlayerInput(getPlayerInput());
            setHitAndBlowCount();
            showResult(count);
            count++;
        }
    }

    private static boolean isCorrectAnswer() {
        return RESULT[HIT_INDEX] == DIGITS;
    }

    private static void setHitAndBlowCount() {
        int hitCount = 0;
        int blowCount = 0;
        for (int i = 0; i < DIGITS; i++) {
            if (isHit(i)) {
                hitCount++;
                continue;
            }
            if (isBlow(i)) {
                blowCount++;
            }
        }
        RESULT[HIT_INDEX] = hitCount;
        RESULT[BLOW_INDEX] = blowCount;
    }

    private static boolean isBlow(int index) {
        for (int answer : ANSWER) {
            if (INPUT[index] == answer) {
                return true;
            }
        }
        return false;
    }

    private static boolean isHit(int index) {
        return INPUT[index] == ANSWER[index];
    }

    private static int getPlayerInput() {
        showFormattedMessage(Messages.WAITING_INPUT, DIGITS);
        String input = STDIN.next();
        int inputNumber = 0;
        try {
            inputNumber = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            showWithNewLine(Messages.ENTER_NUMBER_WARN);
            showNewLine();
            return getPlayerInput();
        }

        if (isCorrectRange(inputNumber)) {
            return inputNumber;
        }
        showWithNewLine(Messages.INVALID_NUMBER_WARN);
        showNewLine();
        return getPlayerInput();
    }

    private static void setPlayerInput(int input) {
        for (int i = 0; i < DIGITS; i++) {
            INPUT[LAST_INPUT_INDEX - i] = input % 10;
            input /= 10;
        }
    }

    private static boolean isCorrectRange(int inputNumber) {
        return MIN_NUMBER <= inputNumber && inputNumber < MAX_NUMBER;
    }

    private static void showResult(int count) {
        if (isCorrectAnswer()) {
            showFormattedMessage(Messages.CORRECT, count);
        } else {
            showFormattedMessage(Messages.HINT, RESULT[HIT_INDEX], RESULT[BLOW_INDEX]);
        }
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
