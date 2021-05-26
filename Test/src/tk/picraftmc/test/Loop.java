package tk.picraftmc.test;

public class Loop {
    public static void main(String[] args) {
        long i = 100_000_000_000L;
        long time = System.currentTimeMillis();
        long nano = System.nanoTime();

        // sumWhile(i);
        System.out.println("While: " + (System.currentTimeMillis() - time) + "ms" + " or " + (System.nanoTime() - nano) + "ns");
        time = System.currentTimeMillis();
        nano = System.nanoTime();

        // sumFor(i);
        System.out.println("For: " + (System.currentTimeMillis() - time) + "ms" + " or " + (System.nanoTime() - nano) + "ns");
        time = System.currentTimeMillis();
        nano = System.nanoTime();

        sumMath(i);
        System.out.println("Math: " + (System.currentTimeMillis() - time) + "ms" + " or " + (System.nanoTime() - nano) + "ns");

    }

    private static void sumWhile(long x) {
        int i = 0;
        long sum = 0;
        while (i < x) {
            sum += i;
            i++;
        }
        System.out.println("Sum While: " + sum);
    }

    private static void sumWhile(int x) {
        sumWhile((long) x);
    }

    private static void sumFor(long x) {
        long sum = 0;
        for (int i = 0; i < x; i++) {
            sum += i;
        }
        System.out.println("Sum For: " + sum);
    }

    private static void sumFor(int x) {
        sumFor((long) x);
    }

    private static void sumMath(long x) {
        long sum = x * (x - 1) >> 1;
        System.out.println("Sum Math: " + sum);
    }

    private static void sumMath(int x) {
        sumMath((long) x);
    }
}
