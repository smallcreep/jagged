package org.libgit2.jagged;

import org.libgit2.jagged.core.Ensure;
import org.libgit2.jagged.core.NativeMethods;

/**
 * Configurable options for libgit2.
 */
public final class Options
{
    /**
     * The current statistics for the object cache.
     */
    public static class CacheStatistics
    {
        private final long used;
        private final long maximum;

        private CacheStatistics(long used, long maximum)
        {
            this.used = used;
            this.maximum = maximum;
        }

        /**
         * The amount of memory currently used by the cache.
         * 
         * @return Cache usage (in bytes)
         */
        public long getUsed()
        {
            return used;
        }

        /**
         * The maximum amount of memory available to the cache.
         * 
         * @return Cache permitted usage (in bytes)
         */
        public long getMaximum()
        {
            return maximum;
        }
    }

    private Options()
    {
    }

    /**
     * Sets the maximum window size used to memory map files.
     * 
     * @param size
     *        the maximum window size
     */
    public static void setMmapWindowSize(long size)
    {
        Ensure.argumentNotNegative(size, "size");

        NativeMethods.optionSetMmapWindowSize(size);
    }

    /**
     * Gets the maximum window size used to memory map files.
     * 
     * @return the maximum window size
     */
    public static long getMmapWindowSize()
    {
        return NativeMethods.optionGetMmapWindowSize();
    }

    /**
     * Sets the maximum amount of memory that can be mapped by the library.
     * 
     * @param limit
     *        the memory limit
     */
    public static void setMmapWindowMappedLimit(long limit)
    {
        Ensure.argumentNotNegative(limit, "limit");

        NativeMethods.optionSetMmapWindowMappedLimit(limit);
    }

    /**
     * Gets the maximum amount of memory that can be mapped by the library.
     * 
     * @return the memory limit
     */
    public static long getMmapWindowMappedLimit()
    {
        return NativeMethods.optionGetMmapWindowMappedLimit();
    }

    /**
     * Sets the search path(s) for configuration data, shared attributes and
     * ignore files for a given configuration level, or <code>null</code> to
     * reset default search path.
     * 
     * Multiple paths can be specified if they are separated by the file path
     * separator. The value <code>$PATH</code> can be specified in order to use
     * the existing path value.
     * 
     * @param level
     *        the configuration level
     * @param path
     *        the path(s) to search or <code>null</code> to reset defaults
     */
    public static void setSearchPath(ConfigurationLevel level, String path)
    {
        Ensure.argumentNotNull(level, "level");

        NativeMethods.optionSetSearchPath(level.getValue(), path);
    }

    /**
     * Gets the search path(s) for a given configuration level.
     * 
     * @param level
     *        the configuration level
     * @return the path(s) to search
     */
    public static String getSearchPath(ConfigurationLevel level)
    {
        Ensure.argumentNotNull(level, "level");

        return NativeMethods.optionGetSearchPath(level.getValue());
    }

    /**
     * Sets whether object caching should be enabled for repositories.
     * 
     * @param enabled
     *        <code>true</code> to enable caching, false otherwise
     */
    public static void setEnableCaching(boolean enabled)
    {
        NativeMethods.optionSetEnableCaching(enabled);
    }

    /**
     * Sets the maximum size for the given type of object to be eligible for
     * caching, or <code>0</code> to indicate that the those type of objects
     * should not be cached.
     * 
     * @param type
     *        the object type
     * @param size
     *        the maximum object size to cache or <code>0</code>
     */
    public static void setCacheObjectLimit(ObjectType type, long size)
    {
        Ensure.argumentNotNull(type, "type");
        Ensure.argumentNotNegative(size, "size");

        NativeMethods.optionSetCacheObjectLimit(type.getValue(), size);
    }

    /**
     * Sets the maximum size for the object cache.
     * 
     * @param max
     *        the maximum size of the cache
     */
    public static void setCacheMaxSize(long max)
    {
        Ensure.argumentNotNegative(max, "max");

        NativeMethods.optionSetCacheMaxSize(max);
    }

    /**
     * Gets the cache statistics, the allowed size of the cache and the current
     * usage.
     * 
     * @return the cache statistics
     */
    public static CacheStatistics getCachedStatistics()
    {
        return NativeMethods.optionGetCachedStatistics();
    }
}
