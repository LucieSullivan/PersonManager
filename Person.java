
/**
 * @author Lucie Sullivan 111718874 Recitation Tuesday at 10AM
 */
public class Person {

    private int age;
    private double height;
    private double weight;
    private String name;
    private String gender;

    public Person(String initName, String initGender, int initAge, double initHeight, double initWeight) {
        name = initName;
        gender = initGender;
        age = initAge;
        height = initHeight;
        weight = initWeight;
    }

    public Person() {

    }

    public int getAge() {
        return age;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public void setAge(int newAge) {
        age = newAge;
    }

    public void setHeight(double newHeight) {
        height = newHeight;
    }

    public void setWeight(double newWeight) {
        weight = newWeight;
    }

    public void setName(String newName) {
        name = newName;
    }

    public void setGender(String newGender) {
        gender = newGender;
    }

    /*
     Find the feet part of the person's height
     */
    public int getFeet() {
        return (int) height / 12;
    }

    /*
     Find the inches part of the person's height
     */
    public int getInches() {
        return (int) height % 12;
    }

    public String toString() {
        String word = "female";
        if (gender.equalsIgnoreCase("m")) {
            word = "male";
        }

        return name + " is a " + age + " year old " + word + " who is " + getFeet() + " feet and "
                + getInches() + " inches tall and weighs " + (int) weight + " pounds.";
    }

    /*This method is used to compare if two person objects have all the same data
     It uses nested if statements and if it gets all the way to the
     last if statment the two objects are the same*/
    public boolean equals(Person other) {
        boolean flag = false;
        if (this.getName().equalsIgnoreCase(other.getName())) {
            if (this.getGender().equalsIgnoreCase(other.getGender())) {
                if (this.getAge() == other.getAge()) {
                    if (this.getHeight() == other.getHeight()) {
                        if (this.getWeight() == other.getWeight()) {
                            flag = true;
                        }
                    }
                }
            }

        }
        return flag;
    }
}
