import java.util.Scanner;
public class FifthTask {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your exponent: ");
        int n=input.nextInt();
        int sum=0;
        for(int i=0;i<=n;i++){
            sum+=(int)Math.pow(2,i);
        }
        System.out.println("Your result: "+sum);
        input.close();
    }
}