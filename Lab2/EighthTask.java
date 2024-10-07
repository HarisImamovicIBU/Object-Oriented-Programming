import java.util.Scanner;
public class EighthTask {
    public static void drawStarsPiramid2(int n){
        for(int i=n;i>0;i--){
            for(int j=0;j<i;j++){
                System.out.print("*");
            }
            System.out.println();
        }
    }
    public static void main(String[] args){
        Scanner input=new Scanner(System.in);
        int numberOfRows=input.nextInt();
        drawStarsPiramid2(numberOfRows);
        input.close();
    }
}