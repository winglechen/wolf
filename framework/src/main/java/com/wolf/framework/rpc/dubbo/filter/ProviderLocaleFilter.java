package com.wolf.framework.rpc.dubbo.filter;

import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import com.wolf.common.util.lang.StringUtil;
import com.wolf.framework.util.LocaleUtil;

/**
 * com.wolf.framework.rpc.dubbo.filter
 *
 * @author Wingle
 * @since 2020/2/24 6:40 下午
 **/
@Activate(group = CommonConstants.PROVIDER)
public class ProviderLocaleFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        String lang = invocation.getAttachment(ConsumerLocaleFilter.LOCALE_KEY);
        if (StringUtil.notEmpty(lang)) {
            LocaleUtil.setLocaleString(lang);
        }

        return invoker.invoke(invocation);
    }
}
