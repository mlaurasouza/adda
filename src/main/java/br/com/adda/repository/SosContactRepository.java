package br.com.adda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.adda.model.SosContact;


@Repository
public interface SosContactRepository extends JpaRepository<SosContact, Long> {
	
	//@Query("SELECT new SosContact(u.id, u.userId, u.name, u.phone) FROM sos_contact u WHERE u.userId = :userId AND u.phone = :phone")
	SosContact findByUserIdAndPhone(@Param("user_id") Long userId, @Param("phone") String phone);
}
