package com.onikitich.mapper;

import com.onikitich.domain.Offer;
import com.onikitich.domain.Product;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface ProductMapper {

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "code", column = "code"),
            @Result(property = "name", column = "name"),
            @Result(property = "price", column = "price"),
            @Result(property = "offer", column = "offer_id", javaType = Offer.class,
                    one = @One(select = "com.onikitich.mapper.OfferMapper.getOfferById"))
    })
    @Select("select * from PRODUCT p where p.code = #{productCode}")
    Product getProductByCode(String productCode);
}
