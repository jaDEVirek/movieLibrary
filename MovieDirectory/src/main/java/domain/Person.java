package domain;

import java.util.Objects;

public abstract class Person {
    private String firstName;
    private String secondName;


    public Person(String firstName, String secondName) {
        this.firstName = firstName;
        this.secondName = secondName;

    }

    protected Person() {
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getSecondName() {
        return this.secondName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return Objects.equals(getFirstName(), person.getFirstName()) &&
                Objects.equals(getSecondName(), person.getSecondName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getSecondName());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Name:'").append(firstName).append('\'');
        sb.append(" Surname:'").append(secondName).append('\'');
        return sb.toString();
    }

}
