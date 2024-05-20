public class Assignment {
    private int studentId;
    private String comments;
    private String FileAddress;

    public Assignment( int studentId, String comments, String FileAddress) {
        this.comments = comments;
        this.FileAddress = FileAddress;
        this.studentId = studentId;
    }

    public String getComments() {
        return comments;
    }
    public String getFileAddress() {
        return FileAddress;
    }
    public int getStudentId() {
        return studentId;
    }
}
