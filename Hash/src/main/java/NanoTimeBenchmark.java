import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NanoTimeBenchmark {
    public static void main(String[] args) {
        final int N = 100_000;
        final int WARMUP = 5;
        final int REPEAT = 5;

        List<String> keys = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            keys.add(HashUtils.randomString(16));
        }

        for (int i = 0; i < WARMUP; i++) {
            runMyPutGet(new MyHashMap<>(), keys);
            runJdkPutGet(new HashMap<>(), keys);
        }

        // 3) 측정: MyHashMap
        long myPutTotal = 0, myGetTotal = 0;
        for (int i = 0; i < REPEAT; i++) {
            long[] times = runMyPutGet(new MyHashMap<>(), keys);
            myPutTotal += times[0];
            myGetTotal += times[1];
        }
        System.out.printf("MyHashMap  put avg: %.3f ms, get avg: %.3f ms%n",
                myPutTotal / 1e6 / REPEAT, myGetTotal / 1e6 / REPEAT);

        // 4) 측정: JDK HashMap
        long jdkPutTotal = 0, jdkGetTotal = 0;
        for (int i = 0; i < REPEAT; i++) {
            long[] times = runJdkPutGet(new HashMap<>(), keys);
            jdkPutTotal += times[0];
            jdkGetTotal += times[1];
        }
        System.out.printf("JDK HashMap put avg: %.3f ms, get avg: %.3f ms%n",
                jdkPutTotal / 1e6 / REPEAT, jdkGetTotal / 1e6 / REPEAT);
    }

    private static long[] runMyPutGet(MyHashMap<String,Integer> map, List<String> keys) {
        int N = keys.size();

        long start = System.nanoTime();
        for (int i = 0; i < N; i++) {
            map.put(keys.get(i), i);
        }
        long putTime = System.nanoTime() - start;

        start = System.nanoTime();
        for (int i = 0; i < N; i++) {
            map.get(keys.get(i));
        }
        long getTime = System.nanoTime() - start;

        return new long[]{ putTime, getTime };
    }

    private static long[] runJdkPutGet(Map<String,Integer> map, List<String> keys) {
        int N = keys.size();

        long start = System.nanoTime();
        for (int i = 0; i < N; i++) {
            map.put(keys.get(i), i);
        }
        long putTime = System.nanoTime() - start;

        start = System.nanoTime();
        for (int i = 0; i < N; i++) {
            map.get(keys.get(i));
        }
        long getTime = System.nanoTime() - start;

        return new long[]{ putTime, getTime };
    }
}