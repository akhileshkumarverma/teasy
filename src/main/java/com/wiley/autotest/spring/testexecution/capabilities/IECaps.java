package com.wiley.autotest.spring.testexecution.capabilities;

import org.openqa.selenium.Platform;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Caps for IE driver
 */
public class IECaps extends TeasyCaps {

    private final UnexpectedAlertBehaviour alertBehaviour;
    private final String version;

    public IECaps(DesiredCapabilities customCaps, String version, UnexpectedAlertBehaviour alertBehaviour) {
        super(customCaps);
        this.version = version;
        this.alertBehaviour = alertBehaviour;
    }

    public DesiredCapabilities get() {
        DesiredCapabilities caps = getIECaps();
        if (!this.customCaps.asMap().isEmpty()) {
            caps.merge(this.customCaps);
        }
        return caps;
    }

    private DesiredCapabilities getIECaps() {
        DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
        caps.setVersion(this.version);
        caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        caps.setCapability(InternetExplorerDriver.IE_SWITCHES, "-private");
        caps.setCapability(InternetExplorerDriver.FORCE_CREATE_PROCESS, true);
        caps.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
        caps.setCapability(CapabilityType.SUPPORTS_ALERTS, true);
        caps.setPlatform(Platform.WINDOWS);
        caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        caps.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, this.alertBehaviour);

        //Found that setting this capability could increase IE tests speed. Should be checked.
        caps.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
        setLoggingPrefs(caps);
        return caps;
    }
}
