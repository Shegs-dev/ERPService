/**
 * 
 */
package com.proinsight.erpservice.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.proinsight.erpservice.entities.Users;

/**
 * @author OluwasegunAjayi on 22nd of February 2021
 *
 */
@Repository("usersRepository")
public interface UsersRepository extends MongoRepository<Users, String> {

	Users findByUsernameOrEmail(String username, String email);

	List<Users> findByType(int type);

	List<Users> findByIdIn(List<String> ids);

}
