package com.pick.repository;

import com.pick.entity.Contact;
import com.pick.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {

    @Query(value = "SELECT * FROM public.m_favorite", nativeQuery = true)
    List<Favorite> searchAll();

}
