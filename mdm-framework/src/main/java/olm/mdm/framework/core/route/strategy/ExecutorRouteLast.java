package olm.mdm.framework.core.route.strategy;


import olm.mdm.framework.core.biz.model.ReturnT;
import olm.mdm.framework.core.biz.model.TriggerParam;
import olm.mdm.framework.core.route.ExecutorRouter;

import java.util.List;

/**
 * Created by xuxueli on 17/3/10.
 */
public class ExecutorRouteLast extends ExecutorRouter {

    @Override
    public ReturnT<String> route(TriggerParam triggerParam, List<String> addressList) {
        return new ReturnT<String>(addressList.get(addressList.size()-1));
    }

}
