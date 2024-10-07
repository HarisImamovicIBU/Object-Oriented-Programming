import java.util.Scanner;
public class NinethTask {
    public static void drawNumberPiramid(int n){
        for(int i=1;i<=n;i++){
            for(int j=1;j<=i;j++){
                System.out.print(j);
            }
            System.out.println();
        }
    }
    public static void main(String[] args){
        Scanner input=new Scanner(System.in);
        int numberOfRows=input.nextInt();
        drawNumberPiramid(numberOfRows);
        input.close();
    }
}