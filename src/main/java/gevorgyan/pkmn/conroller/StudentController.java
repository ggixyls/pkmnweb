package gevorgyan.pkmn.conroller;

import gevorgyan.pkmn.entities.StudentEntities;
import lombok.RequiredArgsConstructor;
import gevorgyan.pkmn.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {
    @Autowired
    private final StudentService studentService;

    @GetMapping("/all")
    public List<StudentEntities> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentEntities> getStudentById(@PathVariable UUID id) {
        StudentEntities student = studentService.getStudentById(id);
        return student != null ? ResponseEntity.ok(student) : ResponseEntity.notFound().build();
    }

    @PostMapping("")
    public StudentEntities createStudent(@RequestBody StudentEntities student) {
        return studentService.saveStudent(student);
    }

    @PutMapping("/{id}")
    public StudentEntities updateStudent(@PathVariable UUID id, @RequestBody StudentEntities student) {
        return studentService.updateStudent(id, student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable UUID id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/group/{group}")
    public List<StudentEntities> getStudentsByGroup(@PathVariable String group) {
        return studentService.getStudentsByGroup(group);
    }

    @GetMapping("")
    public StudentEntities getStudentByFullName(@RequestBody StudentEntities ownerRequest) {
        return studentService.getStudentByFullName(ownerRequest.getFirstName(), ownerRequest.getSurName(), ownerRequest.getFamilyName());
    }
}