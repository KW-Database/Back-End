package com.KWdatabase.teamProject.Service;

import com.KWdatabase.teamProject.dao.ItemTimeConditionDao;
import com.KWdatabase.teamProject.dto.ItemInfoResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HomeService {

    private final ItemTimeConditionDao itemTimeConditionDao;

    public List<ItemInfoResponseDto> getVolumeRank(){
        return itemTimeConditionDao.getVolumeRank();
    }

}
