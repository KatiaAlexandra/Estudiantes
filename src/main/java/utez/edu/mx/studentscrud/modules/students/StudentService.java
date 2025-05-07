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
        List<Student> students = findAll();

        if(student.getName()==null || student.getStudentIdentifier() == null || student.getCareer()== null || student.getEmail() == null || student.getM_lastname()== null || student.getP_lastname() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Todos los campos deben estar llenos");
        }

        for(Student s: students){
            if(s.getStudentIdentifier().equalsIgnoreCase(student.getStudentIdentifier())){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La matrícula ya está registrada");
            }
        }
        return studentRepository.save(student);
    }

    public Student update(Student student){
        Student found = studentRepository.findById(student.getId()).
                orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el estudiante"));
        String studentIdentifier = found.getStudentIdentifier();

        if(!found.getStudentIdentifier().equalsIgnoreCase(studentIdentifier)){
            List<Student> students = studentRepository.findAll();
            for(Student s: students){
                if(s.getStudentIdentifier().equalsIgnoreCase(studentIdentifier)){
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La matrícula ya está registrada");
                }
            }
        }
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
}
