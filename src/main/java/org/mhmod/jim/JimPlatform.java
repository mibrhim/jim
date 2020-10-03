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
import java.lang.reflect.InvocationTargetException;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;


/**
 * Created by mahmoud on 10/5/18.
 */
public class JimPlatform {

    private static final String TAG = "[org.mhmod.jim.Platform]";
    private static ExecutorService mExecutor = Executors.newWorkStealingPool();
    private static Dictionary<Class, JimAgent> services = new Hashtable<>();
    private static Dictionary<Integer, LinkedBlockingQueue<IMessage>> messages = new Hashtable<>();

    /**
     * register a new module to the JIM Platform using its entry point class
     * @param module integer which represents this module
     * @param agent  the class which is the entry point to the module, this class must extends the JimAgent class.
     * @throws Exception if this module couldn't be registered to the platform
     */
    public static void register(Integer module, Class agent) throws Exception {

        // confirm the agent class is a JimAgent class
        if(JimAgent.class.getTypeName().equals(agent.getTypeName())){
            JimLog.w(TAG, "it is not a valid type of agents "+agent.getTypeName());
            throw new Exception("It is not a valid type of agents "+agent.getTypeName());

        }

        JimAgent agentInst = null;
        try {

            // initialize and register the new agent class to the platform
            LinkedBlockingQueue<IMessage> queue = new LinkedBlockingQueue();
            agentInst = (JimAgent) agent.getDeclaredConstructor(LinkedBlockingQueue.class, int.class).newInstance(queue, module);
            messages.put(module, queue);

        } catch (InstantiationException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
            JimLog.e(TAG, "Exception "+e.getMessage());
        }

        // check if the agent is started successfully
        if(agentInst == null){
            JimLog.e(TAG, "Can't initialize the platform agent");
            throw new Exception("Can't initialize the platform agent");
        }

        // run the agent thread to start listening to the internal messages
        mExecutor.execute(agentInst);
        services.put(agent, agentInst);

    }

    /**
     * unregister any module by unregister its entry point class
     * @param agent
     * @throws Exception
     */
    public static void unregister(Class agent) throws Exception {
        JimAgent agentInst = JimPlatform.services.get(agent);
        if(agentInst == null){
            JimLog.w(TAG, "there is no services with this name");
            throw new Exception("there is no services with this name");
        }

        messages.remove(agentInst.mModule);
        agentInst.close();
        services.remove(agent);
        JimLog.d(TAG, "UI Registered in the platform");


    }

    /**
     * send internal message using the platform
     * @param imessage the internal message that you would like to send between the modules
     */
    public static void send(IMessage imessage){
        JimLog.d(TAG, "send message "+imessage.toString());

        synchronized (messages) {

            if(messages.get(imessage.getReceiver()) == null){
                JimLog.w(TAG, "no channel for this module "+imessage.getReceiver());
                return;
            }

            synchronized (messages.get(imessage.getReceiver())) {
                messages.get(imessage.getReceiver()).add(imessage);
                messages.get(imessage.getReceiver()).notify();
            }
        }
    }

    /**
     * Terminate the platform and stop all the running modules
     */
    public static void terminate(){
        while(services.elements().hasMoreElements()) {
            services.remove(services.elements().nextElement());
        }
    }

}
