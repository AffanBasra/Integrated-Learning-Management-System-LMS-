public class Student extends User{
    private String studentName;
    private String email;
    private String hashedPassword;
    private int noOfPresents = 0;
    private int noOfAbsents = 0;
    
    // Constructor
    public Student(int studentId, String studentName, String email, String hashedPassword) {
        this.UserID = studentId;
        this.studentName = studentName;
        this.email = email;
        this.hashedPassword = hashedPassword;
    }

    public Student(int studentId, String studentName, String email, String hashedPassword, int noOfPresents, int noOfAbsents) {
        this(studentId, studentName, email, hashedPassword);
        this.noOfPresents = noOfPresents;
        this.noOfAbsents = noOfAbsents;
    }
    
    // Getters
    public int getStudentId() {
        return UserID;
    }
    
    public String getStudentName() {
        return studentName;
    }
    
    public String getEmail() {
        return email;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public int getNoOfPresents() {
        return noOfPresents;
    }

    public int getNoOfAbsents() {
        return noOfAbsents;
    }
    
    // Setters
    public void setStudentId(int studentId) {
        this.UserID = studentId;
    }
    
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public void markPresent() {
        this.noOfPresents++;
    }

    public void markAbsent() {
        this.noOfAbsents++;
    }
}
