import java.util.Scanner;
public class FourthTask {
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        int first=input.nextInt();
        int second=input.nextInt();
        if(first>second){
            System.out.println("First: "+first);
            System.out.println("Second: "+second);
        }
        else {
            int i = first;
            while (i <= second) {
                System.out.println(i);
                i++;
            }
        }
        input.close();
    }
}