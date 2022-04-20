package com.lagou.service.impl;

import com.lagou.dao.PromotionSpaceMapper;
import com.lagou.domain.PromotionSpace;
import com.lagou.service.PromotionspaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class PromotionspaceServiceImpl implements PromotionspaceService {

    @Autowired
    private PromotionSpaceMapper promotionSpaceMapper;

    /*
     * 获取所有广告位
     * */
    @Override
    public List<PromotionSpace> findAllPromotionSpace() {

        List<PromotionSpace> allPromotionSpace = promotionSpaceMapper.findAllPromotionSpace();

        return allPromotionSpace;
    }

    /*
     * 添加广告位
     * */
    @Override
    public void savePromotionSpace(PromotionSpace promotionSpace) {

        // 1.封装数据
        promotionSpace.setSpaceKey(UUID.randomUUID().toString());
        Date date = new Date();
        promotionSpace.setCreateTime(date);
        promotionSpace.setUpdateTime(date);
        promotionSpace.setIsDel(0);

        // 2.调用mapper方法
        promotionSpaceMapper.savePromotionSpace(promotionSpace);
    }

    /*
     * 根据id查询广告位信息
     * */
    @Override
    public PromotionSpace findPromotionSpaceById(int id) {

        PromotionSpace promotionSpaceById = promotionSpaceMapper.findPromotionSpaceById(id);
        return promotionSpaceById;
    }

    /*
     * 更新广告位
     * */
    @Override
    public void updatePromotionSpace(PromotionSpace promotionSpace) {
        // 封装数据
        promotionSpace.setUpdateTime(new Date());

        // 调用mapper
        promotionSpaceMapper.updatePromotionSpace(promotionSpace);
    }
}
