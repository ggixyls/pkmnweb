package gevorgyan.pkmn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import gevorgyan.pkmn.entities.StudentEntities;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StudentRepository extends JpaRepository<StudentEntities, UUID> {
    List<StudentEntities> findByGroup(String group);

    Optional<StudentEntities> findByFirstNameAndSurNameAndFamilyName(String firstName, String surName, String familyName);
}