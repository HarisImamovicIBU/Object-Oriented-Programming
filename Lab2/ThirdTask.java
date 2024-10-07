import java.util.Scanner;
public class ThirdTask {
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        while(true){
            System.out.println("Enter your number: ");
            int a=input.nextInt();
            if(a==0){
                System.out.println("Finally a zero.");
                break;
            }
        }
        input.close();
    }
}