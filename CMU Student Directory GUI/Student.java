/**
 * @author hardikvp
 *
 */
public class Student {
    /**
     * Code to get the fields of a Student directory.
     * @param FirstName of students
     */
    private String firstName;
    /**
     * Code to get the fields of a Student directory.
     * @param FirstName of students
     */
    private String lastName;
    /**
     * Code to get the fields of a Student directory.
     * @param FirstName of students
     */
    private String andrewID;
    /**
     * Code to get the fields of a Student directory.
     * @param FirstName of students
     */
    private String phoneNumber;
    /**
     * Code to get the fields of a Student directory.
     * @param andrewId of students
     */
    public Student(String andrewId) {
        andrewID = andrewId;
        }
    /**
     * Code to get the fields of a Student directory.
     * @return FirstName of students
     */
    public String getAndrewId() {
        return andrewID;
        }
    /**
     * Code to get the fields of a Student directory.
     * @return FirstName of students
     */
    public String getLastName() {
        return lastName;
        }
    /**
     * Code to get the fields of a Student directory.
     * @param s of students
     */
    public void setLastName(String s) {
        lastName = s;
    }
    /**
     * Code to get the fields of a Student directory.
     * @return FirstName of students
     */
    public String getFirstName() {
        return firstName;
    }
    /**
     * Code to get the fields of a Student directory.
     * @param s of students
     */
    public void setFirstName(String s) {
        this.firstName = s;
    }
    /**
     * Code to get the fields of a Student directory.
     * @return FirstName of students
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
    /**
     * Code to get the fields of a Student directory.
     * @param s of students
     */
    public void setPhoneNumber(String s) {
        this.phoneNumber = s;
    }
    /**
     * Code to get the fields of a Student directory.
     * @return output of students
     */
    public String toString() {
        // TODO Auto-generated method stub
        return firstName + " " +  lastName + " (Andrew ID: " + andrewID + ", Phone Number: " + phoneNumber + ")";
        }
    }
