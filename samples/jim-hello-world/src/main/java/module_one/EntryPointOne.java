/*
 * Copyright (c) 2020 By Mahmoud Ibrahim
 * This class is written as a contribution for the open source community under GPL license.
 *
 * Author: Mahmoud Ibrahim
 * upworks: https://www.upwork.com/o/profiles/users/~01c04883943f6add8f/
 * github: https://github.com/mhmod1990
 * .
 */

package module_one;

import org.mhmod.jim.IMessage;
import org.mhmod.jim.IMessageBuilder;
import org.mhmod.jim.JimAgent;
import org.mhmod.jim.JimPlatform;
import shared.Types;
import shared.custom_payloads.HelloMessage;

import java.util.concurrent.LinkedBlockingQueue;

public class EntryPointOne extends JimAgent {

    public EntryPointOne(LinkedBlockingQueue messageQueue, int module) {
        super(messageQueue, module);
    }

    public void onReceive(IMessage iMessage) throws Throwable {
        HelloMessage payload = (HelloMessage) iMessage.getPayload();

        System.out.println("Recieved message "+ payload.get() +" in Module one from "+iMessage.getSender());

        // build the new internal message response to the previous message
        IMessage im = new IMessageBuilder()
                .receiver(Types.MODULE_TWO)
                .sender(Types.MODULES_ONE)
                .messageType(Types.NEW_MESSAGE_NOTIFY_REPLY)
                .payload(payload)
                .build();

        // send the response
        JimPlatform.send(im);
    }

}
