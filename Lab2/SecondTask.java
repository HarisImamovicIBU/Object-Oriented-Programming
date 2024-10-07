import java.util.Scanner;
public class SecondTask {
    public static void main(String[] args){
        Scanner input=new Scanner(System.in);
        System.out.print("Enter first number: ");
        int a=input.nextInt();
        System.out.print("Enter second number: ");
        int b=input.nextInt();
        System.out.print("Enter third number: ");
        int c=input.nextInt();
        System.out.print("Sum of your numbers is: "+(a+b+c));
        input.close();
    }
}