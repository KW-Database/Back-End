package com.KWdatabase.teamProject.Service;

import com.KWdatabase.teamProject.Model.*;
import com.KWdatabase.teamProject.dao.ExchangeDao;
import com.KWdatabase.teamProject.dao.HoldingsDao;
import com.KWdatabase.teamProject.dao.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExchangeService {
    private final ExchangeDao exchangeDao;
    private final UserDao userDao;
    private final HolderAgeService holderAgeService;
    private final HoldingsDao holdingsDao;

    public Boolean buy(BuyRequestDto buyRequestDto){

        String id = buyRequestDto.getId();
        User user= userDao.findUser(id);
        int age = user.getAge();
        String itemCode = buyRequestDto.getItemCode();
        float price = buyRequestDto.getPrice();
        long itemNumber = buyRequestDto.getItemNumber();
        if(user.getCash()<price*itemNumber) return false;
        HolderAge holderAge = HolderAge.builder()
                .ages(age)
                .itemCode(itemCode)
                .itemNumber(itemNumber)
                .build();
        holderAgeService.updateData(holderAge);
        if (exchangeDao.isExistHolding(id, itemCode) == false){
            Holdings holdings = Holdings.builder()
                    .avgPrice(0)
                    .id(id)
                    .itemCode(itemCode)
                    .itemNumber(0)
                    .build();
            exchangeDao.insertHoldings(holdings);
        }
        exchangeDao.buy(itemCode,id,price,itemNumber);
        return true;
    }

    public Boolean sell(SellRequestDto sellRequestDto){
        String id = sellRequestDto.getId();
        User user= userDao.findUser(id);
        int age = user.getAge();
        String itemCode = sellRequestDto.getItemCode();
        float price = sellRequestDto.getPrice();
        long itemNumber = sellRequestDto.getItemNumber();
        if (exchangeDao.isExistHolding(id, itemCode) == false){
            Holdings holdings = Holdings.builder()
                    .avgPrice(0)
                    .id(id)
                    .itemCode(itemCode)
                    .itemNumber(0)
                    .build();
            exchangeDao.insertHoldings(holdings);
        }
        int userItemNumber = holdingsDao.getHoldingNumber(id,itemCode);
        if(userItemNumber < itemNumber) return false;
        HolderAge holderAge = HolderAge.builder()
                .ages(age)
                .itemCode(itemCode)
                .itemNumber(itemNumber)
                .build();
        holderAgeService.reduceData(holderAge);
        if(userItemNumber == itemNumber) exchangeDao.allSell(itemCode,id,price,itemNumber);
        else exchangeDao.sell(itemCode,id,price,itemNumber);
        return true;
    }
}
