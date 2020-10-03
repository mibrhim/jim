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

/**
 * Created by mahmoud on 11/5/18.
 */
public class IMessageBuilder {

    private static final String TAG = "[IMessageBuilder]";
    private static final int NULL_INT = -10000;
    // internal message to be built
    private IMessage message;

    // the required parameter of the internal message
    private int mReceiver = NULL_INT;

    /**
     * Constructor
     */
    public IMessageBuilder(){
        message = new IMessage();
    }

    /**
     * add the receiver module to the internal message
     * @param receiver int the module id
     * @return
     */
    public IMessageBuilder receiver(int receiver){
        mReceiver = receiver;
        return this;
    }

    /**
     * add the sender module to the internal message
     * @param sender int the id of the sender module
     * @return
     */
    public IMessageBuilder sender(int sender){
        message.setSender(sender);
        return this;
    }

    /**
     * set the message type which is sent between modules
     * @param messageType int which represent the unique id of the internal message
     * @return
     */
    public IMessageBuilder messageType(int messageType){
        message.setMessageType(messageType);
        return this;
    }

    /**
     * add the payload to the internal message
     * @param p
     * @return
     */
    public IMessageBuilder payload(Payload p){
        message.setPayload(p);
        return this;
    }

    /**
     * build the internal message and output it to the user
     * @return
     */
    public IMessage build(){
        if(mReceiver == NULL_INT)
            JimLog.e(TAG,"building internal message without receiver module will cause this message to fail");
        message.setReceiver(mReceiver);

        return message;
    }

}
