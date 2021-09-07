package br.com.adda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.adda.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
