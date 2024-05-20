public class Instructor extends User{
    private String instructorName;
    private String email;
    private String hashedPassword;
    
    // Constructor
    public Instructor(int InstructorId, String InstructorName, String email, String hashedPassword) {
        this.UserID = InstructorId;
        this.instructorName = InstructorName;
        this.email = email;
        this.hashedPassword = hashedPassword;
    }
    
    // Getters
    public int getInstructorId() {
        return UserID;
    }
    
    public String getInstructorName() {
        return instructorName;
    }
    
    public String getEmail() {
        return email;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }
    
    // Setters
    public void setInstructorId(int InstructorId) {
        this.UserID = InstructorId;
    }
    
    public void setInstructorName(String InstructorName) {
        this.instructorName = InstructorName;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }
}
