
/**
 * @author Lucie Sullivan 111718874 Recitation Tuesday at 10AM
 */
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class PersonDataManager {

    private static Person[] people;
    private static int count;

    public PersonDataManager() {

    }

    public static void buildFromFile(String location) throws IllegalArgumentException, FileNotFoundException {
        people = new Person[100];
        try {
            java.io.File file = new java.io.File(location);
            Scanner input = new Scanner(file);
            int column = 0;
            int index = 0;
            while (input.hasNext()) {
                Scanner data = new Scanner(input.nextLine());
                data.useDelimiter(",");
                Person p = new Person();
                while (data.hasNext()) {
                    String stuff = data.next();
                    /*The first if statement is to catch the line of the csv that has labels   
                     so that it doesn't save to the array as null.
                     */
                    if (stuff.contains("Name") || stuff.contains("Sex") || stuff.contains("Age")
                            || stuff.contains("Height") || stuff.contains("Weight")) {
                        column = 0;
                    } else if (column == 0) {
                        stuff = stuff.replaceAll("\"", "");
                        p.setName(stuff.replaceAll(" ", ""));

                    } else if (column == 1) {
                        stuff = stuff.replaceAll("\"", "");
                        p.setGender(stuff.replaceAll(" ", ""));

                    } else if (column == 2) {
                        p.setAge(Integer.parseInt(stuff.replaceAll(" ", "")));

                    } else if (column == 3) {
                        p.setHeight(Double.parseDouble(stuff.replaceAll(" ", "")));

                    } else if (column == 4) {
                        p.setWeight(Double.parseDouble(stuff.replaceAll(" ", "")));

                    } else {
                        column = 0;
                    }
                    column++;
                }
                people[index] = p;
                index++;
                column = 0;
                count = index;
            }
            input.close();
            System.out.println("Loading...\nPerson data loaded successfully!");
        } catch (FileNotFoundException g) {
            System.out.println("That file doesn't exist. Please try again!");
        } catch (IllegalArgumentException j) {
            System.out.println("The input you entered is incorrect. Please try again!");
        }
    }

    public void addPerson(Person newPerson) throws PersonAlreadyExistsException {
        /*
         The first part of this method checks to see if the person already exists using the equals method
         defined in the Person class
         */
        boolean newGuy = true;
        try {
            for (int i = 1; i < count; i++) {
                if (people[i].equals(newPerson)) {
                    newGuy = false;
                    throw new PersonAlreadyExistsException("That person already exists");
                }
            }
        } catch (PersonAlreadyExistsException r) {
            System.out.println("That person is already in the list!");
        }
        /*
         If the person does not exist this part of the method adds the person to the array and then
         uses selection sort to sort the array
         */
        if (newGuy) {
            count++;
            people[count - 1] = newPerson;
            int minIndex;
            Person temp;
            for (int i = 1; i < count; i++) {
                minIndex = i;
                for (int j = i + 1; j < count; j++) {
                    if (comesFirst(people[j].getName(), people[minIndex].getName())) {
                        minIndex = j;
                    }
                    temp = people[minIndex];
                    people[minIndex] = people[i];
                    people[i] = temp;
                }
            }
            System.out.println(newPerson.getName() + " has been added to the list!");
        }
    }

    /*This method uses a binary search algorithm to find the requested person
     Once the person is found the Person toString() method is used to print the information
     */
    public void getPerson(String name) throws PersonDoesNotExistsException {
        boolean flag = true;
        int left = 1;
        int middle;
        int right = count;
        try {
            while (flag) {
                middle = (left + right) / 2;
                String first = people[middle].getName().toUpperCase();
                if (first.equals(name)) {
                    System.out.println(people[middle].toString());
                    flag = false;
                } else if ((int) (first.charAt(0)) < (int) (name.charAt(0))) {
                    left = middle + 1;
                } else if ((int) (first.charAt(0)) > (int) (name.charAt(0))) {
                    right = middle - 1;
                } else {
                    throw new PersonDoesNotExistsException("That person does not exist");
                }
            }
        } catch (PersonDoesNotExistsException e) {
            System.out.println("The person you're looking for doesn't exist. Please try again");
        }
    }

    /*
     This method uses binary search to find the index of the requested person and then uses a for loop
     to swtich all the people starting from the middle to count
     */
    public void removePerson(String name) throws PersonDoesNotExistsException {
        boolean flag = true;
        int left = 1;
        int middle = 0;
        int right = count;
        String yikes = name.substring(0, 1) + name.substring(1).toLowerCase();
        try {
            while (flag) {
                middle = (left + right) / 2;
                String first = people[middle].getName().toUpperCase();
                if (first.equals(name)) {
                    count--;
                    for (int i = middle; i < count; i++) {
                        people[i] = people[i + 1];
                    }
                    people[count] = null;
                    flag = false;
                    System.out.println(yikes + " has been removed!");
                } else if ((int) (first.charAt(0)) < (int) (name.charAt(0))) {
                    left = middle + 1;
                } else if ((int) (first.charAt(0)) > (int) (name.charAt(0))) {
                    right = middle - 1;
                } else {
                    throw new PersonDoesNotExistsException("That person does not exist");
                }
            }
        } catch (PersonDoesNotExistsException e) {
            System.out.println("The person you're looking for doesn't exist. Please try again");
        }
    }

    /*This method prints a table with all the people in the People array
     It works by first printing the headings and then using a for loop to
     print the data for each person by calling the getter methods in the person class*/
    public void printTable() {
        int spot = 1;
        System.out.println("  Name     |     Age     |     Gender    |        Height\t  |     Weight");
        System.out.println("------------------------------------------------------------------------------------");
        for (int i = 0; i < count - 1; i++) {
            System.out.print("  " + people[spot].getName() + "     |");
            System.out.print("     " + people[spot].getAge() + "      |");
            System.out.print("      " + people[spot].getGender() + "        |");
            System.out.print("    " + people[spot].getFeet() + " feet ");
            System.out.print(people[spot].getInches() + " inches\t  |");
            System.out.println("\t" + (int) people[spot].getWeight() + " pounds");
            spot++;
        }

    }

    /*
     This method compares two Strings and checks which comes first alphabetically
     First checks the first letter, and if they are the same it checks the second
     */
    public boolean comesFirst(String newThing, String minInd) {
        newThing = newThing.toLowerCase();
        minInd = minInd.toLowerCase();
        if ((int) newThing.charAt(0) < (int) minInd.charAt(0)) {
            return true;
        } else if ((int) newThing.charAt(0) == (int) minInd.charAt(0)) {
            if ((int) newThing.charAt(1) < (int) minInd.charAt(1)) {
                return true;
            }
            return false;
        }
        return false;
    }

    /*
     This method uses PrintWriter to write the data from the array to a new file that is 
     named by the user
     */
    public void saveToFile(String name) throws FileNotFoundException {
        PrintWriter output = new PrintWriter(name);
        output.println("Name\tSex\tAge\tHeight (in)\tWeight (lb)");
        for (int i = 1; i < count; i++) {
            output.print(people[i].getName() + ", ");
            output.print(people[i].getGender() + ", ");
            output.print(people[i].getAge() + ", ");
            output.print(people[i].getHeight() + ", ");
            output.println(people[i].getWeight());
        }
        output.close();
    }
}
