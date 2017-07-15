package com.onikitich.mapper;

import com.onikitich.domain.SpecialOffer;
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
            @Result(property = "specialOffer", column = "special_offer_id", javaType = SpecialOffer.class,
                    one = @One(select = "com.onikitich.mapper.SpecialOfferMapper.getSpecialOfferById"))
    })
    @Select("select * from PRODUCT p where p.code = #{productCode}")
    Product getProductByCode(String productCode);
}
