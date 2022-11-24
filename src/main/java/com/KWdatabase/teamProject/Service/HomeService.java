package com.KWdatabase.teamProject.Service;

import com.KWdatabase.teamProject.dao.CompanyDao;
import com.KWdatabase.teamProject.dao.ItemTimeConditionDao;
import com.KWdatabase.teamProject.Model.ItemInfoResponseDto;
import com.KWdatabase.teamProject.Model.ItemInfoResponseDto;
import com.KWdatabase.teamProject.Model.PublicDateRankDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HomeService {

    private final ItemTimeConditionDao itemTimeConditionDao;
    private final CompanyDao companyDao;

    public List<ItemInfoResponseDto> getVolumeRank(){
        return itemTimeConditionDao.getVolumeRank();
    }
    public List<PublicDateRankDto> getPublicDateList(){return companyDao.getPublicDateList();}

    public List<ItemInfoResponseDto> getUpRank(){return itemTimeConditionDao.getUpRank();}

    public List<ItemInfoResponseDto> getDownRank(){return  itemTimeConditionDao.getDownRank();}

}
