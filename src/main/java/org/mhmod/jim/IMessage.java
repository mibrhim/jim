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

/**
 * Created by mahmoud on 10/5/18.
 */
public class IMessage{

    private static long count = 0;
    private static final int NULL_INT = -10000;

    /**
     *  Internal Message properties that show the type and direction of the message
     */
    private long id;
    private int mReceiver = NULL_INT;
    private int sender = NULL_INT;
    private int mMessageType = NULL_INT;

    /**
     * internal message payload which is any class implements Payload interface
     */
    private Payload mPayload;

    /**
     * IMessage Constructor
     * auto-increment the message id
     */
    public IMessage(){
        id = count;
        count++;
    }

    /**
     * get the internal message unique id
     * @return long the message id
     */
    public long getId(){
        return id;
    }

    /**
     * get the modules which should receive this message
     * @return int the id of the module which will receive this message
     */
    public int getReceiver() {
        return mReceiver;
    }

    /**
     * set the receiver of the this internal message
     * @param receiver the id of the module which recieves the internal message
     */
    public void setReceiver(int receiver) {
        this.mReceiver = receiver;
    }

    /**
     * get the module which sends the internal message
     * @return int the module which sends the internal message
     */
    public int getSender() {
        return sender;
    }

    /**
     * set the module which sends the internal message
     * @param sender
     */
    public void setSender(int sender) {
        this.sender = sender;
    }

    /**
     * get the internal Message Type
     * @return int the message type
     */
    public int getMessageType() {
        return mMessageType;
    }

    /**
     * set the message type
     * @param messageType the message type
     */
    public void setMessageType(int messageType) {
        this.mMessageType = messageType;
    }

    /**
     * set the payload of the internal message which is any class implements the Payload interface
     * @param p
     */
    public void setPayload(Payload p){
        this.mPayload = p;
    }

    /**
     * get the internal message payload which is any message that implements the Payload interface
     * @return
     */
    public Payload getPayload(){
        return this.mPayload;
    }


    @Override
    public String toString() {
        return "IMessage{" +
                "rec=" + mReceiver +
                ", sender=" + sender +
                ", messageID=" + mMessageType +
                ", payload=" + mPayload +
                ", id=" + id +
                '}';
    }
}
