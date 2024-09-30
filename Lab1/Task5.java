import java.util.Scanner;
public class Task5 {
    public static void main(String[] args){
    Scanner input=new Scanner(System.in);
    System.out.print("Please enter the your number: ");
    int number=input.nextInt();
    //We won't handle all edge cases
    if(number>0){
        System.out.println("Great! "+number+" is greater than 0.");
    }
    else{
        System.out.println("Oops! "+number+" is not greater than 0.");
    }
    //input.close();
    }
}