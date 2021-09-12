package br.com.adda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.adda.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query("SELECT new User(u.id, u.name, u.email, u.password, u.gender, u.age, u.phone, u.photoUrl, u.isAdmin) FROM User u WHERE u.email = :email OR u.phone = :phone")
	User findByEmailAndPhone(@Param("email") String email, @Param("phone") String phone);
}
