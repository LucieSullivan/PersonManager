
/**
 * @author Lucie Sullivan 111718874 Recitation Tuesday at 10AM
 */
import java.util.Scanner;

public class PersonManager {
    /*
     The main method uses a Scanner to take in user input. It uses a while loop to keep the program 
     running until the user chooses to exit. Finally, it uses if/else if statements to use user input 
     to make decisions.
     The purpose of the try/catch statement is to catch if the user inputs any inapropriate data.
     */

    public static void main(String[] args) throws Exception {

        PersonDataManager jim = new PersonDataManager();

        System.out.println("Starting...");
        String choice = "";
        Scanner input = new Scanner(System.in);
        while (!(choice.toLowerCase().equals("q"))) {
            try{
                System.out.println("Menu: \n(I) Import from file \n(A) Add Person \n(R) Remove Person"
                    + "\n(G) Get Information On Person \n(P) Print Table \n(S) Save to File"
                    + "\n(Q) Quit");
            System.out.print("Please select an option: ");
            choice = (input.next());
            
                if (choice.equalsIgnoreCase("i")) {
                    System.out.print("Please enter a location: ");
                    String location = input.next();
                    jim.buildFromFile(location);
                } else if (choice.equalsIgnoreCase("a")) {
                    System.out.print("Please enter the name of the person: ");
                    String n = input.next();
                    System.out.print("Please enter the age: ");
                    int a = input.nextInt();
                    System.out.print("Please enter the gender (M or F): ");
                    String g = input.next().toUpperCase();
                    System.out.print("Please enter the height (in inches): ");
                    double h = input.nextDouble();
                    System.out.print("Please enter the weight (in lbs): ");
                    double w = input.nextDouble();
                    jim.addPerson(new Person(n, g, a, h, w));
                } else if (choice.equalsIgnoreCase("r")) {
                    System.out.print("Please enter the name of the person: ");
                    String person = input.next();
                    jim.removePerson(person.toUpperCase());
                } else if (choice.equalsIgnoreCase("g")) {
                    System.out.print("Please enter the name of the person: ");
                    String person = input.next();
                    jim.getPerson(person.toUpperCase());
                } else if (choice.equalsIgnoreCase("p")) {
                    jim.printTable();
                } else if (choice.equalsIgnoreCase("s")) {
                    System.out.print("Please select a name for the new file: ");
                    String newName = input.next();
                    jim.saveToFile(newName);
                    System.out.println("A file named " + newName + " has been created!");
                } else if (choice.equalsIgnoreCase("q")) {
                    System.out.println("Sorry to see you go!");
                } else {
                    System.out.println("Invalid selection try again");
                }
            }  catch (Exception moo) {
                System.out.println("The input you entered is incorrect! Please try again.");
            }
            
        }
    }
}
