package com.felixherder.ftotbe.instructors;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<InstructorDO, String> {
}
