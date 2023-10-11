package olm.mdm.common.utils.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import org.modelmapper.AbstractCondition;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;



/**
 * @author yhmi
 */
public abstract class DTO {
    @Transient
    @TableField(exist = false)
    //@ApiModelProperty(hidden = true)
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    protected List<String> nullProps = null;

    public <T> T createModel(Class<T> clazz) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper.map(this, clazz);
    }

    public void updateModel(Object model) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.getConfiguration().setPropertyCondition(
            new SkipOrNullPropsCondition<>(this.nullProps, new ArrayList<>()));
        modelMapper.map(this, model);
    }

    public void updateModel(Object model, List<String> skipProps) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.getConfiguration().setPropertyCondition(
            new SkipOrNullPropsCondition<>(this.nullProps, skipProps));
        modelMapper.map(this, model);
    }
}

class SkipOrNullPropsCondition<S, D> extends AbstractCondition<S, D> {
    private List<String> skipProps;
    private List<String> nullProps;

    SkipOrNullPropsCondition() {
    }

    SkipOrNullPropsCondition(List<String> nullProps, List<String> skipProps) {
        this.skipProps = skipProps;
        this.nullProps = nullProps;
    }

    @Override
    public boolean applies(org.modelmapper.spi.MappingContext<S, D> context) {
        String field = context.getMapping().getDestinationProperties().iterator().next().getName();
        if (!skipProps.isEmpty() && skipProps.contains(field)) {
            boolean notContains = !skipProps.contains(field);
            boolean sourceIsNotNull = context.getSource() != null;
            return notContains && sourceIsNotNull;
        }
        return context.getSource() != null || (nullProps != null && nullProps.contains(field));
    }
}


