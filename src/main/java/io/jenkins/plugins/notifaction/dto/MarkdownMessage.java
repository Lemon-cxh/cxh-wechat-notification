package io.jenkins.plugins.notifaction.dto;

import io.jenkins.plugins.notifaction.constant.ContentConstant;
import net.sf.json.JSONObject;

/**
 * Markdown类型文本内容
 * @author 三月柳絮四月雨
 * @date 2020/11/04
 **/
public class MarkdownMessage {

    private String msgtype = "markdown";

    private MarkdownType markdown;

    public static String buildStartContent(NotifactionInfo notifactionInfo) {
        String content = ContentConstant.START_TEXT + ContentConstant.WRAP +
                notifactionInfo.getProjectName() + ContentConstant.WRAP +
                notifactionInfo.getDescription() + ContentConstant.WRAP +
                notifactionInfo.getBranchName() + ContentConstant.WRAP +
                notifactionInfo.getEstimatedDuration() + ContentConstant.WRAP +
                notifactionInfo.getAuthorName() + ContentConstant.WRAP +
                notifactionInfo.getCommitMessage();
        return getJSONString(content);
    }

    public static String buildEndContent(NotifactionInfo notifactionInfo) {
        String content = ContentConstant.END_TEXT + ContentConstant.WRAP +
                notifactionInfo.getProjectName() + ContentConstant.WRAP +
                notifactionInfo.getDescription() + ContentConstant.WRAP +
                notifactionInfo.getResult();
        return getJSONString(content);
    }

    public static String getJSONString(String content) {
        MarkdownType type = new MarkdownType();
        type.setContent(content);
        MarkdownMessage markdownMessage = new MarkdownMessage();
        markdownMessage.setMarkdown(type);
        return JSONObject.fromObject(markdownMessage).toString();
    }

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public MarkdownType getMarkdown() {
        return markdown;
    }

    public void setMarkdown(MarkdownType markdown) {
        this.markdown = markdown;
    }

}
