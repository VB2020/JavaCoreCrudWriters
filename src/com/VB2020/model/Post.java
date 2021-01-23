package com.VB2020.model;

import java.util.Set;

public class Post extends Entity {

    private PostStatus postStatus;
    private String content;

    private boolean created;
    private boolean updated;

    public boolean isCreated() {
        return created;
    }

    public void setCreated(boolean created) {
        this.created = created;
    }

    public boolean isUpdated() {
        return updated;
    }

    public void setUpdated(boolean updated) {
        this.updated = updated;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private Long writerId;
    private Set<Long> labelIds;
    private Writer writer;
    private Set<Label> labels;

    public PostStatus getPostStatus() {
        return postStatus;
    }

    public void setPostStatus(PostStatus postStatus) {
        this.postStatus = postStatus;
    }

    public Long getWriterId() {
        return writerId;
    }

    public void setWriterId(Long writerId) {
        this.writerId = writerId;
    }

    public Set<Long> getLabelIds() {
        return labelIds;
    }

    public void setLabelIds(Set<Long> labelIds) {
        this.labelIds = labelIds;
    }

    public Writer getWriter() {
        return writer;
    }

    public void setWriter(Writer writer) {
        this.writer = writer;
    }

    public Set<Label> getLabels() {
        return labels;
    }

    public void setLabels(Set<Label> labels) {
        this.labels = labels;
    }
}
