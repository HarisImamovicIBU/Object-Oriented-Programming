import java.util.Scanner;
public class TenthTask {
    public static void main(String[] args){
        int numberToGuess=15, counter=0;
        Scanner input=new Scanner(System.in);
        while(true){
            System.out.print("Guess the number: ");
            int guess=input.nextInt();
            counter++;
            if(guess>100 || guess<0){
                System.out.print("Invalid value. The game ends.");
                break;
            }
            else{
                if(guess>numberToGuess){
                    System.out.println("Lower! - This is your "+counter+". guess !");
                }
                else if(numberToGuess>guess){
                    System.out.println("Higher! - This is your "+counter+". guess !");
                }
                else{
                    System.out.print("You are correct!\nYou made "+counter+" guesses.");
                    break;
                }
            }
        }
        input.close();
    }
}