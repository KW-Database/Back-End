package com.KWdatabase.teamProject.Service;

import com.KWdatabase.teamProject.Model.ItemCode;
import com.KWdatabase.teamProject.Model.ItemTimeCondition;
import com.KWdatabase.teamProject.Model.SearchItem;
import com.KWdatabase.teamProject.dao.ItemCodeDao;
import com.KWdatabase.teamProject.dao.ItemTimeConditionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchItemService {

    @Autowired
    private ItemCodeDao itemCodeDao;

    public List<SearchItem> searchItems(String itemName){
        List<ItemCode> itemCodeList = itemCodeDao.getItemCodeListByName(itemName);
        List<SearchItem> searchItems = new ArrayList<>();
        for(ItemCode itemCode : itemCodeList){
            SearchItem searchItem= SearchItem.builder()
                    .itemCode(itemCode.getItemCode())
                    .itemName(itemCode.getItemName()).build();
            searchItems.add(searchItem);
        }
        return searchItems;
    }
}
