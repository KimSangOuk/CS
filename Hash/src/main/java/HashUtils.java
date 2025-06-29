import java.util.Random;

public class HashUtils {
    private static final int P = 31;                   // 기저
//    private static final int M = 1_000_000_009;        // 모듈러용 큰 소수
    private static final int M = 100;                   // 충돌 심화
    
    
    public static int hash(String s) {
        long h = 0, p = 1;
        for (char c : s.toCharArray()) {
            int val = (c - 'a' + 1);
            h = (h + val * p) % M;
            p = (p * P) % M;
        }
        return (int) h;
    }

    public static String randomString(int length) {
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append((char)('a' + rnd.nextInt(26)));
        }
        return sb.toString();
    }
}