package org.mhmod.jim.logger;

public interface JimLogger {

    void write_error(String message);

    void write_debug(String message);

    void write_warning(String message);

}
