package com.magazin.demo.repository;

import com.magazin.demo.model.UserGroup;
import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserGroupRepository  extends JpaRepository<UserGroup, ID> {

    UserGroup findByCode(String code);
}
