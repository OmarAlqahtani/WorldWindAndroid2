/*
 * Copyright (c) 2016 United States Government as represented by the Administrator of the
 * National Aeronautics and Space Administration. All Rights Reserved.
 */

package gov.nasa.worldwind.ogc;

import gov.nasa.worldwind.geom.Sector;
import gov.nasa.worldwind.util.Logger;
import gov.nasa.worldwind.util.Tile;
import gov.nasa.worldwind.util.TileUrlFactory;

/**
 * Factory for constructing URLs associated with WMS Get Map requests.
 */
public class WmsGetMapUrlFactory implements TileUrlFactory {

    /**
     * The WMS service address used to build Get Map URLs.
     */
    protected String serviceAddress;

    /**
     * The WMS protocol version.
     */
    protected String wmsVersion;

    /**
     * The comma-separated list of WMS layer names.
     */
    protected String layerNames;

    /**
     * The comma-separated list of WMS style names. May be null in which case the default style is assumed.
     */
    protected String styleNames;

    /**
     * The coordinate reference system to use in Get Map URLs. Defaults to EPSG:4326.
     */
    protected String coordinateSystem = "EPSG:4326";

    /**
     * Indicates whether Get Map URLs should include transparency.
     */
    protected boolean transparent = true;

    /**
     * The time parameter to include in Get Map URLs. May be null in which case no time parameter is included.
     */
    protected String timeString;

    /**
     * Constructs a WMS Get Map URL builder with specified WMS service parameters.
     *
     * @param serviceAddress the WMS service address
     * @param wmsVersion     the WMS protocol version
     * @param layerNames     comma-separated list of WMS layer names
     * @param styleNames     comma-separated list of WMS style names, may be null in which case the default style is
     *                       assumed
     *
     * @throws IllegalArgumentException If any of the service address, the WMS protocol version, or the layer names are
     *                                  null
     */
    public WmsGetMapUrlFactory(String serviceAddress, String wmsVersion, String layerNames, String styleNames) {
        if (serviceAddress == null) {
            throw new IllegalArgumentException(
                Logger.logMessage(Logger.ERROR, "WmsGetMapUrlFactory", "constructor", "missingServiceAddress"));
        }

        if (wmsVersion == null) {
            throw new IllegalArgumentException(
                Logger.logMessage(Logger.ERROR, "WmsGetMapUrlFactory", "constructor", "missingVersion"));
        }

        if (layerNames == null) {
            throw new IllegalArgumentException(
                Logger.logMessage(Logger.ERROR, "WmsGetMapUrlFactory", "constructor", "missingLayerNames"));
        }

        this.serviceAddress = serviceAddress;
        this.wmsVersion = wmsVersion;
        this.layerNames = layerNames;
        this.styleNames = styleNames;
    }

    /**
     * Constructs a level set with a specified configuration. The configuration's service address, WMS protocol version,
     * layer names and coordinate reference system must be non-null. The style names may be null, in which case the
     * default style is assumed. The time string may be null, in which case no time parameter is included.
     *
     * @param config the configuration for this URL builder
     *
     * @throws IllegalArgumentException If the configuration is null, or if any configuration value is invalid
     */
    public WmsGetMapUrlFactory(WmsLayerConfig config) {
        if (config == null) {
            throw new IllegalArgumentException(
                Logger.logMessage(Logger.ERROR, "WmsGetMapUrlFactory", "constructor", "missingConfig"));
        }

        if (config.serviceAddress == null) {
            throw new IllegalArgumentException(
                Logger.logMessage(Logger.ERROR, "WmsGetMapUrlFactory", "constructor", "missingServiceAddress"));
        }

        if (config.wmsVersion == null) {
            throw new IllegalArgumentException(
                Logger.logMessage(Logger.ERROR, "WmsGetMapUrlFactory", "constructor", "missingVersion"));
        }

        if (config.layerNames == null) {
            throw new IllegalArgumentException(
                Logger.logMessage(Logger.ERROR, "WmsGetMapUrlFactory", "constructor", "missingLayerNames"));
        }

        if (config.coordinateSystem == null) {
            throw new IllegalArgumentException(
                Logger.logMessage(Logger.ERROR, "WmsGetMapUrlFactory", "constructor", "missingCoordinateSystem"));
        }

        this.serviceAddress = config.serviceAddress;
        this.wmsVersion = config.wmsVersion;
        this.layerNames = config.layerNames;
        this.styleNames = config.styleNames;
        this.coordinateSystem = config.coordinateSystem;
        this.transparent = config.transparent;
        this.timeString = config.timeString;
    }

    /**
     * Indicates the WMS service address used to build Get Map URLs.
     *
     * @return the WMS service address
     */
    public String getServiceAddress() {
        return serviceAddress;
    }

    /**
     * Sets the WMS service address used to build Get Map URLs.
     *
     * @param serviceAddress the WMS service address
     *
     * @throws IllegalArgumentException if the service address is null
     */
    public void setServiceAddress(String serviceAddress) {
        if (serviceAddress == null) {
            throw new IllegalArgumentException(
                Logger.logMessage(Logger.ERROR, "WmsGetMapUrlFactory", "setServiceAddress", "missingServiceAddress"));
        }

        this.serviceAddress = serviceAddress;
    }

    /**
     * Indicates the WMS protocol version.
     *
     * @return the WMS protocol version
     */
    public String getWmsVersion() {
        return wmsVersion;
    }

    /**
     * Sets the WMS protocol version.
     *
     * @param wmsVersion the WMS protocol version
     *
     * @throws IllegalArgumentException If the version is null
     */
    public void setWmsVersion(String wmsVersion) {
        if (wmsVersion == null) {
            throw new IllegalArgumentException(
                Logger.logMessage(Logger.ERROR, "WmsGetMapUrlFactory", "setWmsVersion", "missingVersion"));
        }

        this.wmsVersion = wmsVersion;
    }

    /**
     * Indicates the comma-separated list of WMS layer names.
     *
     * @return comma-separated list of WMS layer names
     */
    public String getLayerNames() {
        return layerNames;
    }

    /**
     * Sets the comma-separated list of WMS layer names.
     *
     * @param layerNames comma-separated list of WMS layer names
     *
     * @throws IllegalArgumentException if the layer names are null
     */
    public void setLayerNames(String layerNames) {
        if (layerNames == null) {
            throw new IllegalArgumentException(
                Logger.logMessage(Logger.ERROR, "WmsGetMapUrlFactory", "setLayerNames", "missingLayerNames"));
        }

        this.layerNames = layerNames;
    }

    /**
     * Indicates the comma-separated list of WMS style names, or null in which case the default style is assumed.
     *
     * @return comma-separated list of WMS style names
     */
    public String getStyleNames() {
        return styleNames;
    }

    /**
     * Sets the comma-separated list of WMS style names. May be null in which case the default style is assumed.
     *
     * @param styleNames comma-separated list of WMS style names
     */
    public void setStyleNames(String styleNames) {
        this.styleNames = styleNames;
    }

    /**
     * Indicates the coordinate reference system to use in Get Map URLs. Defaults to EPSG:4326.
     *
     * @return the coordinate reference system to use
     */
    public String getCoordinateSystem() {
        return coordinateSystem;
    }

    /**
     * Sets the coordinate reference system to use in Get Map URLs.
     *
     * @param coordinateSystem the coordinate reference system to use
     *
     * @throws IllegalArgumentException If the coordinate system is null
     */
    public void setCoordinateSystem(String coordinateSystem) {
        if (coordinateSystem == null) {
            throw new IllegalArgumentException(
                Logger.logMessage(Logger.ERROR, "WmsGetMapUrlFactory", "setCoordinateSystem", "missingCoordinateSystem"));
        }

        this.coordinateSystem = coordinateSystem;
    }

    /**
     * Indicates whether Get Map URLs should include transparency.
     *
     * @return true to include transparency, false otherwise
     */
    public boolean isTransparent() {
        return transparent;
    }

    /**
     * Sets whether Get Map URLs should include transparency.
     *
     * @param transparent true to include transparency, false otherwise
     */
    public void setTransparent(boolean transparent) {
        this.transparent = transparent;
    }

    /**
     * Indicates the time parameter to include in Get Map URLs, or null in which case no time parameter is included.
     *
     * @return the time parameter to include
     */
    public String getTimeString() {
        return timeString;
    }

    /**
     * Sets the time parameter to include in Get Map URLs. May be null in which case no time parameter is included.
     *
     * @param timeString the time parameter to include
     */
    public void setTimeString(String timeString) {
        this.timeString = timeString;
    }

    @Override
    public String urlForTile(Tile tile, String imageFormat) {
        if (tile == null) {
            throw new IllegalArgumentException(
                Logger.logMessage(Logger.ERROR, "WmsGetMapUrlFactory", "urlForTile", "missingTile"));
        }

        if (imageFormat == null) {
            throw new IllegalArgumentException(
                Logger.logMessage(Logger.ERROR, "WmsGetMapUrlFactory", "urlForTile", "missingFormat"));
        }

        StringBuilder url = new StringBuilder(this.serviceAddress);

        int index = url.indexOf("?");
        if (index < 0) { // if service address contains no query delimiter
            url.append("?"); // add one
        } else if (index != url.length()) { // else if query delimiter not at end of string
            index = url.lastIndexOf("&");
            if (index != url.length() - 1) {
                url.append("&"); // add a parameter delimiter
            }
        }

        index = this.serviceAddress.toUpperCase().indexOf("SERVICE=WMS");
        if (index < 0) {
            url.append("SERVICE=WMS");
        }

        url.append("&VERSION=").append(this.wmsVersion);
        url.append("&REQUEST=GetMap");
        url.append("&LAYERS=").append(this.layerNames);
        url.append("&STYLES=").append(this.styleNames != null ? this.styleNames : "");

        Sector sector = tile.sector;
        if (this.wmsVersion.equals("1.3.0")) {
            url.append("&CRS=").append(this.coordinateSystem);
            url.append("&BBOX=");
            if (this.coordinateSystem.equals("CRS:84")) {
                url.append(sector.minLongitude()).append(",").append(sector.minLatitude()).append(",");
                url.append(sector.maxLongitude()).append(",").append(sector.maxLatitude());
            } else {
                url.append(sector.minLatitude()).append(",").append(sector.minLongitude()).append(",");
                url.append(sector.maxLatitude()).append(",").append(sector.maxLongitude());

            }
        } else {
            url.append("&SRS=").append(this.coordinateSystem);
            url.append("&BBOX=");
            url.append(sector.minLongitude()).append(",").append(sector.minLatitude()).append(",");
            url.append(sector.maxLongitude()).append(",").append(sector.maxLatitude());
        }

        url.append("&WIDTH=").append(tile.level.tileWidth);
        url.append("&HEIGHT=").append(tile.level.tileHeight);
        url.append("&FORMAT=").append(imageFormat);
        url.append("&TRANSPARENT=").append(this.transparent ? "TRUE" : "FALSE");

        if (this.timeString != null) {
            url.append("&TIME=").append(this.timeString);
        }

        return url.toString();
    }
}
