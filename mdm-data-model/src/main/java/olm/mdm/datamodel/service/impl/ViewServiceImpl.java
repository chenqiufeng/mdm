package olm.mdm.datamodel.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import olm.mdm.common.utils.DateUtils;
import olm.mdm.datamodel.domain.entity.View;
import olm.mdm.datamodel.mapper.ViewMapper;
import olm.mdm.datamodel.service.IViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 视图Service业务层处理
 *
 * @author ruoyi
 * @date 2023-10-11
 */
@Service
public class ViewServiceImpl extends ServiceImpl<ViewMapper, View> implements IViewService {

    @Autowired
    private ViewMapper viewMapper;

    /**
     * 查询视图
     *
     * @param id 视图主键
     * @return 视图
     */
    @Override
    public View selectViewById(Long id) {
        return viewMapper.selectViewById(id);
    }

    /**
     * 查询视图列表
     *
     * @param view 视图
     * @return 视图
     */
    @Override
    public List<View> selectViewList(View view) {
        return viewMapper.selectViewList(view);
    }

    /**
     * 新增视图
     *
     * @param view 视图
     * @return 结果
     */
    @Override
    public int insertView(View view) {
                view.setCreateTime(DateUtils.getNowDate());
            return viewMapper.insertView(view);
    }

    /**
     * 修改视图
     *
     * @param view 视图
     * @return 结果
     */
    @Override
    public int updateView(View view) {
                view.setUpdateTime(DateUtils.getNowDate());
        return viewMapper.updateView(view);
    }

    /**
     * 批量删除视图
     *
     * @param ids 需要删除的视图主键
     * @return 结果
     */
    @Override
    public int deleteViewByIds(Long[] ids) {
        return viewMapper.deleteViewByIds(ids);
    }

    /**
     * 删除视图信息
     *
     * @param id 视图主键
     * @return 结果
     */
    @Override
    public int deleteViewById(Long id) {
        return viewMapper.deleteViewById(id);
    }
}