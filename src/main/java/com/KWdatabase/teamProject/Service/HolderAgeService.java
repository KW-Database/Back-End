package com.KWdatabase.teamProject.Service;

import com.KWdatabase.teamProject.Model.HolderAge;
import com.KWdatabase.teamProject.Model.Holdings;
import com.KWdatabase.teamProject.Model.User;
import com.KWdatabase.teamProject.dao.HolderAgeDao;
import com.KWdatabase.teamProject.dao.HoldingsDao;
import com.KWdatabase.teamProject.dao.UserDao;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HolderAgeService {

    @Autowired
    private HolderAgeDao holderAgeDao;

    @Autowired
    private HoldingsDao holdingsDao;

    @Autowired
    private UserDao userDao;

    public List<HolderAge> getData(String itemCode){
        List<HolderAge> list = holderAgeDao.getHolderAgeList(itemCode);
        long age0 = list.get(0).getItemNumber();
        long age10 = list.get(1).getItemNumber();
        long age80 =list.get(8).getItemNumber();
        long age90 = list.get(9).getItemNumber();
        list.get(1).setItemNumber(age0+age10);
        list.get(8).setItemNumber(age80+age90);
        list.remove(9);
        list.remove(0);
        return list;
    }

    public void insert(HolderAge holderAge){
        holderAgeDao.insertHolderAge(holderAge);
    }

    //처음 한번만 사용
    public void updateAll(){
        List<User> userList= userDao.getUserList();
        for (User user : userList){
            List<Holdings> holdingsList= holdingsDao.getHoldings(user.getId());
            for(Holdings holdings : holdingsList){
                int age = user.getAge();
                age = age - age%10;
                HolderAge holderAge= HolderAge.builder()
                        .itemCode(holdings.getItemCode())
                        .itemNumber(holdings.getItemNumber())
                        .ages(age)
                        .build();
                holderAgeDao.updateHolderAge(holderAge);
            }
        }

    }

    public void updateData(HolderAge holderAge){
        List<HolderAge> holderAgeList = holderAgeDao.getHolderAgeList(holderAge.getItemCode());
        if(holderAgeList.isEmpty()){
            for(int a =0; a<10; a++){
                HolderAge holderAge1= HolderAge.builder()
                        .ages(a*10)
                        .itemCode(holderAge.getItemCode())
                        .itemNumber(0)
                        .build();
                System.out.println(holderAge1.getItemNumber());
                holderAgeDao.insertHolderAge(holderAge1);

            }

        }

        int age = holderAge.getAges();
        age = age- age%10;

        HolderAge holderAge1 = holderAgeDao.getHolderAgeByAges(holderAge.getItemCode(), age);

        System.out.println(holderAge1.getItemNumber());
        System.out.println(holderAge.getItemNumber());

        HolderAge holderAge2 = HolderAge.builder()
                .itemCode(holderAge.getItemCode())
                .itemNumber(holderAge1.getItemNumber()+holderAge.getItemNumber())
                .ages(age)
                .build();

        holderAgeDao.updateHolderAge(holderAge2);
        holderAgeList= holderAgeDao.getHolderAgeList(holderAge.getItemCode());
        for(HolderAge h : holderAgeList){

            System.out.println(h.getItemCode()+ " : " + h.getAges());
        }
    }

    public void reduceData(HolderAge holderAge){
        List<HolderAge> holderAgeList = holderAgeDao.getHolderAgeList(holderAge.getItemCode());

        int age = holderAge.getAges();
        age = age- age%10;

        HolderAge holderAge1 = holderAgeDao.getHolderAgeByAges(holderAge.getItemCode(), age);

        HolderAge holderAge2 = HolderAge.builder()
                .itemCode(holderAge.getItemCode())
                .itemNumber(holderAge1.getItemNumber()-holderAge.getItemNumber())
                .ages(age)
                .build();

        holderAgeDao.updateHolderAge(holderAge2);
    }

}
