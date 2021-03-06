package net.zomis.duga.chat.listen;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import net.zomis.duga.chat.ChatBot;
import net.zomis.duga.chat.BotRoom;
import net.zomis.duga.chat.ChatMessage;
import org.apache.commons.lang3.StringEscapeUtils;

import java.util.logging.Logger;

public class ChatMessageIncoming {

    private static final Logger logger = Logger.getLogger(ChatMessageIncoming.class.getCanonicalName());

    @JsonProperty
    private String content;
    @JsonProperty("event_type")
    private int eventType;
    @JsonProperty("message_id")
    private long messageId;
    @JsonProperty("room_id")
    private long roomId;
    @JsonProperty("time_stamp")
    private long timestamp;
    @JsonProperty("user_id")
    private long userId;
    @JsonProperty("parent_id")
    private long parentId;
    @JsonProperty("id")
    private long id;
    @JsonProperty("user_name")
    private String userName;
    @JsonProperty("message_stars")
    private int messageStars;
    @JsonProperty("room_name")
    private String roomName;
    @JsonProperty("target_user_id")
    private long targetUserId;
    @JsonProperty("show_parent")
    private boolean showParent;

    @JsonProperty("message_edits")
    private int messageEdits;

    ChatBot bot;
    BotRoom params;

    public void reply(String message) {
        bot.postAsync(createReply(message));
    }

    public ChatMessage createReply(String message) {
        return params.message(":" + messageId + " " + message);
    }

    public void post(String message) {
        bot.postAsync(params.message(message));
    }

    public void ping(String message) {
        bot.postAsync(params.message("@" + userName + " " + message));
    }

    @Override
    public String toString() {
        return String.format("Room %s: %s (%d) said %s", roomId, userName, userId, content);
    }

    public int getEventType() {
        return eventType;
    }

    public int getMessageStars() {
        return messageStars;
    }

    public long getId() {
        return id;
    }

    public long getMessageId() {
        return messageId;
    }

    public long getParentId() {
        return parentId;
    }

    public long getRoomId() {
        return roomId;
    }

    public long getTargetUserId() {
        return targetUserId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public long getUserId() {
        return userId;
    }

    public String getContent() {
        return content;
    }

    public String getRoomName() {
        return roomName;
    }

    public String getUserName() {
        return userName;
    }

    public String cleanHTML() {
        return StringEscapeUtils.unescapeHtml4(content);
    }

    @JsonAnySetter
    public void setProperty(String name, Object value) {
        logger.severe("Cannot set property for ChatMessageIncoming: " + name + " = " + value);
        // do nothing, just prevent crash
    }

    public BotRoom getParams() {
        return params;
    }

}
