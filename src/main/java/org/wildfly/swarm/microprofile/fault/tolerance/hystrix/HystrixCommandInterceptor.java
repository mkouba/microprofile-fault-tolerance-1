/*
 * Copyright 2017 Red Hat, Inc, and individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wildfly.swarm.microprofile.fault.tolerance.hystrix;

import java.util.concurrent.Future;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;


/**
 * @author Antoine Sabot-Durand
 */

@Interceptor
@HystrixCommand
@Priority(Interceptor.Priority.LIBRARY_BEFORE)
public class HystrixCommandInterceptor {


    @Inject
    DefaultCommand command;


    @AroundInvoke
    public Object timeMethod(InvocationContext ic) throws Exception {
        System.out.println("Intercepted");
        command.setToRun(() -> {
            Object res = null;
            try {
                res = ic.proceed();
            } catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                return res;
            }
        });
        return command.queue();
    }
}