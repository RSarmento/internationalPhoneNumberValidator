package pt.jumia.phonevalidator.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pt.jumia.phonevalidator.domain.Customer;

import java.awt.print.Pageable;
import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {


}
