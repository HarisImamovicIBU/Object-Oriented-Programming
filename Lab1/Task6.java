import java.util.Scanner;
public class Task6 {
    public static void main(String[] args){
        Scanner input=new Scanner(System.in);
        System.out.print("Please enter the first number: ");
        int firstNumber=input.nextInt();
        System.out.print("Please enter the second number: ");
        int secondNumber=input.nextInt();
        if(firstNumber>secondNumber){
            System.out.print(firstNumber+" is greater");
        }
        else if(secondNumber>firstNumber){
            System.out.print(secondNumber+" is greater");
        }
        else{
            System.out.print(firstNumber+" is the same as "+secondNumber);
        }
        //input.close();
    }
}