package gevorgyan.pkmn.dao;

import gevorgyan.pkmn.entities.StudentEntities;
import lombok.RequiredArgsConstructor;
import gevorgyan.pkmn.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class StudentDao {
    @Autowired
    private final StudentRepository studentRepository;

    public List<StudentEntities> findAll() {
        return studentRepository.findAll();
    }

    public StudentEntities save(StudentEntities student) {
        return studentRepository.save(student);
    }

    public void deleteById(UUID id) {
        studentRepository.deleteById(id);
    }

    public List<StudentEntities> findByGroup(String group) {
        return studentRepository.findByGroup(group);
    }

    public Optional<StudentEntities> findByFullName(String firstName, String surName, String familyName) {
        return studentRepository.findByFirstNameAndSurNameAndFamilyName(firstName, surName, familyName);
    }

    public Optional<StudentEntities> findById(UUID id) {
        return studentRepository.findById(id);
    }
}