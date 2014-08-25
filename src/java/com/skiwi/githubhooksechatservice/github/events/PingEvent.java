
package com.skiwi.githubhooksechatservice.github.events;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author Frank van Heeswijk
 */
public class PingEvent {
    @JsonProperty
    private String zen;
    
    @JsonProperty
    private Hook hook;
    
    @JsonProperty("hook_id")
    private long hookId;

    public String getZen() {
        return zen;
    }

    public Hook getHook() {
        return hook;
    }

    public long getHookId() {
        return hookId;
    }
}