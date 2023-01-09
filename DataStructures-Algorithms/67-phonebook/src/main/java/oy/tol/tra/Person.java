package oy.tol.tra;

public class Person implements Comparable<Person> {
    private String firstName;
    private String lastName;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getFullName() {
        return lastName + " " + firstName;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Person) {
            return this.getFullName().equals(((Person)other).getFullName());
        }
        return false;
    }

    @Override
    public int hashCode() {

        int hash = 10;
        for (int i = 0; i < getFullName().length(); i++) {
            hash = hash * 21 + getFullName().charAt(i);
        }
        return hash;
    }

    @Override
    public int compareTo(Person other) {
        return getFullName().compareTo(other.getFullName());
    }
}
