package com.lagou.service.impl;

import com.lagou.dao.ResourceCategoryMapper;
import com.lagou.domain.ResourceCategory;
import com.lagou.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ResourceCategoryServiceImpl implements ResourceCategoryService {

    @Autowired
    private ResourceCategoryMapper resourceCategoryMapper;

    @Override
    public List<ResourceCategory> findAllResourceCategory() {

        List<ResourceCategory> allResourceCategory = resourceCategoryMapper.findAllResourceCategory();

        return allResourceCategory;
    }

    @Override
    public void saveResourceCategory(ResourceCategory resourceCategory) {


        //封装数据
        Date date = new Date();
        resourceCategory.setCreatedTime(date);
        resourceCategory.setUpdatedTime(date);
        if (resourceCategory.getCreatedBy() == null || "".equals(resourceCategory.getCreatedBy())){
            resourceCategory.setCreatedBy("system");
            resourceCategory.setUpdatedBy("system");
        }
        resourceCategoryMapper.saveResourceCategory(resourceCategory);
    }

    @Override
    public void updateResourceCategory(ResourceCategory resourceCategory) {

        //封装数据
        resourceCategory.setUpdatedTime(new Date());
        if(resourceCategory.getUpdatedBy() == null || "".equals(resourceCategory.getUpdatedBy())){
            resourceCategory.setUpdatedBy("system");
        }
        resourceCategoryMapper.updateResourceCategory(resourceCategory);
    }

    @Override
    public void deleteResourceByCategory(Integer id) {

        //删除资源
        resourceCategoryMapper.deleteResourceByCategoryId(id);
        //删除资源分类
        resourceCategoryMapper.deleteResourceCategoryById(id);
    }
}
