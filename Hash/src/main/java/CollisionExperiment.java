import java.util.HashSet;
import java.util.Set;

/**
 * N개의 랜덤 문자열을 해싱해 충돌(collision) 횟수를 출력
 */
public class CollisionExperiment {
    public static void main(String[] args) {
        int N = 1000;
        Set<Integer> seen = new HashSet<>();
        int collisions = 0;

        for (int i = 0; i < N; i++) {
            String s = HashUtils.randomString(8);
            int h = HashUtils.hash(s);
            if (!seen.add(h)) {
                collisions++;
            }
        }

        System.out.println("총 문자열 수: " + N);
        System.out.println("충돌 횟수: " + collisions);
    }
}