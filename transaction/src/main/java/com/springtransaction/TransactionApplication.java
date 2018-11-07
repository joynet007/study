package com.springtransaction;

import com.springtransaction.povo.ClassRoom;
import com.springtransaction.povo.UserInfo;
import com.springtransaction.service.UserInfoManager;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@MapperScan("com.springtransaction.dao")
@EnableTransactionManagement
@SpringBootApplication
public class TransactionApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransactionApplication.class, args);
	}

	@Resource
	UserInfoManager userInfoManager;

	@PostConstruct
	public void doinsert(){
		UserInfo userInfo = new UserInfo();
		userInfo.setUsername("123456789");

		ClassRoom classRoom = new ClassRoom();
		classRoom.setClassname("09876543211111");

		try {
			userInfoManager.save(userInfo,classRoom);
		}catch (Exception ex){
			ex.printStackTrace();
		}


	}
}
