package com.cindymb.airportapplication.utils;


import com.cindymb.airportapplication.BuildConfig;

import timber.log.Timber;

/**
 * Helper class for logging logs into the console or logcat.
 * Currently it uses Timber, but this can be swapped out for any other logging utility.
 * <p>
 * When using this file in an application, when initializing Timber in the application file, use the following:
 * <p>
 * if (BuildConfig.DEBUG) {
 * Timber.plant(new Timber.DebugTree());
 * }
 * <p>
 * This will ensure Timber is only initialized when its a debug build.
 */
public class LoggingHelper {

    /**
     * Used to log Debug Messages
     *
     * @param aClazz   - Class file
     * @param aMessage - Message
     */
    public static void debug(Class aClazz, String aMessage) {
        if (!BuildConfig.DEBUG) return;
        Timber.tag(aClazz.getSimpleName()).d(aMessage);
    }

    /**
     * Used to log Verbose Messages
     *
     * @param aClazz   - Class file
     * @param aMessage - Message
     */
    public static void verbose(Class aClazz, String aMessage) {
        if (!BuildConfig.DEBUG) return;
        Timber.tag(aClazz.getSimpleName()).v(aMessage);
    }

    /**
     * Used to log Debug Messages
     *
     * @param aClazz   - Class file
     * @param aMessage - Message
     */
    public static void warning(Class aClazz, String aMessage) {
        if (!BuildConfig.DEBUG) return;
        Timber.tag(aClazz.getSimpleName()).w(aMessage);
    }

    /**
     * Used to log Error Messages
     *
     * @param aClazz   - Class file
     * @param aMessage - Message
     */
    public static void error(Class aClazz, String aMessage) {
        if (!BuildConfig.DEBUG) return;
        Timber.tag(aClazz.getSimpleName()).e(aMessage);
    }

    /**
     * Used to log Information Messages
     *
     * @param aClazz   - Class file
     * @param aMessage - Message
     */
    public static void information(Class aClazz, String aMessage) {
        if (!BuildConfig.DEBUG) return;
        Timber.tag(aClazz.getSimpleName()).i(aMessage);
    }
}
