package Models;

public class RegisteredSubject {
    private String studentId,courseId,courseName,credit;

    public RegisteredSubject(String studentId, String courseId, String courseName, String credit) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.courseName = courseName;
        this.credit = credit;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }
}
