package oy.tol.tra;


public class ParenthesisChecker {

    private ParenthesisChecker() {
    }

    public static int checkParentheses(StackInterface<Character> stack, String fromString) throws ParenthesesException {
        int n = 0;

        for (int index = 0; index < fromString.length(); index++){
            char character = fromString.charAt(index);

            if (character == '(' || character == '[' || character == '{'){
                n = n + 1;
                stack.push(character);
            }
            else if(character == ')' || character == ']' || character == '}'){

                if(stack.isEmpty()){
                    throw new ParenthesesException("Too many closing parentheses ", ParenthesesException.TOO_MANY_CLOSING_PARENTHESES);
                }
                else if (character == ')' && stack.pop() != '(' ||
                        character == ']' && stack.pop() != '[' ||
                        character == '}' && stack.pop() != '{'){
                    throw new ParenthesesException("Wrong kind of parenthesis in the text", ParenthesesException.PARENTHESES_IN_WRONG_ORDER);
                }
                else n = n + 1;
            }
        }
        if(stack.isEmpty() == false){
            throw new ParenthesesException("Too few closing parentheses", ParenthesesException.TOO_FEW_CLOSING_PARENTHESES);
        }

        return n;
    }
}
