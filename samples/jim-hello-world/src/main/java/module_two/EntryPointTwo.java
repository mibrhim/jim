/*
 * Copyright (c) 2020 By Mahmoud Ibrahim
 * This class is written as a contribution for the open source community under GPL license.
 *
 * Author: Mahmoud Ibrahim
 * upworks: https://www.upwork.com/o/profiles/users/~01c04883943f6add8f/
 * github: https://github.com/mhmod1990
 * .
 */

package module_two;

import org.mhmod.jim.IMessage;
import org.mhmod.jim.IMessageBuilder;
import org.mhmod.jim.JimAgent;
import shared.custom_payloads.HelloMessage;
import org.mhmod.jim.JimPlatform;
import shared.Types;


import java.util.concurrent.LinkedBlockingQueue;

public class EntryPointTwo extends JimAgent {
    public EntryPointTwo(LinkedBlockingQueue messageQueue, int module) {
        super(messageQueue, module);
    }

    public void onReceive(IMessage iMessage) throws Throwable {
        HelloMessage payload = (HelloMessage) iMessage.getPayload();

        System.out.println("Recieved message "+ payload.get() +" in Module two from "+iMessage.getSender());
    }
}
