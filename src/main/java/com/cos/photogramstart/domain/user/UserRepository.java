package com.cos.photogramstart.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

//어노테이션이 없어도 IOC 등록이 자동으로 된다 JpaRepository를 상속하였기때문에
public interface UserRepository extends JpaRepository<User, Integer> {

    //JPA Query method
    User findByUsername(String username); //찾아서 user Object를 return 해줄것이다

}
