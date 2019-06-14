package com.pmh.Repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pmh.Domains.Phone;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Integer> {
}
