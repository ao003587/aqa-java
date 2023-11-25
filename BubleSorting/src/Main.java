import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int count = 57;
        float minValue = 130;
        float maxValue = 400;
        var numbers = getRandomNumbers(count, minValue, maxValue);
        System.out.println("**Before sorting**");
        print(numbers);
        sort(numbers);
        System.out.println("**After sorting**");
        print(numbers);
    }

    private static float[] getRandomNumbers(int count, float minValue, float maxValue) {
        if (count <= 0) {
            return new float[0];
        }
        var numbers = new float[count];
        var index = count - 1;
        final Random random = new Random();
        while (index >= 0) {
            numbers[index] = getRandomValue(random, minValue, maxValue);
            index--;
        }
        return numbers;
    }

    private static float getRandomValue(Random random, float minValue, float maxValue) throws IllegalArgumentException {
        if(minValue >= maxValue) {
            throw new IllegalArgumentException("getRandomValue. minValue shouldn't be grater or equal to maxValue");
        }
        return minValue + random.nextFloat(maxValue - minValue);
    }

    private static void sort(float[] numbers) {
        for (int i = 1; i < numbers.length; i++) {
            for (int j = 0; j < i; j++) {
                if (numbers[i] > numbers[j]) {
                    var temp = numbers[i];
                    numbers[i] = numbers[j];
                    numbers[j] = temp;
                }
            }
        }
    }

    private static void print(float[] numbers) {
        if (numbers.length == 0) {
            System.out.print("Array is empty");
            return;
        }
        var idx = 0;
        for (float number : numbers) {
            System.out.printf("[%d]::%f%n", idx++, number);
        }
    }
}