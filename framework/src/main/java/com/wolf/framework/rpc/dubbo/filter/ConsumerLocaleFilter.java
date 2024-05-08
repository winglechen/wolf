package com.wolf.framework.rpc.dubbo.filter;

import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import com.wolf.framework.util.LocaleUtil;


/**
 * com.wolf.framework.rpc.dubbo.filter
 *
 * @author Wingle
 * @since 2020/2/24 6:40 下午
 **/
@Activate(group = CommonConstants.CONSUMER)
public class ConsumerLocaleFilter implements Filter {
    public static final String LOCALE_KEY = "lang";

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        String locale = LocaleUtil.getLocaleString();
        if (locale != null) {
            invocation.setAttachment(LOCALE_KEY, locale);
        }

        return invoker.invoke(invocation);
    }
}
