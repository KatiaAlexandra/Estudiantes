package utez.edu.mx.studentscrud.modules.students;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> findAll(){
        return studentRepository.findAll();
    }

    public Student findByStudentIdentifier(String id){
        return studentRepository
                .findByStudentIdentifier(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el estudiante"));
    }

    public Student save(Student student){
        validateStudent(student,false);
        return studentRepository.save(student);
    }

    public Student update(Student student){
        studentRepository.findById(student.getId()).
                orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el estudiante"));
        validateStudent(student,true);
      return studentRepository.save(student);
    }

    public boolean delete(String id){
        Student found = studentRepository.findById(id).orElse(null);
        if(found != null){
            studentRepository.delete(found);
            return true;
        }
       return false;
    }

    private boolean validateStudent(Student student, boolean isUpdate){
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        if(student.getName()==null || student.getStudentIdentifier() == null
                || student.getCareer()== null || student.getEmail() == null || student.getM_lastname()== null
                || student.getP_lastname() == null)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Todos los campos deben estar llenos");
        }

        if(!student.getEmail().matches(regex)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Introduce un correo válido");
        }

        List<Student> students = studentRepository.findAll();

        if(isUpdate) {
            students.removeIf(s -> s.getId().equals(student.getId()));
        }

        for(Student s: students){
            if(s.getStudentIdentifier().equalsIgnoreCase(student.getStudentIdentifier())){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La matrícula ya está registrada");
            }
        }

        return true;
    }
}
