import java.util.*;

public class Calculator {

    public static void main(String[] args) {
        System.out.println("Enter an operator: (+, -, *, /)");
        Scanner keyboard = new Scanner(System.in);
        String operator = keyboard.next();

        // check valid input operator
        while (!(operator.equals("+")) && !(operator.equals("-")) && !(operator.equals("*")) && !(operator.equals("/")))
        {
            System.out.println("Error: Invalid operator.");
            System.out.println("Enter an operator: (+, -, *, /)");
            operator = keyboard.next();
        }

        // get data
        System.out.println("Enter first number:");
        double firstNumber = keyboard.nextDouble();

        System.out.println("Enter second number:");
        double secondNumber = keyboard.nextDouble();

        keyboard.close();

        double answer = 0.0;

        // logic
        switch (operator)
        {
            case "+":
            {
                answer = firstNumber + secondNumber;
                break;
            }
            case "-":
            {
                answer = firstNumber - secondNumber;
                break;
            }
            case "*":
            {
                answer = firstNumber * secondNumber;
                break;
            }
            case "/":
            {
                answer = firstNumber/secondNumber;
                break;
            }
            default:
            {
                System.out.println("Error: Invalid operator.");
                break;
            }
        }

        // answer
        System.out.println("Answer:");
        System.out.println(answer);

    }
}