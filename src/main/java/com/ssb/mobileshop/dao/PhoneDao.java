package com.ssb.mobileshop.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ssb.mobileshop.model.Phone;

public interface PhoneDao {
    List<Phone> phone()throws Exception;
    int add(Phone phone)throws Exception;
    int delete(int phone_id)throws Exception;
    Phone getDetails(int phone_id)throws Exception;
    int editPrice(float price,int id) throws SQLException;
    public int editByStock(int stock, int id) throws Exception;
    Set<String> getAllBrands()throws Exception;
    List<Phone> getByBrand(String brand)throws Exception;
    Map<String, Integer> getPrice(String modelname)throws Exception;
    int updateStock(int stock,String model)throws Exception;
    List<Phone> getByRam(int ram)throws Exception;
    List<Phone> getByPrice(float price)throws Exception;
    public List<Phone> getByRamAndBrand(String brand,int ram) throws Exception;
}
