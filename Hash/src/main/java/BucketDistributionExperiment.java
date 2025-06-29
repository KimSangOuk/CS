public class BucketDistributionExperiment {
    public static void main(String[] args) {
        int N = 10000;
        int B = 50;
        int[] buckets = new int[B];

        for (int i = 0; i < N; i++) {
            String s = HashUtils.randomString(8);
            int idx = HashUtils.hash(s) % B;
            buckets[idx]++;
        }

        System.out.println("버킷 분포:");
        for (int i = 0; i < B; i++) {
            System.out.printf("Bucket %2d: %d%n", i, buckets[i]);
        }
    }
}