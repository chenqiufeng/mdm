package olm.mdm.common.utils.bean;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yhmi
 */
public class ModelDataMapper {
    private static ModelMapper modelMapper = new ModelMapper();

    static {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public static <D, T> D map(T entity, Class<D> outClass) {
        return modelMapper.map(entity, outClass);
    }

    public static <S, D> D map(S source, D destination) {
        modelMapper.getConfiguration().setPropertyCondition(
            new SkipOrNullPropsCondition<>(null, new ArrayList<>()));
        modelMapper.map(source, destination);
        return destination;
    }

    public static <S, D> D map(S entity, Class<D> outClass, List<String> skipProperties) {
        modelMapper.getConfiguration().setPropertyCondition(
            new SkipOrNullPropsCondition<>(null, skipProperties));
        return modelMapper.map(entity, outClass);
    }

    public static <S, D> D map(S source, D destination, List<String> skipProperties, List<String> nullProps) {
        modelMapper.getConfiguration().setPropertyCondition(
            new SkipOrNullPropsCondition<>(nullProps, skipProperties));
        modelMapper.map(source, destination);
        return destination;
    }

    public static <S, D> D map(S source, D destination, List<String> nullProps) {
        modelMapper.getConfiguration().setPropertyCondition(
            new SkipOrNullPropsCondition<>(nullProps, new ArrayList<>()));
        modelMapper.map(source, destination);
        return destination;
    }

    public static <D, T> List<D> mapAll(Collection<T> entityList, Class<D> outClass) {
        return entityList.stream()
            .map(entity -> map(entity, outClass))
            .collect(Collectors.toList());
    }

    public static <D, T> List<D> mapAll(Collection<T> entityList, Class<D> outClass, List<String> skipProperties) {
        return entityList.stream()
            .map(entity -> map(entity, outClass, skipProperties))
            .collect(Collectors.toList());
    }

    public static <D, T> List<D> mapAll(Page<T> pageData, Class<D> outClass) {
        return pageData.getRecords().stream()
                .map(entity -> map(entity, outClass))
                .collect(Collectors.toList());
    }

    public static <D, T> Page<D> mapPage(Page<T> pageData, Class<D> outClass) {
        List<D> records = pageData.getRecords().stream()
            .map(entity -> map(entity, outClass))
            .collect(Collectors.toList());

        Page<D> page = new Page<>();
        page.setRecords(records);
        page.setTotal(pageData.getTotal());
        page.setSize(pageData.getSize());
        page.setCurrent(pageData.getCurrent());
        page.setOrders(pageData.getOrders());

        return page;
    }
}
