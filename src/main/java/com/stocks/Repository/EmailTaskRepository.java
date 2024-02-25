package com.stocks.Repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stocks.user.EmailTask;


@Repository
public interface EmailTaskRepository extends JpaRepository<EmailTask, Long> {
    List<EmailTask> findBySentFalseAndScheduledTimeBefore(LocalDateTime now);
}
