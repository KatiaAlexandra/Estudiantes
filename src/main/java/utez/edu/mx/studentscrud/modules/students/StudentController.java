package utez.edu.mx.studentscrud.modules.students;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/student")
@CrossOrigin({"*"})
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> findAll(){
        return studentService.findAll();
    }

    @GetMapping("/{studentId}")
    public Student findByStudentId(@PathVariable String studentId){
        return studentService.findByStudentIdentifier(studentId);
    }

    @PostMapping
    public Student save(@RequestBody Student student){
        return studentService.save(student);
    }

    @PutMapping
    public Student update(@RequestBody Student student){
        return studentService.update(student);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable String id){
        return studentService.delete(id);
    }
}
