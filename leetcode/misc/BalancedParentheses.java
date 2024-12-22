package leetcode.misc;

import java.util.Stack;

public class BalancedParentheses {

    /**
     * https://www.geeksforgeeks.org/check-if-given-parentheses-expression-is-balanced-or-not/
     * To check if a given parentheses expression is balanced, we can use a stack. A balanced expression satisfies the following conditions:
     *      Every opening parenthesis (, {, or [ has a corresponding closing parenthesis ), }, or ].
     *      Parentheses must be properly nested, meaning the last opened parenthesis must be closed first.
     * Approach:
     * Use a stack to keep track of opening parentheses.
     * Iterate through the string:
     *      If the current character is an opening parenthesis ((, {, or [), push it onto the stack.
     *      If it is a closing parenthesis (), }, or ]):
     *          Check if the stack is empty (no matching opening parenthesis) — if so, the expression is unbalanced.
     *          Otherwise, pop the top of the stack and check if it matches the closing parenthesis.
     * After processing the string, the stack should be empty for a balanced expression.
     */

    public static boolean isBalanced(String expression) {
        // Stack to store opening parentheses
        Stack<Character> stack = new Stack<>();

        // Iterate through the characters in the expression
        for (char c : expression.toCharArray()) {
            // Push opening parentheses onto the stack
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            }
            // Process closing parentheses
            else if (c == ')' || c == '}' || c == ']') {
                // If stack is empty, no matching opening parenthesis
                if (stack.isEmpty()) {
                    return false;
                }
                // Check if the top of the stack matches the closing parenthesis
                char top = stack.pop();
                if ((c == ')' && top != '(') ||
                        (c == '}' && top != '{') ||
                        (c == ']' && top != '[')) {
                    return false;
                }
            }
        }

        // If the stack is not empty, there are unmatched opening parentheses
        return stack.isEmpty();
    }
}
