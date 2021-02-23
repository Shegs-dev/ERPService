/**
 * 
 */
package com.proinsight.erpservice.services;

import com.proinsight.erpservice.dtos.LoginDTO;
import com.proinsight.erpservice.dtos.ResponseDTO;
import com.proinsight.erpservice.entities.Users;

/**
 * @author OluwasegunAjayi on 22nd of February 2021
 *
 */
public interface UsersService {
	
	//Service Methods
	int add(Users user);
	int update(Users user);
	ResponseDTO doLogin(LoginDTO loginDTO);

}
