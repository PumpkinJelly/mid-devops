package com.fomin.studentapi.service;

import com.fomin.studentapi.model.Student;
import com.fomin.studentapi.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public List<Student> getAll() {
        return repository.findAll();
    }

    public Optional<Student> getById(Long id) {
        return repository.findById(id);
    }

    public Student create(Student student) {
        return repository.save(student);
    }

    public Student update(Long id, Student updated) {
        return repository.findById(id)
                .map(student -> {
                    student.setFirstName(updated.getFirstName());
                    student.setLastName(updated.getLastName());
                    student.setGroupName(updated.getGroupName());
                    student.setStudentId(updated.getStudentId());
                    return repository.save(student);
                })
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Student not found with id: " + id);
        }
        repository.deleteById(id);
    }
}