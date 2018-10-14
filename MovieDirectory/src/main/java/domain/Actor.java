package domain;

public class Actor extends Person {
    private String fullName;

    public Actor(String fullName) {
        String[] s = fullName.split(" ");
        this.setFirstName(s[0]);
        this.setSecondName(s[1]);
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }


    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
