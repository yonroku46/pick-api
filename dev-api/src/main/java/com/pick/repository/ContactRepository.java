package com.pick.repository;

import com.pick.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Integer> {

    @Query(value = "SELECT * FROM public.m_contact", nativeQuery = true)
    List<Contact> searchAll();

}
