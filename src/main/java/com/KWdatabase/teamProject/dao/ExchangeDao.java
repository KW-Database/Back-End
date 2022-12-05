package com.KWdatabase.teamProject.dao;

import com.KWdatabase.teamProject.Model.Holdings;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ExchangeDao {
    public void buy(String itemCode, String id, float price, long itemNumber);
    public void insertHoldings(@Param("h") Holdings holdings);
    public void sell(String itemCode, String id, float price, long itemNumber);
    public boolean isExistHolding(String id, String itemCode);
}
