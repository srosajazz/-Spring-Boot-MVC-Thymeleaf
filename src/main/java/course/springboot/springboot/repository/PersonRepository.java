package course.springboot.springboot.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import course.springboot.springboot.model.Person;

@Repository
@Transactional
public interface PersonRepository extends CrudRepository<Person, Long> {

}
