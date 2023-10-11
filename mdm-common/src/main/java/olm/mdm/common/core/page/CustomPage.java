package olm.mdm.common.core.page;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import olm.mdm.common.utils.StringUtils;

import java.util.List;

public class CustomPage<T> extends Page<T> {

    public CustomPage(long current, long size) {
        super();
        this.current = current;
        this.size = size;
    }

    /**
     * mainTableAlias: example: go.
     */
    public CustomPage(long current, long size, String sort, String mainTableAlias) {
        super();
        this.current = current;
        this.size = size;
        if (sort != null) {
            String[] sortList = sort.split(",");
            boolean isAsc = true;
            if (sortList.length > 1) {
                if (!"asc".equals(sortList[1])) {
                    isAsc = false;
                }
            }
            this.addOrder(new OrderItem(mainTableAlias + StringUtils.toUnderScoreCase(sortList[0]), isAsc));
        }
    }

    public CustomPage(long current, long size, List<OrderItem> items) {
        super();
        this.current = current + 1;
        this.size = size;
        this.addOrder(items);
    }

    public CustomPage(long current, long size, OrderItem item) {
        super();
        this.current = current + 1;
        this.size = size;
        if (item != null) {
            this.addOrder(item);
        }
    }

    public CustomPage(long current, long size, long total) {
        super();
        this.current = current + 1;
        this.size = size;
        this.total = total;
    }

    public CustomPage(long current, long size, boolean isSearchCount) {
        super();
        this.current = current + 1;
        this.size = size;
        this.total = 0;
        this.searchCount = isSearchCount;
    }

    public CustomPage(long current, long size, long total, boolean isSearchCount) {
        super();
        if (current > 1) {
            this.current = current + 1;
        }
        this.size = size;
        this.total = total;
        this.searchCount = isSearchCount;
    }
}
