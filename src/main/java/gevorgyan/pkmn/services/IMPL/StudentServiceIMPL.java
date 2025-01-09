package gevorgyan.pkmn.services.IMPL;

import lombok.RequiredArgsConstructor;
import gevorgyan.pkmn.dao.StudentDao;
import gevorgyan.pkmn.entities.StudentEntities;
import gevorgyan.pkmn.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentServiceIMPL implements StudentService {
    @Autowired
    private final StudentDao studentDao;

    @Override
    public List<StudentEntities> getAllStudents() {
        return studentDao.findAll();
    }

    @Override
    public StudentEntities getStudentById(UUID id) {
        return studentDao.findById(id).orElse(null);
    }

    @Override
    public StudentEntities saveStudent(StudentEntities student) {
        if (studentDao.findByFullName(student.getFirstName(), student.getSurName(), student.getFamilyName()).isPresent()) {
            throw new RuntimeException("Student already exists.");
        }
        return studentDao.save(student);
    }

    @Override
    public StudentEntities updateStudent(UUID id, StudentEntities student) {
        if (!studentDao.findById(id).isPresent()) {
            throw new RuntimeException("Student not found.");
        }
        student.setId(id);
        return studentDao.save(student);
    }

    @Override
    public void deleteStudent(UUID id) {
        studentDao.deleteById(id);
    }

    @Override
    public List<StudentEntities> getStudentsByGroup(String group) {
        return studentDao.findByGroup(group);
    }

    @Override
    public StudentEntities getStudentByFullName(String firstName, String surName, String familyName) {
        return studentDao.findByFullName(firstName, surName, familyName).orElse(null);
    }
}