package olm.mdm.web.controller.job;

import olm.mdm.common.annotation.Anonymous;
import olm.mdm.framework.core.biz.AdminBiz;
import olm.mdm.framework.core.biz.model.HandleCallbackParam;
import olm.mdm.framework.core.biz.model.HandleProcessCallbackParam;
import olm.mdm.framework.core.biz.model.RegistryParam;
import olm.mdm.framework.core.biz.model.ReturnT;
import olm.mdm.framework.core.config.JobAdminConfig;
import olm.mdm.framework.util.JacksonUtil;
import olm.mdm.framework.util.JobRemotingUtil;
import olm.mdm.job.service.IJobRegistryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by xuxueli on 17/5/10.
 */
@Anonymous
@RestController
@RequestMapping("/api")
public class JobApiController {
    @Autowired
    private IJobRegistryService jobRegistryService;
    @Resource
    private AdminBiz adminBiz;

    /**
     * callback
     *
     * @param data
     * @return
     */
    @PostMapping("/callback")
    public ReturnT<String> callback(HttpServletRequest request, @RequestBody(required = false) String data) {
        // valid
        if (JobAdminConfig.getAdminConfig().getAccessToken()!=null
                && JobAdminConfig.getAdminConfig().getAccessToken().trim().length()>0
                && !JobAdminConfig.getAdminConfig().getAccessToken().equals(request.getHeader(JobRemotingUtil.XXL_RPC_ACCESS_TOKEN))) {
            return new ReturnT<>(ReturnT.FAIL_CODE, "The access token is wrong.");
        }

        // param
        List<HandleCallbackParam> callbackParamList = null;
        try {
            callbackParamList = JacksonUtil.readValue(data, List.class, HandleCallbackParam.class);
        } catch (Exception e) { }
        if (callbackParamList==null || callbackParamList.size()==0) {
            return new ReturnT<>(ReturnT.FAIL_CODE, "The request data invalid.");
        }

        // invoke
        return adminBiz.callback(callbackParamList);
    }

    /**
     * callback
     *
     * @param data
     * @return
     */
    @PostMapping("/processCallback")
    public ReturnT<String> processCallback(HttpServletRequest request, @RequestBody(required = false) String data) {
        // valid
        if (JobAdminConfig.getAdminConfig().getAccessToken()!=null
                && JobAdminConfig.getAdminConfig().getAccessToken().trim().length()>0
                && !JobAdminConfig.getAdminConfig().getAccessToken().equals(request.getHeader(JobRemotingUtil.XXL_RPC_ACCESS_TOKEN))) {
            return new ReturnT<>(ReturnT.FAIL_CODE, "The access token is wrong.");
        }

        // param
        List<HandleProcessCallbackParam> callbackParamList = null;
        try {
            callbackParamList = JacksonUtil.readValue(data, List.class, HandleProcessCallbackParam.class);
        } catch (Exception e) { }
        if (callbackParamList==null || callbackParamList.size()==0) {
            return new ReturnT<>(ReturnT.FAIL_CODE, "The request data invalid.");
        }

        // invoke
        return adminBiz.processCallback(callbackParamList);
    }



    /**
     * registry
     *
     * @param data
     * @return
     */
    @PostMapping("/registry")
    public ReturnT<String> registry(HttpServletRequest request, @RequestBody(required = false) String data) {
        // valid
        if (JobAdminConfig.getAdminConfig().getAccessToken()!=null
                && JobAdminConfig.getAdminConfig().getAccessToken().trim().length()>0
                && !JobAdminConfig.getAdminConfig().getAccessToken().equals(request.getHeader(JobRemotingUtil.XXL_RPC_ACCESS_TOKEN))) {
            return new ReturnT<String>(ReturnT.FAIL_CODE, "The access token is wrong.");
        }

        // param
        RegistryParam registryParam = null;
        try {
            registryParam = JacksonUtil.readValue(data, RegistryParam.class);
        } catch (Exception e) {}
        if (registryParam == null) {
            return new ReturnT<String>(ReturnT.FAIL_CODE, "The request data invalid.");
        }

        // invoke
        return adminBiz.registry(registryParam);
    }

    /**
     * registry remove
     *
     * @param data
     * @return
     */
    @PostMapping("/registryRemove")
    public ReturnT<String> registryRemove(HttpServletRequest request, @RequestBody(required = false) String data) {
        // valid
        if (JobAdminConfig.getAdminConfig().getAccessToken()!=null
                && JobAdminConfig.getAdminConfig().getAccessToken().trim().length()>0
                && !JobAdminConfig.getAdminConfig().getAccessToken().equals(request.getHeader(JobRemotingUtil.XXL_RPC_ACCESS_TOKEN))) {
            return new ReturnT<>(ReturnT.FAIL_CODE, "The access token is wrong.");
        }

        // param
        RegistryParam registryParam = null;
        try {
            registryParam = JacksonUtil.readValue(data, RegistryParam.class);
        } catch (Exception e) {}
        if (registryParam == null) {
            return new ReturnT<>(ReturnT.FAIL_CODE, "The request data invalid.");
        }

        // invoke
        return adminBiz.registryRemove(registryParam);
    }


}
