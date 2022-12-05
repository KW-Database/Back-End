package com.KWdatabase.teamProject.Service;

import com.KWdatabase.teamProject.Model.BuyRequestDto;
import com.KWdatabase.teamProject.Model.HolderAge;
import com.KWdatabase.teamProject.Model.Holdings;
import com.KWdatabase.teamProject.Model.User;
import com.KWdatabase.teamProject.dao.ExchangeDao;
import com.KWdatabase.teamProject.dao.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExchangeService {
    private final ExchangeDao exchangeDao;
    private final UserDao userDao;
    private final HolderAgeService holderAgeService;

    public Boolean buy(BuyRequestDto buyRequestDto){

        String id = buyRequestDto.getId();
        int age = userDao.findAge(id);
        String itemCode = buyRequestDto.getItemCode();
        float price = buyRequestDto.getPrice();
        long itemNumber = buyRequestDto.getItemNumber();
        HolderAge holderAge = HolderAge.builder()
                .ages(age)
                .itemCode(itemCode)
                .itemNumber(itemNumber)
                .build();
        holderAgeService.updateData(holderAge);
        if (exchangeDao.isExistHolding(id, itemCode) == true){
            exchangeDao.buy(itemCode, id, price, itemNumber);
        }
        else{
            Holdings holdings = Holdings.builder()
                    .avgPrice(0)
                    .id(id)
                    .itemCode(itemCode)
                    .itemNumber(0)
                    .build();
            exchangeDao.insertHoldings(holdings);
            exchangeDao.buy(itemCode,id,price,itemNumber);
        }
        return true;
    }

    public Boolean sell(String itemCode, String id, long price, long itemNumber){
        exchangeDao.sell(itemCode, id, price, itemNumber);
        return true;
    }
}
