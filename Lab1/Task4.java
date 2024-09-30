import java.util.Scanner;
public class Task4 {
    public static void main(String[] args){
        Scanner input=new Scanner(System.in);
        System.out.print("Please enter the first number: ");
        int firstNumber=input.nextInt();
        System.out.print("Please enter the second number: ");
        int secondNumber=input.nextInt();
        System.out.println("The sum of your numbers is: "+(firstNumber+secondNumber));
        //input.close();
    }
}