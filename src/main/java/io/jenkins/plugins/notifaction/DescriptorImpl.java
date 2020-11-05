package io.jenkins.plugins.notifaction;

import hudson.model.AbstractProject;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.Publisher;
import net.sf.json.JSONObject;
import org.kohsuke.stapler.StaplerRequest;

/**
 * 全局数据绑定
 * @author 三月柳絮四月雨
 * @date 2020/11/03
 **/
public class DescriptorImpl extends BuildStepDescriptor<Publisher> {

    private String webhook;

    public DescriptorImpl() {
        super(WechatNotifaction.class);
        load();
    }

    @Override
    public boolean isApplicable(Class<? extends AbstractProject> aClass) {
        return true;
    }

    @Override
    public String getDisplayName() {
        return "企业微信通知";
    }

    @Override
    public boolean configure(StaplerRequest req, JSONObject formData) throws FormException {
        webhook = formData.getString("webhook");
        save();
        return super.configure(req, formData);
    }

    public String getWebhook() {
        return webhook;
    }

    public void setWebhook(String webhook) {
        this.webhook = webhook;
    }
}
