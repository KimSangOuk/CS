import java.util.Stack;

public class BracketValidator {
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            // 열린 괄호는 스택에 push
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            }
            // 닫힌 괄호일 경우
            else {
                if (stack.isEmpty()) return false; // 짝이 없음
                char top = stack.pop();
                if (!isMatching(top, c)) return false;
            }
        }
        return stack.isEmpty(); // 모두 짝이 맞아야 true
    }

    private static boolean isMatching(char open, char close) {
        return (open == '(' && close == ')') ||
                (open == '[' && close == ']') ||
                (open == '{' && close == '}');
    }
}
