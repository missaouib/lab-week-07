package com.minh.labweek07.backend.repository;

import com.minh.labweek07.backend.models.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log, Long> {
}