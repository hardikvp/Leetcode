/**
 * @author hardikvp : Hardik Panchal
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * Code to get the fields of a Student directory.
 */
public class Directory {
    /**
     * Code to get the fields of a Student directory.
     */
    private List<Student> std = new ArrayList<Student>();
    /**
     * Code to get the fields of a Student directory.
     */
    private List<Student> value1 = new ArrayList<Student>();
    /**
     * Code to get the fields of a Student directory.
     */
    private List<Student> value2 = new ArrayList<Student>();
    /**
     * Code to get the fields of a Student directory.
     */
    private List<Student> value3 = new ArrayList<Student>();
    /**
     * Code to get the fields of a Student directory.
     */
    private HashMap<String, Student> andrewID = new HashMap<String, Student>();
    /**
     * Code to get the fields of a Student directory.
     */
    private HashMap<String, List<Student>> firstname = new HashMap<String, List<Student>>();
    /**
     * Code to get the fields of a Student directory.
     */
    private HashMap<String, List<Student>> lastname = new HashMap<String, List<Student>>();
    /**
     * Code to get the fields of a Student directory.
     */
    public Directory() { }
    /**
     * Code to get the fields of a Student directory.
     * @param s String
     */
    public void addStudent(Student s) {
        if (s == null) {
            throw new IllegalArgumentException("Student not permitted");
        } else if (andrewID.containsKey(s.getAndrewId())) {
            throw new IllegalArgumentException("The ID already exists.");
            } else {
            Student first = new Student(s.getAndrewId());
            first.setFirstName(s.getFirstName());
            first.setLastName(s.getLastName());
            first.setPhoneNumber(s.getPhoneNumber());
            andrewID.put(first.getAndrewId(), first);
            std.add(first);
            firstname.put(first.getFirstName(), std);
            lastname.put(first.getLastName(), std);
            }
    }
    /**
     * Code to get the fields of a Student directory.
     * @param andrewId of students
     */
    public void deleteStudent(String andrewId) {
        if (andrewId == null) {
            throw new IllegalArgumentException("ID not permitted");
            } else if (andrewID.containsKey(andrewId)) {
                Student value = (Student) andrewID.get(andrewId);
        andrewID.remove(value.getAndrewId());
        std.remove(value);
        } else {
            throw new IllegalArgumentException("The ID does not exists.");
        }
    }
    /**
     * Code to get the fields of a Student directory.
     * @param andrewId of students
     * @return size of students
     */
    public Student searchByAndrewId(String andrewId) {
        if (andrewId == null) {
            throw new IllegalArgumentException("ID not given");
        }
        if (andrewID.containsKey(andrewId)) {
            Student value = (Student) andrewID.get(andrewId);
            Student first = new Student(value.getAndrewId());
            first.setFirstName(value.getFirstName());
            first.setLastName(value.getLastName());
            first.setPhoneNumber(value.getPhoneNumber());
            return value;
        } else {
            return null;
        }
    }
    /**
     * Code to get the fields of a Student directory.
     * @param firstName of students
     * @return student of students
     */
    public List<Student> searchByFirstName(String firstName) {
        /**
         * Code to get the fields of a Student directory.
         * @param firstName of students
         * @return student of students
         */
       if (firstName == null) {
           throw new IllegalArgumentException("FirstName not permitted");
       }
        if (firstname.containsKey(firstName)) {
            value1.clear();
            for (int i = 0; i < std.size(); i++) {
                if (firstName.equals(std.get(i).getFirstName())) {
                    Student first = new Student(std.get(i).getAndrewId());
                    first.setFirstName(std.get(i).getFirstName());
                    first.setLastName(std.get(i).getLastName());
                    first.setPhoneNumber(std.get(i).getPhoneNumber());
                    value1.add(first);
                    }
                }
            return  value1;
            } else {
              return value3;
            }
        }
    /**
     * Code to get the fields of a Student directory.
     * @return student of students
     * @param lastName of students
     */
    public List<Student> searchByLastName(String lastName) {
        if (lastName == null) {
            throw new IllegalArgumentException("LastName not permitted");
        }
        if (lastname.containsKey(lastName)) {
            value2.clear();
            for (int i = 0; i < std.size(); i++) {
                if (lastName.equals(std.get(i).getLastName())) {
                    Student first = new Student(std.get(i).getAndrewId());
                    first.setFirstName(std.get(i).getFirstName());
                    first.setLastName(std.get(i).getLastName());
                    first.setPhoneNumber(std.get(i).getPhoneNumber());
                    value2.add(first);
                    }
                }
            return value2;
            } else {
              return value3;
            }
        }
    /**
     * Code to get the fields of a Student directory.
     * @return size
     */
    public int size() {
        return andrewID.size();
        }
    }
