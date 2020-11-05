package io.jenkins.plugins.notifaction.dto;

import hudson.model.Result;
import io.jenkins.plugins.notifaction.constant.ContentConstant;

import java.util.Objects;

/**
 * 文本信息
 * @author 三月柳絮四月雨
 * @date 2020/11/05
 **/
public class NotifactionInfo {

    private final String projectName;

    private final String description;

    private final String branchName;

    private final String authorName;

    private final String commitMessage;

    private final Long estimatedDuration;

    private final Result result;

    public String getProjectName() {
        return ContentConstant.QUOTE + ContentConstant.PROJECT_NAME + ContentConstant.GRAY_FONT_COLOR
                + projectName + ContentConstant.END_TAG;
    }

    public String getDescription() {
        return ContentConstant.QUOTE + ContentConstant.DESCRIPTION + ContentConstant.GRAY_FONT_COLOR
                + description + ContentConstant.END_TAG;
    }

    public String getBranchName() {
        return ContentConstant.QUOTE + ContentConstant.BRANCH_NAME + ContentConstant.GRAY_FONT_COLOR
                + branchName + ContentConstant.END_TAG;
    }

    public String getAuthorName() {
        return ContentConstant.QUOTE + ContentConstant.AUTHOR_NAME + ContentConstant.GRAY_FONT_COLOR
                + authorName + ContentConstant.END_TAG;
    }

    public String getCommitMessage() {
        return ContentConstant.QUOTE + ContentConstant.COMMIT_MESSAGE + ContentConstant.GRAY_FONT_COLOR
                + commitMessage + ContentConstant.END_TAG;
    }

    public String getEstimatedDuration() {
        return ContentConstant.QUOTE + ContentConstant.ESTIMATED_DURATION + ContentConstant.GRAY_FONT_COLOR
                + estimatedDuration / 60000 + "分钟" + ContentConstant.END_TAG;
    }

    public String getResult(){
        String data = ContentConstant.QUOTE + ContentConstant.RESULT;
        if (Objects.isNull(result)) {
            return data + ContentConstant.ORANGE_FONT_COLOR +  "未知情况" + ContentConstant.END_TAG;
        }
        if (Result.SUCCESS.equals(result)) {
            return data + ContentConstant.GREEN_FONT_COLOR +  "成功" + ContentConstant.END_TAG;
        }
        if (Result.FAILURE.equals(result)) {
            return data + ContentConstant.ORANGE_FONT_COLOR +  "失败" + ContentConstant.END_TAG;
        }
        if (Result.ABORTED.equals(result)) {
            return data + ContentConstant.ORANGE_FONT_COLOR +  "已中止" + ContentConstant.END_TAG;
        }
        if (Result.UNSTABLE.equals(result)) {
            return data + ContentConstant.ORANGE_FONT_COLOR +  "不稳定" + ContentConstant.END_TAG;
        }
        if (Result.NOT_BUILT.equals(result)) {
            return data + ContentConstant.GRAY_FONT_COLOR +  "没有构建" + ContentConstant.END_TAG;
        }
        return data + ContentConstant.ORANGE_FONT_COLOR +  "未知情况" + ContentConstant.END_TAG;
    }

    public NotifactionInfo(Builder builder) {
        this.projectName = builder.projectName;
        this.description = builder.description;
        this.branchName = builder.branchName;
        this.authorName = builder.authorName;
        this.commitMessage = builder.commitMessage;
        this.estimatedDuration = builder.estimatedDuration;
        this.result = builder.result;
    }

    public static Builder builder() {
        return new Builder();
    }


    public static class Builder {
        private String projectName;

        private String description;

        private String branchName;

        private String authorName;

        private String commitMessage;

        private Long estimatedDuration;

        private Result result;

        public NotifactionInfo build() {
            return new NotifactionInfo(this);
        }

        public Builder projectName(String projectName) {
            this.projectName = projectName;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder branchName(String branchName) {
            this.branchName = branchName;
            return this;
        }

        public Builder authorName(String authorName) {
            this.authorName = authorName;
            return this;
        }

        public Builder commitMessage(String commitMessage) {
            this.commitMessage = commitMessage;
            return this;
        }

        public Builder estimatedDuration(Long estimatedDuration) {
            this.estimatedDuration = estimatedDuration;
            return this;
        }

        public Builder result(Result result) {
            this.result = result;
            return this;
        }

    }
}
