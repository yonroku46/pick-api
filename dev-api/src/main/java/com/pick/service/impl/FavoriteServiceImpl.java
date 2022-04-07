package com.pick.service.impl;

import com.pick.entity.Contact;
import com.pick.entity.Favorite;
import com.pick.exception.ErrorCode;
import com.pick.exception.ServiceException;
import com.pick.repository.ContactRepository;
import com.pick.repository.FavoriteRepository;
import com.pick.service.ContactService;
import com.pick.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoriteServiceImpl implements FavoriteService {

    private final FavoriteRepository favoriteRepository;

    @Override
    public List<Favorite> searchAll() {
        try {
            List<Favorite> response = favoriteRepository.searchAll();
            return response;
        } catch(Exception exception) {
            throw new ServiceException(exception.toString(), ErrorCode.INTER_SERVER_ERROR);
        }
    }
}
