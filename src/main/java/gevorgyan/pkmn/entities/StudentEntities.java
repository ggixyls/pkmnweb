package gevorgyan.pkmn.entities;

import gevorgyan.pkmn.models.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "students")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentEntities implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private UUID id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "surName")
    private String surName;

    @Column(name = "familyName")
    private String familyName;

    @Column(name = "\"group\"")
    private String group;


        public static StudentEntities toEntity(Student student) {
            if (student != null) {
                return StudentEntities.builder()
                        .id(UUID.randomUUID())
                        .surName(student.getSurName())
                        .firstName(student.getFirstName())
                        .familyName(student.getFamilyName())
                        .group(student.getGroup())
                        .build();
            }
            return null;
        }

    @Override
    public String toString() {
        return "StudentEntity{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", surName='" + surName + '\'' +
                ", familyName='" + familyName + '\'' +
                ", group='" + group + '\'' +
                '}';
    }
}