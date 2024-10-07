import java.util.Scanner;
public class SeventhTask {
    public static void drawStarsPiramid(int n){
        for(int i=0;i<n;i++){
            for(int j=0;j<=i;j++){
                System.out.print("*");
            }
            System.out.println();
        }
    }
    public static void main(String[] args){
        Scanner input=new Scanner(System.in);
        int numberOfRows=input.nextInt();
        drawStarsPiramid(numberOfRows);
        input.close();
    }
}