/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.wolf.framework.rpc.dubbo.filter;

import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.common.utils.NetUtils;
import org.apache.dubbo.rpc.*;
import com.wolf.common.lang.exception.BusinessException;
import com.wolf.framework.rpc.exception.WolfExceptionCodec;

import java.util.Map;

import static org.apache.dubbo.common.constants.CommonConstants.*;

/**
 * ConsumerContextFilter set current RpcContext with invoker,invocation, local host, remote host and port
 * for consumer invoker.It does it to make the requires info available to execution thread's RpcContext.
 *
 * @see org.apache.dubbo.rpc.Filter
 */
@Activate(group = CONSUMER,  order = -9900)
public class WolfExceptionHandler extends ListenableFilter {

    public WolfExceptionHandler() {
        super.listener = new WolfExceptionListener();
    }

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        return invoker.invoke(invocation);
    }

    static class WolfExceptionListener implements Listener {
        @Override
        public void onResponse(Result appResponse, Invoker<?> invoker, Invocation invocation) {
            Map<String, String> attachments = appResponse.getAttachments();
            System.out.println("attachments: " + attachments);

            BusinessException e = WolfExceptionCodec.decode(attachments);
            if (null == e ) {
                return;
            }

            WolfExceptionCodec.clearExceptionKeys(attachments);
            appResponse.setException(e);
        }

        @Override
        public void onError(Throwable t, Invoker<?> invoker, Invocation invocation) {

        }
    }
}
