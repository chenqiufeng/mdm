package olm.mdm.datamodel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import olm.mdm.datamodel.domain.entity.View;

import java.util.List;

/**
 * 视图Mapper接口
 *
 * @author ruoyi
 * @date 2023-10-11
 */
public interface ViewMapper extends BaseMapper<View> {
    /**
     * 查询视图
     *
     * @param id 视图主键
     * @return 视图
     */
    public View selectViewById(Long id);

    /**
     * 查询视图列表
     *
     * @param view 视图
     * @return 视图集合
     */
    public List<View> selectViewList(View view);

    /**
     * 新增视图
     *
     * @param view 视图
     * @return 结果
     */
    public int insertView(View view);

    /**
     * 修改视图
     *
     * @param view 视图
     * @return 结果
     */
    public int updateView(View view);

    /**
     * 删除视图
     *
     * @param id 视图主键
     * @return 结果
     */
    public int deleteViewById(Long id);

    /**
     * 批量删除视图
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteViewByIds(Long[] ids);
}