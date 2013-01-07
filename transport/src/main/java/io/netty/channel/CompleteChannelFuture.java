/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package io.netty.channel;

import java.util.concurrent.TimeUnit;

/**
 * A skeletal {@link ChannelFuture} implementation which represents a
 * {@link ChannelFuture} which has been completed already.
 */
abstract class CompleteChannelFuture implements ChannelFuture {

    private final Channel channel;

    /**
     * Creates a new instance.
     *
     * @param channel the {@link Channel} associated with this future
     */
    protected CompleteChannelFuture(Channel channel) {
        if (channel == null) {
            throw new NullPointerException("channel");
        }
        this.channel = channel;
    }

    @Override
    public ChannelFuture addListener(final ChannelFutureListener listener) {
        if (listener == null) {
            throw new NullPointerException("listener");
        }
        DefaultChannelPromise.notifyListener(this, listener);
        return this;
    }

    @Override
    public ChannelFuture addListeners(ChannelFutureListener... listeners) {
        if (listeners == null) {
            throw new NullPointerException("listeners");
        }
        for (ChannelFutureListener l: listeners) {
            if (l == null) {
                break;
            }
            DefaultChannelPromise.notifyListener(this, l);
        }
        return this;
    }

    @Override
    public ChannelFuture removeListener(ChannelFutureListener listener) {
        // NOOP
        return this;
    }

    @Override
    public ChannelFuture removeListeners(ChannelFutureListener... listeners) {
        // NOOP
        return this;
    }

    @Override
    public ChannelFuture await() throws InterruptedException {
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        return this;
    }

    @Override
    public boolean await(long timeout, TimeUnit unit) throws InterruptedException {
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        return true;
    }

    @Override
    public boolean await(long timeoutMillis) throws InterruptedException {
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        return true;
    }

    @Override
    public ChannelFuture awaitUninterruptibly() {
        return this;
    }

    @Override
    public boolean awaitUninterruptibly(long timeout, TimeUnit unit) {
        return true;
    }

    @Override
    public boolean awaitUninterruptibly(long timeoutMillis) {
        return true;
    }

    @Override
    public Channel channel() {
        return channel;
    }

    @Override
    public boolean isDone() {
        return true;
    }
}
