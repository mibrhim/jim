package org.mhmod.jim.logger;

public class JimLog {

    private static JimLogger logger = new ConsoleLogger();
    public final static int DEBUG = 0;
    public final static int RELEASE = 1;
    public final static int FILE_LOG = 0;
    public final static int CONSOLE_LOG = 1;
    public static int DEBUG_LEVEL = DEBUG;


    public static void d(String tag, String message){
        if(DEBUG_LEVEL == DEBUG)
            logger.write_debug(tag+": "+message);
    }

    public static void w(String tag, String message){
        logger.write_warning(tag+": "+message);
    }


    public static void e(String tag, String message){
        logger.write_error(tag+": "+message);
    }



    public static void setLogger(int loggerId){
        switch (loggerId){
            case CONSOLE_LOG: {
                logger = new ConsoleLogger();
                break;
            }

            case FILE_LOG:{
                logger = new FileLogger();
                break;
            }

        }
    }

}
