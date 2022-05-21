package com.pick.repository;

import com.pick.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Integer> {

    @Modifying
    @Transactional
    @Query(value =
            "INSERT INTO public.m_contact" +
            "  (name,email,category,detail,create_time)" +
            " VALUES" +
            "  (:name,:email,:category,:detail,now())"
            , nativeQuery = true)
    int save(@Param("name") String name, @Param("email") String email, @Param("category") String category, @Param("detail") String detail);

    @Query(value = "SELECT * FROM public.m_contact", nativeQuery = true)
    List<Contact> searchAll();

}
