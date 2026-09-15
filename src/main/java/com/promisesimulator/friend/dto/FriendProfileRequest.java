package com.promisesimulator.friend.dto;

/** 친구 프로필 생성·수정 요청 값이다. */
public class FriendProfileRequest {

    private String name;
    private String closeness;
    private String meetingFrequency;
    private String conversationCompatibility;
    private String memo;

    public FriendProfileRequest() {
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCloseness() { return closeness; }
    public void setCloseness(String closeness) { this.closeness = closeness; }
    public String getMeetingFrequency() { return meetingFrequency; }
    public void setMeetingFrequency(String meetingFrequency) { this.meetingFrequency = meetingFrequency; }
    public String getConversationCompatibility() { return conversationCompatibility; }
    public void setConversationCompatibility(String conversationCompatibility) { this.conversationCompatibility = conversationCompatibility; }
    public String getMemo() { return memo; }
    public void setMemo(String memo) { this.memo = memo; }
}
