package com.pick.service.impl;

import com.pick.dto.base.ResponseData;
import com.pick.dto.request.FavoriteListReqDto;
import com.pick.dto.response.FavoriteListResDto;
import com.pick.repository.FavoriteRepository;
import com.pick.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoriteServiceImpl implements FavoriteService {

    private final FavoriteRepository favoriteRepository;

    @Override
    public List<ResponseData> favoriteList(FavoriteListReqDto req) {
        String userCd = req.getUserCd();
        List<Tuple> tuples = favoriteRepository.favoriteList(userCd);
        List<ResponseData> response = new ArrayList<>();
        for(Tuple tuple : tuples) {
            response.add(new FavoriteListResDto(tuple));
        }
        return response;
    }

}
