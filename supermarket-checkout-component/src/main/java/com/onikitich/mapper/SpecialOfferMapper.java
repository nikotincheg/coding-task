package com.onikitich.mapper;

import com.onikitich.domain.SpecialOffer;
import com.onikitich.domain.SpecialOffer;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface SpecialOfferMapper {

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "specialPrice", column = "special_price"),
            @Result(property = "itemsAmount", column = "items_amount")
    })
    @Select("select o.* from SPECIAL_OFFER o where o.id = #{offerId}")
    SpecialOffer getSpecialOfferById(Integer offerId);
}
