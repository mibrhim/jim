/*
 * Copyright (c) 2020 By Mahmoud Ibrahim
 * This class is written as a contribution for the open source community under GPL license.
 *
 * Author: Mahmoud Ibrahim
 * upworks: https://www.upwork.com/o/profiles/users/~01c04883943f6add8f/
 * github: https://github.com/mhmod1990
 * .
 */

package shared.custom_payloads;

import org.mhmod.jim.Payload;

public class HelloMessage implements Payload {

    private String banner = "Hello World";


    public String get(){
        return banner;
    }


}
