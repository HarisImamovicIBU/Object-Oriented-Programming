import java.util.Scanner;
public class SixthTask {
    public static void main(String[] args){
        Scanner input=new Scanner(System.in);
        int n=input.nextInt();
        for(int i=0;i<n;i++){
            PrintText();
        }
        input.close();
    }
    public static void PrintText(){
        System.out.print("In the beggining there were the swamp, the hoe and Java.\n");
    }
}