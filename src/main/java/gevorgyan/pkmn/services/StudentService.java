package gevorgyan.pkmn.services;

import gevorgyan.pkmn.entities.StudentEntities;

import java.util.List;
import java.util.UUID;

public interface StudentService {
    List<StudentEntities> getAllStudents();
    StudentEntities getStudentById(UUID id);
    StudentEntities saveStudent(StudentEntities student);
    StudentEntities updateStudent(UUID id, StudentEntities student);
    void deleteStudent(UUID id);
    List<StudentEntities> getStudentsByGroup(String group);
    StudentEntities getStudentByFullName(String firstName, String surName, String familyName);
}