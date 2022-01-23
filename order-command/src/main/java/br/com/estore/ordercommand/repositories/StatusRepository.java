package br.com.estore.ordercommand.repositories;

import br.com.estore.ordercommand.domain.entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {

    Status findByCode(final String code);

}
