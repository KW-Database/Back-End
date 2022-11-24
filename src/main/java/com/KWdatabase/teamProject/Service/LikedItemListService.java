package com.KWdatabase.teamProject.Service;

import com.KWdatabase.teamProject.Model.ItemDayCondition;
import com.KWdatabase.teamProject.Model.ItemTimeCondition;
import com.KWdatabase.teamProject.dao.ItemDayConditionDao;
import com.KWdatabase.teamProject.dao.ItemTimeConditionDao;
import com.KWdatabase.teamProject.dao.LikedItemDao;
import com.KWdatabase.teamProject.Model.LikedItemListDto;
import com.KWdatabase.teamProject.Model.LikedItemsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LikedItemListService {

    private final LikedItemDao likedItemDao;
    private final ItemTimeConditionDao itemTimeConditionDao;
    private final ItemDayConditionDao itemDayConditionDao;

    public List<LikedItemsResponseDto> getListInfo(String id){
        List<LikedItemListDto> list = likedItemDao.getList(id);
        List<LikedItemsResponseDto> responseDtoList = new ArrayList<>();

        for(LikedItemListDto item : list){
            String itemCode = item.getItemCode();
            ItemTimeCondition itemTimeCondition = itemTimeConditionDao.getNewCondition(itemCode);
            ItemDayCondition itemDayCondition = itemDayConditionDao.getLatestCondition(itemCode);

            String itemName = item.getItemName();
            float price = itemTimeCondition.getExecutionPrice();
            int likedNum = item.getLikedNum();
            float endPrice = itemDayCondition.getEndPrice();
            float changeAmount = price - endPrice;
            float changeRate = changeAmount/endPrice * 100;

            LikedItemsResponseDto likedItemsResponseDto = LikedItemsResponseDto.builder()
                    .changeAmount(changeAmount)
                    .changeRate(changeRate)
                    .itemName(itemName)
                    .likedNum(likedNum)
                    .price(price)
                    .build();

            responseDtoList.add(likedItemsResponseDto);
        }

        return responseDtoList;
    }
}
