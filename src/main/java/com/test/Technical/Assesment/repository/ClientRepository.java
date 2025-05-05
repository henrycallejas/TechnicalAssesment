package com.test.Technical.Assesment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.Technical.Assesment.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{

}
