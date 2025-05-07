package utez.edu.mx.studentscrud.modules.students;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "students")
public class Student {
    @Id
    private String id;
    private String name;
    private String p_lastname;
    private String m_lastname;
    private String email;
    private String career;
    private String studentIdentifier;

    public Student() {
    }

    public Student(String id, String name, String p_lastname, String m_lastname, String email, String career, String studentIdentifier) {
        this.id = id;
        this.name = name;
        this.p_lastname = p_lastname;
        this.m_lastname = m_lastname;
        this.email = email;
        this.career = career;
        this.studentIdentifier = studentIdentifier;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getP_lastname() {
        return p_lastname;
    }

    public void setP_lastname(String p_lastname) {
        this.p_lastname = p_lastname;
    }

    public String getM_lastname() {
        return m_lastname;
    }

    public void setM_lastname(String m_lastname) {
        this.m_lastname = m_lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public String getStudentIdentifier() {
        return studentIdentifier;
    }

    public void setStudentIdentifier(String studentIdentifier) {
        this.studentIdentifier = studentIdentifier;
    }
}
