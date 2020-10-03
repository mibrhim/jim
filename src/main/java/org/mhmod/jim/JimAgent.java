/*
 * Copyright (c) 2020 By Mahmoud Ibrahim
 * This class is written as a contribution for the open source community under GPL license.
 *
 * Author: Mahmoud Ibrahim
 * upworks: https://www.upwork.com/o/profiles/users/~01c04883943f6add8f/
 * github: https://github.com/mhmod1990
 * .
 */

package org.mhmod.jim;

import org.mhmod.jim.logger.JimLog;

import java.sql.SQLException;
import java.util.concurrent.LinkedBlockingQueue;

public abstract class JimAgent implements Runnable {

    private static final String TAG = "[JimAgent]";
    private final String agentName;
    private LinkedBlockingQueue<IMessage> mMessages;
    int mModule;
    private boolean isRunning = true;

    /**
     * the constructor of the JimAgent and its subclasses
     * @param messageQueue the message queue which the agent will receive the message on it
     * @param module  int  the module id which this agent represents
     */
    public JimAgent(LinkedBlockingQueue messageQueue, int module) {
        agentName = this.getClass().getName();
        mMessages = messageQueue;
        mModule = module;
        JimLog.d(TAG, "Running thread " + agentName);
    }

    /**
     * the entry function for this agent thread
     */
    public void run() {
        while (isRunning) {

            //if the messages is empty lock the loop untill the next notify
            synchronized (mMessages) {
                if (mMessages.isEmpty()) {
                    try {
                        mMessages.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            //closed signal received which released the lock
            if (mMessages.isEmpty()) {
                JimLog.w(TAG, "messages is empty " + mMessages.size());
                continue;
            }

            try {
                IMessage iMessage = mMessages.peek();

                if (mModule != iMessage.getReceiver()) {
                    JimLog.d(TAG, "this message not related to the " + mModule);
                    continue;
                }

                onReceive(mMessages.poll());
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }

        JimLog.w(TAG, agentName + " is terminated");
    }

    /**
     * this function should be implemented by the agent to receive the internal message on it.
     * @param message
     * @throws Throwable
     */
    public abstract void onReceive(IMessage message) throws Throwable;

    /**
     * close and terminate this agent thread
     */
    public void close() {
        isRunning = false;
        JimLog.d(TAG, agentName + " close signal received");
    }
}
