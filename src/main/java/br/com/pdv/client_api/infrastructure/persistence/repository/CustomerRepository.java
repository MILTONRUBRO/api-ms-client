package br.com.pdv.client_api.infrastructure.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.pdv.client_api.infrastructure.persistence.entity.CustomerEntity;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    Optional<CustomerEntity> findByDocument(String document);

    @Query("SELECT MAX(c.id) FROM CustomerEntity c")
    Long findMaxId();
}