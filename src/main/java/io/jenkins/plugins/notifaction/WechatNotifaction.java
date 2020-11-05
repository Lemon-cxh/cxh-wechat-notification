package io.jenkins.plugins.notifaction;

import hudson.EnvVars;
import hudson.Extension;
import hudson.FilePath;
import hudson.Launcher;
import hudson.model.*;
import hudson.scm.ChangeLogSet;
import hudson.tasks.*;
import io.jenkins.plugins.notifaction.dto.MarkdownMessage;
import io.jenkins.plugins.notifaction.dto.NotifactionInfo;
import io.jenkins.plugins.notifaction.uitl.NotifactionUtil;
import jenkins.tasks.SimpleBuildStep;
import org.apache.commons.lang.StringUtils;
import org.kohsuke.stapler.DataBoundConstructor;

import javax.annotation.Nonnull;
import java.util.Objects;

/**
 * 企业微信通知
 *
 * @author 三月柳絮四月雨
 * @date 2020/11/03
 **/
public class WechatNotifaction extends Notifier implements SimpleBuildStep {

    private final String webhook;

    @Extension
    public static final DescriptorImpl DESCRIPTOR = new DescriptorImpl();

    @Override
    public boolean prebuild(AbstractBuild<?, ?> build, BuildListener listener) {
        listener.getLogger().println("企业微信通知任务开始构建");
        String webhookUrl = getWebHookUrl();
        if (StringUtils.isBlank(webhookUrl)) {
            listener.getLogger().println("webhook 还未配置");
            return true;
        }
        EnvVars envVars;
        try {
            envVars = build.getEnvironment(listener);
        } catch (Exception e) {
            listener.getLogger().println("读取环境变量异常" + e.getMessage());
            envVars = new EnvVars();
        }
        String authorName = "手动触发";
        String message = envVars.get("GIT_COMMIT");
        ChangeLogSet<?> changeSet = build.getChangeSet();
        if (changeSet.isEmptySet()) {
            AbstractBuild previousBuild = build.getPreviousBuild();
            if (Objects.nonNull(previousBuild)) {
                changeSet = previousBuild.getChangeSet();
            }
        }
        if (!changeSet.isEmptySet()) {
            ChangeLogSet.Entry entry = (ChangeLogSet.Entry) changeSet.getItems()[0];
            authorName = entry.getAuthor().getFullName();
            message = entry.getMsgEscaped();
        }
        AbstractProject project = build.getProject();
        NotifactionInfo notifactionInfo = NotifactionInfo.builder()
                .projectName(project.getFullDisplayName())
                .description(project.getDescription())
                .branchName(envVars.get("GIT_BRANCH"))
                .authorName(authorName)
                .commitMessage(message)
                .estimatedDuration(project.getEstimatedDuration())
                .build();
        NotifactionUtil.push(webhookUrl, MarkdownMessage.buildStartContent(notifactionInfo));
        return true;
    }

    @Override
    public void perform(@Nonnull Run<?, ?> run, @Nonnull FilePath filePath,
                        @Nonnull Launcher launcher, @Nonnull TaskListener taskListener) {
        taskListener.getLogger().println("企业微信通知任务结束构建");
        String webhookUrl = getWebHookUrl();
        if (StringUtils.isBlank(webhookUrl)) {
            taskListener.getLogger().println("webhook 还未配置");
            return;
        }
        NotifactionInfo notifactionInfo = NotifactionInfo.builder()
                .projectName(run.getParent().getFullDisplayName())
                .description(run.getParent().getDescription())
                .result(run.getResult())
                .build();
        NotifactionUtil.push(webhookUrl, MarkdownMessage.buildEndContent(notifactionInfo));
    }

    @Override
    public BuildStepMonitor getRequiredMonitorService() {
        return BuildStepMonitor.BUILD;
    }

    /**
     * 任务中没有配置webhook则取全局配置
     * @return webhook url
     */
    private String getWebHookUrl() {
        return StringUtils.isBlank(webhook) ? DESCRIPTOR.getWebhook() : webhook;
    }

    @DataBoundConstructor
    public WechatNotifaction(String webhook) {
        this.webhook = webhook;
    }

    public String getWebhook() {
        return webhook;
    }
}
