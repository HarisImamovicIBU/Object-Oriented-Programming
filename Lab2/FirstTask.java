
import java.util.Scanner;
public class FirstTask {
    public static void main(String[] args) {
        String password="hareharehare";
        String guess;
        Scanner input=new Scanner(System.in);
        while(true){
        System.out.print("Enter your password: ");
        guess=input.nextLine();
        if(guess.equals(password)){
            System.out.println("Right!!! Secret is: aaaaaaaaaaaaaaaaaa");
            break;
        }
        }
        input.close();
    }
}