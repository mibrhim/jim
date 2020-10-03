/*
 * Copyright (c) 2020 By Mahmoud Ibrahim
 * This class is written as a contribution for the open source community under GPL license.
 *
 * Author: Mahmoud Ibrahim
 * upworks: https://www.upwork.com/o/profiles/users/~01c04883943f6add8f/
 * github: https://github.com/mhmod1990
 * .
 */

import module_one.EntryPointOne;
import module_two.EntryPointTwo;
import org.mhmod.jim.IMessage;
import org.mhmod.jim.IMessageBuilder;
import org.mhmod.jim.JimPlatform;
import shared.custom_payloads.HelloMessage;
import shared.Types;

public class App {

    public static void main(String[] args){
        try {
            JimPlatform.register(Types.MODULES_ONE, EntryPointOne.class);
            JimPlatform.register(Types.MODULE_TWO, EntryPointTwo.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        HelloMessage payload = new HelloMessage();

        // build the message
        IMessage im = new IMessageBuilder()
                .receiver(Types.MODULES_ONE)
                .sender(Types.MAIN_MODULE)
                .messageType(Types.NEW_MESSAGE_NOTIFY)
                .payload(payload)
                .build();

        // send the message
        JimPlatform.send(im);


        // the main thread of the application will be busy doing another things
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }



}
