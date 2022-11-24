package com.KWdatabase.teamProject.Service;

import com.KWdatabase.teamProject.Model.Holdings;
import com.KWdatabase.teamProject.Model.ItemTimeCondition;
import com.KWdatabase.teamProject.dao.HoldingsDao;
import com.KWdatabase.teamProject.dao.ItemCodeDao;
import com.KWdatabase.teamProject.dao.ItemTimeConditionDao;
import com.KWdatabase.teamProject.dao.UserDao;
import com.KWdatabase.teamProject.dto.MyWalletResponseDto;
import com.KWdatabase.teamProject.dto.RatePerCompany;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MyWalletService {
    private final HoldingsDao holdingsDao;
    private final ItemTimeConditionDao itemTimeConditionDao;
    private final UserDao userDao;
    private final ItemCodeDao itemCodeDao;

    public MyWalletResponseDto getMyWallet(String id){
        List<Holdings> holdings = holdingsDao.getHoldings(id);
        long totalHoldings=0;
        long totalAppraisal=0;
        long totalPurchase=0;
        float totalRate;
        MyWalletResponseDto myWalletResponseDto =new MyWalletResponseDto();
        List<RatePerCompany> list = new ArrayList<RatePerCompany>();
        for(Holdings h: holdings){
            String itemCode = h.getItemCode();
            String itemName = itemCodeDao.getItemCode(itemCode).getItemName();
            int itemNumber=h.getItemNumber();
            ItemTimeCondition itemTimeCondition = itemTimeConditionDao.getNewCondition(itemCode);
            long purchase = (long) (h.getAvgPrice() * itemNumber);
            long appraisal = (long) (itemTimeCondition.getExecutionPrice() * itemNumber);
            float rate = (appraisal - purchase)/ (float)purchase * 100;
            RatePerCompany ratePerCompany = RatePerCompany.builder()
                    .totalRate(rate)
                    .itemCode(itemCode)
                    .itemName(itemName)
                    .appraisal(appraisal)
                    .purchase(purchase)
                    .itemNumber(itemNumber)
                    .build();

            list.add(ratePerCompany);
            totalAppraisal += appraisal;
            totalPurchase += purchase;
        }
        long cash = userDao.findUser(id).getCash();
        totalHoldings = totalAppraisal + cash;
        totalRate = (totalAppraisal - totalPurchase) / (float)totalPurchase * 100;

        myWalletResponseDto = MyWalletResponseDto.builder()
                .cash(cash)
                .RatePerCompany(list)
                .totalAppraisal(totalAppraisal)
                .totalHoldings(totalHoldings)
                .totalPurchase(totalPurchase)
                .totalRate(totalRate)
                .build();

        return myWalletResponseDto;
    }
}
