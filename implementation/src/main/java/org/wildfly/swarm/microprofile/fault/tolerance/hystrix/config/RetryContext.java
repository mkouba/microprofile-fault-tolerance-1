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
package org.wildfly.swarm.microprofile.fault.tolerance.hystrix.config;

import java.time.temporal.ChronoUnit;

public class RetryContext {

    private final RetryConfig config;

    private int maxExecNumber = 1;

    private final Long start;

    public RetryContext(RetryConfig config) {
        this.config = config;
        this.start =  System.nanoTime();
        this.maxExecNumber = config.getMaxExecNumber();
    }

    public RetryConfig getConfig() {
        return config;
    }

    public void doRetry() {
        maxExecNumber--;
    }

    public boolean shouldRetry() {
        return maxExecNumber > 0;
    }

    public void incMaxNumberExec() {
        maxExecNumber++;
    }

    public Long getStart() {
        return start;
    }

    public long getMaxDuration() {
        return config.getMaxDuration();
    }

    public long getDelay() {
        return config.getDelay();
    }

    public Class<?>[] getAbortOn() {
        return config.getAbortOn();
    }

    public Class<?>[] getRetryOn() {
        return config.getRetryOn();
    }

    public Long getJitter() {
        return config.getJitter();
    }

    public ChronoUnit getJitterDelayUnit() {
        return config.getJitterDelayUnit();
    }

}
