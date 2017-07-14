package com.onikitich.mapper;

import com.onikitich.domain.Offer;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface OfferMapper {

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "specialPrice", column = "special_price"),
            @Result(property = "itemsAmount", column = "items_amount")
    })
    @Select("select o.* from OFFER o where o.id = #{offerId}")
    Offer getOfferById(Integer offerId);
}
