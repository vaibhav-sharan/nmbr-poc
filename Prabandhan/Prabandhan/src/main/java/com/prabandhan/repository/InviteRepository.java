package com.prabandhan.repository;

import com.prabandhan.model.Invite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InviteRepository extends JpaRepository<Invite, Long> {
}
