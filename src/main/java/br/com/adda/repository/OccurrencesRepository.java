package br.com.adda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import br.com.adda.model.Occurrences;

@Repository
public interface OccurrencesRepository extends JpaRepository<Occurrences, Long>, JpaSpecificationExecutor {
	
}
